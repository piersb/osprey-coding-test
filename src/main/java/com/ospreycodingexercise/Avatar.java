package com.ospreycodingexercise;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer x;
    
    private Integer y;

    private String direction;

    private static List<String> directionList = new ArrayList<>(Arrays.asList("NORTH", "SOUTH", "EAST", "WEST"));


    @JsonIgnore
    public Integer getX() {
        return x;
    }

    @JsonIgnore
    public Integer getY() {
        return y;
    }

    public void setX(int x) {
        if (x >= 1 && x <= 10) {
            this.x = x;
        }
    }

    public void setY(int y) {
        if (y >= 1 && y <= 10) {
            this.y = y;
        }
    }
    
    public void setDirection(String direction) {
        
        if (!directionList.contains(direction)) {
            throw new IllegalArgumentException("Invalid input.");
        }
        
        this.direction = direction;
    }

    public String getLocation() {
        return x + "x" + y;
    }

    public void TryToMove(String direction) {

        if (direction.equals(this.direction)) {
            moveAvatar(direction);
        } else {
            setDirection(direction);
        }

    }

    private void moveAvatar(String direction) {
        switch (direction) {
            case "NORTH" -> setY(y - 1);
            case "SOUTH" -> setY(y + 1);
            case "EAST" -> setX(x + 1);
            case "WEST" -> setX(x - 1);
        }
    }

    public String getDirection() {
        return direction;
    }
}
