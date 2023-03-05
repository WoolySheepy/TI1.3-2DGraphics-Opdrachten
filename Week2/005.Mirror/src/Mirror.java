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

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        g.scale(1, -1);
        g.setBackground(Color.white);
        g.clearRect((int)-this.canvas.getWidth() / 2, (int) -this.canvas.getHeight()/2, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        boolean skipper = false;
        for (int i = -1000; i <= 1000; i += 5) {
            g.drawLine(0, i, 0, i + 5);
            g.drawLine(i, 0, i + 5, 0);
            skipper = !skipper;
            if (skipper) {
                g.setColor(Color.lightGray);
            } else {
                g.setColor(Color.white);
            };
        }

        g.setColor(Color.black);
        int oldX = -1000;
        int oldY = (int)(-1000 * 2.5);
        for (int x = -1000 ; x <= 1000 ; x++) {
            int y = (int)(2.5 * x);
            g.drawLine(x, y, oldX, oldY);
            oldX = x;
            oldY = y;
        }



        g.setColor(Color.red);
        Shape square = new Rectangle2D.Double(-50, 100, 100, 100);
        g.draw(square);

        AffineTransform mirror = new AffineTransform(2 / (1 + (Math.pow(2.5, 2))) - 1, (2 * 2.5) / (1 + (Math.pow(2.5, 2))), (2 * 2.5) / (1 + (Math.pow(2.5, 2))), (2 * (Math.pow(2.5, 2))) / (1 + (Math.pow(2.5, 2))) - 1, 0, 0);
        g.draw(mirror.createTransformedShape(square));

    }


    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
