import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        g.scale(1, -1);
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        double x = this.canvas.getWidth();
        int colour = 0;
        Color[] colours = new Color[13];
        colours[0] = Color.BLACK;
        colours[1] = Color.BLUE;
        colours[2] = Color.CYAN;
        colours[3] = Color.DARK_GRAY;
        colours[4] = Color.GRAY;
        colours[5] = Color.GREEN;
        colours[6] = Color.LIGHT_GRAY;
        colours[7] = Color.MAGENTA;
        colours[8] = Color.ORANGE;
        colours[9] = Color.PINK;
        colours[10] = Color.RED;
        colours[11] = Color.WHITE;
        colours[12] = Color.YELLOW;

        for (double i = 0; i <= x; i += (x / 13)) {
            g.setColor(colours[colour]);
            g.fill(new Rectangle2D.Double((-x / 2) + i, 0, x/13, x/13));
//            g.setColor(Color.BLACK);
//            g.draw(new Rectangle2D.Double((-x / 2) + i, 0, x/13, x/13));
            colour++;
        }


    }


    public static void main(String[] args) {
        launch(Colors.class);
    }

}
