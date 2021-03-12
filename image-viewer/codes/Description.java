package imageviewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Description extends Viewer {
	public BufferedImage addDescription() throws InterruptedException {
		BufferedImage img = null;
		File file = null;

		// Read image
		try {
			file = new File(path);
			
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println(e);
		}

		
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		
		Graphics graphics = result.getGraphics();
		graphics.drawImage(img, 0, 0, null);

		graphics.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 90));
		graphics.setColor(new Color(0,0, 0));

		String watermark = JOptionPane.showInputDialog("Enter The Description For This Image, Please:");

		graphics.drawString(watermark, img.getWidth() / 5, img.getHeight() / 3);

		graphics.dispose();

		String outPutName = JOptionPane.showInputDialog("Enter The OutPut Name For This Image, Please:(e.g Rojin.png)");
		file = new File("D:\\Eclipse\\ImageViewer\\src\\imageviewer\\Photos\\" + outPutName);
		
		try {
			ImageIO.write(result, "png", file);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		JOptionPane.showMessageDialog(null, "The Image Was Successfully Created!", "Adding Description", JOptionPane.INFORMATION_MESSAGE);
		
		return result;
	}
}
