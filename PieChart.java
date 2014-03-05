//----------------------------------------------------------------------
//----------------------------------------------------------------------
//
//		--------------------
//		 C a m e m b e r t:
//		--------------------
//
//----------------------------------------------------------------------
//----------------------------------------------------------------------

//	Java --	de base
//	
import java.io.*;

//	Java --	graphisme de base
//	
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;

//----------------------------------------------------------------------
//----------------------------------------------------------------------

/**
 * Un 'PieChart', c'est un camembert !
 */
public class PieChart extends Canvas {
	// debut de classe
	// ----------------------------------------------------------------------

	/**
	 * Pour de simples raisons pedagogiques, ce composant peut afficher des
	 * images !
	 */
	BufferedImage theImage;

	/**
	 * Valeurs &agrave; afficher.
	 */
	double[] values;

	/**
	 * R&eacute;ception des valeurs &agrave; afficher.
	 */
	void recvValues(double[] v) {
		values = new double[v.length];
		for (int w = 0; w < v.length; w++) {
			values[w] = v[w];
		}
	}

	/**
	 * Constructeur.
	 */
	public PieChart() throws Exception {
		super();

		// un petit pingouin pour le plaisir...
		theImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
		theImage = ImageIO.read(new File("tux06.png"));
	}

	/**
	 * Fonction d'affichage.
	 */
	public void paint(Graphics g) {
		Color[] col = { Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN };
		int nextcol = 3;

		double total = 0.0;
		int alpha1 = 0, alpha;

		// voici le petit pingouin, avec un trait en diagonale
		g.drawImage(theImage, 0, 0, this);
		g.setColor(Color.MAGENTA);
		g.drawLine(0, 0, 400, 300);
		a("...MAGENTA...");

		// affichage des valeurs
		for (int w = 0; w < values.length; w++) {
			total += values[w];
		}
		for (int w = 0; w < values.length; w++) {
			alpha = (int) (360 * values[w] / total);
			if (w == values.length - 1) {
				alpha = 360 - alpha1;
			} else {
				alpha = (int) (360 * values[w] / total);
			}
			// a("w: "+w+"; color: "+nextcol);
			g.setColor(col[nextcol]);
			if (nextcol == (col.length - 1)) {
				nextcol = 0;
			} else {
				nextcol = (nextcol + 1) % (col.length - 1);
			}
			g.fillArc(100, 100, 200, 200, alpha1, alpha);
			alpha1 += alpha;
		}
	}

	/**
	 * Puisqu'il faut tester...
	 */
	public static void main(String[] args) throws Exception {
		a(" ");
		a("--------------------------------------------");
		a(" Exemple (sommaire) de composant graphique: ");
		a("--------------------------------------------");
		a("\nB.M.G. version 2014 Hiver ");

		double[] t = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9 };

		Frame theFrame = new Frame("test camembert");
		PieChart thePie = new PieChart();

		thePie.recvValues(t);

		theFrame.setSize(400, 300);
		thePie.setSize(200, 200);
		theFrame.add(thePie);
		theFrame.validate();
		thePie.setVisible(true);
		theFrame.setVisible(true);
	}

	static void a(String s) {
		System.out.println(s);
	}

	// ----------------------------------------------------------------------
	// fin de classe
}

// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------
// ----------------------------------------------------------------------

