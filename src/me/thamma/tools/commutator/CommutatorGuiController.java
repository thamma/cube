package me.thamma.tools.commutator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Sticker;
import me.thamma.utils.CubeUtils;

import java.io.*;
import java.net.URL;
import java.util.*;

public class CommutatorGuiController implements Initializable {

    final String databasePath = System.getenv("LOCALAPPDATA") + "\\CommutatorHelper\\database.csv";

    @FXML
    private TextField stickerTextField1, stickerTextField2, stickerTextField3;

    @FXML
    private TextField cycleTextField1, cycleTextField2, cycleTextField3;

    @FXML
    private TextField algorithmTextField1, algorithmTextField2;

    @FXML
    private Label algorithmLabel1, algorithmLabel2;

    @FXML
    private Label tickLabel1, tickLabel2;

    @FXML
    private Button button;

    private Map<Cycle, String> algorithmMap;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        loadAlgorithmMap();
        setupDefaultValues();
        setupStickerTextFieldListeners();
        setupAlgorithmTextFieldListeners();
        button.setOnAction(e -> buttonClick());
    }

    private void setupAlgorithmTextFieldListeners() {
        algorithmTextField1.textProperty().addListener(e -> {
            updateColorsAndAvailabilities2();
            if (CubeUtils.isValidAlgorithm(algorithmTextField1.getText()) && !algorithmTextField1.getText().equals("")) {
                Algorithm alg = null;
                try {
                    alg = new Algorithm(algorithmTextField1.getText());
                } catch (Exception e1) {
                }
                algorithmLabel1.setText(alg.simplify().toString());
            } else if (!algorithmLabel1.getText().equals("")) algorithmLabel1.setText("");
        });
        algorithmTextField2.textProperty().addListener(e -> {
            updateColorsAndAvailabilities2();
            if (CubeUtils.isValidAlgorithm(algorithmTextField2.getText()) && !algorithmTextField2.getText().equals("")) {
                Algorithm alg = null;
                try {
                    alg = new Algorithm(algorithmTextField2.getText());
                } catch (Exception e1) {
                }
                algorithmLabel2.setText(alg.simplify().toString());
            } else if (!algorithmLabel2.getText().equals("")) algorithmLabel2.setText("");
        });
    }

    private void enterButtonTrigger(TextField textField) {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonClick();
                updateColorsAndAvailabilities2();
            }
        });
    }

    private void buttonClick() {
        if (button.isDisabled()) return;
        Algorithm alg = null;
        try {
            alg = new Algorithm(algorithmTextField1.getText());
        } catch (Exception e) {
        }
        algorithmMap.put(alg.getCycle(), alg.toString());
        saveAlgorithmMap();
    }

    private void setupStickerTextFieldListeners() {
        updateColorsAndAvailabilities2();
        enterButtonTrigger(algorithmTextField1);
        enterButtonTrigger(stickerTextField1);
        enterButtonTrigger(stickerTextField2);
        enterButtonTrigger(stickerTextField3);

        stickerTextField1.textProperty().addListener(e -> updateColorsAndAvailabilities2());
        stickerTextField2.textProperty().addListener(e -> updateColorsAndAvailabilities2());
        stickerTextField3.textProperty().addListener(e -> updateColorsAndAvailabilities2());
    }

    private static final String BLACK_FILL = "-fx-text-fill: #000000;";
    private static final String RED_FILL = "-fx-text-fill: #932725;";
    private static final String GREEN_FILL = "-fx-text-fill: #34b136;";

    private void updateColorsAndAvailabilities2() {
        if (CubeUtils.isValidSticker(stickerTextField1.getText())
                && CubeUtils.isValidSticker(stickerTextField2.getText())
                && CubeUtils.isValidSticker(stickerTextField3.getText())) {
            Cycle stickerCycle = new Cycle(stickerTextField1.getText(), stickerTextField2.getText(), stickerTextField3.getText());
            //Proper stickers
            applyColorsAndAvailabilities(1);
            if (stickerCycle.isPossible()) {
                //legit cycle
                applyColorsAndAvailabilities(3);
                if (CubeUtils.isValidAlgorithm(algorithmTextField1.getText()) && !algorithmTextField1.getText().equals("")) {
                    //legit algorithm
                    applyColorsAndAvailabilities(5);
                    Algorithm alg = null;
                    try {
                        alg = new Algorithm(algorithmTextField1.getText());
                    } catch (Exception e) { //Never called as asserted above
                    }
                    if (alg.isCommutator()) {
                        //is commutator
                        applyColorsAndAvailabilities(7);
                        Cycle algCycle = alg.getCycle();
                        if (algCycle.equals(stickerCycle)) {
                            // Correct cycle
                            applyColorsAndAvailabilities(9);
                            if (algorithmMap.containsKey(algCycle)) {
                                applyColorsAndAvailabilities(11);
                            } else {
                                applyColorsAndAvailabilities(12);
                            }
                        } else {
                            applyColorsAndAvailabilities(10);
                            // not matching cycle but nice
                            if (algorithmMap.containsKey(algCycle)) {
                                applyColorsAndAvailabilities(13);
                            } else {
                                applyColorsAndAvailabilities(14);
                            }
                            //Save nontheless!
                        }
                    } else {
                        // is no commutator
                        applyColorsAndAvailabilities(8);
                    }
                    //Proper algorithm
                } else {
                    // improper alorithm
                    applyColorsAndAvailabilities(6);
                }

            } else {
                //illegal cycle
                applyColorsAndAvailabilities(4);
            }
        } else {
            //improper stickers
            applyColorsAndAvailabilities(2);
        }
    }

    private void applyColorsAndAvailabilities(int id) {
        switch (id) {
            case 1: {
                stickerTextField1.setStyle(BLACK_FILL);
                stickerTextField2.setStyle(BLACK_FILL);
                stickerTextField3.setStyle(BLACK_FILL);
                algorithmTextField1.setDisable(true);
                button.setDisable(true);
                tickLabel1.setVisible(false);
                tickLabel2.setVisible(false);
            }
            break;
            case 2: {
                stickerTextField1.setStyle(CubeUtils.isValidSticker(stickerTextField1.getText()) ? BLACK_FILL : RED_FILL);
                stickerTextField2.setStyle(CubeUtils.isValidSticker(stickerTextField2.getText()) ? BLACK_FILL : RED_FILL);
                stickerTextField3.setStyle(CubeUtils.isValidSticker(stickerTextField3.getText()) ? BLACK_FILL : RED_FILL);
                algorithmTextField1.setDisable(true);
                button.setDisable(true);
                tickLabel1.setVisible(false);
                tickLabel2.setVisible(false);
                if (!cycleTextField1.getText().equals(""))
                    cycleTextField1.setText("");
                if (!cycleTextField2.getText().equals(""))
                    cycleTextField2.setText("");
                if (!cycleTextField3.getText().equals(""))
                    cycleTextField3.setText("");
                if (!algorithmTextField2.getText().equals(""))
                    algorithmTextField2.setText("");
            }
            break;
            case 3: {
                stickerTextField1.setStyle(BLACK_FILL);
                stickerTextField2.setStyle(BLACK_FILL);
                stickerTextField3.setStyle(BLACK_FILL);
                algorithmTextField1.setDisable(false);
                button.setDisable(true);
                Cycle cycle = new Cycle(stickerTextField1.getText(), stickerTextField2.getText(), stickerTextField3.getText());
                if (algorithmMap.containsKey(cycle)) {
                    String mapEntry = algorithmMap.get(cycle);
                    if (!algorithmTextField2.getText().equals(mapEntry))
                        algorithmTextField2.setText(mapEntry);
                    if (CubeUtils.isValidAlgorithm(mapEntry)) {
                        Algorithm mapEntryAlgorithm = null;
                        try {
                            mapEntryAlgorithm = new Algorithm(mapEntry);
                        } catch (Exception e) {
                        }
                        if (mapEntryAlgorithm.isCommutator() && mapEntryAlgorithm.getCycle().equals(cycle)) {
                            tickLabel2.setVisible(true);
                        } else
                            tickLabel2.setVisible(false);
                    }
                }
            }
            break;
            case 4: {
                stickerTextField1.setStyle(RED_FILL);
                stickerTextField2.setStyle(RED_FILL);
                stickerTextField3.setStyle(RED_FILL);
                algorithmTextField1.setDisable(true);
                button.setDisable(true);
                tickLabel1.setVisible(false);
                tickLabel2.setVisible(false);
                if (!cycleTextField1.getText().equals(""))
                    cycleTextField1.setText("");
                if (!cycleTextField2.getText().equals(""))
                    cycleTextField2.setText("");
                if (!cycleTextField3.getText().equals(""))
                    cycleTextField3.setText("");
                if (!algorithmTextField2.getText().equals("")) algorithmTextField2.setText("");
            }
            break;
            case 5: {
                algorithmTextField1.setStyle(GREEN_FILL);
                button.setDisable(true);
            }
            break;
            case 6: {
                algorithmTextField1.setStyle(RED_FILL);
                button.setDisable(true);
                tickLabel1.setVisible(false);
                tickLabel2.setVisible(false);
                if (!cycleTextField1.getText().equals(""))
                    cycleTextField1.setText("");
                if (!cycleTextField2.getText().equals(""))
                    cycleTextField2.setText("");
                if (!cycleTextField3.getText().equals(""))
                    cycleTextField3.setText("");
            }
            break;
            case 7: {
                Algorithm alg = null;
                try {
                    alg = new Algorithm(algorithmTextField1.getText());
                } catch (Exception e) {
                }
                Cycle stickerCycle = new Cycle(stickerTextField1.getText(), stickerTextField2.getText(), stickerTextField3.getText());
                Cycle algCycle = alg.getCycle();
                cycleTextField1.setText(algCycle.s1.toString());
                cycleTextField2.setText(algCycle.s2.toString());
                cycleTextField3.setText(algCycle.s3.toString());
//                if (algorithmMap.containsKey(algCycle)) {
//                    if (!algorithmTextField2.getText().equals(algorithmMap.get(algCycle)))
//                        algorithmTextField2.setText(algorithmMap.get(algCycle));
//                } else {
//                    if (!algorithmTextField2.getText().equals(""))
//                        algorithmTextField2.setText("");
//                }
                button.setDisable(false);
            }
            break;
            case 8: {
                cycleTextField1.setText("");
                cycleTextField2.setText("");
                cycleTextField3.setText("");
                button.setDisable(true);
                tickLabel1.setVisible(false);
            }
            break;
            case 9: {
                tickLabel1.setVisible(true);
            }
            break;
            case 10: {
                tickLabel1.setVisible(false);
            }
            break;
            case 11: {
                button.setText("Override algorithm");
            }
            break;
            case 12: {
                button.setText("Save algorithm");
            }
            break;
            case 13: {
                button.setText("Override algorithm in database");
            }
            break;
            case 14: {
                button.setText("Add algorithm to database");
            }
            break;
            default:
        }
    }

    private void setupDefaultValues() {
        maxLength(stickerTextField1, 3);
        maxLength(stickerTextField2, 3);
        maxLength(stickerTextField3, 3);

        forceUpperCase(stickerTextField1);
        forceUpperCase(stickerTextField2);
        forceUpperCase(stickerTextField3);

        stickerTextField1.setText("");
        stickerTextField2.setText("");
        stickerTextField3.setText("");

        stickerTextField1.setPromptText("Sticker");
        stickerTextField2.setPromptText("Sticker");
        stickerTextField3.setPromptText("Sticker");

        maxLength(cycleTextField1, 3);
        maxLength(cycleTextField2, 3);
        maxLength(cycleTextField3, 3);

        forceUpperCase(cycleTextField1);
        forceUpperCase(cycleTextField2);
        forceUpperCase(cycleTextField3);

        cycleTextField1.setText("");
        cycleTextField2.setText("");
        cycleTextField3.setText("");

        algorithmTextField1.setText("");
        algorithmTextField2.setText("");

        algorithmTextField1.setPromptText("Enter algorithm");
        algorithmTextField2.setPromptText("Database algorithm");

        algorithmLabel1.setText("");
        algorithmLabel2.setText("");

        tickLabel1.setVisible(false);
        tickLabel2.setVisible(false);

        button.setText("Save algorithm");
        button.setDisable(true);
    }

    private void maxLength(TextField field, int maxLength) {
        field.textProperty().addListener(e -> {
            if (field.getText().length() > maxLength) {
                String s = field.getText().substring(0, maxLength);
                field.setText(s);
            }
        });
    }

    private void forceUpperCase(TextField field) {
        field.textProperty().addListener(e -> {
            if (field.getText().length() == 0)
                return;
            if (!field.getText().equals(field.getText().toUpperCase()))
                field.setText(field.getText().toUpperCase());
        });
    }

    private void loadAlgorithmMap() {
        this.algorithmMap = new HashMap<>();
        List<String> rawLines = loadFile(databasePath);
        for (String s : rawLines) {
            if (s.split(",").length < 3)
                System.exit(-1);
            String piece1 = s.split(",")[0];
            String piece2 = s.split(",")[1];
            String piece3 = s.split(",")[2];
            String key = String.join(",", piece1, piece2, piece3);
            String value = s.replaceFirst(key + ",", "");
            algorithmMap.put(new Cycle(key), value);
        }
    }

    private void saveAlgorithmMap() {
        List<String> rawLines = new ArrayList<>();
        for (Cycle key : this.algorithmMap.keySet()) {
            String value = this.algorithmMap.get(key);
            rawLines.add(key.toString() + "," + value);
        }
        saveFile(databasePath, rawLines);
    }


    public static List<String> loadFile(String subpath) {
        List<String> lines = new ArrayList<String>();
        File f = new File(subpath);
        if (f.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error loading file: \"" + e.getMessage()
                        + "\"");
            }

        } else {
            System.out.println("File \"" + subpath + "\" could not be found.");
        }
        return lines;
    }

    public static boolean saveFile(String subpath, List<String> lines) {
        File f = new File(subpath);
        f.mkdirs();
        if (f.exists()) {
            f.delete();
        }
        try {
            FileWriter writer;
            writer = new FileWriter(f);
            for (String s : lines) {
                writer.write(s + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: \"" + e.getMessage() + "\"");
            return false;
        }
    }

}

