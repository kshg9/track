package com.fin.track.Controllers;

import com.fin.track.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private PasswordField hiddenPasswordText;
    @FXML
    private CheckBox showPasswd;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField username;
    @FXML
    private Button enterBtn;

    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPasswd.isSelected()){
            passwordText.setText(hiddenPasswordText.getText());
            passwordText.setVisible(true);
            hiddenPasswordText.setVisible(false);
            return;
        }
        hiddenPasswordText.setText(passwordText.getText());
        hiddenPasswordText.setVisible(true);
        passwordText.setVisible(false);
    }

    private String getPassword() {
        if (passwordText.isVisible()){
            return passwordText.getText();
        }
        return hiddenPasswordText.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enterBtn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) username.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
}