import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends JFrame
	{
	private String message;
	
	public ErrorFrame(String message)
		{
		super("ERROR");
		this.message = message;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 100);
		setLayout(new GridBagLayout());
		JLabel text = new JLabel(message);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		setVisible(true);
		}
	}
