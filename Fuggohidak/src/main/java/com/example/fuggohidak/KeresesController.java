package com.example.fuggohidak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class KeresesController implements Initializable {


    @FXML public ComboBox comboBox;
    @FXML public ListView<String> listview;
    @FXML public CheckBox checkbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleOpen();
    }

    @FXML
    public void handleExit () {
        FuggohodakController.search.close();
    }

    public void handleOpen() {
        ObservableList<String> options = FXCollections.observableArrayList();

        for (Fuggohid fuggohid : FuggohodakController.fuggohidak) {

            if (!options.contains(fuggohid.getOrszag())) options.add(fuggohid.getOrszag());
        }

        comboBox.setItems(options);
    }

    @FXML
    public void handleSearch() {
        String choice = (String) comboBox.getSelectionModel().getSelectedItem();
        ObservableList<String> data = FXCollections.observableArrayList();

        if (choice != null) {
            if (checkbox.isSelected()) {
                for (Fuggohid fuggohid : FuggohodakController.fuggohidak) {

                    if (fuggohid.getOrszag().equals(choice) && fuggohid.getHossz() > 1000) {
                        data.add(fuggohid.getHid());
                    }
                }
            } else {
                for (Fuggohid fuggohid : FuggohodakController.fuggohidak) {

                    if (fuggohid.getOrszag().equals(choice)) {
                        data.add(fuggohid.getHid());
                    }
                }
            }
        }

        listview.setItems(data);
    }
}
