package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.actions.GoldAction;
import edu.ntnu.idatt2001.paths.playerBuilder.PlayerBuilder;
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