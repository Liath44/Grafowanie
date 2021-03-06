import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeviewPanel extends JPanel implements ActionListener
	{
	private final Visualizer visualizer;
	private final JLabel bool;
	private final static JLabel signature = new JLabel("Draw mode: ");
	private final JButton button;
	private final PrevStatusPanel psp;

	private void setLabel()
		{
		if(visualizer.getTreeview())
			bool.setText("  treeview");
		else
			bool.setText("circleview");
		}

	public TreeviewPanel(Visualizer visualizer, PrevStatusPanel psp)
		{
		super();
		this.visualizer = visualizer;
		this.psp = psp;
		bool = new JLabel("");
		button = new JButton("mode");
		button.addActionListener(this);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(12);
		layout.setVgap(0);
		setLayout(layout);
		add(button);
		add(signature);
		add(bool);
		setLabel();
		}

	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == button)
			{
			visualizer.setTreeview(!visualizer.getTreeview());
			setLabel();
			psp.showOK();
			}
		}	
	}
