import java.util.ArrayList;

public class DirGraph extends AbsGraph
    {
    public void addEdge(Vertex u, Vertex v)
        {
        //update neighbours
        u.addNeighbour(v);
        //create and add new edge
        DirEdge edge = new DirEdge(u, v);
        edges.add(edge);
        }

    /*
     * Iterates through all edges to check whether neighbours are set properly
     * If not updates them
     *
     * Calling this function assures properly set neighbours
     *
     * In DirGraph neighbour from u to v means that there is an edge
     * leading from u to v
     *
     * !!!It does not imply that v is u's neighbour!!!
     */
    private void updateNeighbours()
        {
        for(int i = 0; i < edges.size(); i++)
            {
            AbsEdge edge = edges.get(i);
            if(!edge.getu().hasThisNeighbour(edge.getv()))
                edge.getu().addNeighbour(edge.getv());
            }
        }

    /*
     * D - Directed - Skierowany (graf)
     */
    public String getGraphType()
        {
        return "D\n";
        }

    public void removeEdge(int i)
        {
        edges.get(i).getu().removeNeighbour(edges.get(i).getv());
        edges.remove(i);
        }

    public DirGraph()
        {
        super();
        }

    /*
     * Creates DirGraph from another graph
     *
     * AbsGraph should rather be of type Graph
     *
     * Graph is transformed into DirGraph by having every edge be replaced
     * from Edge to two DirEdges (one from u to v and one from v to u)
     */
    public DirGraph(AbsGraph v)
        {
        //vertexes don't change
        this.vertexes = v.getVertexList();
        ArrayList<AbsEdge> edges = v.getEdgeList();
        ArrayList<AbsEdge> newedges = new ArrayList<>();
        for(int i = 0; i < edges.size(); i++)
            {
            //create two edges in place of one
            DirEdge edge1 = new DirEdge(edges.get(i).getu(), edges.get(i).getv());
            DirEdge edge2 = new DirEdge(edges.get(i).getv(), edges.get(i).getu());
            newedges.add(edge1);
            newedges.add(edge2);
            }
        this.edges = newedges;
        //neighbours don't change
        }
    }
