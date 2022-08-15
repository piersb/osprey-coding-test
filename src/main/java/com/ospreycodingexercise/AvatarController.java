package com.ospreycodingexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class AvatarController {

    private static final String[] directionList = new String[]{"NORTH", "SOUTH", "EAST", "WEST"};

    @Autowired
    private AvatarRepository avatarRepository;

    Random random = new Random();

    /**
     * This should return the current position and direction of the player on the board in the following format:
     * {
     *      "position": "2x2", "direction": "NORTH"
     * }
     * @return {"position": "XxY", "direction": "COMPASS_DIRECTION"}
     */
    @GetMapping("/api/board")
    @ResponseBody
    public Avatar getAvatarInformation() {
        return avatarRepository.findTopByOrderByIdDesc();
    }

    /**
     * Deletes all moves and starts a new game
     * @return String saying board is reset
     */
    @PostMapping("/api/reset")
    public ResponseEntity<Avatar> resetBoard() {
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        int directionNumber = random.nextInt(4);
        String direction = directionList[directionNumber];

        clearHistory();
        setAvatar(x, y, direction);
        return new ResponseEntity<>(avatarRepository.findTopByOrderByIdDesc(), HttpStatus.CREATED);
    }

    /**
     * This accepts a direction path parameter with an empty body and based on the direction received one of the following actions should be performed:
     * Direction received is different from the player’s current direction: - Player direction should be changed to the new direction.
     * Direction received is the same as the player’s current direction and the player’s position is at the end of the board (for that direction):
     * - Do nothing
     * Direction received is the same as the player’s current direction and the player’s position is not at the end of the board (for that direction):
     * - Move the player forward one square in that direction
     * On completion of the appropriate action, the new player position should be persisted to the database.
     * @param direction major compass point
     * @return http status and new position
     */
    @PostMapping("/api/board/{direction}")
    public ResponseEntity<Avatar> AcceptMove(@PathVariable String direction) {

        Avatar currentAvatar = avatarRepository.findTopByOrderByIdDesc();

        Avatar newAvatar = new Avatar();
        newAvatar.setX(currentAvatar.getX());
        newAvatar.setY(currentAvatar.getY());
        newAvatar.TryToMove(currentAvatar.getDirection());

        try {
            newAvatar.TryToMove(direction);
        } catch (Exception e) {
            return new ResponseEntity<>(newAvatar, HttpStatus.BAD_REQUEST);
        }
        
        avatarRepository.save(newAvatar);
        return new ResponseEntity<>(newAvatar, HttpStatus.CREATED);
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
