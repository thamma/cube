package me.thamma.cube.model;

import me.thamma.cube.model.regex.CubeRegex;
import me.thamma.tools.render.render2d.PixMap;
import me.thamma.tools.render.render2d.Render2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Cube {

    /*
     * Encoding:
     *   [c3, c2, c1, rot] where c3,c2,c1 are the according colors, rot the rotation
     *
     * Colors:
     *   U F R D B L  null
     *   1 2 3 4 5 6   0
     * (null color is provided if the piece consists of less than three facelets)
     * leading zeros are omitted to prevent confusion with octal numbers
     *
     * Rotation:
     * implemented, hard recognition.
     * edge rotation is ZZ edge orientation
     */

    private String[] sideStrings = {null, "U", "F", "R", "D", "B", "L"};
    private Map<Sticker, Sticker> reverseLookupTable = null;

    private int[] pieces;
    //
    //  constructors and factories
    //

    /**
     * The default constructor. Initializes as a solved Cube.
     */
    public Cube() {
        this(CubeConstants.Cubes.DEFAULT_CUBE.clone());
    }

    /**
     *
     * @return The internal cube representation
     */
    protected int[] getPieces() {
        return pieces;
    }

    /**
     * Constructs a cube from the intern piece definition.
     *
     * @param pieces the integral piece encoding of the cube
     */
    protected Cube(int[] pieces) {
        this.pieces = pieces;
    }

    /**
     * Creates a new Cube to which an initial Algorithm is applied
     *
     * @param algorithm the algorithm to initialize the cube with.
     */
    public Cube(Algorithm algorithm) {
        this();
        this.turn(algorithm);
    }

    /**
     * Creates a new Cube to which an initial Algorithm is applied
     *
     * @param scramble the String representation of the algorithm to initialize the cube with.
     */
    public static Cube fromScramble(String scramble) {
        return new Cube(Algorithm.fromScramble(scramble));
    }

    private static Cube fromStickers(Piece[] pieces, int[] rotations) {
        List<String> list = new ArrayList<>(Piece.values().length);
        for (int i = 0; i < pieces.length; i++) {
            String temp = "";
            for (char c : pieces[i].name().toCharArray())
                temp = new int[]{5, 4, 2, 6, 3, 1}[(Arrays.binarySearch("BDFLRU".toCharArray(), c))] + temp;
            temp += rotations[i];
            list.add(temp);
        }
        int[] construct = list.stream().mapToInt(Integer::parseInt).toArray();
        return new Cube(construct);
    }

    public static Cube randomCube() {
        Random random = new Random();
        // setup corner permutation array
        List<Integer> list = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] cornerPermutation = list.stream().mapToInt(Integer::intValue).toArray();
        // setup edge permutation array
        for (int i = 0; i < 4; i++) {
            list.add(i + 8);
        }
        Collections.shuffle(list);
        int[] edgePermutation = list.stream().mapToInt(Integer::intValue).toArray();
        // init cube
        Piece[] pieces = new Piece[26];
        int[] cornerSlots = {0, 2, 6, 8, 17, 19, 23, 25};
        int[] edgeSlots = {1, 3, 5, 7, 9, 11, 13, 15, 18, 20, 22, 24};
        int[] centerSlots = {4, 10, 12, 14, 16, 21};
        for (int i = 0; i < cornerSlots.length; i++)
            pieces[cornerSlots[i]] = Piece.values()[cornerSlots[cornerPermutation[i]]];
        for (int i = 0; i < edgeSlots.length; i++)
            pieces[edgeSlots[i]] = Piece.values()[edgeSlots[edgePermutation[i]]];
        for (int i = 0; i < centerSlots.length; i++)
            pieces[centerSlots[i]] = Piece.values()[centerSlots[i]];
        // append rotations
        int[] rotations = new int[26];
        for (int i = 0; i < cornerSlots.length; i++)
            rotations[cornerSlots[i]] = random.nextInt(3);
        for (int i = 0; i < edgeSlots.length; i++)
            rotations[edgeSlots[i]] = random.nextInt(2);
        Cube out = Cube.fromStickers(pieces, rotations);
        //fix parities
        while (out.hasOrientationParity()) {
            out.pieces[0] = out.pieces[0] / 10 * 10 + ((out.pieces[0] % 10 + 1) % 3);
            out.pieces[1] = out.pieces[1] / 10 * 10 + ((out.pieces[1] % 10 + 1) % 2);
        }
        if (out.hasPermutationParity()) {
            int temp = out.pieces[0];
            out.pieces[0] = out.pieces[2];
            out.pieces[2] = temp;
        }
        return out;

    }

    //
    //  public methods
    //

    /**
     * Whether the cube is properly solved, i.e. it is equivalent to a solved cube.
     *
     * @return whether the cube is solved
     */
    public boolean isSolved() {
        return this.equals(new Cube());
    }

    /**
     * Applies a rotation defined by a Turn to the current cube.
     *
     * @param turn the turn to be applied to the cube
     * @return the reference to the current cube
     */
    public Cube turn(Turn turn) {
        if (turn.hasChildren()) {
            for (Turn child : turn.getChildren())
                turn(child);
        } else
            this.cyclePieces(turn.getOffset(), turn.getTarget(), turn.getRotation());
        return this;
    }

    public Cube turn(Algorithm a) {
        a.forEach(this::turn);
        return this;
    }
    
    public boolean canTurn(Turn t) {
        return true;
    }

    public boolean matches(String cubeRegex) {
        try {
            return CubeRegex.compile(cubeRegex).matches(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("parse error");
            return false;
        }
    }

    public Map<Sticker, Sticker> getReverseStickerLookup() {
        if (this.reverseLookupTable != null)
            return this.reverseLookupTable;
        HashMap<Sticker, Sticker> out = new HashMap<>();
        for (Sticker sticker: Sticker.values()) {
            Sticker target = this.getCurrentStickerAt(sticker);
            out.put(target, sticker);
        }
        this.reverseLookupTable = out;
        return this.reverseLookupTable;
    }



    public int getColor(Sticker s) {
        int[] piece = this.getPiece(s.getPiece());
        return piece[2 - (s.getRotation() + piece[3]) % order(piece)];
    }

    public Sticker getCurrentStickerAt(Sticker local) {
        String stickerString = "";
        stickerString += sideStrings[getColor(local)];
        if (local.toString().length() > 1)
            stickerString += sideStrings[getColor(local.rotate())];
        if (local.toString().length() > 2)
            stickerString += sideStrings[getColor(local.rotate().rotate())];
        return Sticker.valueOf(stickerString);
    }

    /**
     * @param piece
     * @return
     */
    public int[] getPiece(Piece piece) {
        return pieceToArray(this.pieces[piece.ordinal()]);
    }

    /**
     * Creates a new Cube Object representing the same cube
     *
     * @return a proper copy of the current cube
     */
    public Cube clone() {
        int[] a = new int[26];
        System.arraycopy(this.pieces, 0, a, 0, pieces.length);
        return new Cube(a);
    }

    /**
     * Rotates the current cube such, that its upper color center faces up and the front color center faces front
     *
     * @return the reference to the current cube
     */
    public Cube normalizeRotation() {
        Cube c;
        for (Algorithm alg : CubeConstants.Algorithms.cubeOrientations) {
            c = this.clone();
            c.turn(alg);
            if (c.getCurrentStickerAt(Sticker.U) == Sticker.U && c.getCurrentStickerAt(Sticker.F) == Sticker.F) {
                this.turn(alg);
                return this;
            }
        }
        System.out.println("Warning: normalizeRotation() found the cube to be invalid");
        return this;
    }
    // public static String[] cornerSolutions = {
    //         "y2","y","y'","",
    //         "xy2","xy","xy'","x",
    //         "z'y'","z'y2","z'","z'y",
    //         "","","","",
    //         "","","","",
    //         "","","",""
    // };

    // public static Sticker[] cornerStickers = {
    //         Sticker.ULB, Sticker.UBR, Sticker.UFL, Sticker.URF,
    //         Sticker.FLU, Sticker.FUR, Sticker.FDL, Sticker.FRD,
    //         Sticker.RFU, Sticker.RUB, Sticker.RDF, Sticker.RBD,
    //         Sticker.DLF, Sticker.DFR, Sticker.DBL, Sticker.DRB,
    //         Sticker.BRU, Sticker.BUL, Sticker.BDR, Sticker.BLD,
    //         Sticker.LBU, Sticker.LUF, Sticker.LDB, Sticker.LFD
    // };

    @Override
    public int hashCode() {
        return 31 * Arrays.hashCode(pieces);
    }

    /**
     * Returns whether this Cube is equivalent to another Object (Cube).
     *
     * @param o the Object to compare this cube to
     * @return true iff o is a Cube and there is a rotation in which the two Cubes are identical.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cube))
            return false;
        Cube local = this.clone().normalizeRotation();
        Cube ref = ((Cube) o).normalizeRotation();
        for (int i = 0; i < CubeConstants.Stickers.faceStickers.length; i++)
            for (int j = 0; j < CubeConstants.Stickers.faceStickers[i].length; j++) {
                if (local.getCurrentStickerAt(CubeConstants.Stickers.faceStickers[i][j]) != ref.getCurrentStickerAt(CubeConstants.Stickers.faceStickers[i][j]))
                    return false;
            }
        return true;
    }

    /**
     * @return the facelet definition as used by the 2-phase algorithms cube implementation
     */
    public String getFaceletDefinition() {
        String out = "";
        for (Sticker sticker : CubeConstants.Stickers.defaultFaceletDefinition) // defines the order
            out += this.getCurrentStickerAt(sticker).toString().substring(0, 1);
        return out;
    }

    /**
     * @param faceletTour The order of facelets to be joined
     * @return the facelet definition as used by the 2-phase algorithms cube implementation or null if the given tour was invalid
     */
    public String getFaceletDefinition(Sticker[] faceletTour) {
        if (faceletTour.length != 54) return null;
        String out = "";
        for (Sticker sticker : faceletTour)
            out += this.getCurrentStickerAt(sticker).toString().charAt(0);
        return out;
    }

    public boolean hasParity() {
        return this.hasPermutationParity() || this.hasOrientationParity();
    }

    public void printGrid(String filename) {

        Render2D render2D = new Render2D(this);
        PixMap pixMap = render2D.getPixmap().scale(50).border(20);

        BufferedImage img = new BufferedImage(pixMap.getWidth(), pixMap.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < pixMap.getHeight(); i++)
            for (int j = 0; j < pixMap.getWidth(); j++)
                img.setRGB(j, i, pixMap.getPixel(i, j).getRGB());

        try {
            ImageIO.write(img, "png", new File(filename + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //
    //  private methods
    //

    private boolean hasPermutationParity() {
        Cube cube = this.clone().normalizeRotation();
        Cycles cycles = new Cycles(cube);
        int count = 0;
        for (Cycle cycle : cycles)
            if (cycle.size() % 2 == 0)
                count++;
        return (count % 2 != 0);
    }

    private boolean hasOrientationParity() {
        int count = 0;
        for (Piece piece : CubeConstants.Pieces.corners) {
            int[] p = this.getPiece(piece);
            count += p[p.length - 1];
        }
        if (count % 3 != 0)
            return true;
        count = 0;
        for (Piece piece : CubeConstants.Pieces.edges) {
            int[] p = this.getPiece(piece);
            count += p[p.length - 1];
        }
        return count % 2 != 0;
    }

    /**
     * Rotates the given piece by the specified rotation. For example
     * (5612, 1) -> 5610
     *
     * @param movedPiece the integral encoding of the piece to be rotate7
     * @param rotation   the number of times the piece is supposed to be rotated clockwise
     * @return the integral encoding of the rotated piece
     */
    private static int rotate(int movedPiece, int rotation) {
        int[] arr = pieceToArray(movedPiece);
        int order = order(arr);
        arr[3] += rotation + order;
        arr[3] %= order;
        return (((arr[0] * 10) + arr[1]) * 10 + arr[2]) * 10 + arr[3];
    }

    /**
     * Splits an integer into an array of its digits. Note that String.join can be used to recover the numbers String representation.
     *
     * @param piece the integer to be split
     * @return the array containing the digits of the input integer
     */
    private static int[] pieceToArray(int piece) {
        String s = new String(new char[4 - ("" + piece).length()]).replace("\0", "0") + piece;
        int[] arr = s.chars().map(a -> a - '0').toArray();
        return arr;
    }

    /**
     * The order of the array representation of a pieces integral representation. Centers, edges and corners each have order 1, 2 or 3.
     *
     * @param pieceArray
     * @return
     */
    private static int order(int[] pieceArray) {
        return (pieceArray[0] == 0 ? 0 : 1) + (pieceArray[2] == 0 ? 0 : 1) + (pieceArray[1] == 0 ? 0 : 1);
    }

    /**
     * Performs a cycling of the pieces of the current cube given the raw data provided by a Turn.
     *
     * @param offset   the offset, how many pieces to skip in the current cycling (usually 2 such that corners,
     *                 edges and centers are each mapped to the same piece type).
     * @param target   the stickers to be cycled
     * @param rotation the rotation applied to each sticker upon cycling.
     */
    protected void cyclePieces(int offset, Piece[] target, int[] rotation) {
        offset = (offset + target.length) % target.length;
        int[] piecesClone = this.pieces.clone();
        for (int i = 0; i < target.length; i++) {
            Piece a = target[(i + offset) % target.length];
            piecesClone[a.ordinal()] = rotate(pieces[target[i].ordinal()], rotation[i]);
        }
        this.pieces = piecesClone;
    }

}
