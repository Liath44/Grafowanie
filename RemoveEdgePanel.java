import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveEdgePanel extends JPanel implements ActionListener
	{
	private Visualizer visualizer;
	private JTextField ver1;
	private JTextField ver2;
	private JButton removeedgebutton;
	private PrevStatusPanel psp;

	public RemoveEdgePanel(Visualizer visualizer, PrevStatusPanel psp)
		{
		super();
		this.visualizer = visualizer;
		this.psp = psp;
		setLayout(new GridBagLayout());
		JLabel ver1label = new JLabel("ver1: ");
		JLabel ver2label = new JLabel("ver2: ");
		ver1 = new JTextField(3);
		ver2 = new JTextField(3);
		removeedgebutton = new JButton("remove edge");
		removeedgebutton.addActionListener(this);
		add(ver1label);
		add(ver1);
		add(ver2label);
		add(ver2);
		add(removeedgebutton);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == removeedgebutton)
			{
			String v1string = ver1.getText();
			String v2string = ver2.getText();
			try
				{
				int v1 = Integer.parseInt(v1string);
				int v2 = Integer.parseInt(v2string);
				visualizer.getGraph().removeEdgeStable(v1, v2);
				psp.showOK();
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
		}
	}
