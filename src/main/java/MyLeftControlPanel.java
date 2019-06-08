
import Entities.GameInfo;
import Entities.GameSession;
import Services.SessionService;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class MyLeftControlPanel {


    public Player player1;
    public Player player2;

    public Button firstPlayer;
    public Button secondPlayer;
    public boolean showGif;
    public VBox mainVBox;
    public Button showAnotherStage;


    public MyLeftControlPanel(Player p1, Player p2, Gifer gifer) {
        showGif=true;
        this.player1 = p1;
        this.player2 = p2;
        this.showAnotherStage = new Button("Показати анімацію в окремому вікні");
        showAnotherStage.setPrefSize(75,50);
        showAnotherStage.setStyle("-fx-background-color: #525252;"+
                "-fx-text-fill: aliceblue");
        setPlayerButtons();
        showAnotherStage.setOnAction(event -> {
            gifer.animationWindow.show();
        });

        mainVBox=new VBox();
        mainVBox.setSpacing(5);
        mainVBox.getChildren().addAll(firstPlayer,secondPlayer,showAnotherStage);
        if(showGif){
            mainVBox.getChildren().add(gifer.vBox);
        }
    }



    public void setPlayerButtons(){
        SessionService sessionService = new SessionService();
        GameInfo gameInfo = sessionService.getInfo(Main.myLogin);
        ImageView firstPlayerIMG=new ImageView
                (new Image(getClass().getResourceAsStream("chars\\char1Avatar.png")));
        firstPlayerIMG.setFitHeight(75);
        firstPlayerIMG.setFitWidth(75);
        ImageView secondPlayerIMG=new ImageView
                (new Image(getClass().getResourceAsStream("chars\\char2Avatar.png")));
        secondPlayerIMG.setFitWidth(75);
        secondPlayerIMG.setFitHeight(75);
        firstPlayer=new Button(getPlayer1Info(player1),firstPlayerIMG);
        secondPlayer= new Button(getPlayer1Info(player2),secondPlayerIMG);

        if(gameInfo.isFirstPlayerTurn()){
                firstPlayer.setStyle("-fx-background-color: #525252; " +
                                        "-fx-text-fill: aliceblue");

        }else{
                firstPlayer.setStyle("-fx-background-color: #959595;"+
                                        "-fx-text-fill: black");
        }


        if(gameInfo.isFirstPlayerTurn()){
                secondPlayer.setStyle("-fx-background-color: #525252;"+
                        "-fx-text-fill: aliceblue");
        }else{
                secondPlayer.setStyle("-fx-background-color: #959595;"+
                        "-fx-text-fill: black");
        }
    }

    public void refrash(Gifer gifer){
        setPlayerButtons();
        mainVBox.getChildren().clear();
        mainVBox.setSpacing(5);
        mainVBox.getChildren().addAll(firstPlayer,secondPlayer);
        if(showGif){
            mainVBox.getChildren().add(gifer.vBox);
        }
    }

    public String getPlayer1Info(Player player){
        StringBuilder s = new StringBuilder(player.name);
        s.append("\nHP: ").append(player.hp).append("\nPos: ").append(player.pos);
        return s.toString();
    }
}
