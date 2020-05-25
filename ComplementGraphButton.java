import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComplementGraphButton extends JButton implements ActionListener
	{
	private Visualizer visualizer;
	private PrevStatusPanel psp;
	
	public ComplementGraphButton(Visualizer visualizer, PrevStatusPanel psp)
		{
		super("complement graph");
		this.visualizer = visualizer;
		this.psp = psp;
		addActionListener(this);
		}
		
	public void actionPerformed(ActionEvent e)
		{
		visualizer.turnToComplementGraph();
		psp.showOK();
		}
	}
