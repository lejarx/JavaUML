import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * How do I set bufferedImage to work? And how do I click in the canvas?
 * 
 * @author afiqjohari
 */
interface MissionExecuter {

	void executeMission(int missionNumber);
}

public class DrawingPanel extends Panel implements MissionExecuter {
	Panel shapePanel = new Panel();
	MyButton lineButton = new MyButton("Line", this, 1);
	MyButton recButton = new MyButton("Rectangle", this, 2);
	MyButton ovalButton = new MyButton("Oval", this, 3);

	Panel colorPanel = new Panel();
	MyButton dodgeBlueButton = new MyButton("Dodge Blue", this, 20);
	MyButton burntOrangeButton = new MyButton("Burnt Orange", this, 21);
	MyButton jadeButton = new MyButton("Jade Green", this, 22);

	Panel canvasPanel = new Panel();
	MyCanvas mainCanvas = new MyCanvas();

	// read and write graphic file
	Panel ioPanel = new Panel();
	TextField fileText = new TextField("File name");
	MyButton readButton = new MyButton("Read", this, 30);
	MyButton writeButton = new MyButton("Write", this, 30);

	// Main Panel
	Panel mainPanel = new Panel();

	public DrawingPanel() {
		super();

		mainCanvas.setBackground(Color.WHITE);
		mainCanvas.setSize(400, 400);

		shapePanel.add(new Label("Shape"));
		shapePanel.add(lineButton);
		shapePanel.add(recButton);
		shapePanel.add(ovalButton);

		colorPanel.add(new Label("Color"));
		colorPanel.add(dodgeBlueButton);
		colorPanel.add(burntOrangeButton);
		colorPanel.add(jadeButton);

		ioPanel.add(new Label("IO"));
		ioPanel.add(fileText);
		ioPanel.add(readButton);
		ioPanel.add(writeButton);

		canvasPanel.add(mainCanvas);

		mainPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(colorPanel);
		mainPanel.add(shapePanel);
		mainPanel.add(ioPanel);
		this.add(mainPanel, BorderLayout.NORTH);
		this.add(mainCanvas, BorderLayout.CENTER);

	}

	class MyButton extends Button implements ActionListener {
		MissionExecuter mi;
		int missionNumber;

		public MyButton(String label, MissionExecuter mi, int missionNumber) {
			super(label);
			this.mi = mi;
			this.missionNumber = missionNumber;
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.mi.executeMission(missionNumber);
		}

	}

	// bufferedImage should have the same size as the canvas

	static class MyCanvas extends Canvas {
		static int mPaintNumber;
		static Color mColor = Color.BLACK; // default color
		private Point pt1 = new Point(0, 0);
		private Point pt2 = new Point(0, 0);

		public MyCanvas() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					pt1 = new Point(e.getX(), e.getY());
					System.out.println("1:" + pt1);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					pt2 = new Point(e.getX(), e.getY());
					System.out.println("2:" + pt2);
				}

			});
		}

		@Override
		public void paint(Graphics g) {
			g.setColor(mColor);
			switch (mPaintNumber) {
			case 1:
				g.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
				break;
			case 2:
				g.drawRect(pt1.x, pt1.y, Math.abs(pt2.x - pt1.x),
						Math.abs(pt2.y - pt1.y));
				g.fillRect(pt1.x, pt1.y, Math.abs(pt2.x - pt1.x),
						Math.abs(pt2.y - pt1.y));
				break;
			case 3:
				g.drawOval(pt1.x, pt1.y, Math.abs(pt2.x - pt1.x),
						Math.abs(pt2.y - pt1.y));
				g.fillOval(pt1.x, pt1.y, Math.abs(pt2.x - pt1.x),
						Math.abs(pt2.y - pt1.y));
				break;
			default:
				break;
			}
		}

		public static void setPaintColor(int r, int g, int b) {
			mColor = new Color(r, g, b);
		}

	}

	@Override
	public void executeMission(int missionNumber) {
		switch (missionNumber) {
		case 1:
			System.out.println("Drawing a line");
			mainCanvas.mPaintNumber = 1;
			break;
		case 2:
			System.out.println("Drawing a rectangle");
			mainCanvas.mPaintNumber = 2;
			break;
		case 3:
			System.out.println("Drawing an oval");
			mainCanvas.mPaintNumber = 3;
			break;
		case 20:
			System.out.println("Setting color to dodger blue");
			MyCanvas.setPaintColor(25, 181, 254);
			break;
		case 21:
			System.out.println("Setting color to burnt orange");
			MyCanvas.setPaintColor(211, 84, 0);
			break;
		case 22:
			System.out.println("Setting color to jade green");
			MyCanvas.setPaintColor(0, 177, 106);
			break;
		default:
			System.out.println("clicked");
			break;
		}

		mainCanvas.repaint();
	}

	public static void main(String[] args) {
		Frame f = new Frame("Drawing test");
		DrawingPanel d = new DrawingPanel();
		f.add(d);
		f.setSize(500, 600);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("The window will be closed");
				System.exit(0);
			}
		});
		f.validate();
		f.setVisible(true);

	}

}
