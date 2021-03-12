package imageviewer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SlideShow extends JFrame {
	JLabel picture;
	Timer tm;
	int p = 0;

	// Images Path In Array
	File file = new File(getClass().getResource("/imageviewer/Photos").getFile());
	String[] list = file.list();

	// Constructor
	public SlideShow() {
		super("RSlideShow");
		picture = new JLabel();
		picture.setBounds(15, 16, 1071, 714);
		showImages(p);
		tm = new Timer(1500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showImages(p);

				p += 1;
				if (p >= list.length)
					p = 0;
			}
		});
		getContentPane().add(picture);
		tm.start();
		getContentPane().setLayout(null);
		setSize(1123, 802);
		getContentPane().setBackground(new Color(153, 153, 204));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}

	// Set Images
	// ---------------------------------------------------------------------------------------------------------------------
	public void showImages(int index) {
		String imageName = list[index];
		ImageIcon icon = new ImageIcon(getClass().getResource("/imageviewer/Photos/" + imageName));
		Image image = icon.getImage().getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH);
		picture.setIcon(new ImageIcon(image));
	}
}
