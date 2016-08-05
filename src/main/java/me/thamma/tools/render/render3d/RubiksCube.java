package me.thamma.tools.render.render3d;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import me.thamma.cube.model.*;
import me.thamma.utils.CubeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple implementation of the Rubik's cube using JavaFX 3D
 * http://stackoverflow.com/questions/34001900/how-to-render-3d-graphics-properly
 *
 * @author JosePereda
 */
public class RubiksCube extends Application {

    public static final float RED = 0.5f / 7f;
    public static final float GREEN = 1.5f / 7f;
    public static final float BLUE = 2.5f / 7f;
    public static final float YELLOW = 3.5f / 7f;
    public static final float ORANGE = 4.5f / 7f;
    public static final float WHITE = 5.5f / 7f;
    public static final float GREY = 6.5f / 7f;

    public static final float Y_UP = BLUE;
    public static final float Y_FRONT = RED;
    public static final float Y_RIGHT = YELLOW;
    public static final float Y_DOWN = GREEN;
    public static final float Y_BACK = ORANGE;
    public static final float X_LEFT = WHITE;
    public static final float X_NONE = GREY;

    private static Point3D pFLD = new Point3D(-1.1, 1.1, -1.1);
    private static Point3D pFD = new Point3D(0, 1.1, -1.1);
    private static Point3D pFRD = new Point3D(1.1, 1.1, -1.1);
    private static Point3D pFL = new Point3D(-1.1, 0, -1.1);
    private static Point3D pF = new Point3D(0, 0, -1.1);
    private static Point3D pFR = new Point3D(1.1, 0, -1.1);
    private static Point3D pFLU = new Point3D(-1.1, -1.1, -1.1);
    private static Point3D pFU = new Point3D(0, -1.1, -1.1);
    private static Point3D pFRU = new Point3D(1.1, -1.1, -1.1);

    private static Point3D pCLD = new Point3D(-1.1, 1.1, 0);
    private static Point3D pCD = new Point3D(0, 1.1, 0);
    private static Point3D pCRD = new Point3D(1.1, 1.1, 0);
    private static Point3D pCL = new Point3D(-1.1, 0, 0);
    private static Point3D pC = new Point3D(0, 0, 0);
    private static Point3D pCR = new Point3D(1.1, 0, 0);
    private static Point3D pCLU = new Point3D(-1.1, -1.1, 0);
    private static Point3D pCU = new Point3D(0, -1.1, 0);
    private static Point3D pCRU = new Point3D(1.1, -1.1, 0);

    private static Point3D pBLD = new Point3D(-1.1, 1.1, 1.1);
    private static Point3D pBD = new Point3D(0, 1.1, 1.1);
    private static Point3D pBRD = new Point3D(1.1, 1.1, 1.1);
    private static Point3D pBL = new Point3D(-1.1, 0, 1.1);
    private static Point3D pB = new Point3D(0, 0, 1.1);
    private static Point3D pBR = new Point3D(1.1, 0, 1.1);
    private static Point3D pBLU = new Point3D(-1.1, -1.1, 1.1);
    private static Point3D pBU = new Point3D(0, -1.1, 1.1);
    private static Point3D pBRU = new Point3D(1.1, -1.1, 1.1);

    private int[] FLD;
    private int[] FD;
    private int[] FRD;
    private int[] FL;
    private int[] F;
    private int[] FR;
    private int[] FLU;
    private int[] FU;
    private int[] FRU;

    private int[] CLD;
    private int[] CD;
    private int[] CRD;
    private int[] CL;
    private int[] C;
    private int[] CR;
    private int[] CLU;
    private int[] CU;
    private int[] CRU;

    private int[] BLD;
    private int[] BD;
    private int[] BRD;
    private int[] BL;
    private int[] B;
    private int[] BR;
    private int[] BLU;
    private int[] BU;
    private int[] BRU;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;

    private List<int[]> patternFaceF;
    private List<Point3D> pointsFaceF;

    private Cube cube;

    private Rotate rotateX;
    private Rotate rotateY;

    private boolean prime;
    private boolean blockinputs = false;

