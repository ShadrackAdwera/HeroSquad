package dao;
import models.*;

import java.util.List;

public interface SquadDao {
    List<Squad> getAllSquads();
    void addSquad(Squad newSquad);
    Squad findById(int id);
    void deleteSquadById(int id);
    void deleteAllSquads();
    List<Hero> getAllHeroesBySquad(int squadId);
}
