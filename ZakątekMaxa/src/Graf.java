import java.awt.*;
import java.util.*;

public class Graf {
	
	public ArrayList<Point> pts = new ArrayList<>();
	int n;
	int r=300;
	
	public void paintComponent(Graphics g, Canvas canvas) {
		for(Point p : pts) {
			p.paintComponent(g, canvas);
		}
	}
	
	public void readPts( Scanner in ) {		//wczytywanie liczby wierzcholkow i ich położeń
		n = in.nextInt();
		for(int i=0; i<n; i++) {
			//int px = sc.nextInt();		podajemy x i y
			//int py = sc.nextInt();
			pts.add(new Point(i, this));
		}
	}
	
	public void clear() {
		pts.clear();
		n=0;
	}
	
}
