import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
/*    public static void main(String[] args) {
        User u = new User("JamieLanister","1234");
        u.setPlaying(true);
        UserService userService= new UserService();
        //userService.update(u);
        System.out.println(userService.getByLogin("JamieLanister"));
    }*/

    public static Stage mainStage;
    public static String myLogin="none";

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        mainStage.setTitle("Teleportation Madness Online");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }
}
