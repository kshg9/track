package com.fin.track.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.Ikonli;
import org.kordamp.ikonli.boxicons.BoxiconsRegular;
import org.kordamp.ikonli.boxicons.BoxiconsSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import java.time.LocalDate;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpController implements Initializable {
    private static final ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private final ObjectProperty<List<Transaction>> transactions = 
        new SimpleObjectProperty<>(new ArrayList<>());

    public OverviewController overviewController;

    public static ObservableList<Expense> getExpenses() {
        return expenses;
    }

    public ObjectProperty<List<Transaction>> transactionsProperty() {
        return transactions;
    }

    public void updateTransactions(List<Transaction> newTransactions) {
        transactions.set(newTransactions);
    }

    @FXML
    private Button addButton;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<CategoryItem> categoryField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField descriptionField;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupCategoryField();
        setupDateField();
        setupAmountField();
    }

    private void setupCategoryField() {
        categoryField.setItems(FXCollections.observableArrayList(
                new CategoryItem("Shopping", new FontIcon(BoxiconsRegular.CART)),
                new CategoryItem("Food & Drinks", new FontIcon(BoxiconsRegular.RESTAURANT)),
                new CategoryItem("Bills & Utils", new FontIcon(BoxiconsRegular.RECEIPT)),
                new CategoryItem("Others", new FontIcon(BoxiconsSolid.CATEGORY))
        ));

        categoryField.setCellFactory(listView -> new ListCell<CategoryItem>() {
            private HBox hbox = new HBox(5);
            private FontIcon icon = new FontIcon();
            private Label label = new Label();

            {
                hbox.getChildren().addAll(icon, label);
            }

            @Override
            protected void updateItem(CategoryItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    icon.setIconCode(item.getIcon().getIconCode());
                    label.setText(item.getName());
                    setGraphic(hbox);
                }
            }
        });

        categoryField.setButtonCell(categoryField.getCellFactory().call(null));
    }

    private void setupDateField() {
        // Prevent manual editing
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

    private void setupAmountField() {
        // Add listener to validate input
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void addExpenseHandler(ActionEvent event) {
        try {
            LocalDate date = dateField.getValue();
            String category = categoryField.getValue().getName();
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            // Direct call to overview controller
            if (overviewController != null && overviewController.tableView != null) {
                overviewController.addTransactionToTable(description, category, amount, date.toString());
                clearFields();
                showAlert("Success", "Expense added successfully!", Alert.AlertType.INFORMATION);
            } else {
                System.out.println("OverviewController or TableView is null!");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add expense: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static class Expense {
        private LocalDate date;
        private String category;
        private String description;
        private int amount;

        public Expense(LocalDate date, String category, String description, int amount) {
            this.date = date;
            this.category = category;
            this.description = description;
            this.amount = amount;
        }

        public String toJsonString() {
            return String.format(
                    "{\n  \"date\": \"%s\",\n  \"category\": \"%s\",\n  \"description\": \"%s\",\n  \"amount\": %d\n}",
                    date, category, description, amount
            );
        }
    }

    private static class CategoryItem {
        private String name;
        private FontIcon icon;

        public CategoryItem(String name, FontIcon icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public FontIcon getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private void clearFields() {
        dateField.setValue(LocalDate.now());
        categoryField.setValue(null);
        descriptionField.clear();
        amountField.clear();
    }

    public void setOverviewController(OverviewController controller) {
        this.overviewController = controller;
    }
}