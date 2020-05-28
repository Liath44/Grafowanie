import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class RemoveVertexButton extends JButton implements ActionListener
	{
	private final Visualizer visualizer;
	private final VertexNumberPanel vnp;
	private final PrevStatusPanel psp;
	
	public void actionPerformed(ActionEvent e)
		{
		if(visualizer.getGraph().countVertexes() == 0)
			{
			psp.showError();
			return;
			}
		visualizer.getGraph().removeVertex();
		vnp.updateText();
		psp.showOK();
		}
		
	public RemoveVertexButton(Visualizer visualizer, VertexNumberPanel vnp, PrevStatusPanel psp)
		{
		super("-");
		this.visualizer = visualizer;
		this.vnp = vnp;
		this.psp = psp;
		addActionListener(this);
		}
	}
