package be.switchfully.gameoflife.backend.gol;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = GameOfLifeController.WORLD_BASE_URL)
public class GameOfLifeController {

    static final String WORLD_BASE_URL = "/api/gol";
    private static Logger logger = Logger.getLogger(GameOfLifeController.class);

    @PostMapping(value = "/phase")
    public List<List<Boolean>> nextGeneration(@RequestBody List<List<Boolean>> currentWorld) {
        logger.info(currentWorld);
//        Collections.shuffle(currentWorld);
        return currentWorld;
    }

    public static int countingTruesInWorld(List<List<Boolean>> world) {
        int counter = 0;
        for (int rij = 0; rij < world.size(); rij++) {
            for (int kolom = 0; kolom < world.size(); kolom++) {
                if (world.get(rij).get(kolom)) {
                    counter++;
                }
            }
        }
        return counter;
    }


    public static int countingHorizontal(List<List<Boolean>> world, int rij, int kolom) {
        int counter = 0;
        if (kolom != world.size() - 1) {
            if (world.get(kolom + 1).get(rij)) {
                counter++;
            }
        }
        if (kolom != 0) {
            if (world.get(kolom - 1).get(rij)) {
                counter++;
            }
        }
        return counter;
    }


    public static int countingVertical(List<List<Boolean>> world, int rij, int kolom) {
        int counter = 0;
        if (rij != 0) {
            if (world.get(kolom).get(rij - 1)) {
                counter++;
            }
        }
        if (rij != world.size() - 1) {
            if (world.get(kolom).get(rij + 1)) {
                counter++;
            }
        }
        return counter;
    }

    public static int countingCorners(List<List<Boolean>> world, int rij, int kolom) {
        int counter = 0;
        if (rij != 0 && kolom != 0) {
            if (getUpperLeftCornerOf(world, rij, kolom)) {
                counter++;
            }
        }
        if (rij != 0 && kolom != world.size() - 1) {
            if (getLowerLeftCornerOf(world, rij, kolom)) {
                counter++;
            }
        }
        if (rij != world.size() - 1 && kolom != 0) {
            if (getUpperRightCornerOf(world, rij, kolom)) {
                counter++;
            }
        }
        if (rij != world.size() - 1 && kolom != world.size() - 1) {
            if (getLowerRightCornerOf(world, rij, kolom)) {
                counter++;
            }
        }
        return counter;
    }

    public static boolean getUpperLeftCornerOf(List<List<Boolean>> world, int rij, int kolom) {
        return world.get(kolom - 1).get(rij - 1);
    }

    public static boolean getUpperRightCornerOf(List<List<Boolean>> world, int rij, int kolom) {
        return world.get(kolom + 1).get(rij - 1);
    }

    public static boolean getLowerLeftCornerOf(List<List<Boolean>> world, int rij, int kolom) {
        return world.get(kolom - 1).get(rij + 1);
    }

    public static boolean getLowerRightCornerOf(List<List<Boolean>> world, int rij, int kolom) {
        return world.get(kolom + 1).get(rij + 1);
    }


    public static int countingNeighbours(List<List<Boolean>> world, int rij, int kolom) {
        return countingHorizontal(world, rij, kolom)
                + countingVertical(world, rij, kolom)
                + countingCorners(world, rij, kolom);
    }

    public static Boolean decideCellFate(List<List<Boolean>> world, int rij, int kolom) {

        int numberOfNeighbours = countingNeighbours(world, rij, kolom);
        boolean result= false;

        if (numberOfNeighbours >= 4 || numberOfNeighbours <= 1) {
            result = false;
        }
        if (!world.get(kolom).get(rij) && numberOfNeighbours == 3) {
            result = true;
        }
        if (numberOfNeighbours == 2) {
            result = world.get(kolom).get(rij);
        }
        return result;

    }
}
