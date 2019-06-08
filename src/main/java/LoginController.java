import Entities.User;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController  {
    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;



    public void logIn(ActionEvent event) throws IOException {
        UserService userService = new UserService();
        if (userService.isRegistered(new User(loginField.getText(),passwordField.getText()))){
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Main.myLogin=loginField.getText();
            Main.mainStage.setScene(new Scene(root));

        }
    }
}
