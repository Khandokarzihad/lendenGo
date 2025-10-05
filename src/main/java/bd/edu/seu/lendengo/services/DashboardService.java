package bd.edu.seu.lendengo.services;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DashboardService {
    public void expandVBox(VBox vbox, double expandedHeight, FontAwesomeIconView angle) {
        vbox.setVisible(true);
        vbox.setManaged(true);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(vbox.prefHeightProperty(), expandedHeight);
        KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        angle.setRotate(270);
    }

    public void shrinkVBox(VBox vbox, FontAwesomeIconView angle) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(vbox.prefHeightProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(300), e-> {
            vbox.setVisible(false);
            vbox.setManaged(false);
        }, kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        vbox.setPrefHeight(0);
        angle.setRotate(0);
    }

    public void slideOut(VBox vbox,  FontAwesomeIconView angle) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(vbox.prefWidthProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(300), e-> {
            vbox.setVisible(false);
            vbox.setManaged(false);
        }, kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        vbox.setPrefWidth(0);
        angle.setRotate(180);
    }

    public void slideIn(VBox vbox, double expandedHeight, FontAwesomeIconView angle) {
        vbox.setVisible(true);
        vbox.setManaged(true);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(vbox.prefWidthProperty(), expandedHeight);
        KeyFrame kf = new KeyFrame(Duration.millis(300), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        angle.setRotate(0);
    }
}
