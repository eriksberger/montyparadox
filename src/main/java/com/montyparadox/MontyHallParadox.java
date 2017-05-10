package com.montyparadox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.montyparadox.MontyHallParadox.Strategy.*;

/**
 * Created by Jens Eriksberger on 2016-12-02.
 * Useless but proves the point...
 */
class MontyHallParadox {
    private static final Logger LOGGER = LoggerFactory.getLogger(MontyHallParadox.class);
    private static final Random randomGenerator = new Random();

    private static int changeDoor(int door1, int door2) {
        int result = randomGenerator.nextInt(3);
        while (result == door1 || result == door2) {
            result = randomGenerator.nextInt(3);
        }
        return result;
    }

    void playGame(int rounds, Strategy currentStrategy) {
        LOGGER.info(String.format("Begin playing %s rounds with strategy %s ", rounds, currentStrategy));
        int wins = 0;
        for (int i = 0; i < rounds; i++) {
            int correctDoor = randomGenerator.nextInt(3);
            int myInitialChoice = randomGenerator.nextInt(3);
            int gateKeepersReveal = changeDoor(correctDoor, myInitialChoice);
            int myFinalChoice = chooseByStrategy(currentStrategy, myInitialChoice, gateKeepersReveal);
            if (myFinalChoice == correctDoor) {
                wins++;
            }
        }
        LOGGER.info(String.format("Got %s wins with strategy %s", wins, currentStrategy));
    }

    private int chooseByStrategy(Strategy currentStrategy, int myInitialChoice, int gateKeepersReveal) {
        if (currentStrategy.equals(ALWAYS_SWITCH)) {
            return changeDoor(myInitialChoice, gateKeepersReveal);
        } else if (currentStrategy.equals(NEVER_SWITCH)) {
            return myInitialChoice;
        } else if (currentStrategy.equals(RANDOM)) {
            return randomGenerator.nextInt(1) == 1 ? changeDoor(myInitialChoice, gateKeepersReveal) : myInitialChoice;
        } else {
            throw new IllegalArgumentException("Bad strategy");
        }
    }

    public enum Strategy {
        ALWAYS_SWITCH, NEVER_SWITCH, RANDOM
    }
}
