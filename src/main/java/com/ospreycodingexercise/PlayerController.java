package com.ospreycodingexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PlayerController {
    
    @Autowired
    private PlayerRepository playerRepository;
    private int x;
    private int y;
    private String direction;

    @GetMapping("/api/board")
    @ResponseBody
    public Iterable<Player> getPlayerInformation() {
        return playerRepository.findAll();
    }
    
    @GetMapping("/api/reset")
    public String resetBoard() {
        Random random = new Random();
        RandomiseSettings(random);

        setAvatar();
        return "Reset board!";
    }

    private void RandomiseSettings(Random random) {
        x = random.nextInt(10) + 1;
        y = random.nextInt(10) + 1;
        int directionNumber = random.nextInt(4);
        String[] directionList = {"NORTH", "SOUTH", "EAST", "WEST"};
        direction = directionList[directionNumber];
    }

    private void setAvatar() {
        Player player = new Player();
        player.setX(x);
        player.setY(y);
        player.setDirection(direction);
        playerRepository.save(player);
    }

}
