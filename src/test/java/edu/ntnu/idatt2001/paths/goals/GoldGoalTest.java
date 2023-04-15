package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.actions.GoldAction;
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
        testPlayer = new Player("Test", 100, 100, 100);
    }

    @Test
    void isFullfilled() {
        goldAction.execute(testPlayer);
        assertTrue(goldGoal.isFulfilled(testPlayer));
    }

}