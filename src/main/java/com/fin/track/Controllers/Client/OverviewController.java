package com.fin.track.Controllers.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TreeItem;
import java.util.List;
import com.fin.track.Utils.JsonUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import com.fin.track.Controllers.Client.ExpController;
import com.fin.track.Models.Expense;
import com.fin.track.Controllers.Client.Transaction;

public class OverviewController implements Initializable {

    @FXML
    public TreeTableView<Transaction> tableView;
    public List<Transaction> transactions;

    @FXML
    private Label balance;

    @FXML
    private Label basic;

    @FXML
    private Label food;

    @FXML
    private Label others;

    @FXML
    private Label shopping;

    @FXML
    private ExpController expCalculatorController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        loadDummyData();
    }

    public void initializeTable() {
        TreeTableColumn<Transaction, String> purposeColumn = new TreeTableColumn<>("Purpose");
        TreeTableColumn<Transaction, String> categoryColumn = new TreeTableColumn<>("Category");
        TreeTableColumn<Transaction, Double> sumColumn = new TreeTableColumn<>("Sum");
        TreeTableColumn<Transaction, String> dateColumn = new TreeTableColumn<>("Date");

        purposeColumn.setCellValueFactory(param -> 
            new SimpleStringProperty(param.getValue().getValue().getPurpose()));
        categoryColumn.setCellValueFactory(param -> 
            new SimpleStringProperty(param.getValue().getValue().getCategory()));
        sumColumn.setCellValueFactory(param -> 
            new SimpleObjectProperty<>(param.getValue().getValue().getSum()));
        dateColumn.setCellValueFactory(param -> 
            new SimpleStringProperty(param.getValue().getValue().getDate()));

        tableView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        purposeColumn.setMinWidth(100);
        categoryColumn.setMinWidth(80);
        sumColumn.setMinWidth(80);
        dateColumn.setMinWidth(80);

        tableView.getColumns().setAll(List.of(purposeColumn, categoryColumn, sumColumn, dateColumn));

        if (tableView.getRoot() == null) {
            TreeItem<Transaction> root = new TreeItem<>(new Transaction("Root", "", 0.0, ""));
            root.setExpanded(true);
            tableView.setRoot(root);
            tableView.setShowRoot(false);
        }
    }

    public void addTransactionToTable(String purpose, String category, double amount, String date) {
        Transaction transaction = new Transaction(purpose, category, amount, date);
        TreeItem<Transaction> item = new TreeItem<>(transaction);
        tableView.getRoot().getChildren().add(item);
        tableView.refresh();
    }

    private void loadDummyData() {
        TreeItem<Transaction> root = new TreeItem<>(new Transaction("Root", "", 0.0, ""));
        root.setExpanded(true);
        
        // Add some dummy transactions
        List<Transaction> dummyTransactions = List.of(
            new Transaction("Groceries", "Food", 50.0, "2024-03-20"),
            new Transaction("Netflix", "Basic", 15.99, "2024-03-19"),
            new Transaction("Amazon Purchase", "Shopping", 129.99, "2024-03-18"),
            new Transaction("Restaurant", "Food", 35.50, "2024-03-17"),
            new Transaction("Gas", "Basic", 45.0, "2024-03-16")
        );
        
        for (Transaction transaction : dummyTransactions) {
            TreeItem<Transaction> item = new TreeItem<>(transaction);
            root.getChildren().add(item);
        }
        
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.refresh();
    }

    @FXML
    public void setExpCalculatorController(ExpController controller) {
        this.expCalculatorController = controller;
        // Set up any listeners or communication needed between controllers
        setupControllerCommunication();
    }

    private void setupControllerCommunication() {
        expCalculatorController.transactionsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TreeItem<Transaction> root = tableView.getRoot();
                if (root == null) {
                    root = new TreeItem<>(new Transaction("Root", "", 0.0, ""));
                    root.setExpanded(true);
                    tableView.setRoot(root);
                    tableView.setShowRoot(false);
                }
                
                root.getChildren().clear();
                for (Transaction transaction : newValue) {
                    root.getChildren().add(new TreeItem<>(transaction));
                }
                tableView.refresh();
            }
        });
    }

    public void addTransaction(Transaction transaction) {
        System.out.println("Adding transaction to TreeTableView: " + transaction.getPurpose()); // Debug print
        
        if (tableView == null) {
            System.out.println("ERROR: tableView is null!"); // Debug print
            return;
        }

        if (tableView.getRoot() == null) {
            System.out.println("Creating new root for TreeTableView"); // Debug print
            TreeItem<Transaction> root = new TreeItem<>(new Transaction("Root", "", 0.0, ""));
            root.setExpanded(true);
            tableView.setRoot(root);
            tableView.setShowRoot(false);
        }
        
        tableView.getRoot().getChildren().add(new TreeItem<>(transaction));
        System.out.println("Current number of items: " + tableView.getRoot().getChildren().size()); // Debug print
        tableView.refresh();
    }
}
