import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PhotoInfoGUI extends JFrame {
	
	public PhotoInfoGUI() {
		
		super("Add Photo");
		this.setLayout(new BorderLayout());
		
		JPanel item = new JPanel();
		JPanel button = new JPanel();
		JPanel blank = new JPanel();
		
		GridLayout grid = new GridLayout(2,6);
		item.setLayout(grid);
		
		item.add(new JLabel("Name"));
		item.add(new JLabel("Added Time"));
		item.add(new JLabel("Category"));
		item.add(new JLabel("Created Time"));
		item.add(new JLabel("Image File"));
		item.add(new JLabel("Select"));
		item.add(new JTextField());
		item.add(blank);
		
		item.add(new JTextField());
		item.add(new JTextField());
		item.add(new JTextField());
		item.add(new JButton("File"));
		
		JButton Cancel = new JButton("Cancel");
		
		button.add(Cancel);
		button.add(new JButton("OK"));
		
		this.add(item, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
				
	}

}

