package Animation.Unused;

import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GifPlayer {
    /*public ImageView moveGif;
    public VBox vBox;
    public Slider slider;
    public GifPlayer gifPlayer;
    public Stage animationWindow;
    public Scene gifScene;
    public int size;

    public GifPlayer() {
        this.gifPlayer= new GifPlayer();
        playChar1Face(250);
            this.slider=new Slider(01,500,350);
            slider.setOnMouseClicked(event -> {
            resizeGifPlayer();
        });
        this.size=(int)slider.getValue();
        this.vBox=new VBox();
        vBox.getChildren().addAll(moveGif,slider);
        this.gifScene = new Scene(vBox,250,250);
        animationWindow=new Stage();
        animationWindow.setTitle("animation window");
        animationWindow.setScene(gifScene);
        //animationWindow.show();
    }


    public void resizeGifPlayer(){
        this.size=(int)slider.getValue();
        moveGif.setFitHeight(size);
        moveGif.setFitWidth(size);

        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();
    }
    public void playChar1Walk(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char2StandWalkStand.gif").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();

    }
    public void playChar2Walk(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char1StandWalkStand.gif").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();

    }

    public void platChar1Teleport(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char1Tele.gif").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();
    }
    public void platChar2Teleport(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char2Tele.gif").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();
    }


    public void playChar1Face(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char1Avatar4.png").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();
    }
    public void playChar1Face(int size){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char1Avatar4.png").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        //animationWindow.setScene(gifScene);

    }
    public void playChar2Face(){
        Image i = new Image(this.getClass().getResource("Sources\\chars\\char2Avatar4.png").toExternalForm());
        this.moveGif = new ImageView(i);
        System.out.println(i.getException());
        this.moveGif.setImage(i);
        this.size=(int)slider.getValue();
        this.moveGif.setFitWidth(size);
        this.moveGif.setFitHeight(size);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(moveGif,slider);
        animationWindow.setScene(gifScene);
        animationWindow.close();
        animationWindow.show();
    }

*/
}
