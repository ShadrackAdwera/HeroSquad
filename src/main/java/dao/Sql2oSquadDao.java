package dao;

import models.*;
import org.sql2o.*;

import java.util.List;

public class Sql2oSquadDao implements SquadDao {
    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void addSquad(Squad newSquad){
        String sql = "INSERT INTO squads (Name, MaxSize, Cause) VALUES (:name, :maxSize, :cause)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(newSquad) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            newSquad.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
    @Override
    public List<Squad> getAllSquads(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads") //raw sql
                    .executeAndFetch(Squad.class); //fetch a list
        }

    }
    @Override
    public Squad findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Squad.class); //fetch an individual item
        }

    }
    @Override
    public void deleteSquadById(int id){
        String sql = "DELETE from squads WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void deleteAllSquads(){
        String sql = "DELETE from squads";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public List<Hero> getAllHeroesBySquad(int squadId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes WHERE squadId = :squadId")
                    .addParameter("squadId", squadId)
                    .executeAndFetch(Hero.class);
        }
    }

}
