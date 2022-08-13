package com.ospreycodingexercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    
    @GetMapping("/api/board")
    @ResponseBody
    public String getPlayerInformation() throws JsonProcessingException {
        Player player = new Player();
        player.setX(2);
        player.setY(2); 
        player.setDirection("NORTH");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(player);
    }
    
}
