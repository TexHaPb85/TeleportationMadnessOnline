

import Entities.GameInfo;
import Services.SessionService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class MainPanel {

    public Scene mainScene;
    public Gifer rightController;
    public MyLeftControlPanel leftController;
    public GameField gameField;


    public HBox mainH;
    public Player p1;
    public Player p2;

    public Button nextStepBtn;
    public Button cube;
    public Button refreashBtn;

    public SessionService sessionService = new SessionService();

    public MainPanel(){

        BtnSetting();
        ImageView firstPlayerIMG=new ImageView
                (new Image(getClass().getResourceAsStream("chars\\char1Avatar.png")));
        firstPlayerIMG.setFitHeight(40);
        firstPlayerIMG.setFitWidth(40);
        ImageView secondPlayerIMG=new ImageView
                (new Image(getClass().getResourceAsStream("chars\\char2Avatar.png")));
        secondPlayerIMG.setFitWidth(40);
        secondPlayerIMG.setFitHeight(40);
        GameInfo gameInfo = sessionService.getInfo(Main.myLogin);
        this.p1=new Player(100, true,firstPlayerIMG, gameInfo.getPlayer1());
        this.p2=new Player(100, false,secondPlayerIMG, gameInfo.getPlayer2());


        this.rightController= new Gifer();
        this.leftController=new MyLeftControlPanel(p1,p2,rightController);
        this.gameField = new GameField(p1,p2);
        //this.mainFieldl=new MyGridField(this.size,p1,p2);

        leftController.mainVBox.getChildren().addAll(cube,nextStepBtn,refreashBtn);
        mainH=new HBox();
        mainH.getChildren().addAll(leftController.mainVBox, gameField.root /*mainFieldl.field, rightController.vBox*/);
        mainScene=new Scene(mainH,1400,780);
        mainScene.getStylesheets().add("Animation/css/style1.css");
        refresh();

    }

    public void BtnSetting(){
        cube= new Button("0");
        cube.setPrefSize(75,75);
        this.nextStepBtn = new Button("Make a move...");
        nextStepBtn.setPrefSize(120,75);
        nextStepBtn.setStyle("-fx-background-color: #525252;"+
                "-fx-text-fill: aliceblue");
        nextStepBtn.setOnAction(event -> {
            makeMove();
            leftController.setPlayerButtons();
           /* leftController.mainVBox.getChildren().clear();
            leftController.mainVBox.getChildren().addAll(leftController.firstPlayer,leftController.secondPlayer,
                    leftController.moveGif,nextStepBtn);*/

        });
        this.refreashBtn = new Button("Refreash");
        refreashBtn.setPrefSize(75,50);
        refreashBtn.setStyle("-fx-background-color: #525252;"+
                "-fx-text-fill: aliceblue");
        refreashBtn.setOnAction(event -> {
           refresh();
        });
    }

    public void refresh(){
        GameInfo gameInfo = sessionService.getInfo(Main.myLogin);
        p1.pos=gameInfo.getPos1();
        p2.pos=gameInfo.getPos2();
        gameField.checkTeleport(rightController);
        gameField.p1.goTo(gameField.fields.get(p1.pos).xFromLeft,gameField.fields.get(p1.pos).yFromTop-10);
        gameField.p2.goTo(gameField.fields.get(p2.pos).xFromLeft-10,gameField.fields.get(p2.pos).yFromTop-45);
        leftController.refrash(rightController);
        leftController.mainVBox.getChildren().addAll(cube,nextStepBtn, refreashBtn);
    }


    public void checkWinner(){

        if(p1.pos>=54){
            p1.pos=55;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Перший гравець: "+p1.name+" переміг!!!");
            alert.setHeaderText("Перемога першого гравця!");
            alert.showAndWait();
        }else if (p2.pos>54) {
            p2.pos=55;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Другий гравець: "+p2.name + " переміг!!!");
            alert.setHeaderText("Перемога другого гравця!");
            alert.showAndWait();
        }
    }

    public void makeMove(){
        checkWinner();
        refresh();
        GameInfo gameInfo = sessionService.getInfo(Main.myLogin);
        int stepNum = 1+(int)(Math.random()*6);
        cube.setText(String.valueOf(stepNum));
        if(gameInfo.isFirstPlayerTurn()){
            p1.pos+=stepNum;
            p1.isMoving=false;
            p2.isMoving=true;
            rightController.playChar2Walk();
            gameInfo.setFirstPlayerTurn(false);
            gameInfo.setPos1(p1.pos);
            sessionService.updateGame(gameInfo);

        }else {
            p2.pos+=stepNum;
            p2.isMoving=false;
            p1.isMoving=true;
            rightController.playChar1Walk();
            gameInfo.setFirstPlayerTurn(true);
            gameInfo.setPos2(p2.pos);
            sessionService.updateGame(gameInfo);
        }
        gameField.checkTeleport(rightController);
        gameField.p1.goTo(gameField.fields.get(p1.pos).xFromLeft,gameField.fields.get(p1.pos).yFromTop-10);
        gameField.p2.goTo(gameField.fields.get(p2.pos).xFromLeft-10,gameField.fields.get(p2.pos).yFromTop-45);
        leftController.refrash(rightController);
        leftController.mainVBox.getChildren().addAll(cube,nextStepBtn, refreashBtn);
    }
}
