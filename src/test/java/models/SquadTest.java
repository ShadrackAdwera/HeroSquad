package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SquadTest {

    @Before
    public void setUp() throws Exception {
        Squad.clearAll();
    }

    @Test
    public void instantiatesCorrectly_boolean(){
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        assertTrue(newSquad instanceof Squad);
    }

    @Test
    public void storeSquadName_String() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        assertEquals("Justice League", newSquad.getName());
    }

    @Test
    public void getSizeOfEachSquad_int() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        assertEquals(5, newSquad.getMaxSize());
    }

    @Test
    public void getCauseOfSquad_String() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        assertEquals("Eliminate Doomsday", newSquad.getCause());
    }

    @Test
    public void addHeroToSquad_List() {
        List<Hero> newHeroes = new ArrayList<>();
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");

    }
    @Test
    public void getAllHeroesInSquad_List(){
        List<Hero> allHeroesInSquad = new ArrayList<>();
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        Hero testValue2 = new Hero("Flash",31, "Super Speed","Iris West");
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        allHeroesInSquad.add(testValue);
        allHeroesInSquad.add(testValue2);
        assertTrue(allHeroesInSquad.contains(testValue2));
    }
    @Test
    public void getAllSquads_List(){
        List<Squad> allSquads = new ArrayList<>();
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        Squad newSquad2 = new Squad("Avengers",5,"Reverse the snap");
        allSquads.add(newSquad);
        allSquads.add(newSquad2);
        assertTrue(allSquads.contains(newSquad));
    }
    @Test
    public void storesId_int(){
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        assertEquals(1, newSquad.getId());
    }
    @Test
    public void findSquadById_int(){
        List<Squad> allSquads = new ArrayList<>();
        Squad newSquad = new Squad("Justice League",5,"Eliminate Doomsday");
        Squad newSquad2 = new Squad("Avengers",5,"Reverse the snap");
        allSquads.add(newSquad);
        allSquads.add(newSquad2);
        assertEquals(1, Squad.findSquad(newSquad.getId()).getId());

    }
}