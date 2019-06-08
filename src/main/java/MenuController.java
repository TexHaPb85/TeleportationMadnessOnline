import Entities.GameSession;
import Entities.User;
import Services.SessionService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class MenuController {
    @FXML
    ImageView userImageView;

    @FXML
    Label userInfoLabel;

    @FXML
    TableView<GameSession> sessionTableView;

    @FXML
    TableColumn<GameSession, Integer> idColumn;

    @FXML
    TableColumn<GameSession, String> p1Column;

    @FXML
    TableColumn<GameSession, String> p2Column;

    SessionService sessionService = new SessionService();
    UserService userService = new UserService();

    public void refreash(){
        ObservableList<GameSession> gameSessions = FXCollections.observableArrayList();
        sessionService.getAllSessions().stream().forEach(s->gameSessions.add(s));
        idColumn.setCellValueFactory(new PropertyValueFactory<GameSession, Integer>("id"));
        p1Column.setCellValueFactory(new PropertyValueFactory<GameSession, String>("player1"));
        p2Column.setCellValueFactory(new PropertyValueFactory<GameSession, String>("player2"));
        sessionTableView.setItems(gameSessions);
        if(sessionService.shellStart(Main.myLogin)){
            startNewGame();
        }
    }

    public void startNewGame(){
        GameSession gameSession = sessionService.getSession(Main.myLogin);
        sessionService.addGame(gameSession);
        MainPanel mainPanel= new MainPanel();
        Main.mainStage.setScene(mainPanel.mainScene);

    }


    public void newGame(ActionEvent event) {
        sessionService.addSession(Main.myLogin);
        refreash();
    }

    public void joinGame(ActionEvent event) {
        int id = sessionTableView.getSelectionModel().getSelectedItem().getId();
        sessionService.addPlayerToSession(id,Main.myLogin);
        refreash();
    }

    public void refBtn(ActionEvent event) {
        User u =userService.getByLogin(Main.myLogin);
        Image playerImg = new Image(u.getPhotoURL());
        userImageView.setImage(playerImg);
        userInfoLabel.setText("Login:\n"+u.getLogin()+"is not playing");
        refreash();
    }
}
