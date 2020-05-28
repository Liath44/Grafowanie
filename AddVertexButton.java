import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVertexButton extends JButton implements ActionListener
	{
	private final Visualizer visualizer;
	private final VertexNumberPanel vnp;
	private final PrevStatusPanel psp;
		
	public void actionPerformed(ActionEvent e)
		{
		visualizer.getGraph().addVertex();
		vnp.updateText();
		psp.showOK();
		}
		
	public AddVertexButton(Visualizer visualizer, VertexNumberPanel vnp, PrevStatusPanel psp)
		{
		super("+");
		this.visualizer = visualizer;
		this.vnp = vnp;
		this.psp = psp;
		addActionListener(this);
		}
	}
