import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintInfoButton extends JButton implements ActionListener
	{
	private Visualizer visualizer;
	private PrevStatusPanel psp;

	public void actionPerformed(ActionEvent e)
		{
		System.out.println(visualizer.getGraphInfo());
		System.out.print("\n");
		psp.showOK();
		}

		
	//TODO: Error when no vertexes
	public PrintInfoButton(Visualizer visualizer, PrevStatusPanel psp)
		{
		super("get info");
		this.visualizer = visualizer;
		this.psp = psp;
		addActionListener(this);
		}
	}
