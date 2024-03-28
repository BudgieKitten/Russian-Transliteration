package com.example.project3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class RusToLatinGUI extends Application {
    private final Insets INSET_VALUE = new Insets(10, 11, 12, 13);
    private final Insets INSET_RB = new Insets(5, 5, 5, 5);
    private final Font FONT_VALUE = new Font(20);

    // Store values of these radio buttons for ConversionEngine.java
    private boolean checkName = false;
    private boolean checkSilent = true;

    @Override
    public void start(Stage stage) {
        // Main pane
        BorderPane pane = new BorderPane();

        // Switch Panes
        HBox paneRussianEnglish = new HBox();

        Label lbRussian = new Label("Russian");
        lbRussian.setFont(FONT_VALUE);
        lbRussian.setPadding(INSET_VALUE);
        Label lbEnglish = new Label("English");
        lbEnglish.setFont(FONT_VALUE);
        lbEnglish.setPadding(INSET_VALUE);
        paneRussianEnglish.setAlignment(Pos.CENTER);

        Button btSwitch = new Button("Switch");
        btSwitch.setPadding(INSET_RB);
        btSwitch.setGraphic(new ImageView("file:./src/main/images/swap2.png"));
        btSwitch.setContentDisplay(ContentDisplay.RIGHT);

        paneRussianEnglish.getChildren().addAll(lbRussian, btSwitch, lbEnglish);
        pane.setTop(paneRussianEnglish);

        // Pane for translating and displaying texts
        BorderPane paneTextArea = new BorderPane();

        pane.setCenter(paneTextArea);

        Line lineTop = new Line();
        lineTop.setStartX(0); lineTop.setStartY(100);
        lineTop.setEndX(1000); lineTop.setEndY(100);

        paneTextArea.setTop(lineTop);

        /*
        Options for choosing name, silent sound, resizing, upload document or only inputting text
         */
        HBox paneHBoxMain = new HBox(40);

        // Pane for Choosing name or not
        HBox paneChooseName = new HBox(5);

        ToggleGroup toggleName = new ToggleGroup();

        Label lbName = new Label("Name:");
        lbName.setPadding(INSET_RB);
        RadioButton rbNameTrue = new RadioButton("True");
        rbNameTrue.setPadding(INSET_RB);
        RadioButton rbNameFalse = new RadioButton("False");
        rbNameFalse.setPadding(INSET_RB);

        rbNameTrue.setToggleGroup(toggleName);
        rbNameFalse.setToggleGroup(toggleName);

        toggleName.selectToggle(rbNameFalse);

        paneChooseName.getChildren().addAll(lbName, rbNameTrue, rbNameFalse);
        paneHBoxMain.getChildren().add(paneChooseName);

        // Pane for choosing silent sound or not
        HBox paneSilentSound = new HBox(3);

        ToggleGroup toggleSilentSound = new ToggleGroup();

        Label lbSilent = new Label("Silent sound(Ь Ъ):");
        lbSilent.setPadding(INSET_RB);
        RadioButton rbSilentTrue = new RadioButton("True");
        rbSilentTrue.setPadding(INSET_RB);
        RadioButton rbSilentFalse = new RadioButton("False");
        rbSilentFalse.setPadding(INSET_RB);

        rbSilentTrue.setToggleGroup(toggleSilentSound);
        rbSilentFalse.setToggleGroup(toggleSilentSound);

        toggleSilentSound.selectToggle(rbSilentTrue);

        paneSilentSound.getChildren().addAll(lbSilent, rbSilentTrue, rbSilentFalse);
        paneHBoxMain.getChildren().add(paneSilentSound);

        // Resizing

        HBox paneResizing = new HBox(5);

        Label lbResizable = new Label("Resizable:");
        lbResizable.setPadding(INSET_RB);

        ToggleGroup toggleResizable = new ToggleGroup();

        RadioButton rbResizeTrue = new RadioButton("True");
        rbResizeTrue.setPadding(INSET_RB);
        RadioButton rbResizeFalse = new RadioButton("False");
        rbResizeFalse.setPadding(INSET_RB);
        rbResizeTrue.setToggleGroup(toggleResizable);
        rbResizeFalse.setToggleGroup(toggleResizable);

        toggleResizable.selectToggle(rbResizeTrue);

        paneResizing.getChildren().addAll(lbResizable, rbResizeTrue, rbResizeFalse);
        paneHBoxMain.getChildren().add(paneResizing);

        paneTextArea.setCenter(paneHBoxMain);

        // Upload documents
        HBox paneUpload = new HBox(15);

        Button btChooseText = new Button("Choose text");
        btChooseText.setPadding(INSET_RB);
        Text txtNote = new Text("Only .txt file please!");
        txtNote.setFont(FONT_VALUE);
        paneUpload.getChildren().addAll(btChooseText, txtNote);

        paneHBoxMain.getChildren().addAll(paneUpload);

        /*
        Pane for dealing with text area
         */
        HBox paneForTA = new HBox(2);

        TextArea taLeft = new TextArea();
        taLeft.setFont(FONT_VALUE);
        taLeft.setWrapText(true); taLeft.setEditable(true);
        taLeft.setPrefRowCount(50); //taLeft.setPrefColumnCount(43);

        TextArea taRight = new TextArea();
        taRight.setPadding(new Insets(0, 3, 0, 0));
        taRight.setFont(FONT_VALUE);
        taRight.setWrapText(true); taRight.setEditable(false);
        taRight.setPrefRowCount(50); //taRight.setPrefRowCount(50);

        paneForTA.getChildren().addAll(taLeft, taRight);

        pane.setBottom(paneForTA);

        /*
        Dealing with behavior and user's input
         */
        btSwitch.setOnAction(e -> {
            if (lbRussian.getText().equals("Russian")) {
                lbRussian.setText("English");
                lbEnglish.setText("Russian");
            } else {
                lbRussian.setText("Russian");
                lbEnglish.setText("English");
            }
        });
        // Check if user wants Name chosen
        rbNameTrue.setOnAction(e -> {
            checkName = true;
        });

        rbNameFalse.setOnAction(e -> {
            checkName = false;
        });

        // Check if user wants silent sound included
        rbSilentTrue.setOnAction(e -> {
            checkSilent = true;
        });

        rbSilentFalse.setOnAction(e -> {
            checkSilent = false;
        });

        // Choose file
        btChooseText.setOnAction(e -> {
            // To display on text area left
            String output = "";

            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose File to read");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(stage);

                Scanner input = new Scanner(selectedFile);
                while (input.hasNextLine()) {
                    output += input.nextLine();
                }
                input.close();
            }
            catch (NullPointerException ex) {
                // Do nothing because user clicks exit button of FileChooser
            }
            catch (IOException ex) {
                // Do nothing b/c this is only to satisfy Scanner checked exception
            }

            taLeft.setText(output);

            if (lbRussian.getText().equals("Russian")) {
                taRight.setText(ConversionEngine.ruToLatin(taLeft.getText(), checkName, checkSilent));
            } else {
                taRight.setText(ConversionEngine.latinToRu(taLeft.getText(), checkName, checkSilent));
            }

            taLeft.appendText("");
            taRight.appendText("");

        });

        // Check if resizable is true or false
        rbResizeFalse.setOnAction(e -> {
            stage.setResizable(false);
        });

        rbResizeTrue.setOnAction(e -> {
            stage.setResizable(true);
        });

        taLeft.setOnKeyReleased(e -> {
            if (lbRussian.getText().equals("Russian")) {
                taRight.setText(ConversionEngine.ruToLatin(taLeft.getText(), checkName, checkSilent));
            } else {
                taRight.setText(ConversionEngine.latinToRu(taLeft.getText(), checkName, checkSilent));
            }
            taLeft.appendText("");
            taRight.appendText("");
        });

        // Showing stages
        Scene scene = new Scene(pane, 1000, 500);
        stage.setTitle("Russian To Latin converter");
        stage.setScene(scene);
        stage.show();

        /*
        Set custom behavior
        Put here b/c it can only modify after stage.show() to get the real value.
        B/f that there is no value to work with. That's why
         */
        taLeft.requestFocus();
        paneRussianEnglish.setSpacing(scene.getWidth() / 5);

        // Dealing with behavior of the stage (minimize, maximize, whatever)
        stage.widthProperty().addListener(e -> {
            lineTop.setEndX(stage.getWidth());

            if (stage.isMaximized()) {
                paneRussianEnglish.setSpacing(scene.getWidth() / 2.7);
            } else if (scene.getWidth() >= 1000) {
                paneRussianEnglish.setSpacing(scene.getWidth() / 4.5);
            } else if (scene.getWidth() < 1000){
                paneRussianEnglish.setSpacing(scene.getWidth() / 5);
            }

            taLeft.setPrefColumnCount((int) (scene.getWidth() / 15) );
            taRight.setPrefColumnCount((int) (scene.getWidth() / 15) );
            // Dealing with unbalanced text area right side
            taRight.setPadding(new Insets(0, 8, 0, 0));
        });

    }
}
