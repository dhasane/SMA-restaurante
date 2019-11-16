package Utils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class VisualUtils {

    public static BufferedImage getBufferedImage(String imageFile, Component c) {
        Image image = c.getToolkit().getImage(imageFile);
        waitForImage(image, c);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(c), image.getHeight(c), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, c);
        return(bufferedImage);
    }

    private static boolean waitForImage(Image image, Component c) {
        MediaTracker tracker = new MediaTracker(c);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch(InterruptedException ie) {}
        return(!tracker.isErrorAny());
    }
    

    public static JFrame openInJFrame(Container content, int width, int height) {
        return(openInJFrame(content, width, height, content.getClass().getName(), Color.white));
    }

    public static JFrame openInJFrame(Container content, int width, int height, String title, Color bgColor) {
        JFrame frame = new JFrame(title);
        frame.setBackground(bgColor);
        content.setBackground(bgColor);
        frame.setSize(width, height);
        frame.setContentPane(content);
        frame.addWindowListener(new ExitListener());
        frame.setVisible(true);
        return(frame);
    }

    public static class ExitListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }

}


