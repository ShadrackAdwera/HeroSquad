package dao;

import org.sql2o.Connection;
import models.Hero;
import org.sql2o.*;
import org.junit.*;

import static org.junit.Assert.*;


public class Sql2oHeroDaoTest {
    private static Sql2oHeroDao heroesDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad";
        ;
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        heroesDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void returnsHeroId() throws Exception {
        Hero newHero = new Hero("Pete Casteglioni", 42, "The Punisher", "No weakness", 1);
        int idOfHero = newHero.getId();
        heroesDao.addHero(newHero);
        assertNotEquals(idOfHero, newHero.getId());
    }

    //    @Test
//    public void existingHeroesCanBeFoundById() throws Exception {
//        Hero newHero = new Hero ("Pete Casteglioni", 42,"The Punisher","No weakness", 1);
//        heroesDao.addHero(newHero); //add to dao (takes care of saving)
//        Hero foundHero = heroesDao.findById(newHero.getId()); //retrieve
//        assertEquals(newHero, foundHero); //should be the same
//    }
    @Test
    public void addedHeroesAreReturnedFromGetAll() throws Exception {
        Hero newHero = new Hero("Pete Casteglioni", 42, "The Punisher", "No weakness", 1);
        heroesDao.addHero(newHero); //add to dao (takes care of saving)
        assertEquals(1, heroesDao.allHeroes().size());
    }

    @Test
    public void squadIdReturnedCorrectly() throws Exception {
        Hero newHero = new Hero("Pete Casteglioni", 42, "The Punisher", "No weakness", 1);
        int heroSquadId = newHero.getSquadId();
        heroesDao.addHero(newHero);
        assertEquals(heroSquadId, heroesDao.findById(newHero.getId()).getSquadId());
    }

    @After
    public void tearDown() throws Exception {
        heroesDao.deleteAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
    }
}