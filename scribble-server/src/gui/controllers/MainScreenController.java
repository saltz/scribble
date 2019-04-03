package gui.controllers;

import gateways.concrete.ServerGateway;
import gateways.interfaces.IServerGateway;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.GameHostRequest;
import models.GameRequest;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends Application {
    private IServerGateway gateway;

    @FXML
    public ListView<String> history;

    public MainScreenController() {
        gateway = new ServerGateway() {
            @Override
            public void receiveGameRequest(GameRequest request) {
                history.getItems().add(String.format("JOIN request from user %s with score %s", request.userId, request.userScore));
                history.refresh();

                super.receiveGameRequest(request);
            }

            @Override
            public void receiveHostRequest(GameHostRequest request) {
                history.getItems().add(String.format("HOST request from user %s lobby: %s max players: %s", request.hostId, request.lobbyName, request.maxAmountOfPlayers));
                history.refresh();

                super.receiveHostRequest(request);
            }
        };
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../screens/MainScreen.fxml"));
        primaryStage.setTitle("Scribble");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
