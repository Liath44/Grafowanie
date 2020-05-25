import javax.swing.*;
import java.awt.*;

public class PrevStatusPanel extends JPanel
	{
	private final static JLabel INITMESSAGE = new JLabel("Previous status: ");
	private JLabel status;
	private final static Color OKCOLOR = Color.GREEN.darker();
	private final static Color ERRORCOLOR = Color.RED;
	
	public PrevStatusPanel()
		{
		super();
		status = new JLabel("");
		add(INITMESSAGE);
		add(status);
		}
		
	public void showError()
		{
		status.setForeground(ERRORCOLOR);
		status.setText("ERROR");
		}
		
	public void showOK()
		{
		status.setForeground(OKCOLOR);
		status.setText("OK");
		}
	}
