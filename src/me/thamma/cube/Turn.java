package me.thamma.cube;

import java.util.concurrent.TimeUnit;

import static me.thamma.cube.Cube.*;

public enum Turn {
    UP(2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    UP_PRIME(-2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    FRONT(2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    FRONT_PRIME(-2, new int[]{UFL, UF, URF, RF, DFR, DF, DLF, LF}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}),
    RIGHT(2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    RIGHT_PRIME(-2, new int[]{URF, UR, UBR, RB, DRB, DR, DFR, RF}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}),
    DOWN(2, new int[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
    DOWN_PRIME(-2, new int[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}),
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
    UP_WIDE(UP, EQUATORIAL),
    UP_WIDE_PRIME(UP_PRIME, EQUATORIAL_PRIME),
    FRONT_WIDE(FRONT, STANDING),
    FRONT_WIDE_PRIME(FRONT, STANDING_PRIME),
    RIGHT_WIDE(RIGHT, MIDDLE_PRIME),
    RIGHT_WIDE_PRIME(RIGHT_PRIME, MIDDLE),
    DOWN_WIDE(DOWN, EQUATORIAL),
    DOWN_WIDE_PRIME(DOWN_PRIME, EQUATORIAL_PRIME),
    BACK_WIDE(BACK, STANDING_PRIME),
    BACK_WIDE_PRIME(BACK, STANDING),
    LEFT_WIDE(LEFT, MIDDLE),
    LEFT_WIDE_PRIME(LEFT_PRIME, MIDDLE_PRIME);

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
        int id = this.ordinal() + (this.ordinal() % 2 == 0 ? 1 : -1);
        return Turn.values()[id];
    }

    public static Turn byString(String s) {
        switch (s) {
            case "U":
                return Turn.UP;
            case "F":
                return Turn.FRONT;
            case "R":
                return Turn.RIGHT;
            case "D":
                return Turn.DOWN;
            case "B":
                return Turn.BACK;
            case "L":
                return Turn.LEFT;
            case "U'":
                return Turn.UP_PRIME;
            case "F'":
                return Turn.FRONT_PRIME;
            case "R'":
                return Turn.RIGHT_PRIME;
            case "D'":
                return Turn.DOWN_PRIME;
            case "B'":
                return Turn.BACK_PRIME;
            case "L'":
                return Turn.LEFT_PRIME;
            case "u":
            case "Uw":
                return Turn.UP_WIDE;
            case "f":
            case "Fw":
                return Turn.FRONT_WIDE;
            case "r":
            case "Rw":
                return Turn.RIGHT_WIDE;
            case "d":
            case "Dw":
                return Turn.DOWN_WIDE;
            case "b":
            case "Bw":
                return Turn.BACK_WIDE;
            case "l":
            case "Lw":
                return Turn.LEFT_WIDE;
            case "u'":
            case "Uw'":
                return Turn.UP_WIDE_PRIME;
            case "f'":
            case "Fw'":
                return Turn.FRONT_WIDE_PRIME;
            case "r'":
            case "Rw'":
                return Turn.RIGHT_WIDE_PRIME;
            case "d'":
            case "Dw'":
                return Turn.DOWN_WIDE_PRIME;
            case "b'":
            case "Bw'":
                return Turn.BACK_WIDE_PRIME;
            case "l'":
            case "Lw'":
                return Turn.LEFT_WIDE_PRIME;
            case "X":
                return Turn.X;
            case "Y":
                return Turn.Y;
            case "Z":
                return Turn.Z;
            case "X'":
                return Turn.X_PRIME;
            case "Y'":
                return Turn.Y_PRIME;
            case "Z'":
                return Turn.Z_PRIME;
            case "x":
                return Turn.X;
            case "y":
                return Turn.Y;
            case "z":
                return Turn.Z;
            case "x'":
                return Turn.X_PRIME;
            case "y'":
                return Turn.Y_PRIME;
            case "z'":
                return Turn.Z_PRIME;
            default:
                return null;

        }
    }


}
