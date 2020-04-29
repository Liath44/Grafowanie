import java.awt.*;
import java.util.*;

public class Krawedzie {				//wszystkie krawędzie grafu
	
	int n;
	ArrayList<Edge> tab = new ArrayList<>();//tablica wszystkich krawędzi
	
	public void paintComponent(Graphics g, Canvas canvas, Graf V) {
		for(Edge e: tab) {			//przekazujemy pędzel każdej krawędzi żeby sie narysowała
			e.paintComponent(g, canvas, V);
		}
	}
	
	public void readE(Scanner sc, Graf V) {//wczytujemy liczbe krawędzi a potem ich końce
		n = sc.nextInt();
		for(int i=0; i<n; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			tab.add(new Edge(u, v));
			V.pts.get(u).add_neigh(v);
			V.pts.get(v).add_neigh(u);
		}
	}
	
	public void clear() {
		tab.clear();
		n=0;
	}
	
}
