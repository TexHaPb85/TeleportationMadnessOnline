package Animation.Unused;

import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoPlayer {

    private MediaView mediaView;
    private Slider seekSlider;
    private Label time;
    private MediaPlayer mediaPlayer;
    private Boolean flag;

    public VideoPlayer() {
        seekSlider = new Slider();
        mediaView = new MediaView();
        time = new Label();
        time.setPrefWidth(100);
        flag=false;
    }

    public static MediaView playChar1Walk(){
        Media media = new Media("file:/C:/MySnakesAndLaddersVersion/src/Sources/char1/char1Walk.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.play();
        return mediaView;
    }

}
