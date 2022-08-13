package com.ospreycodingexercise;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PlayerTest {

    
    @ParameterizedTest
    @MethodSource(value = "provideOutOfBoundsLocations")
    public void LocationCannotBeSetOutOfBounds(int[] location) {
        Player player = new Player();
        int x = location[0];
        int y = location[1];
        assertThat(player.getLocation()).isNotEqualTo(x + "x" + y);
    }
    
    public static int[][] provideOutOfBoundsLocations() {
        return new int[][] {{20, 5}};
    }

}
