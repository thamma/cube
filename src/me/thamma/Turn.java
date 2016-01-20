package me.thamma; /**
 * Created by Dominic on 1/20/2016.
 */

import java.util.Arrays;

import static me.thamma.Cube.*;

public enum Turn {
    UP(2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    UP_PRIME(-2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    FRONT(2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{1, 1, -1, 1, 1, 1, -1, 1}),
    FRONT_PRIME(-2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{1, 1, -1, 1, 1, 1, -1, 1}),
    RIGHT(2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    RIGHT_PRIME(-2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
//    RIGHT_PRIME(RIGHT.inverse()),
//    DOWN(),
//    DOWN_PRIME(RIGHT.inverse()),
//    BACK(),
//    BACK_PRIME(BACK.inverse()),
//    LEFT(),
//    LEFT_PRIME(LEFT.inverse()),
//    MIDDLE(),
//    MIDDLE_PRIME(MIDDLE.inverse()),
//    EQUATORIAL(),
//    EQUATORIAL_PRIME(EQUATORIAL.inverse()),
//    STANDING(),
//    STANDING_PRIME(STANDING.inverse()),
//    X(LEFT, MIDDLE, RIGHT_PRIME),
//    Y(UP, EQUATORIAL_PRIME, DOWN_PRIME),
//    Z(FRONT, STANDING, BACK_PRIME),
//    X_PRIME(X.inverse())
    ;


    private int offset;
    private int[] target, rotation;
    private Turn[] turns;

    Turn(Turn... turns) {
        this.turns = turns;
    }

    public void print() {
        if (this.hasChildren()) {
            for (int i = 0; i < this.turns.length; i++)
                System.out.println(this.turns[i].offset + ", " + Arrays.toString(this.turns[i].target) + ", " + Arrays.toString(this.turns[i].rotation));
        } else
            System.out.println(offset + ", " + Arrays.toString(target) + ", " + Arrays.toString(rotation));
    }

    Turn(int offset, int[] target, int[] rotation) {
        this.offset = offset;
        this.target = target;
        this.rotation = rotation;
    }

    public boolean hasChildren() {
        return turns != null;
    }

    public Turn[] getChildren() {
        return this.turns;
    }

    public int getOffset() {
        return this.offset;
    }

    public int[] getTarget() {
        return this.target;
    }

    public int[] getRotation() {
        return this.rotation;
    }


}
