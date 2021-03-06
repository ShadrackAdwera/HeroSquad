package dao;

import models.*;
import org.sql2o.*;

import java.util.List;

public class Sql2oSquadDao implements SquadDao {
    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addSquad(Squad newSquad) {
        String sql = "INSERT INTO squads (name, maxsize, cause) VALUES (:name, :maxSize, :cause)";
        try (Connection con = sql2o.open()) { //open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(newSquad) //map argument onto the query to use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            newSquad.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //error!
        }
    }

    @Override
    public List<Squad> getAllSquads() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM squads") //raw sql
                    .executeAndFetch(Squad.class); //fetch a list
        }

    }

    @Override
    public Squad findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Squad.class); //fetch an individual item
        }

    }

    @Override
    public void deleteSquadById(int id) {
        String sql = "DELETE from squads WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllSquads() {
        String sql = "DELETE from squads";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Hero> getAllHeroesBySquad(int squadId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM hero WHERE squadId = :squadId")
                    .addParameter("squadId", squadId)
                    .executeAndFetch(Hero.class);
        }
    }

}
