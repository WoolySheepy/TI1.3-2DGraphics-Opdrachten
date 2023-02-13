import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private float phi;
    private float gradient;
    private Canvas canvas;
    private boolean transformed = false;
    private boolean drawIt = false;
    private boolean alreadySet = false;
    private Timeline animation;
    private double a;
    private double b;
    private double c;
    private double d;

    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(1920, 1080);
        FXGraphics2D g = new FXGraphics2D(canvas.getGraphicsContext2D());
        transform(g);
        g.setBackground(Color.WHITE);
        g.clearRect((int)-(this.canvas.getWidth()/2), (int)-(this.canvas.getHeight()/2), (int)(this.canvas.getWidth()), (int)(this.canvas.getHeight()));

        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));
        VBox drawButBox = new VBox();
        ToggleGroup drawButtons = new ToggleGroup();

        Button add = new Button("add");
        Button random = new Button("random");
        Button clear = new Button("clear");
        CheckBox draw = new CheckBox("draw");
//        RadioButton doDraw = new RadioButton("draw");
//        RadioButton dontDraw = new RadioButton("dont draw");
//        doDraw.setSelected(true);

        topBar.getChildren().add(v1 = new TextField("" + (Math.round(Math.random() * 300))));
        topBar.getChildren().add(v2 = new TextField("" + (Math.round(Math.random() * 75))));
        topBar.getChildren().add(v3 = new TextField("" + (Math.round(Math.random() * 300))));
        topBar.getChildren().add(v4 = new TextField("" + (Math.round(Math.random() * 75))));
        topBar.getChildren().add(add);
        topBar.getChildren().add(random);
        topBar.getChildren().add(clear);
        topBar.getChildren().add(draw);
//        topBar.getChildren().add(drawButBox);
//        drawButBox.getChildren().add(doDraw);
//        drawButBox.getChildren().add(dontDraw);
//        doDraw.setToggleGroup(drawButtons);
//        dontDraw.setToggleGroup(drawButtons);

//       if (doDraw.isSelected()) {
//           drawIt = true;
//       }
//       if (dontDraw.isSelected()) {
//           drawIt = false;
//       }
        draw.setOnAction(event -> {
            if (drawIt) {
                drawIt = false;
            } else if (!drawIt) {
                drawIt = true;
            }
        });
        clear.setOnAction(event -> {
            clear(g);
        });
        random.setOnAction(event -> {
            clear(g);
            this.alreadySet = false;
            v1.setText("" + (Math.round(Math.random() * 300)));
            v2.setText("" + (Math.round(Math.random() * 75)));
            v3.setText("" + (Math.round(Math.random() * 300)));
            v4.setText("" + (Math.round(Math.random() * 75)));
            draw(g);
        });
        add.setOnAction( event -> draw(g));

        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();

    }

    public void clear(FXGraphics2D g){
        g.transform(new AffineTransform());
        g.setBackground(Color.WHITE);
        g.clearRect((int)-(this.canvas.getWidth()/2), (int)-(this.canvas.getHeight()/2), (int)(this.canvas.getWidth()), (int)(this.canvas.getHeight()));
    }
    public void transform(FXGraphics2D g) {
        if (!this.transformed) {
            g.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
            g.scale(1, -1);
            g.setBackground(Color.WHITE);
            this.transformed = true;
        }
    }

    public void draw(FXGraphics2D g) {
        transform(g);

        this.a = Double.parseDouble(v1.getText());
        this.b = Double.parseDouble(v2.getText());
        this.c = Double.parseDouble(v3.getText());
        this.d = Double.parseDouble(v4.getText());

//        g.setColor(Color.getHSBColor((float)Math.random(), 1, 1 ));

        float resolution = 0.00001f;
        float scale = 0.75f;
        float x;
        float y;
        float lastX = (float)(((a * Math.cos(b * 0)) + (c * Math.cos(d * 0))));
        float lastY = (float)(((a * Math.sin(b * 0)) - (c * Math.sin(d * 0))));

        if (!drawIt) {
            for (this.phi = 0; this.phi < (Math.PI * 2); this.phi += resolution) {
                g.setColor(Color.getHSBColor(gradient / 10, 1, 1));
                gradient += (resolution * 2);
                if (gradient == 1) {
                    gradient = 0;
                }
                x = (float) (((a * Math.cos(b * this.phi)) + (c * Math.cos(d * this.phi))));
                y = (float) (((a * Math.sin(b * this.phi)) - (c * Math.sin(d * this.phi))));

                g.draw(new Line2D.Float(x * scale, y * scale, lastX * scale, lastY * scale));

                lastX = x;
                lastY = y;
            }
            this.animation.stop();
        } else {
            this.animation = new Timeline(new KeyFrame(Duration.millis(0.1), event -> {
                this.phi += resolution;
                drawPart(g);
            }));
            this.animation.setCycleCount(Animation.INDEFINITE);
            this.animation.play();

        }
    }

    public void drawPart(FXGraphics2D g) {
        transform(g);

        float resolution = 0.00001f;
        float scale = 0.75f;
        float x;
        float y;
        float lastX;
        float lastY;

        if (!alreadySet) {
            a = Double.parseDouble(v1.getText());
            b = Double.parseDouble(v2.getText());
            c = Double.parseDouble(v3.getText());
            d = Double.parseDouble(v4.getText());
            lastX = (float) (((a * Math.cos(b * 0)) + (c * Math.cos(d * 0))));
            lastY = (float) (((a * Math.sin(b * 0)) - (c * Math.sin(d * 0))));
            alreadySet = true;
        }
        gradient += (resolution * 3);
        if (gradient == 1) {
            gradient = 0;
        }
        g.setColor(Color.getHSBColor(gradient / 10, 1, 1));
        x = (float) (((a * Math.cos(b * this.phi)) + (c * Math.cos(d * this.phi))));
        y = (float) (((a * Math.sin(b * this.phi)) - (c * Math.sin(d * this.phi))));
        lastX = x;
        lastY = y;

        g.draw(new Line2D.Float(x * scale, y * scale, lastX * scale, lastY * scale));

        lastX = x;
        lastY = y;
    }

    public static void main(String[] args) {
        launch(Spirograph.class);
    }
}
