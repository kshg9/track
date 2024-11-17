package com.fin.track.Controllers.Client;

import com.fin.track.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientFrameController implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Analytics" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAnalyticsView());
                case "Manage Expense" -> client_parent.setCenter(Model.getInstance().getViewFactory().getExpenseManagerView());
                case "Manage funds" -> client_parent.setCenter(Model.getInstance().getViewFactory().getManageFundsView());
                case "Financial Tools" -> client_parent.setCenter(Model.getInstance().getViewFactory().getFinToolsView());
                case "Profile" -> client_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
