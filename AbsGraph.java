import java.util.*;

public abstract class AbsGraph
    {
    protected ArrayList<Vertex> vertexes;
    protected ArrayList<AbsEdge> edges;
    protected final int radius = 300;//TODO: static (blok statyczny) or in constructor

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

    public void printShortestPath(int beg, int end, ArrayList<Vertex> path)
        {
        if(beg < 0 || beg >= vertexes.size() || end < 0 || end >= vertexes.size())
            {
            System.out.println("One of those indexes does not exist in graph: " + beg + " || " + end);
            }
        else
            {
            printShortestPath(vertexes.get(beg), vertexes.get(end), path);
            }
        }
        
    public void printShortestPath(Vertex beg, Vertex end, ArrayList<Vertex> path)
        {
        if(path == null)
            {
            System.out.println("No path from vertex " + beg.getIndex() + " to " + end.getIndex());
            }
        else
            {
            System.out.print("Path from vertex " + beg.getIndex() + " to " + end.getIndex() + ":  ");
            for(int i = path.size() - 1; i >= 0; --i)
                {
                System.out.print(path.get(i).getIndex() + "  ");
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

    public void unvisitEdges()
        {
        for(int i = 0; i < edges.size(); i++)
            {
            edges.get(i).unvisit();
            }
        }

    public void unvisitVertexes()
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            vertexes.get(i).unvisit();
            }
        }

    public boolean isComplete()
        {
        for(int i = 0; i < vertexes.size(); i++) {
            if(getVertex(i).getNeighboursNumber()!=vertexes.size()-1) {
                return false;
                }
            }
        return true;
        }

    public ArrayList<Vertex> findShortestPath(int beg, int end)
        {
        if(beg < 0 || beg >= vertexes.size())
            return null;
        if(end < 0 || end >= vertexes.size())
            return null;
        return findShortestPath(vertexes.get(beg), vertexes.get(end));
        }
        
    protected void markComponent(Vertex v)
        {
        if(!v.wasVisited())
            {
            v.visit();
            for(int i = 0; i < edges.size(); i++)
                {
                if(edges.get(i).getu().equals(v))
                    {
                    markComponent(edges.get(i).getv());
                    }
                else if(edges.get(i).getv().equals(v))
                    {
                    markComponent(edges.get(i).getu());
                    }
                }
            }
        }
        
    private void initiatePath(Vertex[] path, Vertex beg)
        {
        path[beg.getIndex()] = new Vertex(-1, -1, -1);
        for(int i = 0; i < path.length; i++)
            {
            if(i != beg.getIndex())
                path[i] = null;
            }
        }
        
    private ArrayList<Vertex> turnPathToList(Vertex[] path, Vertex i, Vertex beg)
        {
        ArrayList<Vertex> outcome = new ArrayList<>();
        while(!i.equals(beg))
            {
            outcome.add(i);
            i = path[i.getIndex()];
            }
        outcome.add(i);
        return outcome;
        }
        
    public ArrayList<Vertex> findComponents()
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
        
    public ArrayList<Vertex> findShortestPath(Vertex beg, Vertex end)
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

    protected void resetMatrix(int[][] matrix)
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            for(int j = 0; j < vertexes.size(); j++)
                matrix[i][j] = 0;
            }
        }
        
    protected void addVertexesToNewGraph(AbsGraph g)
        {
        for(int i = 0; i < vertexes.size(); i++)
            {
            g.addVertex();
            }
        }
        
    public abstract void addEdge(Vertex u, Vertex v);
    public abstract String getGraphType();
    public abstract void removeEdge(int i);
    
    public abstract int graphDegree();
    public abstract boolean isTree();
    //public abstract ArrayList<AbsEdge> getEulerPath();
    public abstract boolean isEulerian();
    public abstract boolean hasCycle();
    public abstract boolean isConnected();
    public abstract AbsGraph findComplementGraph();

    public AbsGraph()
        {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
        }
    }
