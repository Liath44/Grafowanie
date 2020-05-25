import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
	{
	private Visualizer visualizer;
	
	public GUI()
		{
		super("Graphtastic");
		visualizer = new Visualizer(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLayout(new FlowLayout());
		
		VertexNumberPanel vnp = new VertexNumberPanel(visualizer);
		PrevStatusPanel psp = new PrevStatusPanel();
		
		add(new AddVertexButton(visualizer, vnp, psp));
		add(new RemoveVertexButton(visualizer, vnp, psp));
		add(new PrintInfoButton(visualizer, psp));
		add(vnp);
		add(new AddEdgePanel(visualizer, psp));
		add(new RemoveEdgePanel(visualizer, psp));
		add(new ImportPanel(visualizer, vnp, psp));
		add(new ExportPanel(visualizer, psp));
		add(new TurnToOtherGraphButton(visualizer, psp));
		add(new ComplementGraphButton(visualizer, psp));
		add(psp);
		
		setVisible(true);
		}
	}
