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
    
    @GetMapping("/api/board")
    @ResponseBody
    public Iterable<Player> getPlayerInformation() {
        return playerRepository.findAll();
    }
    
    @GetMapping("/api/reset")
    public String resetBoard() {
        Random random = new Random();
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        int directionNumber = random.nextInt(4);
        String[] directionList = {"NORTH", "SOUTH", "EAST", "WEST"};
        String direction = directionList[directionNumber];
        
        Player player = new Player();
        player.setX(x);
        player.setY(y);
        player.setDirection(direction);
        playerRepository.save(player);
        return "Reset board!";
    }
    
}
