package dao;

import org.junit.*;
import models.Hero;
import org.sql2o.*;

import org.sql2o.Connection;

import static org.junit.Assert.*;

public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }
    @Test
    public void returnsHeroId() throws Exception {
        Hero newHero = setUpHero();
        int idOfHero = newHero.getId();
        heroDao.addHero(newHero);
        assertNotEquals(idOfHero, newHero.getId());
    }
    @Test
    public void existingHeroesCanBeFoundById() throws Exception {
        Hero newHero = setUpHero();
        heroDao.addHero(newHero);
        Hero foundHero = heroDao.findById(newHero.getId());
        assertEquals(newHero, foundHero);
    }
    @Test
    public void addedHeroesAreReturnedFromGetAll() throws Exception {
        Hero newHero = setUpHero();
        heroDao.addHero(newHero); //add to dao (takes care of saving)
        assertEquals(1, heroDao.allHeroes().size());
    }
    @Test
    public void squadIdReturnedCorrectly() throws Exception{
        Hero newHero = setUpHero();
        int heroSquadId = newHero.getSquadId();
        heroDao.addHero(newHero);
        assertEquals(heroSquadId, heroDao.findById(newHero.getId()).getSquadId());
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    //helper method
    public Hero setUpHero(){
        return new Hero("Pete Casteglioni", 42,"The Punisher","No weakness",1);
    }
}