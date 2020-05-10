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

    public int graphDegree()
        {
        return 0;
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
        
	//needed for isCyclic()
      public boolean isCyclicUtil(int i, boolean[] stack)  
    {    
        if (Stack[i]) 
            return true; 
  
        if (vertexes.get(i).wasVisited()) 
            return false; 
              
       vertexes.get(i).visit(); 
  
        Stack[i] = true; 
        Vertex t = vertexes.get(i);
        
        for(int j=0; j<t.getNeighboursNumber(); j++){
            Vertex neighbour=t.getNeighbour(i);
            if (isCyclicUtil(neighbour.getIndex(), stack)) 
                return true; }
                  
        stack[i] = false; 
  
        return false; 
    } 
  
    //check if the graph contains a cycle 
    //needed for isTree()  
    public boolean isCyclic()  
    { 
        unvisitVertexes();
        boolean[] stack = new boolean[vertexes.size()]; 
      
        for (int i = 0; i < vertexes.size(); i++) 
            if (isCyclic(i, stack)) 
                return true; 
  
        return false; 
    }  
    //needed for isConnected()
     void dfs1(int x,ArrayList<Vertex> vertexes1) 
    { 
        
        vertexes1.get(x).visit();
        Vertex t = vertexes1.get(x);
         
        for(int i=0; i<t.getNeighboursNumber(); i++) {
            Vertex neighbour=t.getNeighbour(i);
            for(int j=0; j<edges.size(); j++){
            AbsEdge edge = edges.get(j);
            
            if (!neighbour.wasVisited()&&edge.getu().hasThisNeighbour(edge.getv())) 
                dfs1(i, vertexes1); 
        } }
    } 
  
     //needed for isConnected()
     void dfs2(int x,ArrayList<Vertex> vertexes2)  
    { 
        vertexes2.get(x).visit();
     
        Vertex t = vertexes2.get(x);
        
         for(int i=0; i<t.getNeighboursNumber(); i++) {
            Vertex neighbour=t.getNeighbour(i);
            for(int j=0; j<edges.size(); j++){
            AbsEdge edge = edges.get(j);
            
            if (!neighbour.wasVisited()&&edge.getv().hasThisNeighbour(edge.getu())) 
                dfs1(i, vertexes2); 
        } }
    } 

    //needed for isTree()
    public boolean isConnected() 
    { 
       ArrayList<Vertex> vertexes1 = new ArrayList<>();
       vertexes1=vertexes;
       ArrayList<Vertex> vertexes2 = new ArrayList<>();
       vertexes2=vertexes;
        
        for (int i = 0; i <= vertexes.size(); i++) 
        vertexes1.get(i).unvisit();
        dfs1(0,vertexes1); 
  
         
        for (int i = 0; i <= vertexes.size(); i++) 
        vertexes2.get(i).unvisit();
        dfs2(0,vertexes2); 
  
        for (int i = 0; i <= vertexes.size(); i++) 
        {          
            
            if (!vertexes1.get(i).wasVisited()&& !vertexes2.get(i).wasVisited()) 
                return false; 
        } 
  
        
        return true; 
    } 
         
   public boolean isTree(){
 
    if(edges.size()!=vertexes.size()-1)
        return false;
    if(isCyclic())
        return false;
    if(isConnected())
        return true;
    else return false;
               
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
  boolean isEulerian() 
	{
	return false;
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
