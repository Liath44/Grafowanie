import java.util.ArrayList;

public class Graph extends AbsGraph
    {
    public void addEdge(Vertex u, Vertex v)
        {
        //update neighbours
        u.addNeighbour(v);
        //if u == v we don't want to add neighbour for a second time
        if(!u.equals(v))
            {
            v.addNeighbour(u);
            }
        //create and add new edge
        Edge edge = new Edge(u, v);
        edges.add(edge);
        }

    /*
     * Iterates through all edges to check whether neighbours are set properly
     * If not updates them
     *
     * Calling this function assures properly set neighbours
     */
    private void updateNeighbours()
        {
        for(int i = 0; i < edges.size(); i++)
            {
            AbsEdge edge = edges.get(i);
            if(!edge.getu().hasThisNeighbour(edge.getv()))
                edge.getu().addNeighbour(edge.getv());
            if(!edge.getv().hasThisNeighbour(edge.getu()) && !edge.getv().equals(edge.getu()))
                edge.getv().addNeighbour(edge.getu());
            }
        }

    /*
     * S - Straight - Prosty (graf)
     */
    public String getGraphType()
        {
        return "S\n";
        }

    public void removeEdge(int i)
        {
        edges.get(i).getu().removeNeighbour(edges.get(i).getv());
        if(!edges.get(i).getv().equals(edges.get(i).getu()))
            {
            edges.get(i).getv().removeNeighbour(edges.get(i).getu());
            }
        edges.remove(i);
        }

    public int graphDegree()
        {
        int degree=0;
        for(int i=0;i<vertexes.size();i++) {
            if (degree < vertexes.get(i).getNeighboursNumber())
                degree = vertexes.get(i).getNeighboursNumber();
            }
        return degree;
        }

    public boolean hasCycle()
        {
        ArrayList<Vertex> components = findComponents();
        for(int i = 0; i < components.size(); i++)
            {
            if(hasCycle(components.get(i).getIndex(), -1))
                {
                unvisitVertexes();
                return true;
                }
            }
        unvisitVertexes();
        return false;
        }
        
	//needed for isTree()
 	//check if graph reachable from vertex 0 
    //check if graph is cyclic  
    //marks all vertices reachablefrom 0.
        
    private Boolean hasCycle(int v, int parent) 
    { 
        vertexes.get(v).visit();
        Integer i; 
        Vertex t = vertexes.get(v);
         
        for(int j=0; j<vertexes.get(v).getNeighboursNumber(); j++)
        { 
            Vertex neighbour=t.getNeighbour(j);
            i = neighbour.getIndex();  
         
	    if (!vertexes.get(i).wasVisited()) 
            { 
                if (hasCycle(i, v)) 
                    return true; 
            } 
             
            else if (i != parent) 
               return true; 
        } 
        return false; 
    } 
  
    
    public boolean isTree() 
        {
        if(!isConnected())
            return false;
            
        if (hasCycle(0, -1))
            {
            unvisitVertexes();
            return false;
            }
         
        for(int u = 0; u < vertexes.size(); u++) 
            {
            if (!vertexes.get(u).wasVisited())
                {
                unvisitVertexes();
                return false;
                }
            }
        
        return true; 
        }

    public boolean isConnected()
        {
        if(vertexes.size() == 0)
            return true;
        markComponent(vertexes.get(0));
        for(int i = 0; i < vertexes.size(); i++)
            {
            if(!vertexes.get(i).wasVisited())
                {
                unvisitVertexes();
                return false;
                }
            }
        unvisitVertexes();
        return true;
        }
    
    public boolean isEulerian()
        {
        if(!isConnected())
            return false;
        for(int i = 0; i < vertexes.size(); i++)
            {
            if(vertexes.get(i).getNeighboursNumber() % 2 == 1)
                return false;
            }
        return true;
        }
        
    public Graph()
        {
        super();
        }

    /*
     * Creates Graph from another graph
     *
     * AbsGraph should rather be of type DirGraph
     *
     * DirGraph is transformed into Graph by having every edge be replaced
     * from Edge to DirEge
     */
    public Graph(AbsGraph v)
        {
        //vertexes don't change
        this.vertexes = v.getVertexList();
        ArrayList<AbsEdge> edges = v.getEdgeList();
        ArrayList<AbsEdge> newedges = new ArrayList<>();
        for(int i = 0; i < edges.size(); i++)
            {
            //In DirGraph we can have edge from v to u AND from u to v
            //In Graph we can only have edge from v to u OR from u to v
            if(!edgeExists(newedges, edges.get(i).getu(), edges.get(i).getv()))
                {
                Edge edge = new Edge(edges.get(i).getu(), edges.get(i).getv());
                newedges.add(edge);
                }
            }
        this.edges = newedges;
        //Update neighbours and x, y coordinates
        updateNeighbours();
        }
    }
