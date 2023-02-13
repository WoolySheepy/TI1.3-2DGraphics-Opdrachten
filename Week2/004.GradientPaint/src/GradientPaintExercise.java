import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static com.sun.prism.j2d.paint.MultipleGradientPaint.CycleMethod.NO_CYCLE;
import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;
    Point2D point2 = new Point(0, 0);

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

        this.canvas.setOnMouseDragged(e -> {
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
            point2.setLocation(e.getX() - (this.canvas.getWidth() / 2), e.getY() - (this.canvas.getHeight() / 2));
        });
    }


    public void draw(FXGraphics2D g)
    {
        g.setTransform(new AffineTransform());
        g.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        g.scale(1, 1);
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        Color[] colours = new Color[3];
        colours[0] = Color.BLACK;
        colours[1] = Color.BLUE;
        colours[2] = Color.CYAN;
        float[] floats = new float[3];
        floats[0] = 0f;
        floats[1] = 0.5f;
        floats[2] = 1f;

        Shape giantRect = new Rectangle2D.Double(-this.canvas.getWidth()/2, -this.canvas.getHeight()/2, this.canvas.getWidth(), this.canvas.getHeight());

        Point2D point1 = new Point2D.Double(0, 0);

        Paint p = new RadialGradientPaint(point2, 30f, point2, floats, colours, MultipleGradientPaint.CycleMethod.REPEAT);

        g.setPaint(p);
        g.fill(giantRect);
    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
