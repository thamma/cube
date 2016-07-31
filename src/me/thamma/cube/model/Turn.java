package me.thamma.cube.model;

import static me.thamma.cube.model.Piece.*;

public enum Turn {
    UP(new String[]{"U"}, 2, new Piece[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    UP_PRIME(new String[]{"U'"}, -2, new Piece[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    FRONT(new String[]{"F"}, 2, new Piece[]{UFL, UF, URF, FR, DFR, DF, DLF, FL}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    FRONT_PRIME(new String[]{"F'"}, -2, new Piece[]{UFL, UF, URF, FR, DFR, DF, DLF, FL}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    RIGHT(new String[]{"R"}, 2, new Piece[]{URF, UR, UBR, BR, DRB, DR, DFR, FR}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    RIGHT_PRIME(new String[]{"R'"}, -2, new Piece[]{URF, UR, UBR, BR, DRB, DR, DFR, FR}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    DOWN(new String[]{"D"}, 2, new Piece[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    DOWN_PRIME(new String[]{"D'"}, -2, new Piece[]{DLF, DF, DFR, DR, DRB, DB, DBL, DL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}, Axis.Y),
    BACK(new String[]{"B"}, 2, new Piece[]{UBR, UB, ULB, BL, DBL, DB, DRB, BR}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    BACK_PRIME(new String[]{"B'"}, -2, new Piece[]{UBR, UB, ULB, BL, DBL, DB, DRB, BR}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1}, Axis.Z),
    LEFT(new String[]{"L"}, 2, new Piece[]{ULB, UL, UFL, FL, DLF, DL, DBL, BL}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),
    LEFT_PRIME(new String[]{"L'"}, -2, new Piece[]{ULB, UL, UFL, FL, DLF, DL, DBL, BL}, new int[]{-1, 0, 1, 0, -1, 0, 1, 0}, Axis.X),

    MIDDLE(new String[]{"M"}, 2, new Piece[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.X),
    MIDDLE_PRIME(new String[]{"M'"}, -2, new Piece[]{UF, F, DF, D, DB, B, UB, U}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.X),
    EQUATORIAL(new String[]{"E"}, 2, new Piece[]{FL, F, FR, R, BR, B, BL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Y),
    EQUATORIAL_PRIME(new String[]{"E'"}, -2, new Piece[]{FL, F, FR, R, BR, B, BL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Y),
    STANDING(new String[]{"S"}, 2, new Piece[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Z),
    STANDING_PRIME(new String[]{"S'"}, -2, new Piece[]{UL, U, UR, R, DR, D, DL, L}, new int[]{1, 0, 1, 0, 1, 0, 1, 0}, Axis.Z),

    X(new String[]{"x", "X"}, Axis.X, LEFT_PRIME, MIDDLE_PRIME, RIGHT),
    X_PRIME(new String[]{"x'", "X'"}, Axis.X, LEFT, MIDDLE, RIGHT_PRIME),
    Y(new String[]{"y", "Y"}, Axis.Y, UP, EQUATORIAL_PRIME, DOWN_PRIME),
    Y_PRIME(new String[]{"y'", "Y'"}, Axis.Y, UP_PRIME, EQUATORIAL, DOWN),
    Z(new String[]{"z", "Z"}, Axis.Z, FRONT, STANDING, BACK_PRIME),
    Z_PRIME(new String[]{"z'", "Z'"}, Axis.Z, FRONT_PRIME, STANDING_PRIME, BACK),

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
    private Piece[] target;
    private int[] rotation;
    private Axis axis;
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
     * @param axis                 The axis on which the Turn operates on
     * @param children             The Turns the current Turn is composed of. Must (obviously) not be recursive!
     */
    Turn(String[] stringRepresentation, Axis axis, Turn... children) {
        this.stringRepresentation = stringRepresentation;
        this.axis = axis;
        this.children = children;
    }

    /**
     * The constructor for elementary Turns
     *
     * @param stringRepresentation The possible String representations in cube notation of the current Turn.
     *                             Must not be empty as the first element is the default representation!
     * @param offset               the offset, how many pieces to skip in the current cycling (usually 2 such that corners,
     *                             edges and centers are each mapped to the same piece type).
     * @param target               the pieces to be cycled
     * @param rotation             the rotation applied to each sticker upon cycling.
     * @param axis                 The axis on which the Turn operates on
     * @see {@link me.thamma.cube.model.Cube#turn(Turn)} for more detail
     */
    Turn(String[] stringRepresentation, int offset, Piece[] target, int[] rotation, Axis axis) {
        this.stringRepresentation = stringRepresentation;
        this.offset = offset;
        this.target = target;
        this.rotation = rotation;
        this.axis = axis;
    }

    private Turn[] lookupMirrors() {
        switch (this) {
            case UP:
                return new Turn[]{UP_PRIME, DOWN_PRIME, UP_PRIME};
            case UP_PRIME:
                return new Turn[]{UP, DOWN, UP};
            case FRONT:
                return new Turn[]{FRONT_PRIME, FRONT_PRIME, BACK_PRIME};
            case FRONT_PRIME:
                return new Turn[]{FRONT, FRONT, BACK};
            case RIGHT:
                return new Turn[]{LEFT_PRIME, RIGHT_PRIME, RIGHT_PRIME};
            case RIGHT_PRIME:
                return new Turn[]{LEFT, RIGHT, RIGHT};
            case DOWN:
                return new Turn[]{DOWN_PRIME, UP_PRIME, DOWN_PRIME};
            case DOWN_PRIME:
                return new Turn[]{DOWN, UP, DOWN};
            case BACK:
                return new Turn[]{BACK_PRIME, BACK_PRIME, FRONT_PRIME};
            case BACK_PRIME:
                return new Turn[]{BACK, BACK, FRONT};
            case LEFT:
                return new Turn[]{RIGHT_PRIME, LEFT_PRIME, LEFT_PRIME};
            case LEFT_PRIME:
                return new Turn[]{RIGHT, LEFT, LEFT};

            case MIDDLE:
                return new Turn[]{MIDDLE, MIDDLE_PRIME, MIDDLE_PRIME};
            case MIDDLE_PRIME:
                return new Turn[]{MIDDLE_PRIME, MIDDLE, MIDDLE};
            case EQUATORIAL:
                return new Turn[]{EQUATORIAL_PRIME, EQUATORIAL, EQUATORIAL_PRIME};
            case EQUATORIAL_PRIME:
                return new Turn[]{EQUATORIAL, EQUATORIAL_PRIME, EQUATORIAL};
            case STANDING:
                return new Turn[]{STANDING_PRIME, STANDING_PRIME, STANDING};
            case STANDING_PRIME:
                return new Turn[]{STANDING, STANDING, STANDING_PRIME};

            case X:
                return new Turn[]{X, X_PRIME, X_PRIME};
            case X_PRIME:
                return new Turn[]{X_PRIME, X, X};
            case Y:
                return new Turn[]{Y_PRIME, Y, Y_PRIME};
            case Y_PRIME:
                return new Turn[]{Y, Y_PRIME, Y};
            case Z:
                return new Turn[]{Z_PRIME, Z_PRIME, Z};
            case Z_PRIME:
                return new Turn[]{Z, Z, Z_PRIME};

            case UP_WIDE:
                return new Turn[]{UP_WIDE_PRIME, DOWN_WIDE_PRIME, UP_WIDE_PRIME};
            case UP_WIDE_PRIME:
                return new Turn[]{UP_WIDE, DOWN_WIDE, UP_WIDE};
            case FRONT_WIDE:
                return new Turn[]{FRONT_WIDE_PRIME, FRONT_WIDE_PRIME, BACK_WIDE_PRIME};
           case FRONT_WIDE_PRIME:
                return new Turn[]{FRONT_WIDE, FRONT_WIDE, BACK_WIDE};
            case RIGHT_WIDE:
                return new Turn[]{LEFT_WIDE_PRIME, RIGHT_WIDE_PRIME, RIGHT_WIDE_PRIME};
           case RIGHT_WIDE_PRIME:
                return new Turn[]{LEFT_WIDE, RIGHT_WIDE, RIGHT_WIDE};
            case DOWN_WIDE:
                return new Turn[]{DOWN_WIDE_PRIME, UP_WIDE_PRIME, DOWN_WIDE_PRIME};
            case DOWN_WIDE_PRIME:
                return new Turn[]{DOWN_WIDE, UP_WIDE, DOWN_WIDE};
            case BACK_WIDE:
                return new Turn[]{BACK_WIDE_PRIME, BACK_WIDE_PRIME, FRONT_WIDE_PRIME};
            case BACK_WIDE_PRIME:
                return new Turn[]{BACK_WIDE, BACK_WIDE, FRONT_WIDE};
            case LEFT_WIDE:
                return new Turn[]{RIGHT_WIDE_PRIME, LEFT_WIDE_PRIME, LEFT_WIDE_PRIME};
            case LEFT_WIDE_PRIME:
                return new Turn[]{RIGHT_WIDE, LEFT_WIDE, LEFT_WIDE};
            default:
                return null;
        }
    }

    /**
     * @return a Turn[6] array containing the x, x', y, y', z and z' translations of the current turn.
     * <p>
     * For instance, given Turn T und rotations X, ..., Z', returns the array [t1, ..., t6] such that t1 is the same
     */
    private Turn[] lookUpTranslations() {
        switch (this) {
            case UP:
                return new Turn[]{FRONT, BACK, UP, UP, LEFT, RIGHT};
            case UP_PRIME:
                return new Turn[]{FRONT_PRIME, BACK_PRIME, UP_PRIME, UP_PRIME, LEFT_PRIME, RIGHT_PRIME};
            case FRONT:
                return new Turn[]{DOWN, UP, RIGHT, LEFT, FRONT, FRONT};
            case FRONT_PRIME:
                return new Turn[]{DOWN_PRIME, UP_PRIME, RIGHT_PRIME, LEFT_PRIME, FRONT_PRIME, FRONT_PRIME};
            case RIGHT:
                return new Turn[]{RIGHT, RIGHT, BACK, FRONT, UP, DOWN};
            case RIGHT_PRIME:
                return new Turn[]{RIGHT_PRIME, RIGHT_PRIME, BACK_PRIME, FRONT_PRIME, UP_PRIME, DOWN_PRIME};
            case DOWN:
                return new Turn[]{BACK, FRONT, DOWN, DOWN, RIGHT, LEFT};
            case DOWN_PRIME:
                return new Turn[]{BACK_PRIME, FRONT_PRIME, DOWN_PRIME, DOWN_PRIME, RIGHT_PRIME, LEFT_PRIME};
            case BACK:
                return new Turn[]{UP, DOWN, LEFT, RIGHT, BACK, BACK};
            case BACK_PRIME:
                return new Turn[]{UP_PRIME, DOWN_PRIME, LEFT_PRIME, RIGHT_PRIME, BACK_PRIME, BACK_PRIME};
            case LEFT:
                return new Turn[]{LEFT, LEFT, FRONT, BACK, DOWN, UP};
            case LEFT_PRIME:
                return new Turn[]{LEFT_PRIME, LEFT_PRIME, FRONT_PRIME, BACK_PRIME, DOWN_PRIME, UP_PRIME};

            case MIDDLE:
                return new Turn[]{MIDDLE, MIDDLE, STANDING_PRIME, STANDING, EQUATORIAL_PRIME, EQUATORIAL};
            case MIDDLE_PRIME:
                return new Turn[]{MIDDLE_PRIME, MIDDLE_PRIME, STANDING, STANDING_PRIME, EQUATORIAL, EQUATORIAL_PRIME};
            case EQUATORIAL:
                return new Turn[]{STANDING, STANDING_PRIME, EQUATORIAL, EQUATORIAL, MIDDLE, MIDDLE_PRIME};
            case EQUATORIAL_PRIME:
                return new Turn[]{STANDING_PRIME, STANDING, EQUATORIAL_PRIME, EQUATORIAL_PRIME, MIDDLE_PRIME, MIDDLE};
            case STANDING:
                return new Turn[]{EQUATORIAL_PRIME, EQUATORIAL, MIDDLE, MIDDLE_PRIME, STANDING, STANDING};
            case STANDING_PRIME:
                return new Turn[]{EQUATORIAL, EQUATORIAL_PRIME, MIDDLE_PRIME, MIDDLE, STANDING_PRIME, STANDING_PRIME};

            case X:
                return new Turn[]{X, X, Z_PRIME, Z, Y, Y_PRIME};
            case X_PRIME:
                return new Turn[]{X_PRIME, X_PRIME, Z, Z_PRIME, Y_PRIME, Y};
            case Y:
                return new Turn[]{Z, Z_PRIME, Y, Y, X_PRIME, X};
            case Y_PRIME:
                return new Turn[]{Z_PRIME, Z, Y_PRIME, Y_PRIME, X, X_PRIME};
            case Z:
                return new Turn[]{Y_PRIME, Y, X, X_PRIME, Z, Z};
            case Z_PRIME:
                return new Turn[]{Y, Y_PRIME, X_PRIME, X, Z_PRIME, Z_PRIME};

            case UP_WIDE:
                return new Turn[]{FRONT_WIDE, BACK_WIDE, UP_WIDE, UP_WIDE, LEFT_WIDE, RIGHT_WIDE};
            case UP_WIDE_PRIME:
                return new Turn[]{FRONT_WIDE_PRIME, BACK_WIDE_PRIME, UP_WIDE_PRIME, UP_WIDE_PRIME, LEFT_WIDE_PRIME, RIGHT_WIDE_PRIME};
            case FRONT_WIDE:
                return new Turn[]{DOWN_WIDE, UP_WIDE, RIGHT_WIDE, LEFT_WIDE, FRONT_WIDE, FRONT_WIDE};
            case FRONT_WIDE_PRIME:
                return new Turn[]{DOWN_WIDE_PRIME, UP_WIDE_PRIME, RIGHT_WIDE_PRIME, LEFT_WIDE_PRIME, FRONT_WIDE_PRIME, FRONT_WIDE_PRIME};
            case RIGHT_WIDE:
                return new Turn[]{RIGHT_WIDE, RIGHT_WIDE, BACK_WIDE, FRONT_WIDE, UP_WIDE, DOWN_WIDE};
            case RIGHT_WIDE_PRIME:
                return new Turn[]{RIGHT_WIDE_PRIME, RIGHT_WIDE_PRIME, BACK_WIDE_PRIME, FRONT_WIDE_PRIME, UP_WIDE_PRIME, DOWN_WIDE_PRIME};

            case DOWN_WIDE:
                return new Turn[]{BACK_WIDE, FRONT_WIDE, DOWN_WIDE, DOWN_WIDE, RIGHT_WIDE, LEFT_WIDE};
            case DOWN_WIDE_PRIME:
                return new Turn[]{BACK_WIDE_PRIME, FRONT_WIDE_PRIME, DOWN_WIDE_PRIME, DOWN_WIDE_PRIME, RIGHT_WIDE_PRIME, LEFT_WIDE_PRIME};
            case BACK_WIDE:
                return new Turn[]{UP_WIDE, DOWN_WIDE, LEFT_WIDE, RIGHT_WIDE, BACK_WIDE, BACK_WIDE};
            case BACK_WIDE_PRIME:
                return new Turn[]{UP_WIDE_PRIME, DOWN_WIDE_PRIME, LEFT_WIDE_PRIME, RIGHT_WIDE_PRIME, BACK_WIDE_PRIME, BACK_WIDE_PRIME};
            case LEFT_WIDE:
                return new Turn[]{LEFT_WIDE, LEFT_WIDE, FRONT_WIDE, BACK_WIDE, DOWN_WIDE, UP_WIDE};
            case LEFT_WIDE_PRIME:
                return new Turn[]{LEFT_WIDE_PRIME, LEFT_WIDE_PRIME, FRONT_WIDE_PRIME, BACK_WIDE_PRIME, DOWN_WIDE_PRIME, UP_WIDE_PRIME};
            default:
                return null;
        }
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

    public boolean isCubeRotation() {
        return this.ordinal() >= 18 && this.ordinal() < 24;
    }

    public boolean isSliceTurn() {
        return this.ordinal() >= 12 && this.ordinal() < 18;
    }

    public boolean isWideTurn() {
        return this.ordinal() >= 24 && this.ordinal() < 36;
    }

    /**
     * @return the axis on which the Turn operates on
     */
    public Axis getAxis() {
        return axis;
    }

    public Turn translateTurn(Turn translation) {
        if (!translation.isCubeRotation())
            return null;
        return this.lookUpTranslations()[translation.ordinal() - 18];
    }

    public Turn mirrorTurn(Turn translation) {
        return this.lookupMirrors()[translation.getAxis().ordinal()];
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
    Piece[] getTarget() {
        return this.target;
    }

    /**
     * @return the rotation applied to each sticker upon cycling.
     */
    int[] getRotation() {
        return this.rotation;
    }
}
