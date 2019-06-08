import Entities.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {
    public ImageView img;
    public KeyValue xValue;
    public KeyValue yValue;
    public String name;
    public int hp;
    public int pos;
    public boolean isMoving;


    public Player(String name, int hp, ImageView imageView){
        this.img=imageView;
        this.name = name;
        this.hp = hp;
        this.pos = 1;
        this.isMoving = false;
    }


    public Player(int hp, boolean b, ImageView secondPlayerIMG, String name) {
        this.name="some player";
        this.hp = hp;
        this.pos = 1;
        this.img=secondPlayerIMG;
        this.isMoving = b;
        xValue=new KeyValue(img.xProperty(),821);
        yValue=new KeyValue(img.yProperty(),383);
    }

    public void goTo(int x, int y){
        xValue=new KeyValue(img.xProperty(),x);
        yValue=new KeyValue(img.yProperty(),y);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2200),xValue,yValue);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }

    public void teleportTo(int x, int y, Gifer rightControlPanel, int num){
        xValue=new KeyValue(img.xProperty(),x);
        yValue=new KeyValue(img.yProperty(),y);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(900),xValue,yValue);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
        if(num==1){
            rightControlPanel.platChar1Teleport();
        }else  if(num==2){
            rightControlPanel.platChar2Teleport();
        }
    }

    public void setImgSize(int size){
        img.setFitHeight(size);
        img.setFitWidth(size);
    }
}
