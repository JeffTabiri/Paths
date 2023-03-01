package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.goals.GoldGoal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {

    GoldGoal goldGoal = new GoldGoal(100);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isFullfilled() {
    }

    @Test
    void GoldGoalTest() {
       // assertEquals(100, goldGoal.getMinimumGold());
    }
}