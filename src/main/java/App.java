import dao.Sql2oHeroDao;
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
    public static void main(String[]args){
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
//        staticFileLocation("/public");
        //get: show all heroes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> heroes = heroDao.allHeroes();
            model.put("hero", heroes);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete all heroes
        get("/heroes/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            heroDao.deleteAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a hero
        get("/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int heroToDelete = Integer.parseInt(request.params("id"));
            heroDao.deleteHero(heroToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: display new squad form
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process squads form
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String squadName = request.queryParams("name");
            int squadSize = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
//        //get: view squads and heroes
//        get("/squads/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Squads> squads = squadsDao.getAllSquads();
//            model.put("squad", squads);
//            int idOfSquadToFind = Integer.parseInt(req.params("id")); //new
//            Squads foundSquad = squadsDao.findById(idOfSquadToFind);
//            model.put("squad", foundSquad);
//            List<Heroes> allHeroesFromSquad = squadsDao.getAllHeroesBySquad(idOfSquadToFind);
//            model.put("hero", allHeroesFromSquad);
//            model.put("squads", squadsDao.getAllSquads()); //refresh list of links for navbar
//            return new ModelAndView(model, "squads-detail.hbs"); //new
//        }, new HandlebarsTemplateEngine());
        //get: show new heroes form
//        get("/heroes/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Squads> squads = squadsDao.getAllSquads();
//            model.put("squads", squads);
//            return new ModelAndView(model, "heroes.hbs");
//        }, new HandlebarsTemplateEngine());



        //heroes: process new hero form
//        post("/heroes", (req, res) -> { //URL to make new hero on POST route
//            Map<String, Object> model = new HashMap<>();
//            List<Squads> allSquads = squadsDao.getAllSquads();
//            model.put("squads", allSquads);
//            String name = req.queryParams("name");
//            int age = Integer.parseInt(req.queryParams("age"));
//            String superPower = req.queryParams("superPower");
//            String weakness = req.queryParams("weakness");
//            int squadId = Integer.parseInt(req.queryParams("squadId"));
//            Heroes newHero = new Heroes(name,age,superPower,weakness,squadId);
//            heroesDao.addHero(newHero);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
        //get: show an individual hero
//        get("/squads/:squadId/heroes/:heroId", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idHeroToFind = Integer.parseInt(req.params("heroId")); //pull id - must match route segment
//            Heroes foundHero = heroesDao.findById(idHeroToFind);
//            int idSquadToFind = Integer.parseInt(req.params("squadId"));
//            Squads foundSquad = squadsDao.findById(idSquadToFind);
//            model.put("hero", foundHero); //add it to model for template to display
//            model.put("squad", foundSquad);
//            model.put("squads", squadsDao.getAllSquads()); //refresh list of links for navbar
//            return new ModelAndView(model, "hero-detail.hbs");
//        }, new HandlebarsTemplateEngine());

        //squad and hero members
//        get("/squads/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfSquadToFind = Integer.parseInt(req.params("id")); //new
//            Squads foundSquad = squadsDao.findById(idOfSquadToFind);
//            model.put("squad", foundSquad);
//            List<Heroes> allHeroesInSquad = squadsDao.getAllHeroesBySquad(idOfSquadToFind);
//            model.put("hero", allHeroesInSquad);
//            model.put("squads", squadsDao.getAllSquads()); //refresh list of links for navbar
//            return new ModelAndView(model, "squads-detail.hbs"); //new
//        }, new HandlebarsTemplateEngine());

    }

}
