import java.awt.Graphics;

public abstract class AbsEdge
    {
	protected Vertex u;
	protected Vertex v;
	protected boolean wasvisited;

	public AbsEdge(Vertex u, Vertex v)
        {
        this.u = u;
        this.v = v;
        wasvisited = false;
        }

    public Vertex getu()
        {
        return u;
        }

    public Vertex getv()
        {
        return v;
        }

    public boolean equals(AbsEdge edge)
        {
        return equals(edge.getu(), edge.getv());
        }

	public abstract boolean equals(Vertex ver1, Vertex ver2);

    public abstract void paintComponent(Graphics graphics, Visualizer vis, AbsGraph v);
    }
