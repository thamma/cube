package me.thamma.cube;

import static me.thamma.cube.Cube.*;

public enum Turn {
    UP(2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    UP_PRIME(-2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    FRONT(2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    FRONT_PRIME(-2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    RIGHT(2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    RIGHT_PRIME(-2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    DOWN(2, new int[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    DOWN_PRIME(-2, new int[]{DBL, DB, DRB, DR, DFR, DF, DLF, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    BACK(2, new int[]{UBR, UB, ULB, LB, DBL, DB, DRB, RB}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    BACK_PRIME(-2, new int[]{UBR, UB, ULB, LB, DBL, DB, DRB, RB}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    LEFT(2, new int[]{ULB, UL, UFL, LF, DLF, DL, DBL, LB}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    LEFT_PRIME(-2, new int[]{ULB, UL, UFL, LF, DLF, DL, DBL, LB}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    MIDDLE(2, new int[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    MIDDLE_PRIME(-2, new int[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    EQUATORIAL(2, new int[]{LF, F, RF, R, RB, B, LB, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    EQUATORIAL_PRIME(-2, new int[]{LF, F, RF, R, RB, B, LB, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    STANDING(2, new int[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    STANDING_PRIME(-2, new int[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}),
    X(LEFT, MIDDLE, RIGHT_PRIME),
    X_PRIME(LEFT_PRIME, MIDDLE_PRIME, RIGHT),
    Y(UP, EQUATORIAL_PRIME, DOWN_PRIME),
    Y_PRIME(UP_PRIME, EQUATORIAL, DOWN),
    Z(FRONT, STANDING, BACK_PRIME),
    Z_PRIME(FRONT_PRIME, STANDING_PRIME, BACK),
    ;


    private int offset;
    private int[] target, rotation;
    private Turn[] turns;

    Turn(Turn... turns) {
        this.turns = turns;
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

    public Turn inverse() {
        int id = this.ordinal() + (this.ordinal()%2==0?1:-1);
        return Turn.values()[id];
    }


}
