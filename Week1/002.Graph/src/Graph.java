import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Graph extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Graph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.scale(1,-1);

        graphics.setColor(Color.lightGray);
        graphics.draw(new Line2D.Double(0, -500, 0, 600));
        graphics.draw(new Line2D.Double(-1000, 0, 1000, 0));

        for (int x = -1000 ; x < 1000 ; x++) {
            if (x % 50 == 0) {
                graphics.draw(new Line2D.Double(x, -10, x, 10));
            }
        }
        for (int y = -500 ; y < 600 ; y++) {
            if (y % 40 == 0) {
                graphics.draw(new Line2D.Double(-10, y, 10, y));
            }
        }

        double resolution = 0.01;
        double lastY = Math.pow(-100, 3);

        graphics.setColor(Color.black);
        for(double x = -100; x < 100; x += resolution)
        {
            float y = (float)Math.pow(x, 3);
            graphics.draw(new Line2D.Double(x * 5 , y * 0.001f, (x-resolution) * 5 , lastY * 0.001f));
            lastY = y;
        }

    }
    
    
    
    public static void main(String[] args) {
        launch(Graph.class);
    }

}
