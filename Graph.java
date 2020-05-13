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

    //TODO: Move this function to AbsGraph after SPRINT 2
    public ArrayList<Vertex> findComponents()//Ja chcę to
        {
        ArrayList<Vertex> outcome = new ArrayList<>();
        for(int i = 0; i < vertexes.size(); i++)
            {
            if(!vertexes.get(i).wasVisited())
                {
                outcome.add(vertexes.get(i));
                markComponent(vertexes.get(i));
                }
            }
        unvisitVertexes();
        return outcome;
        }

	//needed for isTree()
 	//check if graph reachable from vertex 0 
        //check if graph is cyclic  
        //marks all vertices reachablefrom 0.
 	
    Boolean isCyclic(int v, int parent) 
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
                if (isCyclic(i, v)) 
                    return true; 
            } 
             
            else if (i != parent) 
               return true; 
        } 
        return false; 
    } 
  
    
    public boolean isTree() 
    { 
        
        for (int i = 0; i < vertexes.size(); i++) 
            vertexes.get(i).unvisit(); 
  
        if (isCyclic(0, -1)) 
            return false; 
         
        for (int u = 0; u < vertexes.size(); u++) 
            if (!vertexes.get(u).wasVisited()) 
                return false; 
  
        return true; 
    } 
    

    public AbsGraph findComplementGraph()
        {
        return null;
        }

    public ArrayList<Vertex> getHamiltonianPath()
        {
        return null;
        }

    public ArrayList<AbsEdge> getEulerPath()//L
        {
        return null;
        }

	//needed for isEulerian()
	//function used in Depth First Search for a graph 
     void DFSUtil(int v) 
    { 
        vertexes.get(v).visit();
        
        Vertex t = vertexes.get(v);
	Integer j;
        
        for(int i=0; i<vertexes.get(v).getNeighboursNumber(); i++)
        { 
          Vertex neighbour=t.getNeighbour(i);
	  j = neighbour.getIndex();
		
            if (!vertexes.get(j).wasVisited()) 
                DFSUtil(j); 
        } 
    } 
  


    // check if all non-zero degree vertices are connected.
   // needded for isEulerian()
    public boolean isConnected() 
    { 
            
        int i; 
        for (i = 0; i < vertexes.size(); i++) 
          vertexes.get(i).unvisit();
                    
        for (i = 0; i < vertexes.size(); i++) 
             if(  vertexes.get(i).getNeighboursNumber()!=0)
                break; 
        
        if (i == vertexes.size()) 
            return true; 
  
        
        DFSUtil(i); 
  
        for (i = 0; i <vertexes.size() ; i++) 
             if (vertexes.get(i).wasVisited() == false && vertexes.get(i).getNeighboursNumber() > 0) 
               return false; 
  
        return true; 
    } 
  
   // check if given graph is Eulerian
    public boolean isEulerian() //prev ver - boolean isEulerian()
    {  
        if (!isConnected()) 
            return false; 
  
        // Count vertices with odd degree 
        int odd = 0; 
        for (int i = 0; i < vertexes.size(); i++) 
            if (vertexes.get(i).getNeighboursNumber()%2!=0) 
                odd++; 
  
        if (odd > 2) 
            return false; 
  
        return true; 
    } 
  

    //TODO: Move this function to AbsGraph after SPRINT 2
    public ArrayList<Vertex> findShortestPath(Vertex beg, Vertex end)//I to
        {
        ArrayList<Vertex> queue = new ArrayList<>();
        Vertex[] path = new Vertex[vertexes.size()];
        initiatePath(path, beg);
        queue.add(beg);
        while(!queue.isEmpty())
            {
            Vertex cur = queue.get(0);
            queue.remove(0);
            if(cur.equals(end))
                {
                unvisitVertexes();
                return turnPathToList(path, end, beg);
                }
            for(int i = 0; i < cur.getNeighboursNumber(); i++)
                {
                Vertex neighbour = cur.getNeighbour(i);
                if(!neighbour.wasVisited())
                    {
                    path[neighbour.getIndex()] = cur;
                    queue.add(neighbour);
                    neighbour.visit();
                    }
                }
            }
        unvisitVertexes();
        return null;
        }

    public boolean isComplete()
        {
        return false;
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
