package me.thamma.tools.commutator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import me.thamma.cube.Algorithm;
import me.thamma.cube.Sticker;
import me.thamma.cube.compiler.lexer.IllegalCharacterException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedEndOfLineException;
import me.thamma.cube.compiler.parser.expressions.Exceptions.UnexpectedTokenException;

import java.io.*;
import java.net.URL;
import java.util.*;

public class CommutatorGuiController implements Initializable {

    final String databasePath = System.getenv("LOCALAPPDATA") + "\\CommutatorHelper\\database.csv";

    @FXML
    private TextField field0;

    @FXML
    private TextField field1;

    @FXML
    private TextField field2;

    @FXML
    private TextField display;

    @FXML
    private Button button;

    @FXML
    private Label entry;

    @FXML
    private Label tick;


    private Map<Cycle, String> algorithmMap;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        loadAlgorithmMap();
        updateView();
        field0.setPromptText("first sticker");
        field1.setPromptText("second sticker");
        field2.setPromptText("third sticker");
        display.setPromptText("algorithm");
        button.setFocusTraversable(false);
        enterTrigger(field0);
        enterTrigger(field1);
        enterTrigger(field2);
        enterTrigger(display);
        field0.textProperty().addListener(e -> {
            upperCase(field0);
            maxLength(field0, 3);
            updateView();
        });
        field1.textProperty().addListener(e -> {
            upperCase(field1);
            maxLength(field1, 3);
            updateView();
        });
        field2.textProperty().addListener(e -> {
            upperCase(field2);
            maxLength(field2, 3);
            updateView();
        });
        entry.setOnMouseClicked(e -> {
            try {
                Algorithm a = new Algorithm(entry.getText());
                System.out.println(a);
                System.out.println(a.order());
                System.out.println(a.getCycle());
            } catch (UnexpectedTokenException e1) {
                e1.printStackTrace();
            } catch (IllegalCharacterException e1) {
                e1.printStackTrace();
            } catch (UnexpectedEndOfLineException e1) {
                e1.printStackTrace();
            }
        });
        display.textProperty().addListener(e -> {
            updateView();
            display.setStyle((Algorithm.isAlgorithm(display.getText()) && display.getText().length() != 0) ? "-fx-text-fill: #007f00;" : "-fx-text-fill: #000000;");
            try {
                Algorithm alg = new Algorithm(display.getText());
                alg = alg.simplify();
                Tooltip tt = new Tooltip(alg.toString());
                display.setTooltip(tt);
                System.out.println(alg);
            } catch (UnexpectedTokenException | IllegalCharacterException | UnexpectedEndOfLineException e1) {
            }

        });
        button.setOnAction((e) -> buttonClick());
    }

    //[R'D',LDL':U']
    private void enterTrigger(TextField field) {
        field.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonClick();
            }
        });
    }

    private void maxLength(TextField field, int maxLength) {
        if (field.getText().length() > maxLength) {
            String s = field.getText().substring(0, maxLength);
            field.setText(s);
        }
    }

    private void upperCase(TextField field) {
        if (field.getText().length() == 0)
            return;
        if (!field.getText().equals(field.getText().toUpperCase()))
            field.setText(field.getText().toUpperCase());
    }

    private void buttonClick() {
        if (button.isDisabled()) return;
        //
        //  Functionality
        //
        String value = String.join(",", field0.getText(), field1.getText(), field2.getText());
        Cycle c = new Cycle(value);
        String buttonText = "";
        if (algorithmMap.containsKey(c)) {
            buttonText = "Algorithm overridden";
        } else
            buttonText = "Algorithm saved";
        algorithmMap.put(c, display.getText());
        saveAlgorithmMap();
        updateView();
        button.setDisable(true);
        button.setText(buttonText);
    }

    private void updateView() {
        if (field0.getText().length() == 0) {
            field0.setStyle("-fx-text-fill: #000000;");
            field1.setStyle("-fx-text-fill: #000000;");
            field2.setStyle("-fx-text-fill: #000000;");
            button.setDisable(true);
            button.setText("Save algorithm");
            entry.setText("");
        } else if (field0.getText().length() <= 3) {
            if (field0.getText().length() == 0 || field1.getText().length() == 0 || field2.getText().length() == 0) {
                field0.setStyle("-fx-text-fill: #000000;");
                field1.setStyle("-fx-text-fill: #000000;");
                field2.setStyle("-fx-text-fill: #000000;");
                button.setDisable(true);
                button.setText("Save algorithm");
                entry.setText("");
            } else if (field0.getText().equals(field1.getText()) || field1.getText().equals(field2.getText()) || field0.getText().equals(field2.getText())) {
                field0.setStyle("-fx-text-fill: #ff0000;");
                field1.setStyle("-fx-text-fill: #ff0000;");
                field2.setStyle("-fx-text-fill: #ff0000;");
                button.setDisable(true);
                button.setText("Save algorithm");
                entry.setText("");
            } else {
                if (field0.getText().length() != field1.getText().length() || field1.getText().length() != field2.getText().length() || field0.getText().length() != field2.getText().length()) {
                    field0.setStyle("-fx-text-fill: #ff0000;");
                    field1.setStyle("-fx-text-fill: #ff0000;");
                    field2.setStyle("-fx-text-fill: #ff0000;");
                    button.setDisable(true);
                    button.setText("Save algorithm");
                    entry.setText("");
                } else {
                    if (Sticker.isValidSticker(field0.getText()) && Sticker.isValidSticker(field1.getText()) && Sticker.isValidSticker(field2.getText())) {
                        field0.setStyle("-fx-text-fill: #000000;");
                        field1.setStyle("-fx-text-fill: #000000;");
                        field2.setStyle("-fx-text-fill: #000000;");
                        String value = String.join(",", field0.getText(), field1.getText(), field2.getText());
                        Cycle c = new Cycle(value);
                        if (algorithmMap.containsKey(c)) {
                            button.setText("Override algorithm");
                            entry.setText(algorithmMap.get(c));
                        } else
                            button.setText("Save algorithm");
                        button.setDisable(display.getText().length() == 0 || display.getText().equals(entry.getText()));
                    } else {
                        field0.setStyle(Sticker.isValidSticker(field0.getText()) ? "-fx-text-fill: #000000;" : "-fx-text-fill: #ff0000;");
                        field1.setStyle(Sticker.isValidSticker(field1.getText()) ? "-fx-text-fill: #000000;" : "-fx-text-fill: #ff0000;");
                        field2.setStyle(Sticker.isValidSticker(field2.getText()) ? "-fx-text-fill: #000000;" : "-fx-text-fill: #ff0000;");
                        button.setDisable(true);
                        button.setText("Save algorithm");
                        entry.setText("");
                    }
                }
            }
        }
        try {
            Algorithm alg = new Algorithm(entry.getText());
            Cycle c = new Cycle(field0.getText() + "," + field1.getText() + "," + field2.getText());
            System.out.println(alg);
            System.out.println(alg.getCycle());
            System.out.println(c);
            System.out.println(c.equals(alg.getCycle()));
            tick.setVisible(c.equals(alg.getCycle()));
        } catch (UnexpectedTokenException e) {
            e.printStackTrace();
        } catch (IllegalCharacterException e) {
            e.printStackTrace();
        } catch (UnexpectedEndOfLineException e) {
            e.printStackTrace();
        }
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

