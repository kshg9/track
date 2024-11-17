package com.fin.track.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class FundsController {

    @FXML
    private Label balance;

    @FXML
    private DatePicker dateField;

    @FXML
    private Label flow;

    @FXML
    private Label income;

    @FXML
    private Label outcome;

    @FXML
    private TextField sourceField;

    @FXML
    private TextField sumField;

    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        setupDateField();
        setupSumField();
        updateLabels();
    }

    private void setupDateField() {
        dateField.setEditable(false);

        // Set today's date as default
        dateField.setValue(LocalDate.now());

        // Add listener to validate date
        dateField.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });
    }

    private void setupSumField() {
        sumField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*\\.?\\d*")) {
                sumField.setText(oldValue);
            }
        });
    }

    @FXML
    private void addFundsHandler(ActionEvent event) {
        try {
            LocalDate date = dateField.getValue();
            String source = sourceField.getText();
            double sum = Double.parseDouble(sumField.getText());

            if (date == null || source.isEmpty() || sumField.getText().isEmpty()) {
                showAlert("Invalid Input", "Please fill all fields with valid data.");
                return;
            }

            updateLabels(sum);

            dateField.setValue(LocalDate.now());
            sourceField.clear();
            sumField.clear();

            showAlert("Success", "Funds added successfully!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            showAlert("Invalid Amount", "Please enter a valid number for the sum.");
        }
    }

    private void updateLabels() {
        balance.setText("₹1000.00");
        income.setText("₹500.00");
        outcome.setText("₹200.00");
        flow.setText("₹300.00");
    }

    private void updateLabels(double newSum) {
        // This is a simplified update. In a real application, you'd update based on your data model
        double currentBalance = Double.parseDouble(balance.getText().replace("₹", ""));
        double currentIncome = Double.parseDouble(income.getText().replace("₹", ""));
        double currentOutcome = Double.parseDouble(outcome.getText().replace("₹", ""));

        if (newSum > 0) {
            currentBalance += newSum;
            currentIncome += newSum;
        } else {
            currentBalance += newSum; 
            currentOutcome -= newSum;
        }

        double currentFlow = currentIncome - currentOutcome;

        balance.setText(String.format("₹%.2f", currentBalance));
        income.setText(String.format("₹%.2f", currentIncome));
        outcome.setText(String.format("₹%.2f", currentOutcome));
        flow.setText(String.format("₹%.2f", currentFlow));
    }

    private void showAlert(String title, String content) {
        showAlert(title, content, Alert.AlertType.ERROR);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}