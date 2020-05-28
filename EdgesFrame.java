import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EdgesFrame extends JFrame implements ActionListener
	{
	private final Visualizer visualizer;
	private int currentpage;
	private final JLabel[] labelarr;
	private final JButton next;
	private final JButton prev;
	private final JButton refresh;
	private final static int arrsize = 8;
	
	private void initializeLabels()
		{
		for(int i = 0; i < arrsize; i++)
			{
			labelarr[i] = new JLabel("");
			labelarr[i].setVerticalAlignment(JLabel.CENTER);
			labelarr[i].setHorizontalAlignment(JLabel.CENTER);
			}
		}
	
	private void addLabels()
		{
		for(int i = 0; i < arrsize; i++)
			add(labelarr[i]);
		}
	
	private void setLabels()
		{
		ArrayList<AbsEdge> edges = visualizer.getGraph().getEdgeList();
		int i = currentpage * arrsize;
		int j = 0;
		int end = Math.min(i + arrsize, edges.size());
		while(i < end)
			{
			labelarr[j].setText(edges.get(i).getu().getIndex() + " - " + edges.get(i).getv().getIndex());
			++i;
			++j;
			}
		while(j < arrsize)
			{
			labelarr[j].setText("");
			++j;
			}
		}
		
	public EdgesFrame(Visualizer visualizer)
		{
		super("");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(arrsize + 3, 1));
		setSize(100, 350);
		this.visualizer = visualizer;
		currentpage = 0;
		labelarr = new JLabel[arrsize];
		initializeLabels();
		next = new JButton(">");
		prev = new JButton("<");
		refresh = new JButton("R");
		next.addActionListener(this);
		prev.addActionListener(this);
		refresh.addActionListener(this);
		addLabels();
		add(prev);
		add(next);
		add(refresh);
		setLabels();
		setResizable(false);
		setVisible(true);
		}

	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == next && currentpage < visualizer.getGraph().countEdges()/arrsize)
			{
			++currentpage;
			setLabels();
			}
		else if(source == prev && currentpage > 0)
			{
			--currentpage;
			setLabels();
			}
		else if(source == refresh)
			setLabels();
		}
	}
