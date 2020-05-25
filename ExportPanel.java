import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportPanel extends JPanel implements ActionListener
	{
	private Visualizer visualizer;
	private JTextField exportfield;
	private JButton exportbutton;
	private PrevStatusPanel psp;

	public ExportPanel(Visualizer visualizer, PrevStatusPanel psp)
		{
		super();
		this.visualizer = visualizer;
		this.psp = psp;
		setLayout(new GridBagLayout());
		JLabel label = new JLabel("export graph: ");
		exportfield = new JTextField(16);
		exportbutton = new JButton("export");
		exportbutton.addActionListener(this);
		add(label);
		add(exportfield);
		add(exportbutton);
		}

	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == exportbutton)
			{
			try
				{
				visualizer.exportGraph(exportfield.getText());
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
			finally 
				{
				visualizer.clean();
				}
			}
		}
	}
