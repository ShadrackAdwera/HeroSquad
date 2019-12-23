package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void instantiatesCorrectly_boolean(){
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertTrue(testValue instanceof Hero);
    }
    @Test
    public void storesName_String() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertEquals("Batman", testValue.getName());
    }

    @Test
    public void storesAge_int() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertEquals(38, testValue.getAge());
    }

    @Test
    public void storesSuperPowers_String() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertEquals("Rich", testValue.getSuperPowers());
    }

    @Test
    public void storesWeaknesses_String() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertEquals("Human", testValue.getWeakness());
    }

    @Test
    public void squadMember_boolean() {
        Hero testValue = new Hero("Batman",38, "Rich","Human");
        assertFalse(testValue.squadMember());
    }
}