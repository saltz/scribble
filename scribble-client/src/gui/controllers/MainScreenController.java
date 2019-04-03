package gui.controllers;

import gateways.concrete.ClientGateway;
import gateways.interfaces.IClientGateway;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.GameRequest;
import models.GameUpdate;
import models.LobbyInfo;
import models.User;

import java.util.UUID;

public class MainScreenController extends Application {
    private IClientGateway gateway;
    private User user;

    @FXML
    public TextField firstname;
    @FXML
    public TextField lastname;
    @FXML
    public TextField username;
    @FXML
    public TextField score;

    public MainScreenController() {
        gateway = new ClientGateway() {
            @Override
            public void receiveLobby(LobbyInfo lobbyInfo) {

            }

            @Override
            public void receiveGameUpdates(GameUpdate update) {

            }
        };
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../screens/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Scribble");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createUser(MouseEvent event) {
        user = new User(UUID.randomUUID(), firstname.getText(), lastname.getText(), username.getText());
        System.out.println(String.format("user created %s", user.toString()));
    }

    public void findLobby(MouseEvent event) {
        int number = Integer.parseInt(score.getText());
        gateway.sendGameRequest(new GameRequest(user.userId, number));
    }
}
