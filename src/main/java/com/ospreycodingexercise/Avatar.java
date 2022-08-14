package com.ospreycodingexercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer x;
    private Integer y;

    private String direction;

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

    public String getLocation() {
        return x + "x" + y;
    }

    public void setDirection(String direction) {
        if (direction.equals(this.direction)) {
            moveAvatar(direction);
        } else {
            this.direction = direction;
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
