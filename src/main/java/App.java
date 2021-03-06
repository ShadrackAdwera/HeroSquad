import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App {
    private static int getHerokuAssignedPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT")!=null){
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        } else {
            return 4567;
        }
    }
    public static void main(String[]args){
        port(getHerokuAssignedPort());
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad?sslmode=require";
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
//        String connectionString = "jdbc:postgresql://ec2-107-22-197-30.compute-1.amazonaws.com:5432/ddgq6qu40nffj2?sslmode=require";
//        Sql2o sql2o = new Sql2o(connectionString, "pcyipscktytnvr", "81e07b730ddb3445cfa222f7704db8b79884dd3278734f01042e590d211424f2");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);
        staticFileLocation("/public");
        //get: show all heroes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAllSquads();
            model.put("squads", squads);
            List<Hero> heroes = heroDao.allHeroes();
            model.put("heroes", heroes);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete all squads and all heroes
        get("/squads/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            squadDao.deleteAllSquads();
            heroDao.deleteAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.deleteAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: delete a hero
        get("/squads/:squadId/heroes/:heroId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHero = Integer.parseInt(request.params("heroId"));
            heroDao.deleteHero(idOfHero);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a squad
        get("/squads/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquad = Integer.parseInt(request.params("id"));
            squadDao.deleteSquadById(idOfSquad);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: display new squad form
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAllSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process squads form
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String squadName = request.queryParams("name");
            int squadSize = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            Squad newSquad = new Squad(squadName, squadSize, cause);
            squadDao.addSquad(newSquad);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
//        //get: view squads and heroes
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAllSquads();
            model.put("squads", squads);
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesFromSquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes", allHeroesFromSquad);
            model.put("squads", squadDao.getAllSquads());
            return new ModelAndView(model, "squads-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show new heroes form
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAllSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAllSquads();
            model.put("squads", allSquads);
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String superPower = request.queryParams("superPower");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero = new Hero(name,age,superPower,weakness,squadId);
            heroDao.addHero(newHero);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //get: show an individual hero
        get("/squads/:squadId/heroes/:heroId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idHeroToFind = Integer.parseInt(request.params("heroId"));
            Hero foundHero = heroDao.findById(idHeroToFind);
            int idSquadToFind = Integer.parseInt(request.params("squadId"));
            Squad foundSquad = squadDao.findById(idSquadToFind);
            model.put("hero", foundHero);
            model.put("squad", foundSquad);
            model.put("squads", squadDao.getAllSquads());
            return new ModelAndView(model, "hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: squad and hero members
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad", foundSquad);
            List<Hero> allHeroesInSquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("hero", allHeroesInSquad);
            model.put("squads", squadDao.getAllSquads());
            return new ModelAndView(model, "squads-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display coming soon
        get("/wars",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAllSquads();
            model.put("squads", squads);
            return new ModelAndView(model, "wars.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
