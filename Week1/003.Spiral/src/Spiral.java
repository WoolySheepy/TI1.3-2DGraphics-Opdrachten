import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {

        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.scale(1,-1);

        float resolution = 0.01f;
        float scale = 10;
        float n = 0.9f;
        float O = n * 0;
        float x;
        float y;
        float lastX = n * O * (float)Math.cos(O);
        float lastY = n * O * (float)Math.sin(O);

        for (float r = 0 ; r < 150 ; r += resolution) {
            O = n * r;
            x = n * O * (float)Math.cos(O);
            y = n * O * (float)Math.sin(O);
            graphics.draw(new Line2D.Float(x*scale, y*scale, lastX*scale, lastY*scale));
            lastX = x;
            lastY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
