package me.thamma.cube.model;

import static me.thamma.cube.model.Sticker.*;

public enum Turn {
    UP(new String[]{"U"}, 2, new Sticker[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    UP_PRIME(new String[]{"U'"}, -2, new Sticker[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    FRONT(new String[]{"F"}, 2, new Sticker[]{UFL, UF, URF, FR, DFR, DF, DLF, FL}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    FRONT_PRIME(new String[]{"F'"}, -2, new Sticker[]{UFL, UF, URF, FR, DFR, DF, DLF, FL}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    RIGHT(new String[]{"R"}, 2, new Sticker[]{URF, UR, UBR, BR, DRB, DR, DFR, FR}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    RIGHT_PRIME(new String[]{"R'"}, -2, new Sticker[]{URF, UR, UBR, BR, DRB, DR, DFR, FR}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    DOWN(new String[]{"D"}, 2, new Sticker[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    DOWN_PRIME(new String[]{"D'"}, -2, new Sticker[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    BACK(new String[]{"B"}, 2, new Sticker[]{UBR, UB, ULB, BL, DBL, DB, DRB, BR}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    BACK_PRIME(new String[]{"B'"}, -2, new Sticker[]{UBR, UB, ULB, BL, DBL, DB, DRB, BR}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    LEFT(new String[]{"L"}, 2, new Sticker[]{ULB, UL, UFL, FL, DLF, DL, DBL, BL}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    LEFT_PRIME(new String[]{"L'"}, -2, new Sticker[]{ULB, UL, UFL, FL, DLF, DL, DBL, BL}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),

    MIDDLE(new String[]{"M"}, 2, new Sticker[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.X),
    MIDDLE_PRIME(new String[]{"M'"}, -2, new Sticker[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.X),
    EQUATORIAL(new String[]{"E"}, 2, new Sticker[]{FL, F, FR, R, BR, B, BL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Y),
    EQUATORIAL_PRIME(new String[]{"E'"}, -2, new Sticker[]{FL, F, FR, R, BR, B, BL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Y),
    STANDING(new String[]{"S"}, 2, new Sticker[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Z),
    STANDING_PRIME(new String[]{"S'"}, -2, new Sticker[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Z),

    X(new String[]{"X", "x"}, Axis.X, LEFT_PRIME, MIDDLE_PRIME, RIGHT),
    X_PRIME(new String[]{"X'", "x'"}, Axis.X, LEFT, MIDDLE, RIGHT_PRIME),
    Y(new String[]{"Y", "y"}, Axis.Y, UP, EQUATORIAL_PRIME, DOWN_PRIME),
    Y_PRIME(new String[]{"Y'", "y'"}, Axis.Y, UP_PRIME, EQUATORIAL, DOWN),
    Z(new String[]{"Z", "z"}, Axis.Z, FRONT, STANDING, BACK_PRIME),
    Z_PRIME(new String[]{"Z'", "z'"}, Axis.Z, FRONT_PRIME, STANDING_PRIME, BACK),
    UP_WIDE(new String[]{"u", "Uw"}, Axis.Y, UP, EQUATORIAL_PRIME),
    UP_WIDE_PRIME(new String[]{"u'", "Uw'"}, Axis.Y, UP_PRIME, EQUATORIAL),
    FRONT_WIDE(new String[]{"f", "Fw"}, Axis.Z, FRONT, STANDING),
    FRONT_WIDE_PRIME(new String[]{"f'", "Fw'"}, Axis.Z, FRONT, STANDING_PRIME),
    RIGHT_WIDE(new String[]{"r", "Rw"}, Axis.X, RIGHT, MIDDLE_PRIME),
    RIGHT_WIDE_PRIME(new String[]{"r'", "Rw'"}, Axis.X, RIGHT_PRIME, MIDDLE),
    DOWN_WIDE(new String[]{"d", "Dw"}, Axis.Y, DOWN, EQUATORIAL),
    DOWN_WIDE_PRIME(new String[]{"d'", "Dw'"}, Axis.Y, DOWN_PRIME, EQUATORIAL_PRIME),
    BACK_WIDE(new String[]{"b", "Bw"}, Axis.Z, BACK, STANDING_PRIME),
    BACK_WIDE_PRIME(new String[]{"b'", "Bw'"}, Axis.Z, BACK_PRIME, STANDING),
    LEFT_WIDE(new String[]{"l", "Lw"}, Axis.X, LEFT, MIDDLE),
    LEFT_WIDE_PRIME(new String[]{"l'", "Lw'"}, Axis.X, LEFT_PRIME, MIDDLE_PRIME),;

    //
    //  fields
    //

    private int offset;
    private Sticker[] target;
    private int[] rotation;
    private Turn[] children;
    private String[] stringRepresentation;

    //
    //  constructors
    //

    /**
     * The constructor for non-elementary Turns
     *
     * @param stringRepresentation The possible String representations in cube notation of the current Turn.
     *                             Must not be empty as the first element is the default representation
     * @param children             The Turns the current Turn is composed of. Must (obviously) not be recursive!
     */
    Turn(String[] stringRepresentation, Axis axis, Turn... children) {
        //TODO handle axis
        this.stringRepresentation = stringRepresentation;
        this.children = children;
    }

    /**
     * The constructor for elementary Turns
     *
     * @param stringRepresentation The possible String representations in cube notation of the current Turn.
     *                             Must not be empty as the first element is the default representation!
     * @param offset               the offset, how many pieces to skip in the current cycling (usually 2 such that corners,
     *                             edges and centers are each mapped to the same piece type).
     * @param target               the stickers to be cycled
     * @param rotation             the rotation applied to each sticker upon cycling.
     * @see {@link me.thamma.cube.model.Cube#turn(Turn)} for more detail
     */
    Turn(String[] stringRepresentation, int offset, Sticker[] target, int[] rotation, Axis axis) {
        //TODO handle axis
        this.stringRepresentation = stringRepresentation;
        this.offset = offset;
        this.target = target;
        this.rotation = rotation;
    }

    //
    //  public methods
    //

    /**
     * @return the inverse Turn of the current one.
     */
    public Turn inverse() {
        return Turn.values()[this.ordinal() ^ 1];
    }

    @Override
    public String toString() {
        return this.stringRepresentation[0];
    }

    /**
     * @param stringRepresentation the String representation of a Turn
     * @return the according Turn, if the stringRepresentation was valid
     */
    public static Turn byString(String stringRepresentation) {
        for (Turn turn : Turn.values())
            for (String rep : turn.stringRepresentation)
                if (rep.equals(stringRepresentation))
                    return turn;
        return null;
    }

    //
    //  private and package private methods
    //

    /**
     * @return Whether the current Turn has children Turns, i.e. it is no elementary Turn
     */
    boolean hasChildren() {
        return children != null;
    }

    /**
     * @return An array of children nodes if the current Turn is no elementary one, else null.
     */
    Turn[] getChildren() {
        return this.children;
    }

    /**
     * @return the offset, how many pieces to skip in the current cycling (usually 2 such that corners,
     * edges and centers are each mapped to the same piece type).
     */
    int getOffset() {
        return this.offset;
    }

    /**
     * @return the target stickers to be cycled
     */
    Sticker[] getTarget() {
        return this.target;
    }

    /**
     * @retun the rotation applied to each sticker upon cycling.
     */
    int[] getRotation() {
        return this.rotation;
    }


}
