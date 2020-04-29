import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class Main {
	
	public static void main(String[] args) {
		
		Canvas canvas = new Canvas();//nasze białe pole, na którym się rysuje
		JFrame frame = new JFrame("GraphVis");//tytuł
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//musi być
		
		JRadioButton circle = new JRadioButton("Circle view");//circle view i konfiguracja
		circle.setBounds(20, 50, 100, 25);
		circle.setSelected(true);
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.treeview=circle.isSelected();
				canvas.read();		//trzeba potem narysować jeszcze raz
				canvas.repaint();
			}
		});
		
		JRadioButton tree = new JRadioButton("Tree view");//tree view i konfiguracja
		tree.setBounds(20, 70, 100, 25);
		tree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.treeview=tree.isSelected();
				canvas.read();		//trzeba potem narysować jeszcze raz
				canvas.repaint();
			}
		});
		
		JCheckBox indices = new JCheckBox("Indices");//indeksy
		indices.setBounds(20, 95, 120, 25);
		indices.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				canvas.indeksy=indices.isSelected();
				canvas.read();		//trzeba potem narysować jeszcze raz
				canvas.repaint();
			}
		});
		
		ButtonGroup grp = new ButtonGroup();		//laczymy je w grupę żeby nie dało się zaznaczyć dwóch jednocześnie
		grp.add(circle);
		grp.add(tree);
		
		JButton b1 = new JButton("Choose a graph file!");		//przycisk wywołujący wybór pliku
		b1.setBounds(10, 10, 185, 35);
		b1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					canvas.inputf = jfc.getSelectedFile();
					canvas.read();
					canvas.setBounds(200, 10, 700, 700);
					canvas.repaint();
				}
			}
		});
		
		JTextArea text = new JTextArea("Paste graph here!");			//dla leniwych
		text.setBounds(10, 120, 185, 200);
		Border border = BorderFactory.createLineBorder(Color.BLACK);	//ramka textboxa żeby ładnie było
	    text.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 5, 5, 5)));
	    text.addKeyListener((KeyListener) new KeyListener(){
	    	public void keyPressed(KeyEvent e){					//ESC jako "wykonaj"
	    		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
	    			canvas.in = new Scanner(text.getText());
					canvas.read();
					canvas.setBounds(200, 10, 700, 700);
					canvas.repaint();
	    		}
	    	}
	    	public void keyReleased(KeyEvent e){
	    	}
	    	public void keyTyped(KeyEvent e){
	    	}
	    });
		
	    frame.add(tree);		//wszystko na koniec dodajemy do "ramki" czyli naszego okienka programu
		frame.add(circle);
		frame.add(indices);
		frame.add(text);
		frame.add(b1);
		frame.add(canvas);
		frame.setLayout(null); 
		frame.setSize(1000, 750);
		frame.setVisible(true);
	}
	
}
