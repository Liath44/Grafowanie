import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
    {
	public static void main(String[] args)
        {
        if(args.length == 0)
            {
            System.out.println("No input file given.");
            return;
            }
        Canvas canvas = new Canvas(true);
        try
            {
            canvas.createGraphFromFile(args[0]);
            AbsGraph v = canvas.getGraph();
            v.addVertex();
            v.addVertex();
            v.addEdge(0, 5);
            v.removeVertex(6);
            v.removeEdge(2, 1);
            canvas.turnToGraph();
            v.printInfo();
            canvas.exportGraph("output");
            }
        catch(FileNotFoundException e)
            {
            System.out.println("File " + args[0] + " was not found");
            }
        catch(IOException e)
            {
            System.out.println("IOException");
            }
        catch(Canvas.WrongNumberOfVertexes e)
            {
            System.out.println("Number of vertexes should be > 0");
            System.out.println("Number of vertexes given: " + e.getn());
            }
        catch(Canvas.GraphTypeWronglySpecified e)
            {
            System.out.println("Graph type is specified by S/D");
            System.out.println("String given: " + e.getNotGraphType());
            }
        catch(Canvas.DuplicatedEdges e)
            {
            System.out.println("File should not contain duplicated edges");
            System.out.println("Index of duplicated edge: " + e.getEdge());
            }
        catch(Canvas.NoGraphTypeGiven e)
            {
            System.out.println("Graph type was not given in file");
            }
        catch(Canvas.WrongEdgesInput e)
            {
            System.out.println("In input files edge is interpreted by pair of integers.");
            System.out.println("Each integer should be a number from 0 to n-1 where n is number of vertexes");
            System.out.println("Index of wrongly formated edge: " + e.getEdge());
            }
        catch(Canvas.WrongNumberOfEdges e)
            {
            System.out.println("Number of edges should be >= 0");
            System.out.println("Number of edges given: " + e.getn());
            }
        catch(Canvas.RedundantCharactersInInput e)
            {
            System.out.println("Redundant characters at the end of file were found");
            }
        catch(Canvas.NoNumberOfVertexesGiven e)
            {
            System.out.println("Number of vertexes was not given in file");
            }
        catch(Canvas.NoNumberOfEdgesGiven e)
            {
            System.out.println("Number of edges was not given in file");
            }
        }
    }
