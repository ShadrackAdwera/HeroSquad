package dao;

import models.Hero;

import java.util.List;

public interface HeroDao {
    void addHero(Hero newHero);
    List<Hero> allHeroes();
    Hero findById(int id);
    void deleteHero(int id);
    void deleteAllHeroes();
}
