import java.awt.*;
import java.util.ArrayList;

public class Vertex
    {
	private int x;
	private int y;
	private boolean wasvisited;
	private final int dotsize = 3;
	private int index;
	private	ArrayList<Vertex> neighbours = new ArrayList<>();

	public Integer getIndex()
        {
        return index;
        }

    public void setIndex(int i)
        {
        index = i;
        }

	public void addNeighbour(Vertex ver)
        {
        neighbours.add(ver);
        }

    public boolean hasThisNeighbour(Vertex ver)
        {
        for(int i = 0; i < neighbours.size(); i++)
            {
            if(neighbours.get(i).equals(ver))
                return true;
            }
        return false;
        }

    public void removeNeighbour(Vertex ver)
        {
        for(int i = 0; i < neighbours.size(); i++)
            {
            if(neighbours.get(i).equals(ver))
                {
                neighbours.remove(i);
                return;
                }
            }
        }

    public void paintComponent(Graphics g, Visualizer visualizer)
        {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(x-dotsize/2, y-dotsize/2, dotsize, dotsize);
        //g2d.drawString(Integer.toString(index), x+size/2, y-size);		//indeksy wierzcholkow
        }

    public boolean equals(Vertex ver)
        {
        return ver.getIndex() == index;
        }

    public void setCircleCoordinates(int novertexes)
        {
        x = (int)(Math.cos(2*Math.PI/novertexes*(index))*300);
        y = (int)(Math.sin(2*Math.PI/novertexes*(index))*300);
        }

    public Vertex getNeighbour(int i)
        {
        return neighbours.get(i);
        }

    public int getNeighboursNumber()
        {
        return neighbours.size();
        }

	public Vertex(int index, int x, int y)
        {
        this.x = x;
        this.y = y;
        wasvisited = false;
        this.index = index;
        }

	public Vertex(int index, int novertexes)
        {
		this.index = index;
        wasvisited = false;
        setCircleCoordinates(novertexes);
	    }
    }
