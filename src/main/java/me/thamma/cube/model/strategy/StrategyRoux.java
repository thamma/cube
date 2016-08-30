package me.thamma.cube.model.strategy;

import me.thamma.cube.model.Turn;
import me.thamma.cube.model.regex.CubeRegex;

import static me.thamma.cube.model.Turn.*;

public class StrategyRoux extends Strategy {


    @Override
    SubStrategy[] getSteps() {
        return new SubStrategy[] {
                // First Block
                //   first slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return PRIMITIVE_TURNS;
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("..............................D..................L..L.");
                    }
                },
                //   second slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return PRIMITIVE_TURNS;
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("............F..F...........D..D..................LL.LL");
                    }
                },
                //   third slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return PRIMITIVE_TURNS;
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("............F..F...........D..D..D.......B..B...LLLLLL");
                    }
                },
                // Second Block
                //   first slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return new Turn[]{RIGHT, RIGHT_PRIME, UP, UP_PRIME, MIDDLE, MIDDLE_PRIME};
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("............F..F.........R.D..D.DD.......B..B...LLLLLL");
                    }
                },
                //   second slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return new Turn[]{RIGHT, RIGHT_PRIME, UP, UP_PRIME, MIDDLE, MIDDLE_PRIME};
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("............F.FF.F...R..RR.D.DD.DD.......B..B...LLLLLL");
                    }
                },
                //   third slot
                new SubStrategy() {

                    @Override
                    Turn[] getTurns() {
                        return new Turn[]{RIGHT, RIGHT_PRIME, UP, UP_PRIME, MIDDLE, MIDDLE_PRIME};
                    }

                    @Override
                    CubeRegex getRegex() throws Exception {
                        return CubeRegex.compile("............F.FF.F...RRRRRRD.DD.DD.D...B.BB.B...LLLLLL");
                    }
                }
        };
    }
}
