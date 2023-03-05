import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Renderable {

    private Color colour;
    private Point2D position = new Point(0, 0);
    private Shape shape;

    public Renderable(Color colour, double x, double y, Shape shape) {
        this.colour = colour;
        this.position.setLocation(x, y);
        this.shape = shape;
    }

    public Shape getShape() {
        return this.shape;
    }

    public Color getColour() {
        return this.colour;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void draw(FXGraphics2D g, double x, double y) {
        this.position.setLocation(x, y);
        if (this.shape instanceof Rectangle2D) {
            this.shape = new Rectangle2D.Double(this.position.getX(), this.position.getY(), ((Rectangle2D) this.shape).getWidth(), ((Rectangle2D) this.shape).getHeight());
        } else if (this.shape instanceof Ellipse2D) {
            this.shape = new Ellipse2D.Double(this.position.getX(), this.position.getY(), ((Ellipse2D) this.shape).getWidth(), ((Ellipse2D) this.shape).getHeight());
        }

        g.setColor(this.colour);
        g.fill(this.shape);
        g.setColor(Color.black);
        g.draw(this.shape);
    }
}
