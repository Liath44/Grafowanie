import java.awt.Graphics;

public class DirEdge extends AbsEdge
    {
    public DirEdge(Vertex u, Vertex v)
        {
        super(u, v);
        }

    public boolean equals(Vertex ver1, Vertex ver2)
        {
        return u.equals(ver1) && v.equals(ver2);
        }

    public void paintComponent(Graphics graphics, Canvas canv, AbsGraph v)
        {
        System.out.println("Directed edge");
        }
    }
