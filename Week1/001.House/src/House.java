import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.scale(5, -5);

        //walls
        graphics.draw(new Line2D.Double(-50, -50, 50, -50));
        graphics.draw(new Line2D.Double(-50, 25, -50, -50));
        graphics.draw(new Line2D.Double(-50, 25, 0, 75));

        //roof
        graphics.draw(new Line2D.Double(0, 75, 50, 25));
        graphics.draw(new Line2D.Double(50, 25, 50, -50));

        //door
        graphics.draw(new Line2D.Double(-40, -50, -40, -10));
        graphics.draw(new Line2D.Double(-40, -10, -20, -10));
        graphics.draw(new Line2D.Double(-20, -50, -20, -10));

        //window
        graphics.draw(new Line2D.Double(0, -10, 30, -10));
        graphics.draw(new Line2D.Double(0, -30, 30, -30));
        graphics.draw(new Line2D.Double(0, -30, 0, -10));
        graphics.draw(new Line2D.Double(30, -30, 30, -10));

        //handle
        graphics.draw(new Line2D.Double(-37, -30, -37, -30));
    }

    public static void main(String[] args) {
        launch(House.class);
    }

}
