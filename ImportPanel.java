import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportPanel extends JPanel implements ActionListener 
	{
	private final Visualizer visualizer;
	private final JTextField importfield;
	private final JButton importbutton;
	private final VertexNumberPanel vnp;
	private final PrevStatusPanel psp;
	
	public ImportPanel(Visualizer visualizer, VertexNumberPanel vnp, PrevStatusPanel psp)
		{
		super();
		this.visualizer = visualizer;
		this.vnp = vnp;
		this.psp = psp;
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		layout.setHgap(5);
		setLayout(layout);
		JLabel label = new JLabel("import graph: ");
		importfield = new JTextField(16);
		importbutton = new JButton("import");
		importbutton.addActionListener(this);
		add(label);
		add(importfield);
		add(importbutton);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == importbutton)
			{
			try
				{
				visualizer.createGraphFromFile(importfield.getText());
				vnp.updateText();
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
