package dao;

import models.*;
import org.junit.*;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import static org.junit.Assert.*;


public class Sql2oSquadDaoTest {
    private static Sql2oSquadDao squadDao;
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/herosquad";
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        squadDao.deleteAllSquads();
        heroDao.deleteAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
    }

    @Test
    public void returnSquadId() throws Exception{
        Squad newSquad = setUpSquad();
        int squadId = newSquad.getId();
        squadDao.addSquad(newSquad);
        assertNotEquals(squadId, newSquad.getId());
    }
    @Test
    public void squadsCanBeFoundById() throws Exception{
        Squad newSquad = setUpSquad();
        squadDao.addSquad(newSquad);
        Squad foundSquad = squadDao.findById(newSquad.getId());
        assertEquals(newSquad, foundSquad);
    }
    @Test
    public void addedSquadsAreReturned() throws Exception{
        Squad newSquad = setUpSquad();
        Squad newSquadTwo = new Squad("Justice League",7,"Stop Doomsday");
        squadDao.addSquad(newSquad);
        squadDao.addSquad(newSquadTwo);
        assertEquals(2, squadDao.getAllSquads().size());
    }
    @Test
    public void getAllHeroesInSquadReturned() throws Exception {
        Squad newSquads = setUpSquad();
        squadDao.addSquad(newSquads);
        int squadId = newSquads.getId();
        Hero newHero = new Hero ("Pete Casteglioni", 42,"The Punisher","No weakness", squadId);
        Hero newHeroTwo = new Hero ("Dare Devil", 42,"Ninja","Law firm", squadId);
        Hero newHeroThree = new Hero ("Dr. Manhattan", 35,"Infinite Power","No weakness", squadId);
        heroDao.addHero(newHero);
//      heroDao.addHero(newHeroTwo);
        assertEquals(1, squadDao.getAllHeroesBySquad(squadId).size());
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHero));
//        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHeroTwo));
    }

    //helper method
    public Squad setUpSquad(){
        return new Squad("Justice League",7,"Stop Doomsday");
    }
}