package be.switchfully.gameoflife.backend.gol;

import com.jayway.restassured.internal.assertion.GetAtPathFragmentEscaper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class GameOfLifeControllerTest {

    private List<List<Boolean>> world = new ArrayList<>();


    @Before
    public void setUp() throws Exception {

        List<Boolean> kolom1 = new ArrayList<>();
        List<Boolean> kolom3 = new ArrayList<>();
        List<Boolean> kolom2 = new ArrayList<>();

        kolom1.add(true);
        kolom1.add(true);
        kolom1.add(true);
        kolom2.add(false);
        kolom2.add(false);
        kolom2.add(false);
        kolom3.add(true);
        kolom3.add(true);
        kolom3.add(false);

        world.add(kolom1);
        world.add(kolom2);
        world.add(kolom3);
    }

    @Test
    public void countingTruesInWorld_shouldReturnCorrectAmountOfTrues() throws Exception {
        int expected = 5;
        int actual = GameOfLifeController.countingTruesInWorld(world);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void countingHorizontal_shouldReturnCorrectAmountOfTrueNeighbours() throws Exception {
        int expected = 2;
        int actual = GameOfLifeController.countingHorizontal(world, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void countingVertical_shouldReturnCorrectAmountOfTrueNeighbours() throws Exception {
        int expected = 0;
        int actual = GameOfLifeController.countingVertical(world, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void countCorners_shouldReturnCorrectAmountOfTrueNeighbours() throws Exception {
        int expected = 3;
        int actual = GameOfLifeController.countingCorners(world, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void countNeighbours_shouldReturnTotalOfAllNeighbours() throws Exception {
        int expected = 5;
        int actual = GameOfLifeController.countingNeighbours(world, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void decideCellFate_shouldNotChangeDeadcelWith5Neighbours() throws Exception {
        Boolean expected = false;
        Boolean actual = GameOfLifeController.decideCellFate(world, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void decideCellFate_shouldReviveDeadCellWithThreeNeighbours() throws Exception {
        Boolean expected = true;
        Boolean actual = GameOfLifeController.decideCellFate(world, 1, 2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void decideCellFate_shouldKillOffALivingCellWithOutEnoughNeighbours() throws Exception {
        Boolean expected = false;
        Boolean actual = GameOfLifeController.decideCellFate(world, 0, 0);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void decideCellFate_shouldMakeALivingCellWithTwoOrThreeNeighboursSurvive() throws Exception {
        Boolean expected = true;
        Boolean actual = GameOfLifeController.decideCellFate(world, 2, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getUpperLeftCorner_shouldReturnUpperLeftCorner() throws Exception {
        Boolean expected = true;
        Boolean actual = GameOfLifeController.getUpperLeftCornerOf(world,1,1);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void getUpperRightCorner_shouldReturnUpperRightCorner() throws Exception {
        Boolean expected = true;
        Boolean actual = GameOfLifeController.getUpperRightCornerOf(world,1,1);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void getLowerLeftCorner_shouldReturnLowerLeftCorner() throws Exception {
        Boolean expected = true;
        Boolean actual = GameOfLifeController.getLowerLeftCornerOf(world,1,1);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void getLowerRightCorner_shouldReturnLowerRightCorner() throws Exception {
        Boolean expected = false;
        Boolean actual = GameOfLifeController.getLowerRightCornerOf(world,1,1);
        assertThat(actual).isEqualTo(expected);
    }


}