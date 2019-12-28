package dao;

import models.Hero;
import org.sql2o.*;
import java.util.List;

public class Sql2oHeroDao implements HeroDao{
    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addHero(Hero newHero) {
        String sql = "INSERT INTO hero (Name, Age, SuperPowers, Weakness, SquadId) VALUES (:name, :age, :superPowers, :weakness, :squadId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(newHero)
                    .executeUpdate()
                    .getKey();
            newHero.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Hero> allHeroes() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hero")
                    .executeAndFetch(Hero.class);
        }
    }
    @Override
    public void deleteHero(int id) {
        String sql = "DELETE from hero WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllHeroes() {
        String sql = "DELETE from hero";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Hero findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM hero WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Hero.class);
        }
    }


}
