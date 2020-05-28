import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
	{
	private final Visualizer visualizer;
	
	public GUI()
		{
		super("graphtastic");
		visualizer = new Visualizer(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 610);//400,335
		setLayout(new FlowLayout());
		
		VertexNumberPanel vnp = new VertexNumberPanel(visualizer);
		PrevStatusPanel psp = new PrevStatusPanel();
		
		add(new AddVertexButton(visualizer, vnp, psp));
		add(new RemoveVertexButton(visualizer, vnp, psp));
		add(vnp);
		//add(new IndexesPanel(visualizer, psp));
		//add(new TreeviewPanel(visualizer, psp));
		add(new AddEdgePanel(visualizer, psp));
		add(new RemoveEdgePanel(visualizer, psp));
		add(new ShowEdgesButton(visualizer, psp));
		add(new PrintInfoButton(visualizer, psp));
		add(new ImportPanel(visualizer, vnp, psp));
		add(new ExportPanel(visualizer, psp));
		add(new TurnToOtherGraphButton(visualizer, psp));
		add(new ComplementGraphButton(visualizer, psp));
		add(psp);
        visualizer.setPreferredSize(new Dimension(350, 300));
		add(visualizer);
		JButton b1 = new JButton("Repaint!");		//przycisk wywołujący wybór pliku
		b1.setSize(30,100);
		b1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				visualizer.repaint();
				psp.showOK();
			}
		});
		add(b1);
		
		setResizable(false);
		setVisible(true);
		}
	}
