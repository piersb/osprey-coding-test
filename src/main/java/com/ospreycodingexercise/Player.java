package com.ospreycodingexercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String x;
    private String y;
    
    private String direction;

    public void setX(String x) {
        this.x = x;
    }
    
    public void setY(String y) {
        this.y = y;
    }

    public String getLocation() {
        return x + "x" + y;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public String getDirection() {
        return "NORTH";
    }
}
