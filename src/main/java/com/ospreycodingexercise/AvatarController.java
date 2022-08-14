package com.ospreycodingexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class AvatarController {

    private static final String[] directionList = new String[]{"NORTH", "SOUTH", "EAST", "WEST"};
    
    @Autowired
    private AvatarRepository avatarRepository;
    
    Random random = new Random();
    
    @GetMapping("/api/board")
    @ResponseBody
    public Iterable<Avatar> getAvatarInformation() {
        return avatarRepository.findAll();
    }
    
    @GetMapping("/api/reset")
    public String resetBoard() {
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        int directionNumber = random.nextInt(4);
        String direction = directionList[directionNumber];

        clearHistory();
        setAvatar(x, y, direction);
        return "Reset board!";
    }

    public void clearHistory() {
        avatarRepository.deleteAll();
    }

    private void setAvatar(int x, int y, String direction) {
        Avatar avatar = new Avatar();
        avatar.setX(x);
        avatar.setY(y);
        avatar.setDirection(direction);
        avatarRepository.save(avatar);
    }

}
