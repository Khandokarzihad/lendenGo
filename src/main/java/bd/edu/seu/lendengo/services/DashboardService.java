package bd.edu.seu.lendengo.services;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
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

    // Hover In (Mouse Entered)
    public void boxHoverIn(FontAwesomeIconView icon) {
        ScaleTransition zoomIn = new ScaleTransition(Duration.millis(300), icon);
        zoomIn.setToX(1.2);
        zoomIn.setToY(1.2);
        zoomIn.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition moveUp = new TranslateTransition(Duration.millis(300), icon);
        moveUp.setToY(15);
        moveUp.setToX(-25);
        moveUp.setInterpolator(Interpolator.EASE_BOTH);

        zoomIn.play();
        moveUp.play();
    }

    // Hover Out (Mouse Exited)
    public void boxHoverOut(FontAwesomeIconView icon) {
        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(300), icon);
        zoomOut.setToX(1.0);
        zoomOut.setToY(1.0);
        zoomOut.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition moveDown = new TranslateTransition(Duration.millis(300), icon);
        moveDown.setToY(10);
        moveDown.setToX(-10);
        moveDown.setInterpolator(Interpolator.EASE_BOTH);

        zoomOut.play();
        moveDown.play();
    }
}
