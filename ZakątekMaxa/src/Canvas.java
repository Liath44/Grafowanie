import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class Canvas extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Graf V = new Graf();
	Krawedzie E = new Krawedzie();
	TreeDfs tree = null;
	boolean indeksy = false;
	boolean treeview = false;
	public File inputf = null;
	public Scanner in = null;

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;					//castujemy pędzel g do "fajniejszego" pędzla g2d (więcej możliwości potem)
														//tło
        g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
														//translacja żeby 0,0 było na środku
        g2d.translate(350, 350);
		g2d.setColor(Color.black);						//kolor pędzla
		
		V.paintComponent(g, this);						//przekazujemy pędzel wierzchołkom i każemy się narysować
		E.paintComponent(g, this, V);					//przekazujemy pędzel krawędziom i każemy się narysować (dodajemy dostęp do wierzchołków żeby znało ich pozycje na canvasie)
	}
	
	public void read() {
		
		if(inputf == null && in == null)	//żeby nie czytać z null-a
			return;
		V.clear();
		E.clear();
		if(in == null) {
			try {							//żeby nie czytać z nieistniejącego pliku
				in = new Scanner(inputf);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				System.exit(0);
			}
		}
		V.readPts( in );				//n a potem ewentualnie x i y
		E.readE( in, V );				//m a potem u i v
		in.close();
		
		if(treeview) {
			tree = new TreeDfs(V, V.pts.get(0).getindex());
			tree.setcoordinates(V, 400, 400);		//wysokosc i szerokosc drzewa(prostokącik na środku canvasu)
		}
		in = null;						//zerujemy in na kolejne rysowanie
	}

}
