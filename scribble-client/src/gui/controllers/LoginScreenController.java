package gui.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../screens/LoginScreen.fxml"));
        primaryStage.setTitle("Scribble");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void login(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../screens/MainScreen.fxml"));
        stage.setTitle("Scribble");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
