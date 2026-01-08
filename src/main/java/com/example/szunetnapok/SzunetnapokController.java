package com.example.szunetnapok;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class SzunetnapokController implements Initializable {

    @FXML public Spinner<Integer> spinner_year;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate localDate = LocalDate.now();
        int minYear = localDate.getYear();
        int maxYear = minYear + 10;

        SpinnerValueFactory.IntegerSpinnerValueFactory integerSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minYear, maxYear);
        spinner_year.setValueFactory(integerSpinnerValueFactory);
        spinner_year.getValueFactory().setValue(minYear);
    }

    @FXML protected void onShowButtonClick() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        Integer year = spinner_year.getValueFactory().getValue();
        YearHolidays yearHolidays = SzunetNapokApiClient.getYear(year);
    }
}