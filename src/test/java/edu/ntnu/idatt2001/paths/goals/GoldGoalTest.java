package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {

    GoldGoal goldGoal;

    GoldAction goldAction;

    Player testPlayer;



    @BeforeEach
    void setUp() {
        int gold = 100;
        goldAction = new GoldAction(gold);
        goldGoal = new GoldGoal(gold);
        testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    }

    @Test
    void isFullfilled() {
        goldAction.execute(testPlayer);
        assertTrue(goldGoal.isFulfilled(testPlayer));
    }

}