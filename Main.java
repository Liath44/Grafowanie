import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main
    {
	public static void main(String[] args)
        {
        if(args.length == 0)
            {
            System.out.println("No input file given.");
            return;
            }
        Visualizer visualizer = new Visualizer(true);
        try
            {
            visualizer.createGraphFromFile(args[0]);
            AbsGraph v = visualizer.getGraph();
            System.out.print(v.getGraphType());
            System.out.println("Graph degree: " + v.graphDegree());
            System.out.println("Is complete: " + v.isComplete());
            ArrayList<Vertex> shortestpath = v.findShortestPath(0, 1);
            v.printShortestPath(0, 1, shortestpath);
            System.out.println("Is connected: " + v.isConnected());
            System.out.println("Number of components: " + v.findComponents().size());
            System.out.println("Has cycle: " + v.hasCycle());
            System.out.println("Is tree: " + v.isTree());
            System.out.println("Is Eulerian: " + v.isEulerian());
            System.out.print("\n");
            System.out.println("-- Info on complement graph --");
            AbsGraph complement = v.findComplementGraph();
            complement.printInfo();
            visualizer.exportGraph("output");
            }
        catch(FileNotFoundException e)
            {
            System.out.println("File " + args[0] + " was not found");
            }
        catch(IOException e)
            {
            System.out.println("IOException");
            }
        catch(Visualizer.WrongNumberOfVertexes e)
            {
            System.out.println("Number of vertexes should be > 0");
            System.out.println("Number of vertexes given: " + e.getn());
            }
        catch(Visualizer.GraphTypeWronglySpecified e)
            {
            System.out.println("Graph type is specified by S/D");
            System.out.println("String given: " + e.getNotGraphType());
            }
        catch(Visualizer.DuplicatedEdges e)
            {
            System.out.println("File should not contain duplicated edges");
            System.out.println("Index of duplicated edge: " + e.getEdge());
            }
        catch(Visualizer.NoGraphTypeGiven e)
            {
            System.out.println("Graph type was not given in file");
            }
        catch(Visualizer.WrongEdgesInput e)
            {
            System.out.println("In input files edge is interpreted by pair of integers.");
            System.out.println("Each integer should be a number from 0 to n-1 where n is number of vertexes");
            System.out.println("Index of wrongly formated edge: " + e.getEdge());
            }
        catch(Visualizer.WrongNumberOfEdges e)
            {
            System.out.println("Number of edges should be >= 0");
            System.out.println("Number of edges given: " + e.getn());
            }
        catch(Visualizer.RedundantCharactersInInput e)
            {
            System.out.println("Redundant characters at the end of file were found");
            }
        catch(Visualizer.NoNumberOfVertexesGiven e)
            {
            System.out.println("Number of vertexes was not given in file");
            }
        catch(Visualizer.NoNumberOfEdgesGiven e)
            {
            System.out.println("Number of edges was not given in file");
            }
        finally
            {
            visualizer.clean();
            }
        }
    }
