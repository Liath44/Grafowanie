import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
    {
	public static void main(String[] args)
        {
        Canvas canvas = new Canvas(true);
        try
            {
            canvas.createGraphFromFile("graphfile");
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
        catch(Canvas.ImproperInputException e)
            {
            System.out.println("Improper input file");
            }
        catch(FileNotFoundException e)
            {
            System.out.println("File was not found");
            }
        catch(IOException e)
            {
            System.out.println("IOException");
            }
        }
    }
