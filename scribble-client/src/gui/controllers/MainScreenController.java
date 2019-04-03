package gui.controllers;

import gateways.concrete.ClientGateway;
import gateways.interfaces.IClientGateway;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;

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
    @FXML
    public TextField lobbyName;
    @FXML
    public TextField maxAmountOfPlayers;
    @FXML
    public ListView<String> history;


    public MainScreenController() {
        gateway = new ClientGateway() {
            @Override
            public void receiveLobby(LobbyInfo lobbyInfo) {
                history.getItems().add(String.format("Lobby received: %s with score: %s with player amount: %s", lobbyInfo.lobbyName, lobbyInfo.hostScore, lobbyInfo.maxAmountOfUsers));
                history.refresh();

                //TODO connect to lobby
                super.joinGame(user, lobbyInfo.lobbyName);
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

    public void createUser(ActionEvent event) {
        user = new User(UUID.randomUUID(), firstname.getText(), lastname.getText(), username.getText());
        System.out.println(String.format("user created %s", user.toString()));
    }

    public void findLobby(ActionEvent event) {
        gateway.sendGameRequest(new GameRequest(user.userId, Integer.parseInt(score.getText())));
    }

    public void hostLobby(ActionEvent event) {
        gateway.hostGame(new GameHostRequest(user.userId, lobbyName.getText(), Integer.parseInt(maxAmountOfPlayers.getText())));
    }
}
