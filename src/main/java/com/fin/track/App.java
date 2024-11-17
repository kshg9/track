package com.fin.track;

import com.fin.track.Models.Model;
import com.fin.track.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/frame.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("BudgetBuddy");
//        stage.setScene(scene);
//        stage.show();
//        Model.getInstance().getViewFactory().getDashboardView();

//        ViewFactory viewFactory = new ViewFactory();
//        viewFactory.showClientWindow();
        Model.getInstance().getViewFactory().showClientWindow();
    }

//    public static void main(String[] args) {
//        launch();
//    }
}