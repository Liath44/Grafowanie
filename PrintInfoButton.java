import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintInfoButton extends JButton implements ActionListener
	{
	private final Visualizer visualizer;
	private final PrevStatusPanel psp;

	public void actionPerformed(ActionEvent action)
		{
		try
			{
			String message = visualizer.getGraphInfoHTML();
			psp.showOK();
			EventQueue.invokeLater(new Runnable()
				{
				public void run()
					{
					new PrintInfoFrame(message);
					}
				});
			}
		catch(Exception e)
			{
			psp.showError();
			EventQueue.invokeLater(new Runnable()
				{
				public void run()
					{
					new ErrorFrame(e.getMessage());
					}
				});
			}
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
