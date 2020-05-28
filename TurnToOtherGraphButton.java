import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnToOtherGraphButton extends JButton implements ActionListener
	{
	private final Visualizer visualizer;
	private final PrevStatusPanel psp;
	
	public TurnToOtherGraphButton(Visualizer visualizer, PrevStatusPanel psp)
		{
		super("switch types");
		this.visualizer = visualizer;
		this.psp = psp;
		addActionListener(this);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		if(visualizer.getGraph().getGraphType().equals("S\n"))
			visualizer.turnToDirGraph();
		else
			visualizer.turnToGraph();
		psp.showOK();
		}
	}
