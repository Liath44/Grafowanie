import java.util.ArrayList;
import java.util.Arrays;

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

    public int graphDegree()
        {
        int maxdeg = 0;
        int[] degrees = new int[vertexes.size()];
        Arrays.fill(degrees, 0);
        for(int i = 0; i < edges.size(); i++)
            {
            degrees[edges.get(i).getu().getIndex()]++;
            degrees[edges.get(i).getv().getIndex()]++;
            }
        for(int j = 0; j < degrees.length; j++)
            {
            if(degrees[j] > maxdeg)
                maxdeg = degrees[j];
            }
        return maxdeg;
        }

    public boolean hasCycle()
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            for(int j = 0; j < vertexes.get(i).getNeighboursNumber(); j++)
                {
                tryToGoEverywhere(vertexes.get(i).getNeighbour(j));
                }
            if(vertexes.get(i).wasVisited())
                return true;
            unvisitVertexes();
            }
        return false;
        }
        
        
    private void tryToGoEverywhere(Vertex v)
        {
        if(!v.wasVisited())
            {
            v.visit();
            for(int i = 0; i < v.getNeighboursNumber(); i++)
                tryToGoEverywhere(v.getNeighbour(i));
            }
        }
        
    public boolean isConnected()
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            tryToGoEverywhere(vertexes.get(i));
            for(int j = 0; j < vertexes.size(); j++)
                {
                if(!vertexes.get(j).wasVisited())
                    {
                    unvisitVertexes();
                    return false;
                    }
                }
            unvisitVertexes();
            }
        return true;
        }
         
    //TODO: maybe?
    public boolean isTree()
        { 
        return false;
        }

    public boolean isEulerian()
        {
        if(!isConnected())
            return false;
        int[] edgesin = new int[vertexes.size()];
        Arrays.fill(edgesin, 0);
        for(int i = 0; i < edges.size(); i++)
            edgesin[edges.get(i).getv().getIndex()]++;
        for(int j = 0; j < vertexes.size(); j++)
            {
            if(edgesin[j] != vertexes.get(j).getNeighboursNumber())
                return false;
            }
        return true;
        }

    private void fillMatrix(int[][] matrix)
        {
        for(int i = 0; i < edges.size(); i++)
            {
            matrix[edges.get(i).getu().getIndex()][edges.get(i).getv().getIndex()] += 1;
            }
        }

    private void addComplementEdges(int[][] matrix, AbsGraph outcome)
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            for(int j = 0; j < vertexes.size(); j++)
                {
                if(i != j && matrix[j][i] == 0)
                    outcome.addEdge(outcome.getVertex(j), outcome.getVertex(i));
                }
            }
        }

    public AbsGraph findComplementGraph()
        {
        int[][] matrix = new int[vertexes.size()][vertexes.size()];
        resetMatrix(matrix);
        fillMatrix(matrix);
        DirGraph outcome = new DirGraph();
        addVertexesToNewGraph(outcome);
        addComplementEdges(matrix, outcome);
        return outcome;
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
            newedges.add(edge1);
            if(!edges.get(i).getv().equals(edges.get(i).getu()))
                {
                DirEdge edge2 = new DirEdge(edges.get(i).getv(), edges.get(i).getu());
                newedges.add(edge2);
                }
            }
        this.edges = newedges;
        //neighbours don't change
        }
    }
