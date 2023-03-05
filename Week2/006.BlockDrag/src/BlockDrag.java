import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    private ResizableCanvas canvas;
    private Point2D point = new Point(0, 0);
    FXGraphics2D fxg;
    ArrayList<Renderable> shapes = new ArrayList<>();
    Renderable selected;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        fxg = new FXGraphics2D(canvas.getGraphicsContext2D());

        init(fxg);
        draw(fxg);
    }


    public void draw(FXGraphics2D g) {
        g.setTransform(new AffineTransform());
        g.setBackground(Color.white);
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        g.setColor(Color.black);

        for (Renderable shape : shapes) {
            if (shape != selected) {
                shape.draw(g, shape.getPosition().getX(), shape.getPosition().getY());
            }
        }

        if (selected != null) {
            selected.draw(g, point.getX() - (selected.getShape().getBounds().getWidth() / 2), point.getY() - (selected.getShape().getBounds().getHeight() / 2));
        }
    }

    public void init(FXGraphics2D g) {
//        Renderable square = new Renderable(Color.blue, 100, 100, new Rectangle2D.Double(100, 100, 50, 50));
//        Renderable rect = new Renderable(Color.red, 0, 100, new Rectangle2D.Double(0, 100, 70, 50));
//        Renderable circle = new Renderable(Color.green, 200, 100, new Ellipse2D.Double(200, 100, 50, 50));
//        Renderable oval = new Renderable(Color.yellow, 300, 100, new Ellipse2D.Double(300, 100, 70, 50));
//
//        shapes.add(square);
//        shapes.add(rect);
//        shapes.add(circle);
//        shapes.add(oval);

        for (int i = 0 ; i < 10 ; i++) {
            double random1 = Math.random() * 10;
            double random2 = Math.random() * 10;
            Color colour = null;
            Renderable shape = null;
            if (random1 < 2.5) {
                colour = Color.blue;
            } else if (random1 < 5) {
                colour = Color.red;
            } else if (random1 < 7.5) {
                colour = Color.green;
            } else if (random1 <= 10) {
                colour = Color.yellow;
            }

            if (random2 < 2.5) {
                shape = new Renderable(colour, i * 80, 100, new Rectangle2D.Double(i * 50, 100, 50, 50));
            } else if (random2 < 5) {
                shape = new Renderable(colour, i * 80, 100, new Rectangle2D.Double(i * 50, 100, 70, 50));
            } else if (random2 < 7.5) {
                shape = new Renderable(colour, i * 80, 100, new Ellipse2D.Double(i * 50, 100, 50, 50));
            } else if (random2 <= 10) {
                shape = new Renderable(colour, i * 80, 100, new Ellipse2D.Double(i * 50, 100, 70, 50));
            }
            shapes.add(shape);
        }

    }


    public static void main(String[] args) {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e) {
        for (Renderable thing : shapes) {
            if (thing.getShape().contains(e.getX(), e.getY())) {
                selected = thing;
            }
        }
    }

    private void mouseReleased(MouseEvent e) {
        selected = null;
    }

    private void mouseDragged(MouseEvent e) {
        if (selected.getShape().contains(e.getX(), e.getY())) {
            point.setLocation(e.getX(), e.getY());
            draw(fxg);
        }
    }

}
