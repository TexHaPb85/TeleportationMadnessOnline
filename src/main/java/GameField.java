
import Utilities.RandomUtil;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;


public class GameField {
    public Player p1;
    public Player p2;
    public Group root;
    public int[] teleports;
    public ArrayList<FieldPos> fields;
    public ArrayList<ImageView> teleportsImg;
    public ImageView fieldImg;

    public GameField(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        setBackgroundImagine();
        fillFieldsList();
        root = new Group();
        root.getChildren().addAll(fieldImg,p1.img,p2.img);
        p1.goTo(fields.get(0).xFromLeft,fields.get(0).yFromTop-10);
        p2.goTo(fields.get(0).xFromLeft-10,fields.get(0).yFromTop-45);
        setRandonTeleports();
    }

    public void goTo(int x, int y, ImageView img){
        KeyValue xValue=new KeyValue(img.xProperty(),x);
        KeyValue yValue=new KeyValue(img.yProperty(),y);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1800),xValue,yValue);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
    }

    /**
     * initial teleport setting on their positions
     */
    public void setRandonTeleports(){
        teleports= new int[9];
        for (int i = 0; i <teleports.length ; i++) {
            teleports[i]= RandomUtil.getRandom(2,52);
        }
        teleportsImg=new ArrayList<>();
        for (int i = 0; i < teleports.length; i++) {
            if(i<teleports.length/2+1){
                ImageView imageView = new ImageView
                        (new Image(getClass().getResourceAsStream("other\\blue.png")));
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                teleportsImg.add(imageView);
            }else {
                ImageView imageView = new ImageView
                        (new Image(getClass().getResourceAsStream("other\\yellow.png")));
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                teleportsImg.add(imageView);
            }

        }
        for (int i = 0; i <teleportsImg.size(); i++) {
            root.getChildren().add(teleportsImg.get(i));
            goTo(fields.get(teleports[i]).xFromLeft,
                    fields.get(teleports[i]).yFromTop-25,
                    teleportsImg.get(i));
        }

        System.out.println(showTeleportsPosition());
    }

    /**
     * @return string of teleport positions
     */
    public String showTeleportsPosition(){
        StringBuilder s = new StringBuilder("Teleports:\n");
        for (int i = 0; i <teleports.length ; i++) {
            s.append(teleports[i]).append(", ");
        }
        return s.toString();
    }

    public void setBackgroundImagine(){
        fieldImg=new ImageView
                (new Image(getClass().getResourceAsStream("other\\mappoints.png")));
        fieldImg.setFitHeight(671);
        fieldImg.setFitWidth(1204);
    }

    /**
     * перевіряє чи не вступив гравець в телепорт, воводить алерти рішення телепорту
     * @param right
     */
    public void checkTeleport(Gifer right){
        for (int i = 0; i < teleports.length; i++) {
            if (p1.pos==teleports[i]){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Гравець 1 потрапив у телепорт!");
                alert.setHeaderText("Ви втрапили в телепорт!!!");
                alert.setContentText("Пане, " + p1.name+" Ви втрапили в телепорт "+
                        "Ви можете довіритися долі втративши 5% свого здоров'я та телепортуватися в невідомому напрямку " +
                        "або спробувати вибратися із телепорту втративши 20% свого здоров'я!");
                ButtonType buttonTypeOne = new ButtonType("Телепортуватися(-5% HP)");
                ButtonType buttonTypeTwo = new ButtonType("Тікати від телепорту(-20% HP)");

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne){
                    p1.hp-=5;
                    int newPos = (int)(Math.random()*teleports.length);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText("Ви телепортувалися з "+p1.pos+" в "+teleports[newPos]+" позицію!");
                    alert1.showAndWait();
                    p1.pos=teleports[newPos]+1;
                    p1.teleportTo(fields.get(p1.pos).xFromLeft,
                            fields.get(p1.pos).yFromTop-10,
                            right, 1
                            );
                } else if (result.get() == buttonTypeTwo) {
                    p1.hp-=20;
                    p1.pos+=1;
                }
            }else if (p2.pos==teleports[i]) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Гравець 2 потрапив у телепорт!");
                alert.setHeaderText("Ви втрапили в телепорт!!!");
                alert.setContentText("Пане," + p2.name+"Ви втрапили в телепорт"+
                        "Ви можете довіритися долі втративши 5% свого здоров'я та телепортуватися в невідомому напрямку" +
                        "або спробувати вибратися із телепорту втративши 20% свого здоров'я!");
                ButtonType buttonTypeOne = new ButtonType("Телепортуватися(-5% HP)");
                ButtonType buttonTypeTwo = new ButtonType("Тікати від телепорту(-20% HP)");

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne){
                    p2.hp-=5;
                    int newPos = (int)(Math.random()*teleports.length);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText("Ви телепортувалися з "+p2.pos+" в "+teleports[newPos]+" позицію!");
                    alert1.showAndWait();
                    p2.pos=teleports[newPos]+1;
                    p1.teleportTo(fields.get(p2.pos).xFromLeft,
                            fields.get(p2.pos).yFromTop-45,
                            right, 2
                    );

                } else if (result.get() == buttonTypeTwo) {
                    p2.hp-=20;
                    p2.pos+=1;
                }
            }
        }
    }

    public void fillFieldsList(){
        fields= new ArrayList<>();
        fields.add(new FieldPos(34,215));
        fields.add(new FieldPos(90,228));
        fields.add(new FieldPos(146,193));
        fields.add(new FieldPos(202,159));
        fields.add(new FieldPos(262,137));

        fields.add(new FieldPos(318,116));
        fields.add(new FieldPos(369,116));
        fields.add(new FieldPos(370,172));
        fields.add(new FieldPos(361,232));
        fields.add(new FieldPos(309,249));
        fields.add(new FieldPos(253,297));
        fields.add(new FieldPos(193,327));

        fields.add(new FieldPos(138,357));
        fields.add(new FieldPos(81,378));
        fields.add(new FieldPos(133,413));
        fields.add(new FieldPos(202,427));
        fields.add(new FieldPos(254,434));
        fields.add(new FieldPos(318,433));
        fields.add(new FieldPos(417,425));
        fields.add(new FieldPos(430,369));
        fields.add(new FieldPos(482,344));

        fields.add(new FieldPos(546,361));
        fields.add(new FieldPos(606,382));
        fields.add(new FieldPos(619,434));
        fields.add(new FieldPos(541,433));
        fields.add(new FieldPos(485,455));
        fields.add(new FieldPos(443,512));
        fields.add(new FieldPos(464,567));
        fields.add(new FieldPos(524,576));
        fields.add(new FieldPos(585,567));
        fields.add(new FieldPos(649,541));

        fields.add(new FieldPos(705,520));
        fields.add(new FieldPos(765,546));
        fields.add(new FieldPos(825,533));
        fields.add(new FieldPos(881,524));
        fields.add(new FieldPos(937,511));
        fields.add(new FieldPos(997,489));
        fields.add(new FieldPos(937,434));
        fields.add(new FieldPos(885,442));
        fields.add(new FieldPos(834,434));
        fields.add(new FieldPos(821,382));

        fields.add(new FieldPos(765,335));
        fields.add(new FieldPos(705,344));
        fields.add(new FieldPos(645,322));
        fields.add(new FieldPos(597,292));
        fields.add(new FieldPos(653,275));
        fields.add(new FieldPos(683,215));
        fields.add(new FieldPos(731,240));
        fields.add(new FieldPos(778,258));
        fields.add(new FieldPos(826,232));
        fields.add(new FieldPos(868,193));
        fields.add(new FieldPos(924,215));
        fields.add(new FieldPos(972,262));
        fields.add(new FieldPos(1032,283));
        fields.add(new FieldPos(1079,309));
        fields.add(new FieldPos(1143,283));

    }
}
