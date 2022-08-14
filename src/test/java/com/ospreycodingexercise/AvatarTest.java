package com.ospreycodingexercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class AvatarTest {
    private Avatar testAvatar;

    @BeforeEach
    public void before() {
        testAvatar = new Avatar();
        testAvatar.setX(5);
        testAvatar.setY(5);
        testAvatar.setDirection("NORTH");
    }
    
    @AfterEach
    public void after() {
        testAvatar = null;
    }
    
    @ParameterizedTest
    @MethodSource(value = "provideOutOfBoundsLocations")
    public void LocationCannotBeSetOutOfBounds(int[] location) {
        int x = location[0];
        int y = location[1];
        testAvatar.setX(x);
        testAvatar.setY(y);
        assertThat(testAvatar.getLocation())
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
    public void NewDirectionChangesDirectionButDoesNotMove() {
        testAvatar.setDirection("WEST");
        assertThat(testAvatar.getDirection()).isEqualTo("WEST");
        assertThat(testAvatar.getLocation()).isEqualTo("5x5");
    }
    
    @ParameterizedTest
    @MethodSource(value = "InputInTheSameDirectionMovesAvatarForward")
    public void AvatarMovesIfDirectionIsTheSame(String direction, String expectedResult) {
        
        // ensure that we're facing in the direction we want to test a move in...
        if (!direction.equals(testAvatar.getDirection())) {
            testAvatar.setDirection(direction);
        }
        
        testAvatar.setDirection(direction);
        assertThat(testAvatar.getDirection()).isEqualTo(direction);
        assertThat(testAvatar.getLocation()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> InputInTheSameDirectionMovesAvatarForward() {
        return Stream.of(
                Arguments.of("NORTH", "5x4"),
                Arguments.of("SOUTH", "5x6"),
                Arguments.of("EAST", "6x5"),
                Arguments.of("WEST", "4x5")
        );
    }

    @ParameterizedTest
    @MethodSource(value = "attemptsToMoveOffBoard")
    public void AvatarWillNotMoveOffBoard(int x, int y, String direction) {
        testAvatar.setX(x);
        testAvatar.setY(y);
        testAvatar.setDirection(direction);
        testAvatar.setDirection(direction);
        assertThat(testAvatar.getDirection()).isEqualTo(direction);
        assertThat(testAvatar.getLocation()).isEqualTo(x + "x" + y);
    }

    public static Stream<Arguments> attemptsToMoveOffBoard() {
        return Stream.of(
                Arguments.of(10, 10, "EAST"),
                Arguments.of(10, 10, "SOUTH"),
                Arguments.of(1, 1, "NORTH"),
                Arguments.of(1, 1, "WEST")
                );
    }
    
    
    @Test
    public void AvatarCanBeCreated() {
        
    }
    

}
