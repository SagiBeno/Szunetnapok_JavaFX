package com.example.szunetnapok;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SzunetnapokController implements Initializable {

    @FXML public Spinner<Integer> spinner_year;
    @FXML public Label information_label;
    @FXML public TableView<HolidayDay> tableview;
    @FXML public TableColumn<HolidayDay, LocalDate> date_column;
    @FXML public TableColumn<HolidayDay, String> day_column;
    @FXML public TableColumn<HolidayDay, String> holidayType_column;
    @FXML public TableColumn<HolidayDay, String> type_column;
    @FXML public Button searchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate localDate = LocalDate.now();
        int minYear = localDate.getYear();
        int maxYear = minYear + 10;

        SpinnerValueFactory.IntegerSpinnerValueFactory integerSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minYear, maxYear);
        spinner_year.setValueFactory(integerSpinnerValueFactory);
        spinner_year.getValueFactory().setValue(minYear);

        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));

        holidayType_column.setCellValueFactory(new PropertyValueFactory<>("name"));

        day_column.setCellValueFactory(cell ->
                new SimpleStringProperty(weekdayToText(cell.getValue().getWeekday()))
        );

        type_column.setCellValueFactory(cell ->
                new SimpleStringProperty(typeToText(cell.getValue().getType()))
        );
    }

    @FXML protected void onShowButtonClick() throws IOException, InterruptedException {
        searchButton.setDisable(true);
        spinner_year.setEditable(false);

        Integer year = spinner_year.getValueFactory().getValue();
        YearHolidays yearHolidays = SzunetNapokApiClient.getYear(year);

        if (yearHolidays.getDays().isEmpty()) {
            information_label.setText("Nem jeleníthetőek meg adatok!");
            information_label.setVisible(true);
            tableview.setVisible(false);
        } else {
            tableview.setVisible(true);
            information_label.setVisible(false);
            ObservableList<HolidayDay> data = FXCollections.observableArrayList(yearHolidays.getDays());
            tableview.setItems(data);
        }

        searchButton.setDisable(false);
        spinner_year.setEditable(true);
    }

    public String weekdayToText(int day) {
        return switch (day) {
            case 1 -> "Hétfő";
            case 2 -> "Kedd";
            case 3 -> "Szerda";
            case 4 -> "Csütörtök";
            case 5 -> "Péntek";
            case 6 -> "Szombat";
            case 7 -> "Vasárnap";
            default -> "Ismeretlen";
        };
    }

    public String typeToText(int type) {
        return switch (type) {
            case 1 -> "Munkaszüneti nap";
            case 2 -> "Munkanap";
            default -> "Ismeretlen";
        };
    }
}