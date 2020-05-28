import javax.swing.*;
import java.awt.*;

public class PrintInfoFrame extends JFrame
	{
	private final String message;
	
	public PrintInfoFrame(String message)
		{
		super("graph info");
		this.message = message;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(230, 200);
		setLayout(new GridBagLayout());
		JLabel text = new JLabel(message);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		setVisible(true);
		setResizable(false);
		}
	}
