import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.awt.Graphics;

public class Canvas extends JPanel
    {
    private AbsGraph v;
    //private TreeDFS tree;
    private boolean indexes;
    private boolean treeview;
    private Scanner scanner;
    private FileWriter filewriter;

    class ImproperInputException extends Exception{}

	public void paintComponent(Graphics g)
        {
        System.out.println("Paint graph");
	    }

	/*
	 * Turns AbsGraph v to Directed Graph
	 */
    public void turnToDirGraph()
        {
        if(Objects.equals(v.getGraphType(), "S\n"))
            {
            DirGraph newv = new DirGraph(v);
            v = newv;
            }
        }

    /*
     * Turns AbsGraph v to Graph
     */
    public void turnToGraph()
        {
        if(Objects.equals(v.getGraphType(), "D\n"))
            {
            Graph newv = new Graph(v);
            v = newv;
            }
        }

    private void initializeGraph(String graphtype, int novertexes, int noedges) throws ImproperInputException
        {
        if(novertexes <= 0 || noedges < 0)
            throw new ImproperInputException();
        if(Objects.equals(graphtype, "S"))
            v = new Graph();
        else if(Objects.equals(graphtype, "D"))
            v = new DirGraph();
        else
            throw new ImproperInputException();
        for(int i = 0; i < novertexes; i++)
            {
            Vertex vertex = new Vertex(i, novertexes);
            v.addVertex(vertex);
            }
        }

    private void initializeEdge(int ver1, int ver2, int novertexes) throws ImproperInputException
        {
        //TODO: Implement (v, v) edges
        if(ver1 >= novertexes || ver2 >= novertexes || ver1 < 0 || ver2 < 0 || ver1 == ver2)
            throw new ImproperInputException();
        if(v.edgeExists(v.getVertex(ver1), v.getVertex(ver2)))
            throw new ImproperInputException();
        v.addEdge(v.getVertex(ver1), v.getVertex(ver2));
        }

	public void createGraphFromFile(String path) throws FileNotFoundException, ImproperInputException
        {
        scanner = new Scanner(new File(path));
        if(!scanner.hasNext())
            throw new ImproperInputException();
        String graphtype = scanner.next();
        if(!scanner.hasNextInt())
            throw new ImproperInputException();
        int novertexes = scanner.nextInt();
        if(!scanner.hasNextInt())
            throw new ImproperInputException();
        int noedges = scanner.nextInt();
        initializeGraph(graphtype, novertexes, noedges);
        for(int i = 0; i < noedges; i++)
            {
            if(!scanner.hasNextInt())
                throw new ImproperInputException();
            int ver1 = scanner.nextInt();
            if(!scanner.hasNextInt())
                throw new ImproperInputException();
            int ver2 = scanner.nextInt();
            initializeEdge(ver1, ver2, novertexes);
            }
        if(scanner.hasNext())
            throw new ImproperInputException();
        scanner.close();
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
        filewriter.close();
        }

    public Canvas(boolean isdir)
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
    }
