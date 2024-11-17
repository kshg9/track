package com.fin.track.Controllers.Client;

import com.fin.track.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button dashboardHandler;
    public Button analyticsHandler;
    public Button expenseHandler;
    public Button fundingHandler;
    public Button finToolsHandler;
    public Button profileHandler;

    private Button lastPressedButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lastPressedButton = dashboardHandler;
        lastPressedButton.getStyleClass().add("pressed");
        addListeners();
    }

    public void addListeners(){
        dashboardHandler.setOnAction(e -> onDashboard());
        analyticsHandler.setOnAction(e -> onAnalytics());
        expenseHandler.setOnAction(e -> onExpense());
        fundingHandler.setOnAction(e -> onFunding());
        finToolsHandler.setOnAction(e -> onFinToolsHandler());
        profileHandler.setOnAction(e -> onProfile());
    }

    private void onFinToolsHandler() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Financial Tools");
        updateLastPressed(finToolsHandler);
    }

    private void onFunding() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Manage funds");
        updateLastPressed(fundingHandler);
    }

    private void onExpense() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Manage Expense");
        updateLastPressed(expenseHandler);
    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
        updateLastPressed(dashboardHandler);
    }

    private void onAnalytics(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Analytics");
        updateLastPressed(analyticsHandler);
    }

    private void onProfile(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Profile");
        updateLastPressed(profileHandler);
    }

    private void updateLastPressed(Button clickedButton) {
        if (lastPressedButton != clickedButton) {
            lastPressedButton.getStyleClass().remove("pressed");
            clickedButton.getStyleClass().add("pressed");

            lastPressedButton = clickedButton;
        }
    }
}
