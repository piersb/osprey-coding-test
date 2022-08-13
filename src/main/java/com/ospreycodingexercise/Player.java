package com.ospreycodingexercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private int x;
    private int y;
    
    private String direction;

    public void setX(int x) {
        if (x <= 10) {
            this.x = x;
        }
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public String getLocation() {
        return x + "x" + y;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public String getDirection() {
        return direction;
    }
}
