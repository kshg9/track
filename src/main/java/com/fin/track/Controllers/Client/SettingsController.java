package com.fin.track.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsController {

    @FXML
    private TextField email;
    @FXML
    private TextField fullname;
    @FXML
    private PasswordField hiddenPasswordText;
    @FXML
    private TextField passwordText;
    @FXML
    private CheckBox showPasswd;
    @FXML
    private TextField username;

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

    @FXML
    void deleteInfo(ActionEvent event) {

    }

    @FXML
    void saveInfo(ActionEvent event) {

    }

}
