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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
public class AvatarTest {
    private Avatar testAvatar;

    /**
     * Test avatar in the middle of the board facing north.
     */
    @BeforeEach
    public void before() {
        testAvatar = new Avatar();
        testAvatar.setX(5);
        testAvatar.setY(5);
        testAvatar.TryToMove("NORTH");
    }

    @AfterEach
    public void after() {
        testAvatar = null;
    }

    /**
     * Attempts to set X and Y co-ordinates outside the board bounds. Expected response is that
     * no change in location occurs
     *
     * @param location {x, y}
     */
    @ParameterizedTest
    @MethodSource(value = "provideOutOfBoundsLocations")
    public void LocationCannotBeSetOutOfBounds(int[] location) {
        int x = location[0];
        int y = location[1];
        testAvatar.setX(x);
        testAvatar.setY(y);
        assertThat(testAvatar.getLocation())
                .as("Setting x: " + x + " y:" + y + " should have been rejected due to exceeding board boundaries")
                .isNotEqualTo(x + "x" + y);
    }

    /**
     * Parameters for LocationCannotBeSetOutOfBounds.
     *
     * @return {x, y} where at least one of x and y is out of bounds
     */
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

    /**
     * Tests that given a standard test avatar, attempting to set a direction other than North
     * will change the direction of the avatar and leave it in position.
     */
    @Test
    public void NewDirectionChangesDirectionButDoesNotMove() {
        testAvatar.TryToMove("WEST");
        assertThat(testAvatar.getDirection()).isEqualTo("WEST");
        assertThat(testAvatar.getLocation()).isEqualTo("5x5");
    }

    /**
     * Tests that a TryToMove call in the current direction will move the avatar
     * one space in that direction.
     *
     * @param direction      to move
     * @param expectedResult returned
     */
    @ParameterizedTest
    @MethodSource(value = "InputInTheSameDirectionMovesAvatarForward")
    public void AvatarMovesIfDirectionIsTheSame(String direction, String expectedResult) {

        // ensure that we're facing in the direction we want to test a move in...
        if (!direction.equals(testAvatar.getDirection())) {
            testAvatar.TryToMove(direction);
        }

        testAvatar.TryToMove(direction);
        assertThat(testAvatar.getDirection()).isEqualTo(direction);
        assertThat(testAvatar.getLocation()).isEqualTo(expectedResult);
    }

    /**
     * Parameters for AvatarMovesIfDirectionIsTheSame. Assumes standard test Avatar.
     *
     * @return String direction, String Expected result.
     */
    public static Stream<Arguments> InputInTheSameDirectionMovesAvatarForward() {
        return Stream.of(
                Arguments.of("NORTH", "5x4"),
                Arguments.of("SOUTH", "5x6"),
                Arguments.of("EAST", "6x5"),
                Arguments.of("WEST", "4x5")
        );
    }

    /**
     * Tests that if the avatar is at a board edge attempts to move it off the board edge
     * will have no effect
     *
     * @param x         co-ordinate
     * @param y         co-ordinate
     * @param direction direction to move
     */
    @ParameterizedTest
    @MethodSource(value = "attemptsToMoveOffBoard")
    public void AvatarWillNotMoveOffBoard(int x, int y, String direction) {
        testAvatar.setX(x);
        testAvatar.setY(y);
        testAvatar.setDirection(direction);
        testAvatar.TryToMove(direction);
        assertThat(testAvatar.getDirection()).isEqualTo(direction);
        assertThat(testAvatar.getLocation()).isEqualTo(x + "x" + y);
    }

    /**
     * Parameters for AvatarWillNotMoveOffBoard. Tests by attempting to move off board
     * in the North-west and South-east corners.
     *
     * @return int x, int y, String direction
     */
    public static Stream<Arguments> attemptsToMoveOffBoard() {
        return Stream.of(
                Arguments.of(10, 10, "EAST"),
                Arguments.of(10, 10, "SOUTH"),
                Arguments.of(1, 1, "NORTH"),
                Arguments.of(1, 1, "WEST")
        );
    }

    @Test
    public void NonValidInputShouldResultInNoChange() {
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> testAvatar.TryToMove("UP"));

        assertTrue(exception.getMessage().contains("Invalid input"));
        assertThat(testAvatar.getDirection()).isEqualTo("NORTH");
        assertThat(testAvatar.getLocation()).isEqualTo("5x5");
    }
    
}
