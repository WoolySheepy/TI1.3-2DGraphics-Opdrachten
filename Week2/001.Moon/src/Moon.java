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

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D g)
    {
        g.setTransform(new AffineTransform());
        g.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        g.scale(2, -2);
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        g.setColor(Color.lightGray);
        for (int x = -2000 ; x < 2000 ; x += 10) {
            if (x == 0) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(x, 2000, x, -2000);
        }
        for (int y = -2000 ; y < 2000 ; y += 10) {
            if (y == 0) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(-2000, y, 2000, y);
        }

        g.setColor(Color.black);
        GeneralPath theMoon = new GeneralPath();
        theMoon.moveTo(-10, -60);
        theMoon.curveTo(120, -100, 110, 80, 0, 50);
        theMoon.quadTo(90, -15, -10, -60);
        theMoon.closePath();
        g.fill(theMoon);

        Shape circle1 = new Ellipse2D.Double(-150, -70, 130, 130);
        Shape circle2 = new Ellipse2D.Double(-190, -60, 130, 130);

        Area otherCircle1 = new Area(circle1);
        Area otherCircle2 = new Area(circle2);

        otherCircle1.subtract(otherCircle2);

        g.draw(otherCircle1);
        g.fill(otherCircle1);
//        g.draw(circle1);
//        g.draw(circle2);
        g.draw(theMoon);



    }


    public static void main(String[] args)
    {
        launch(Moon.class);
    }

}
