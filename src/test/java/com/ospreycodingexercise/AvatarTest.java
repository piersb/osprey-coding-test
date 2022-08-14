package com.ospreycodingexercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AvatarTest {
    private Avatar avatar;
    
    @BeforeEach
    public void before() {
        avatar = new Avatar();
    }
    
    @AfterEach
    public void after() {
        avatar = null;
    }
    
    @ParameterizedTest
    @MethodSource(value = "provideOutOfBoundsLocations")
    public void LocationCannotBeSetOutOfBounds(int[] location) {
        int x = location[0];
        int y = location[1];
        avatar.setX(x);
        avatar.setY(y);
        assertThat(avatar.getLocation())
                .as("Setting x: " + x +" y:" + y + " should have been rejected due to exceeding board boundaries")
                .isNotEqualTo(x + "x" + y);
    }

    public static int[][] provideOutOfBoundsLocations() {
        return new int[][]{
                {20, 5}, // x high
                {0, 5}, // x low
                {-5, 5}, // x negative
                {5, 20}, // y high
                {5, 0}, // y low
                {5, -5}, // y negative
        };
    }
    
    @Test
    public void DirectionCanBeChanged() {
        avatar.setDirection("NORTH");
        assertThat(avatar.getDirection()).isEqualTo("NORTH");
        avatar.setDirection("WEST");
        assertThat(avatar.getDirection()).isEqualTo("WEST");
    }
    
    @Test
    public void PlayerCanBeCreated() {
        
    }
    
}
