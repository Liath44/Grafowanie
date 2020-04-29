import java.util.ArrayList;

public abstract class AbsGraph
    {
    protected ArrayList<Vertex> vertexes;
    protected ArrayList<AbsEdge> edges;
    protected final int radius = 300;

    public Integer countVertexes()
        {
        return vertexes.size();
        }

    public Integer countEdges()
        {
        return edges.size();
        }

    public void addVertex(Vertex vertex)
        {
        vertexes.add(vertex);
        }

    public void addVertex()
        {
        vertexes.add(new Vertex(vertexes.size(), 0, 0));
        }

    public void addEdge(int ver1, int ver2)
        {
        addEdge(vertexes.get(ver1), vertexes.get(ver2));
        }
    /*
     * Some functions destroy vertexes indexes (egz. removeVertex(int))
     * Some functions don't update x,y parameters of vertexes
     *
     * This function regenerates vertexes
     * Updates indexes so that they are from 0 to n-1 where n is number of vertexes
     * Updates x, y parameters to match circle view
     */
    public void regenerateVertexes()
        {
        int novertexes = vertexes.size();
        for(int i = 0; i < novertexes; i++)
            {
            vertexes.get(i).setIndex(i);
            vertexes.get(i).setCircleCoordinates(novertexes);
            }
        }

    public void removeVertex(int i)
        {
        vertexes.remove(i);
        for(int j = 0; j < edges.size(); j++)
            {
            if(edges.get(j).getu().getIndex() == i || edges.get(j).getv().getIndex() == i)
                {
                removeEdge(j);
                --j;
                }
            }
        regenerateVertexes();
        }

    public Vertex getVertex(int i)
        {
        return vertexes.get(i);
        }

    public ArrayList<Vertex> getVertexList()
        {
        return vertexes;
        }

    public ArrayList<AbsEdge> getEdgeList()
        {
        return edges;
        }

    public AbsEdge getEdge(int i)
        {
        return edges.get(i);
        }

    public void printInfo()
        {
        System.out.print("Type: " + getGraphType());
        System.out.println("Number of vertexes: " + vertexes.size());
        System.out.println("Edges: ");
        for(int i = 0; i < edges.size(); i++)
            {
            System.out.println(edges.get(i).getu().getIndex() + "    -    " + edges.get(i).getv().getIndex());
            }
        for(int j = 0; j < vertexes.size(); j++)
            {
            System.out.print(j + " neighbours:  ");
            for(int k = 0; k < vertexes.get(j).getNeighboursNumber(); k++)
                {
                System.out.print(vertexes.get(j).getNeighbour(k).getIndex() + "  ");
                }
            System.out.print("\n");
            }
        }

    public void removeEdge(int ver1, int ver2)
        {
        removeEdge(vertexes.get(ver1), vertexes.get(ver2));
        }

    public void removeEdge(Vertex ver1, Vertex ver2)
        {
        for(int i = 0; i < edges.size(); i++)
            {
            if(edges.get(i).equals(ver1, ver2))
                {
                removeEdge(i);
                return;
                }
            }
        }

    protected boolean edgeExists(ArrayList<AbsEdge> edges, Vertex ver1, Vertex ver2)
        {
        for(int i = 0; i < edges.size(); i++)
            {
            if(edges.get(i).equals(ver1, ver2))
                return true;
            }
        return false;
        }

    public boolean edgeExists(Vertex ver1, Vertex ver2)
        {
        return edgeExists(edges, ver1, ver2);
        }

    public abstract void addEdge(Vertex u, Vertex v);

    public abstract String getGraphType();

    public abstract void removeEdge(int i);

    public AbsGraph()
        {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
        }
    }
