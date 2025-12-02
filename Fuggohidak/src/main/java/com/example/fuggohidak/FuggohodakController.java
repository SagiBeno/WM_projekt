package com.example.fuggohidak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FuggohodakController {
    public ArrayList<Fuggohid> fuggohidak = new ArrayList<>();
    @FXML public VBox hidakSzama_VBox;
    @FXML public ListView<String> listview;
    @FXML public TextField hely_Textfield;
    @FXML public TextField orszag_Textfield;
    @FXML public TextField hossz_Textfield;
    @FXML public TextField ev_Textfield;
    @FXML public RadioButton elotti_RadioButton;
    @FXML public RadioButton utani_RadioButton;
    @FXML public TextField hidakSzama_TextField;

    public ArrayList<Fuggohid> filereader(String filename) throws FileNotFoundException {
        ArrayList<Fuggohid> hidak = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            //Első sor kihagyása
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                int helyezes = Integer.parseInt(parts[0].trim());
                String hid = parts[1].trim();
                String hely = parts[2].trim();
                String orszag = parts[3].trim();
                int hossz = Integer.parseInt(parts[4].trim());
                int ev = Integer.parseInt(parts[5].trim());

                hidak.add(new Fuggohid(helyezes, hid, hely, orszag, hossz, ev));
            }

            return hidak;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hidak;
    }

    @FXML
    public void handleOpen() {
        try {
            fuggohidak = filereader("fuggohidak.csv");

            if (!fuggohidak.isEmpty()) {
                hidakSzama_VBox.setDisable(false);

                ObservableList<String> listviewData = FXCollections.observableArrayList();

                for (Fuggohid fuggohid : fuggohidak) {
                    listviewData.add(fuggohid.getHid());
                }

                listview.setItems(listviewData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleTextFields () {
        String hid = listview.getSelectionModel().getSelectedItem();

        for (Fuggohid fuggohid : fuggohidak) {
            if (fuggohid.getHid().equalsIgnoreCase(hid)) {
                hely_Textfield.setText(fuggohid.getHely());
                orszag_Textfield.setText(fuggohid.getOrszag());
                hossz_Textfield.setText(String.valueOf(fuggohid.getHossz()));
                ev_Textfield.setText(String.valueOf(fuggohid.getEv()));
            }
        }
    }

    @FXML
    public void handleRadioButtons () {
        int darab = 0;

        if (elotti_RadioButton.isSelected()) {
            for (Fuggohid fuggohid : fuggohidak) {
                if (fuggohid.getEv() < 2000) {
                    darab++;
                }
            }

            hidakSzama_TextField.setText(String.valueOf(darab));
        }

        if (utani_RadioButton.isSelected()) {
            for (Fuggohid fuggohid : fuggohidak) {
                if (fuggohid.getEv() >= 2000) {
                    darab++;
                }
            }

            hidakSzama_TextField.setText(String.valueOf(darab));
        }
    }


}