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

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
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

        Shape outsideCircle = new Ellipse2D.Double(-75, -75, 150, 150);

        GeneralPath swirl1 = new GeneralPath();
        swirl1.moveTo(0, 75);
        swirl1.curveTo(55, 72, 55, 0, 0, 0);
        swirl1.closePath();

        GeneralPath swirl2 = new GeneralPath();
        swirl2.moveTo(0, 0);
        swirl2.curveTo(-55, 0, -55, -72, 0, -75);
        swirl2.closePath();

        GeneralPath halfCircle = new GeneralPath();
        halfCircle.moveTo(0, 75);
        halfCircle.curveTo(-100, 75, -100, -75, 0, -75);
        halfCircle.closePath();

        Shape blackDot = new Ellipse2D.Double(-6.25, 32.5, 12.5, 12.5);
        Shape whiteDot = new Ellipse2D.Double(-6.25, -44, 12.5, 12.5);

        Area a1 = new Area(outsideCircle);
        Area a2 = new Area(swirl1);
        Area a3 = new Area(swirl2);
        Area a4 = new Area(halfCircle);

        a1.subtract(a2);
        a2.add(a4);
        a1.subtract(a4);
        a1.add(a3);

        g.fill(blackDot);
        g.fill(a1);
        g.draw(outsideCircle);
        g.setColor(Color.WHITE);
        g.fill(whiteDot);


    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
