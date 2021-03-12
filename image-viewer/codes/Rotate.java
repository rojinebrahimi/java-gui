package imageviewer;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
 
// Rotates an image 90 degrees clockwise/counter clockwise using AffineTransform in Java
// Preserves the full image.
public class Rotate extends Viewer{
   
    public void rotateImage() throws Exception {
        BufferedImage source = ImageIO.read(new File(path));
         
        BufferedImage output = new BufferedImage(source.getHeight(), source.getWidth(), source.getType());
            
        AffineTransformOp op = new AffineTransformOp(rotateCounterClockwise90(source), AffineTransformOp.TYPE_BILINEAR);
        op.filter(source, output);
        
        String rotatedName = JOptionPane.showInputDialog("Enter The Name For This Rotated Image(e.g Rojin.png): ");
        
        ImageIO.write(output, "png", new File("D:\\Eclipse\\ImageViewer\\src\\imageviewer\\Rotated\\" + rotatedName ));
        JOptionPane.showMessageDialog(null, "The Image Is Rotated Successfully!");
         
    }
     
    // Rotates clockwise 90 degrees
    private AffineTransform rotateClockwise90(BufferedImage source) {
        AffineTransform imageTransform = new AffineTransform();
        imageTransform.rotate(Math.PI/2, source.getWidth()/2, source.getHeight()/2);
        double offset = (source.getWidth()-source.getHeight())/2;
        imageTransform.translate(offset,offset);
        return imageTransform;
    }
     
    // Rotates counter clockwise 90 degrees
    private AffineTransform rotateCounterClockwise90(BufferedImage source) {
        AffineTransform imageAT = new AffineTransform();
        imageAT.rotate(-Math.PI/2, source.getWidth()/2, source.getHeight()/2);
        double offset = (source.getWidth()-source.getHeight())/2;
        imageAT.translate(-offset,-offset);
        return imageAT;
    }
     
}