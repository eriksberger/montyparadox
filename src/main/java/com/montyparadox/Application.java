package com.montyparadox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.montyparadox.MontyHallParadox.Strategy.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        MontyHallParadox s = new MontyHallParadox();
        s.playGame(100, ALWAYS_SWITCH);
        s.playGame(100, NEVER_SWITCH);
        s.playGame(100, RANDOM);
    }

}
