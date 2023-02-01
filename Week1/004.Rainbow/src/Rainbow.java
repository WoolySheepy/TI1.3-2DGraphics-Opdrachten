import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D g) {
        g.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        g.scale(1,-1);

        float radiusInside = 400;
        float radiusOutside = 500;
        float degree = 0.0001f;

        g.draw(new Line2D.Double(-2000, 0, 2000, 0));

        for (float i = 0 ; i < Math.PI ; i +=degree) {

            g.setColor(Color.getHSBColor(i/(float)Math.PI, 1, 1 ));

            float x1 = radiusInside * (float) Math.cos(i);
            float y1 = radiusInside * (float) Math.sin(i);
            float x2 = radiusOutside * (float) Math.cos(i);
            float y2 = radiusOutside * (float) Math.sin(i);

            g.draw(new Line2D.Float(x1, y1, x2, y2));
        }

    }
    
    
    
    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
