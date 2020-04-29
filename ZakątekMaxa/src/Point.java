import java.awt.*;
import java.util.ArrayList;

public class Point {
	
	private int x;
	private int y;
	private int size = 3;
	private int index;
	public	ArrayList<Integer> neigh = new ArrayList<>();		//zbiór sąsiadów dla każdego punktu
	
	
	public Point( int _index, int _x, int _y) {
		x = _x;
		y = _y;
		index = _index;
	}
	
	public Point( int _index, Graf V) {
		index = _index;
		x = (int)(Math.sin(2*Math.PI/V.n*(index))*V.r);
		y = (int)(-Math.cos(2*Math.PI/V.n*(index))*V.r);
	}
	
	//Getters
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public int getindex() {
		return index;
	}
	
	//Setters
	public void setx( int val ) {
		this.x = val;
	}
	public void sety( int val ) {
		this.y = val;
	}
	public void setsize( int val ) {
		this.size = val;
	}
	public void add_neigh( Integer _index ) {
		this.neigh.add(_index);
	}
	
	public void paintComponent(Graphics g, Canvas canvas) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.fillOval(x-size/2, y-size/2, size, size);
		if(canvas.indeksy)
			g2d.drawString(Integer.toString(index), x+size/2, y-size);		//indeksy wierzcholkow
	}
	
}
