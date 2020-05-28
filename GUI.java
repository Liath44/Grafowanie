import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
	{
	private final Visualizer visualizer;
	
	public GUI()
		{
		super("graphtastic");
		visualizer = new Visualizer(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 335);
		setLayout(new FlowLayout());
		
		VertexNumberPanel vnp = new VertexNumberPanel(visualizer);
		PrevStatusPanel psp = new PrevStatusPanel();
		
		add(new AddVertexButton(visualizer, vnp, psp));
		add(new RemoveVertexButton(visualizer, vnp, psp));
		add(vnp);
		add(new IndexesPanel(visualizer, psp));
		add(new TreeviewPanel(visualizer, psp));
		add(new AddEdgePanel(visualizer, psp));
		add(new RemoveEdgePanel(visualizer, psp));
		add(new ShowEdgesButton(visualizer, psp));
		add(new PrintInfoButton(visualizer, psp));
		add(new ImportPanel(visualizer, vnp, psp));
		add(new ExportPanel(visualizer, psp));
		add(new TurnToOtherGraphButton(visualizer, psp));
		add(new ComplementGraphButton(visualizer, psp));
		add(psp);
		
		setResizable(false);
		setVisible(true);
		}
	}
