import java.awt.*;

public class Edge {			//jedna krawędź
	
	private int u;
	private int v;
	Edge(int _u, int _v){
		u=_u;
		v=_v;
	}
	
	public void paintComponent(Graphics g, Canvas canvas, Graf V) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(V.pts.get(u).getx(), V.pts.get(u).gety(), V.pts.get(v).getx(), V.pts.get(v).gety());
	}
	
}
