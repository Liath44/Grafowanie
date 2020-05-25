import javax.swing.*;

public class VertexNumberPanel extends JPanel
	{
	private Visualizer visualizer;
	private JLabel text;
	
	public VertexNumberPanel(Visualizer visualizer)
		{
		super();
		this.visualizer = visualizer;
		text = new JLabel("NO vertexes: " + visualizer.getGraph().countVertexes());
		add(text);
		}
		
	public void updateText()
		{
		text.setText("NO vertexes: " + visualizer.getGraph().countVertexes());
		}
	}
