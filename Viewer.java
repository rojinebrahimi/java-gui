package imageviewer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class Viewer {

	private JFrame frmRimageviewer;
	Image image;
	JLabel img = new JLabel();
	public static String path;
	BufferedImage bmg;
	float offset = 170.0f;
	JLabel label;
	JButton btnNext;
	JButton btnPrevious;
	JFileChooser chooser;
	Sound sound = new Sound();
	File file;

	// ---------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer window = new Viewer();
					window.frmRimageviewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	public Viewer() {
		initialize();
	}

	// First Position
	int pos = 0;

	// ---------------------------------------------------------------------------------------------------------------------------
	private void initialize() {
		frmRimageviewer = new JFrame();
		frmRimageviewer.getContentPane().setBackground(new Color(51, 153, 153));
		frmRimageviewer.setTitle("RImageViewer");
		frmRimageviewer.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		frmRimageviewer.setBounds(100, 100, 1411, 894);
		frmRimageviewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIManager.put("OptionPane.minimumSize", new Dimension(500, 100));
		UIManager.put("OptionPane.messageFont", new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));

		// Feel & Look
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		JMenuBar menubar;

		JMenu menu;
		JMenu menu1;
		JMenu menu2;
		JMenu menu3;
		JMenu menu4;
		JMenu menu5;

		JMenuItem open;
		JMenuItem zoomin;
		JMenuItem zoomout;
		JMenuItem darker;
		JMenuItem brighter;
		JMenuItem save;
		JMenuItem description;
		JMenuItem playSound;
		JMenuItem stopSound;

		frmRimageviewer.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1388, 700);
		frmRimageviewer.getContentPane().add(scrollPane);

		// Use a label to display the image
		label = new JLabel();
		label.setBackground(new Color(204, 204, 255));
		label.setBounds(300, 200, 1370, 580);
		scrollPane.setViewportView(label);

		btnNext = new JButton("<< Previous");
		btnNext.setBackground(new Color(204, 255, 204));
		btnNext.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnNext.setBounds(15, 728, 187, 52);
		frmRimageviewer.getContentPane().add(btnNext);

		btnPrevious = new JButton("Next >>");
		btnPrevious.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnPrevious.setBackground(new Color(204, 255, 204));
		btnPrevious.setBounds(229, 728, 187, 52);
		frmRimageviewer.getContentPane().add(btnPrevious);

		JButton btnRotate = new JButton("Rotate");
		btnRotate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnRotate.setBackground(new Color(204, 255, 204));
		btnRotate.setBounds(972, 728, 187, 52);
		frmRimageviewer.getContentPane().add(btnRotate);

		JButton btnSlideShow = new JButton("Slide Show");
		btnSlideShow.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnSlideShow.setBackground(new Color(204, 255, 204));
		btnSlideShow.setBounds(1187, 728, 187, 52);
		frmRimageviewer.getContentPane().add(btnSlideShow);

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));

		menubar = new JMenuBar();
		menubar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		menubar.setBackground(new Color(153, 153, 255));
		frmRimageviewer.setJMenuBar(menubar);

		menu = new JMenu("File");
		menu.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu);

		open = new JMenuItem("Open File");
		open.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		open.setHorizontalAlignment(SwingConstants.LEFT);
		menu.add(open);

		menu1 = new JMenu("Zoom");
		menu1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu1);

		zoomin = new JMenuItem("Zoom In");
		zoomin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		zoomin.setHorizontalAlignment(SwingConstants.LEFT);
		menu1.add(zoomin);

		zoomout = new JMenuItem("Zoom Out");
		zoomout.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		zoomout.setHorizontalAlignment(SwingConstants.LEFT);
		menu1.add(zoomout);

		menu2 = new JMenu("Contrast");
		menu2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu2);

		darker = new JMenuItem("Darker");
		darker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		darker.setHorizontalAlignment(SwingConstants.LEFT);
		menu2.add(darker);

		brighter = new JMenuItem("Brighter");
		brighter.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		brighter.setHorizontalAlignment(SwingConstants.LEFT);
		menu2.add(brighter);

		menu3 = new JMenu("Store");
		menu3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu3);

		save = new JMenuItem("Save File");
		save.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		save.setHorizontalAlignment(SwingConstants.LEFT);
		menu3.add(save);

		menu4 = new JMenu("Add");
		menu4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu4);

		description = new JMenuItem("Description");
		description.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		description.setHorizontalAlignment(SwingConstants.LEFT);
		menu4.add(description);

		menu5 = new JMenu("Sound");
		menu5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		menubar.add(menu5);

		playSound = new JMenuItem("Play Sound");
		playSound.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		playSound.setHorizontalAlignment(SwingConstants.LEFT);
		menu5.add(playSound);

		stopSound = new JMenuItem("Stop Sound");
		stopSound.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		stopSound.setHorizontalAlignment(SwingConstants.LEFT);
		menu5.add(stopSound);

		// Opening An Image
		open.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// show file chooser dialog
				int result = chooser.showOpenDialog(null);

				// if file selected, set it as icon of label
				if (result == JFileChooser.APPROVE_OPTION) {
					String name = chooser.getSelectedFile().getPath();
					ImageIcon icon = new ImageIcon(name);
					Image image = icon.getImage();
					Image newImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon img = new ImageIcon(newImage);
					label.setIcon(img);
					path = chooser.getSelectedFile().getAbsolutePath();
				}
			}
		});

		// Zoom In
		zoomin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int w = label.getWidth();
					int h = label.getHeight();
					File file = new File(path);
					Image img = ImageIO.read(file);
					ImageIcon icon = new ImageIcon(zoomImage(w + 100, h + 100, img));
					label.setIcon(icon);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		// Zoom Out
		zoomout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int w = label.getWidth();
					int h = label.getHeight();
					File file = new File(path);
					Image img = ImageIO.read(file);
					ImageIcon icon = new ImageIcon(zoomImage(w - 100, h - 100, img));
					label.setIcon(icon);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		// Rotates The Image
		btnRotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Rotate rotate = new Rotate();
				try {
					rotate.rotateImage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// Slide Show
		btnSlideShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SlideShow();
			}

		});

		// Saves The New Picture
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Enter A Name For The Picture, Please(e.g 'Rojin.png'):");
				try {
					saveScreenShot(label, name);

					JOptionPane.showMessageDialog(null, "Your Image Was SuccessFully Saved!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		// Brighter
		brighter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resize(getBright());
			}

		});

		// Darker
		darker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				resize(getDark());
			}

		});

		// Next Picture
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pos = pos + 1;
				if (pos >= getImages().length) {
					pos = 0;
				}
				showImages(pos);
			}

		});

		// Previous Picture
		btnPrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pos = pos - 1;
				if (pos < 0) {
					pos = getImages().length - 1;
				}
				showImages(pos);
			}

		});

		// Play Sound
		playSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					sound.soundPlayer();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}

		});

		// Stops The Sound
		stopSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sound.stopSound();
			}

		});

		description.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Description ds = new Description();
				try {
					ds.addDescription();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

	// Multiple Images
	// ---------------------------------------------------------------------------------------------------------------------------
	public String[] getImages() {

		File file = new File(getClass().getResource("/imageviewer/Photos").getFile());
		String[] imagesList = file.list();
		return imagesList;
	}

	// Showing The Images
	// ---------------------------------------------------------------------------------------------------------------------------
	public void showImages(int index) {
		String[] imagesList = getImages();
		String imageName = imagesList[index];
		ImageIcon icon = new ImageIcon(getClass().getResource("/imageviewer/Photos/" + imageName));
		Image image = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(image));
	}

	// Zoom
	// ---------------------------------------------------------------------------------------------------------------------------
	 Image zoomImage(int w, int h, Image img) {
		BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buf.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, w, h, null);
		g2d.dispose();
		return buf;
	}

	// Getting Screen Shot
	// ---------------------------------------------------------------------------------------------------------------------------
	BufferedImage getScreenShot(Component component) {
		BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		component.paint(img.getGraphics());
		return img;
	}

	// Saving The Screen Shot
	// ---------------------------------------------------------------------------------------------------------------------------
	public void saveScreenShot(Component component, String fileName) throws IOException {
		BufferedImage img = getScreenShot(component);
		ImageIO.write(img, "png", new File("D:\\Eclipse\\ImageViewer\\src\\imageviewer\\Saved\\" + fileName));
	}

	// Brighter Picture
	// ---------------------------------------------------------------------------------------------------------------------------
	public BufferedImage getBright() {

		File file = new File(path);

		try {
			bmg = ImageIO.read(file);
		} catch (IOException ex) {
			Logger.getLogger(path);
		}

		brighten();
		ImageIcon icon = new ImageIcon(bmg);
		label.setIcon(icon);
		return bmg;

	}

	private void brighten() {

		rescale();
		ImageIcon icon = new ImageIcon(bmg);
		label.setIcon(icon);

	}// end brighten()

	public void rescale() {
		RescaleOp rescale;

		rescale = new RescaleOp(0.5f, 100.0f, null);
		bmg = rescale.filter(bmg, null);
	}

	// Darker Picture
	// ---------------------------------------------------------------------------------------------------------------------------
	public BufferedImage getDark() {

		File file = new File(path);
		try {
			bmg = ImageIO.read(file);
		} catch (IOException ex) {
			Logger.getLogger(path);
		}

		darken();
		ImageIcon icon = new ImageIcon(bmg);
		label.setIcon(icon);
		return bmg;

	}

	private void darken() {

		rescaleDark();
		ImageIcon icon = new ImageIcon(bmg);
		label.setIcon(icon);

	}// end brighten()

	public void rescaleDark() {
		RescaleOp rescale;

		rescale = new RescaleOp(0.5f, 0, null);
		bmg = rescale.filter(bmg, null);
	}

	// Resize
	// ---------------------------------------------------------------------------------------------------------------------------
	public void resize(BufferedImage bufferedImage) {
		ImageIcon imageIcon = new ImageIcon(fitImage(bmg, label.getWidth(), label.getHeight()));
		label.setIcon(imageIcon);
	}

	private Image fitImage(Image img, int w, int h) {
		BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimage;
	}
}
