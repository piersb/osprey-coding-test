package com.ospreycodingexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
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
//        Random random = new Random();
//        int x = random.nextInt(10) + 1;
//        int y = random.nextInt(10) + 1;
//        List<String> directionList = Arrays.asList("NORTH", "SOUTH", "EAST", "WEST");
//        String direction = directionList.get(random.nextInt(directionList.size()));
//        Player player = new Player();
//        player.setX(x);
//        player.setY(y);
//        player.setDirection(direction);
        Player player = new Player();
        player.setX(5);
        player.setY(5);
        player.setDirection("NORTH");
        playerRepository.save(player);
        return "Reset board!";
    }
    
}
