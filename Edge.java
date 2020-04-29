import java.awt.Graphics;

public class Edge extends AbsEdge
    {
    public Edge(Vertex u, Vertex v)
        {
        super(u, v);
        }

    public boolean equals(Vertex ver1, Vertex ver2)
        {
        return (u.equals(ver1) && v.equals(ver2) || v.equals(ver1) && u.equals(ver2));
        }

    public void paintComponent(Graphics graphics, Canvas canv, AbsGraph v)
        {
        System.out.println("Straight edge");
        }
    }