    @Override
    public void start(Stage primaryStage) {
        prime = false;
        cube = Cube.randomCube();
        //cube.turn(new Algorithm("[[RBU: RU' RU RU RU' R'U' R2], x2 y'] [B2: R U2 R' U' R U' R' L' U2 L U L' U L]"));
        Group sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, 600, 600, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-12);
        scene.setCamera(camera);
        rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.CONTROL)
                prime ^= true;
            //System.out.println(prime);
        });
        scene.setOnKeyTyped(e -> {
            if (blockinputs)
                return;
            Algorithm a = Algorithm.fromScramble("" + e.getCharacter().toUpperCase() + (prime ? "'" : ""));
            cube.turn(a);
            sceneRoot.getChildren().clear();
            loadCube(cube);
            renderCube(scene, sceneRoot, primaryStage);
            //int sum = 0;
            //for (Piece piece: Piece.corners)
            //   sum += cube.getPiece(piece)[3];
            //System.out.println("par: "+sum);
            //System.out.println("par % 3: "+(sum % 3));

        });
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        loadCube(cube);
        renderCube(scene, sceneRoot, primaryStage);
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                String s = scanner.nextLine();
                if (blockinputs) continue;
                if (s.equals("solve")) {
                    this.cube = new Cube();
                } else if (s.equals("solution")) {
                    System.out.printf("Computing solution...\n");
                    System.out.printf("> %s\n", CubeUtils.anySolve(this.cube));
                    continue;
                } else if (s.equals("simulation2")) {
                    blockinputs = true;
                    if (this.cube.isSolved())
                        this.cube = Cube.randomCube();
                    new Thread(() -> {
                        Random random = new Random();
                        while (!this.cube.matches("(.)45...llllll")) {
                            this.cube.turn(Turn.values()[random.nextInt(12)]);
                            Platform.runLater(() -> {
                                loadCube(cube);
                                renderCube(scene, sceneRoot, primaryStage);
                            });
                            try {
                                Thread.sleep(25);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        blockinputs = false;
                    }).start();
                } else if (s.equals("simulation")) {
                    blockinputs = true;
                    if (this.cube.isSolved()) this.cube = Cube.randomCube();
                    Algorithm algorithm = Algorithm.fromCube(this.cube);
                    new Thread(() -> {
                        for (Turn t : algorithm) {
                            this.cube.turn(t);
                            Platform.runLater(() -> {
                                loadCube(cube);
                                renderCube(scene, sceneRoot, primaryStage);
                            });
                            try {
                                Thread.sleep(750);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        blockinputs = false;
                    }).start();
                } else {
                    Algorithm algorithm = Algorithm.fromScramble(s);
                    this.cube.turn(algorithm);
                    System.out.println(this.cube.matches(".o.o.o.o.(.)18.o.o.o.o.(.)18"));
                }
                Platform.runLater(() -> {
                    loadCube(cube);
                    renderCube(scene, sceneRoot, primaryStage);
                });
            }
        }).start();
    }

    private void renderCube(Scene scene, Group sceneRoot, Stage primaryStage) {

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("/palette.png")));

        Group meshGroup = new Group();

        AtomicInteger cont = new AtomicInteger();
        patternFaceF.forEach(p -> {
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(p));
            meshP.setMaterial(mat);
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        });

        meshGroup.getTransforms().addAll(rotateX, rotateY);

        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));

        scene.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        scene.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle() - (mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle() + (mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });

        primaryStage.setTitle("Simple Rubik's Cube - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static final int GRAY = 6;

    public void loadCube(Cube cube) {
        FLD = new int[]{-1 + cube.getColor(Sticker.FDL), GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.LFD), -1 + cube.getColor(Sticker.DLF)};
        FD = new int[]{-1 + cube.getColor(Sticker.FD), GRAY, GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.DF)};
        FRD = new int[]{-1 + cube.getColor(Sticker.FRD), -1 + cube.getColor(Sticker.RDF), GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.DFR)};
        FL = new int[]{-1 + cube.getColor(Sticker.FL), GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.LF), GRAY};
        F = new int[]{-1 + cube.getColor(Sticker.F), GRAY, GRAY, GRAY, GRAY, GRAY};
        FR = new int[]{-1 + cube.getColor(Sticker.FR), -1 + cube.getColor(Sticker.RF), GRAY, GRAY, GRAY, GRAY};
        FLU = new int[]{-1 + cube.getColor(Sticker.FLU), GRAY, -1 + cube.getColor(Sticker.UFL), GRAY, -1 + cube.getColor(Sticker.LUF), GRAY};
        FU = new int[]{-1 + cube.getColor(Sticker.FU), GRAY, -1 + cube.getColor(Sticker.UF), GRAY, GRAY, GRAY};
        FRU = new int[]{-1 + cube.getColor(Sticker.FUR), -1 + cube.getColor(Sticker.RFU), -1 + cube.getColor(Sticker.URF), GRAY, GRAY, GRAY};

        CLD = new int[]{GRAY, GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.LD), -1 + cube.getColor(Sticker.DL)};
        CD = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.D)};
        CRD = new int[]{GRAY, -1 + cube.getColor(Sticker.RD), GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.DR)};
        CL = new int[]{GRAY, GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.L), GRAY};
        C = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, GRAY};
        CR = new int[]{GRAY, -1 + cube.getColor(Sticker.R), GRAY, GRAY, GRAY, GRAY};
        CLU = new int[]{GRAY, GRAY, -1 + cube.getColor(Sticker.UL), GRAY, -1 + cube.getColor(Sticker.LU), GRAY};
        CU = new int[]{GRAY, GRAY, -1 + cube.getColor(Sticker.U), GRAY, GRAY, GRAY};
        CRU = new int[]{GRAY, -1 + cube.getColor(Sticker.RU), -1 + cube.getColor(Sticker.UR), GRAY, GRAY, GRAY};
