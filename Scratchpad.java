import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Vector;

public class Scratchpad extends Canvas {

	Vector points = new Vector();

	Scratchpad() {
		setBackground(Color.yellow);
	}

	public boolean mouseDown(Event evt, int x, int y) {
		points.addElement(new Point(x, y));
		return true;
	}

	public boolean mouseDrag(Event evt, int x, int y) {
		points.addElement(new Point(x, y));
		repaint();
		return true;
	}

	public boolean mouseUp(Event evt, int x, int y) {
		points.addElement(null);
		return true;
	}

	public void paint(Graphics g) {
		Enumeration e = points.elements();
		Point lastPt = null;
		while (e.hasMoreElements()) {
			Point pt = (Point) e.nextElement();
			if (lastPt != null && pt != null) {
				g.drawLine(lastPt.x, lastPt.y, pt.x, pt.y);
			}
			lastPt = pt;
		}
	}

	public void clear() {
		points = new Vector();
		repaint();
	}
}