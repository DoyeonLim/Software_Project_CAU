import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class AlbumGUI {
	
	private Album album;
	
	private JPanel center;
	private Photo select = null;
	private int sort_type;
	
	private LoadFile load;
	
	
	public AlbumGUI() {
		Album album = new Album();
		
		JFrame frame = new JFrame("Simple Photo Album");
		frame.setLayout(new BorderLayout());
		
		JPanel sort = new JPanel();
		sort.setLayout(new BorderLayout());
		
		JButton Date = new JButton("Date");
		JButton Category =new JButton("Category");
		
		sort.add(Date, BorderLayout.WEST);
		sort.add(new JPanel(), BorderLayout.CENTER);
		sort.add(Category, BorderLayout.EAST);
		
		frame.add(sort, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,1));
		
		sortByDate();
		
		frame.add(center,BorderLayout.CENTER);
		
		JPanel info = new JPanel();
		info.setLayout(new FlowLayout());
		
		JButton Edit = new JButton("EDIT");
		JButton Add = new JButton("ADD");
		JButton Delete = new JButton("DELETE");
		JButton Load = new JButton("LOAD");
		JButton Save = new JButton("SAVE");
		
		info.add(Edit);
		info.add(Add);
		info.add(Delete);
		info.add(Load);
		info.add(Save);
		
		frame.add(info, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByDate();
			}
		});
		
		Category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByCategory();
			}
		});
		
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(select == null)
					new ErrorFrame("Plese select a photo.");
				
				else
					new PhotoInfoFrame(select);
				
				select = null;
			}
		});
		
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PhotoAddFrame(album);
				refresh();
			}
		});
		
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(select == null)
					new ErrorFrame("Please select a photo.");
				
				else
					album.delete(select);
				
				select = null;
				refresh();
			}
		});
		
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				load = new LoadFile();
				
				String album_name = load.load().toString();
				
				if(album_name != "")
					album = new Album(album_name);
				
				refresh();
			}
		});
		
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				album.saveAlbum("Photo-normal.data");
			}
		});
	}
	
	private void sortByDate() {
		
		center.removeAll();
		
		ArrayList<JPanel> list = new ArrayList<JPanel>();
		
		album.sortbyDate();
		
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		
		JPanel date = null;
		String compare = "";
		
		int i;
		
		for(i=0; i<this.album.numPhotos(); i++) {
			Photo target = this.album.getPhoto(i);
			
			if(!compare.equals(target.getAdded_time().substring(0,10))) {
				
				if(i != 0) list.add(date);
				
				date = new JPanel(new FlowLayout(FlowLayout.LEFT));
				date.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
						target.getAdded_time().substring(0,10)));
				compare = target.getAdded_time().substring(0,10);
			}
			
			JPanel image = new JPanel(new BorderLayout());
			
			String info = target.getFile_name();
			
			ImageIcon photo = new ImageIcon(info);
			photo = imageSetSize(photo, 200 , 150);
			
			JLabel tmp = new JLabel(photo);
			tmp.setHorizontalAlignment(JLabel.CENTER);
			
			image.add(tmp, BorderLayout.CENTER);
			image.add(new JLabel(target.getName()), BorderLayout.SOUTH);
			date.add(image);
			
			image.addMouseListener(new MouseAdapter() {
				public void mouseselect(MouseEvent e) {
					select = target;
				}
			});
		}
		
		list.add(date);
		
		for(JPanel j : list) {
			center.add(j);
		}
		
		center.revalidate();
		center.repaint();
		sort_type = 1;
		
	}
	
	private void sortByCategory() {
		
		center.removeAll();
		
		ArrayList<JPanel> list = new ArrayList<JPanel>();
		
		album.sortbyCategory();
		
		BoxLayout bl = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(bl);
		
		JPanel category = null;
		String compare = null;
		
		for(int i=0; i<this.album.numPhotos(); i++) {
			Photo target = this.album.getPhoto(i);
			
			if(i==0 || !compare.equals(target.getCategory())) {
				
				if(i != 0) list.add(category);
				
				category = new JPanel(new FlowLayout(FlowLayout.LEFT));
				category.setBorder(new TitledBorder(new LineBorder(Color.black, 1),
						target.getCategory()));
				compare = target.getCategory();
			}
			
			JPanel image = new JPanel(new BorderLayout());
			
			String info = target.getFile_name();
			
			ImageIcon photo = new ImageIcon(info);
			photo = imageSetSize(photo, 200 , 150);
			
			JLabel tmp = new JLabel(photo);
			tmp.setHorizontalAlignment(JLabel.CENTER);
			
			image.add(tmp, BorderLayout.CENTER);
			image.add(new JLabel(target.getName()), BorderLayout.SOUTH);
			category.add(image);
			
			category.addMouseListener(new MouseAdapter() {
				public void mouseselect(MouseEvent e) {
					select = target;
				}
			});
		}
		
		list.add(category);
		
		for(JPanel i : list) {
			center.add(i);
		}
		
		center.revalidate();
		center.repaint();
		sort_type = 0;
		
	}
	
	private void refresh() {
		
		if(sort_type == 1)
			sortByDate();
		
		else
			sortByCategory();
	}
	
	private ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		
		Image img = icon.getImage();
		img = img.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		
		return icon;
	}
		
}