// F   R   U   B   L   D
        BLD = new int[]{GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.BLD), -1 + cube.getColor(Sticker.LDB), -1 + cube.getColor(Sticker.DBL)};
        BD = new int[]{GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.BD), GRAY, -1 + cube.getColor(Sticker.DB)};
        BRD = new int[]{GRAY, -1 + cube.getColor(Sticker.RBD), GRAY, -1 + cube.getColor(Sticker.BDR), GRAY, -1 + cube.getColor(Sticker.DRB)};
        BL = new int[]{GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.BL), -1 + cube.getColor(Sticker.LB), GRAY};
        B = new int[]{GRAY, GRAY, GRAY, -1 + cube.getColor(Sticker.B), GRAY, GRAY};
        BR = new int[]{GRAY, -1 + cube.getColor(Sticker.RB), GRAY, -1 + cube.getColor(Sticker.BR), GRAY, GRAY};
        BLU = new int[]{GRAY, GRAY, -1 + cube.getColor(Sticker.ULB), -1 + cube.getColor(Sticker.BUL), -1 + cube.getColor(Sticker.LBU), GRAY};
        BU = new int[]{GRAY, GRAY, -1 + cube.getColor(Sticker.UB), -1 + cube.getColor(Sticker.BU), GRAY, GRAY};
        BRU = new int[]{GRAY, -1 + cube.getColor(Sticker.RUB), -1 + cube.getColor(Sticker.UBR), -1 + cube.getColor(Sticker.BRU), GRAY, GRAY};

        patternFaceF = Arrays.asList(
                FLD, FD, FRD, FL, F, FR, FLU, FU, FRU,
                CLD, CD, CRD, CL, C, CR, CLU, CU, CRU,
                BLD, BD, BRD, BL, B, BR, BLU, BU, BRU);

        pointsFaceF = Arrays.asList(
                pFLD, pFD, pFRD, pFL, pF, pFR, pFLU, pFU, pFRU,
                pCLD, pCD, pCRD, pCL, pC, pCR, pCLU, pCU, pCRU,
                pBLD, pBD, pBRD, pBL, pB, pBR, pBLU, pBU, pBRU);
    }

    private TriangleMesh createCube(int[] face) {
        TriangleMesh m = new TriangleMesh();

        // POINTS
        m.getPoints().addAll(
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f
        );

        // TEXTURES
        m.getTexCoords().addAll(
                Y_UP, 0.5f,
                Y_FRONT, 0.5f,
                Y_RIGHT, 0.5f,
                Y_DOWN, 0.5f,
                Y_BACK, 0.5f,
                X_LEFT, 0.5f,
                X_NONE, 0.5f
        );

        // FACES
        m.getFaces().addAll(
                2, face[0], 3, face[0], 6, face[0],      // F
                3, face[0], 7, face[0], 6, face[0],

                0, face[1], 1, face[1], 2, face[1],      // R
                2, face[1], 1, face[1], 3, face[1],

                1, face[2], 5, face[2], 3, face[2],      // U
                5, face[2], 7, face[2], 3, face[2],

                0, face[3], 4, face[3], 1, face[3],      // B
                4, face[3], 5, face[3], 1, face[3],

                4, face[4], 6, face[4], 5, face[4],      // L
                6, face[4], 7, face[4], 5, face[4],

                0, face[5], 2, face[5], 4, face[5],      // D
                2, face[5], 6, face[5], 4, face[5]
        );

        return m;
    }

    public static void main(String[] args) {
        launch(args);
    }

}