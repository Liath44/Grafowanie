import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexesPanel extends JPanel implements ActionListener
	{
	private final Visualizer visualizer;
	private final JLabel bool;
	private final static JLabel signature = new JLabel("Show indexes: ");
	private final JButton button;
	private final PrevStatusPanel psp;
	
	private void setLabel()
		{
		if(visualizer.getIndexes())
			bool.setText("yes");
		else
			bool.setText("  no");
		}
	
	public IndexesPanel(Visualizer visualizer, PrevStatusPanel psp)
		{
		super();
		this.visualizer = visualizer;
		this.psp = psp;
		bool = new JLabel("");
		button = new JButton("indexes");
		button.addActionListener(this);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(17);
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
			visualizer.setIndexes(!visualizer.getIndexes());
			setLabel();
			psp.showOK();
			}
		}
	}
