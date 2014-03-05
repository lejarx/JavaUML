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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

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

	// Panel's graphic
	BufferedImage theImage = new BufferedImage(400, 400,
			BufferedImage.TYPE_3BYTE_BGR);
	Graphics panelGraphics;

	public DrawingPanel() {
		super();

		mainCanvas.setBackground(Color.WHITE);
		mainCanvas.setForeground(Color.WHITE);
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
		panelGraphics = theImage.createGraphics();
		// color theImage to white
		panelGraphics.setColor(Color.WHITE);
		panelGraphics.fillRect(0, 0, mainCanvas.getWidth(),
				mainCanvas.getHeight());

	}

	class MyButton extends Button implements ActionListener {
		MissionExecuter mi;
		int missionNumber;

		public MyButton(String label, MissionExecuter mi, int missionNumber) {
			super(label);
			this.mi = mi;
			this.missionNumber = missionNumber;
			this.addActionListener(this); // the button listens to itself
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.mi.executeMission(missionNumber);
		}

	}

	class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
		int mShape;
		Point pt1 = new Point(0, 0);
		Point pt2 = new Point(0, 0);

		public MyCanvas() {
			addMouseListener(this); // the canvas listens to itself
		}

		@Override
		public void paint(Graphics g) {
			g.drawImage(theImage, 0, 0, this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Mouse is clicked");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			pt1 = new Point(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			pt2 = new Point(e.getX(), e.getY());
			switch (mShape) {
			case 1:
				panelGraphics.drawLine(mainCanvas.pt1.x, mainCanvas.pt1.y,
						mainCanvas.pt2.x, mainCanvas.pt2.y);
				break;
			case 2:
				panelGraphics.drawRect(mainCanvas.pt1.x, mainCanvas.pt1.y,
						Math.abs(mainCanvas.pt2.x - mainCanvas.pt1.x),
						Math.abs(mainCanvas.pt2.y - mainCanvas.pt1.y));
				break;
			case 3:
				panelGraphics.drawOval(mainCanvas.pt1.x, mainCanvas.pt1.y,
						Math.abs(mainCanvas.pt2.x - mainCanvas.pt1.x),
						Math.abs(mainCanvas.pt2.y - mainCanvas.pt1.y));
				break;

			default:
				break;
			}
			repaint();
			pt1.x = 0;
			pt1.y = 0;
			pt2.y = 0;
			pt2.y = 0;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("Mouse is in");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("Mouse is out");
		}

		// MouseMotionListener
		@Override
		public void mouseDragged(MouseEvent e) {

		}

		// MouseMotionListener
		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	@Override
	public void executeMission(int missionNumber) {
		switch (missionNumber) {
		case 1:
			System.out.println("Drawing a line");
			mainCanvas.mShape = 1;
			break;
		case 2:
			System.out.println("Drawing a rectangle");
			mainCanvas.mShape = 2;
			break;
		case 3:
			System.out.println("Drawing an oval");
			mainCanvas.mShape = 3;
			break;
		case 20:
			System.out.println("Setting color to dodger blue");
			panelGraphics.setColor(new Color(25, 181, 254));
			break;
		case 21:
			System.out.println("Setting color to burnt orange");
			panelGraphics.setColor(new Color(211, 84, 0));
			break;
		case 22:
			System.out.println("Setting color to jade green");
			panelGraphics.setColor(new Color(0, 177, 106));
			break;
		default:
			System.out.println("clicked");
			break;
		}

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

// xor
// UML code generator

