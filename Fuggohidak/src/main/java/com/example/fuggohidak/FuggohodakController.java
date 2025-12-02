package com.example.fuggohidak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FuggohodakController {
    public ArrayList<Fuggohid> fuggohidak = new ArrayList<>();
    @FXML public VBox hidakSzama_VBox;
    @FXML public ListView<String> listview;

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
}