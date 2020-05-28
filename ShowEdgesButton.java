import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowEdgesButton extends JButton implements ActionListener
	{
	private final Visualizer visualizer;
	private final PrevStatusPanel psp;
	
	public void actionPerformed(ActionEvent action)
		{
		EventQueue.invokeLater(new Runnable()
			{
			public void run()
				{
				new EdgesFrame(visualizer);
				}
			});
		}
		
	public ShowEdgesButton(Visualizer visualizer, PrevStatusPanel psp)
		{
		super("show edges");
		this.psp = psp;
		this.visualizer = visualizer;
		addActionListener(this);
		}
	}
