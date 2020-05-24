import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.awt.Graphics;

public class Visualizer extends JPanel
    {
    private AbsGraph v;
    //private TreeDFS tree;
    private boolean indexes;
    private boolean treeview;
    private Scanner scanner;
    private FileWriter filewriter;

	public void paintComponent(Graphics g)
        {
        System.out.println("Paint graph");
	    }
	  
	public String getShortestPathInfo(int v1, int v2)
		{
		if(v1 < 0 || v1 >= v.getVertexList().size())
			return "Path from vertex " + v1 + " to " + v2 + ": Graph does not contain vertex " + v1;
		if(v2 < 0 || v2 >= v.getVertexList().size())
			return "Path from vertex " + v1 + " to " + v2 + ": Graph does not contain vertex " + v2;
		return getShortestPathInfo(v.getVertex(v1), v.getVertex(v2));
		}	
	    
	public String getShortestPathInfo(Vertex v1, Vertex v2)
		{
		ArrayList<Vertex> path = v.findShortestPath(v1, v2);
		StringBuilder builder = new StringBuilder();
		if(path == null)
			{
			builder.append("No path from vertex ");
			builder.append(v1.getIndex());
			builder.append(" to ");
			builder.append(v2.getIndex());
			return builder.toString();
			}
		builder.append("Path from vertex ");
		builder.append(v1.getIndex());
		builder.append(" to ");
		builder.append(v2.getIndex());
		builder.append(": ");
		for(int i = path.size() - 1; i > 0; i--)
			{
			builder.append(path.get(i).getIndex());
			builder.append(" -> ");
			}
		builder.append(path.get(0).getIndex());
		return builder.toString();
		}	
	    
	public String getGraphInfo()
        {
        StringBuilder builder = new StringBuilder();
        builder.append("Graph type: ");
        builder.append(v.getGraphType());
        builder.append("Number of vertexes: ");
        builder.append(v.getVertexList().size());
        builder.append("\n");
        builder.append("Number of edges: ");
        builder.append(v.getEdgeList().size());
        builder.append("\n");
        builder.append("Edges:\n");
        for(AbsEdge edge: v.getEdgeList())
            {
            builder.append(edge.getu().getIndex());
            builder.append(" - ");
            builder.append(edge.getv().getIndex());
            builder.append("\n");    
            }
        builder.append("Graph degree: ");
        builder.append(v.graphDegree());
        builder.append("\n");
        builder.append("Number of components: ");
        builder.append(v.findComponents().size());
        builder.append("\n");
        builder.append("Has cycle: ");
        builder.append(v.hasCycle());
        builder.append("\n");
        builder.append("Is Eulerian: ");
        builder.append(v.isEulerian());
        builder.append("\n");
        builder.append("Is complete: ");
        builder.append(v.isComplete());
		builder.append("\n");
		builder.append("Is connected: ");
		builder.append(v.isConnected());
		builder.append("\n");
		builder.append("Is tree: ");
		builder.append(v.isTree());
		return builder.toString();
        }

	/*
	 * Turns AbsGraph v to Directed Graph
	 */
    public void turnToDirGraph()
        {
        if(Objects.equals(v.getGraphType(), "S\n"))
            {
            v = new DirGraph(v);
            }
        }

    /*
     * Turns AbsGraph v to Graph
     */
    public void turnToGraph()
        {
        if(Objects.equals(v.getGraphType(), "D\n"))
            {
            v = new Graph(v);
            }
        }

    private void initializeGraph(String graphtype, int novertexes, int noedges)
    throws WrongNumberOfVertexes, WrongNumberOfEdges, GraphTypeWronglySpecified
        {
        if(novertexes <= 0)
            throw new WrongNumberOfVertexes(novertexes);
        if(noedges < 0)
            throw new WrongNumberOfEdges(noedges);
        if(Objects.equals(graphtype, "S"))
            v = new Graph();
        else if(Objects.equals(graphtype, "D"))
            v = new DirGraph();
        else
            throw new GraphTypeWronglySpecified(graphtype);
        for(int i = 0; i < novertexes; i++)
            {
            Vertex vertex = new Vertex(i, novertexes);
            v.addVertex(vertex);
            }
        }

    private void initializeEdge(int ver1, int ver2, int novertexes, int i)
    throws WrongEdgesInput, DuplicatedEdges
        {
        if(ver1 >= novertexes || ver2 >= novertexes || ver1 < 0 || ver2 < 0)
            throw new WrongEdgesInput(i);
        if(v.edgeExists(v.getVertex(ver1), v.getVertex(ver2)))
            throw new DuplicatedEdges(i);
        v.addEdge(v.getVertex(ver1), v.getVertex(ver2));
        }

	public void createGraphFromFile(String path)
    throws FileNotFoundException, NoGraphTypeGiven, NoNumberOfVertexesGiven, NoNumberOfEdgesGiven,
    WrongNumberOfVertexes, WrongNumberOfEdges, GraphTypeWronglySpecified, WrongEdgesInput, DuplicatedEdges,
    RedundantCharactersInInput
        {
        scanner = new Scanner(new File(path));
        if(!scanner.hasNext())
            throw new NoGraphTypeGiven();
        String graphtype = scanner.next();
        if(!scanner.hasNextInt())
            throw new NoNumberOfVertexesGiven();
        int novertexes = scanner.nextInt();
        if(!scanner.hasNextInt())
            throw new NoNumberOfEdgesGiven();
        int noedges = scanner.nextInt();
        initializeGraph(graphtype, novertexes, noedges);
        for(int i = 0; i < noedges; i++)
            {
            if(!scanner.hasNextInt())
                throw new WrongEdgesInput(i);
            int ver1 = scanner.nextInt();
            if(!scanner.hasNextInt())
                throw new WrongEdgesInput(i);
            int ver2 = scanner.nextInt();
            initializeEdge(ver1, ver2, novertexes, i);
            }
        if(scanner.hasNext())
            throw new RedundantCharactersInInput();
        }
        
    public void exportGraph(String path) throws IOException
        {
        filewriter = new FileWriter(path);
        filewriter.write(v.getGraphType());
        filewriter.write(v.countVertexes().toString() + " " + v.countEdges().toString() + "\n");
        for(int i = 0; i < v.countEdges(); i++)
            {
            Integer ver1 = v.getEdge(i).getu().getIndex();
            Integer ver2 = v.getEdge(i).getv().getIndex();
            filewriter.write(ver1.toString() + " " + ver2.toString() + "\n");
            }
        }

    public void clean()
        {
        try
            {
            if(filewriter != null)
                filewriter.close();
            }
        catch(IOException e)
            {
            System.out.println("IOException - closing filewriter");
            }
        if(scanner != null)
            scanner.close();
        }

    public Visualizer(boolean isdir)
        {
        if(isdir)
            v = new DirGraph();
        else
            v = new Graph();
        indexes = false;
        treeview = false;
        scanner = null;
        filewriter = null;
        }

    public AbsGraph getGraph()
        {
        return v;
        }

    static class WrongNumberOfVertexes extends Exception{
        private final int n;
        public WrongNumberOfVertexes(int n){
            super();
            this.n = n;
        }
        public int getn(){
            return n;
        }
    }
    static class WrongNumberOfEdges extends Exception{
        private final int n;
        public WrongNumberOfEdges(int n){
            super();
            this.n = n;
        }
        public int getn(){
            return n;
        }
    }
    static class GraphTypeWronglySpecified extends Exception{
        private final String notgraphtype;
        public GraphTypeWronglySpecified(String ngt){
            super();
            notgraphtype = ngt;
        }
        public String getNotGraphType(){
            return notgraphtype;
        }
    }
    static class WrongEdgesInput extends Exception{
        private final int edge;
        public WrongEdgesInput(int edge){
            this.edge = edge;
        }
        public int getEdge(){
            return edge;
        }
    }
    static class DuplicatedEdges extends Exception{
        private final int edge;
        public DuplicatedEdges(int edge){
            this.edge = edge;
        }
        public int getEdge(){
            return edge;
        }
    }
    static class NoGraphTypeGiven extends Exception{}
    static class NoNumberOfVertexesGiven extends Exception{}
    static class NoNumberOfEdgesGiven extends Exception{}
    static class RedundantCharactersInInput extends Exception{}
    }
