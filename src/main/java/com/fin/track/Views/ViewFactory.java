package com.fin.track.Views;

import com.fin.track.Controllers.Client.ClientFrameController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private final StringProperty clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane analyticsView;
    private AnchorPane expenseView;
    private AnchorPane fundsView;
    private AnchorPane finToolsView;
    private AnchorPane profileView;

    public ViewFactory(){
        this.clientSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/fxml/overview.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dashboardView;
    }

    public AnchorPane getAnalyticsView() {
        if (analyticsView == null) {
            try{
                analyticsView = new FXMLLoader(getClass().getResource("/fxml/stats.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return analyticsView;
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/frame.fxml"));
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("BudgetBuddy");
        stage.setResizable(true); // Setting it true creates unusual errors.
        stage.show();
    }

    public AnchorPane getExpenseManagerView() {
        if (expenseView == null) {
            try{
                expenseView = new FXMLLoader(getClass().getResource("/fxml/expense.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return expenseView;
    }

    public AnchorPane getManageFundsView() {
        if (fundsView == null) {
            try{
                fundsView = new FXMLLoader(getClass().getResource("/fxml/funds.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fundsView;
    }

    public AnchorPane getFinToolsView() {
        if (finToolsView == null) {
            try{
                finToolsView = new FXMLLoader(getClass().getResource("/fxml/fintools.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return finToolsView;
    }

    public AnchorPane getProfileView() {
        if (profileView == null) {
            try{
                profileView = new FXMLLoader(getClass().getResource("/fxml/settings.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileView;
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
