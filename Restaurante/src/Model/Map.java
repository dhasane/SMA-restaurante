package Model;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel{

    private int sizex;              // tamaño en x
    private int sizey;              // tamaño en y
    private int width;
    private int height;

    private List<WorldObject> dust;
    private List<WorldObject> bots;

    private BufferedImage image         = Map.getBufferedImage("img/world/baldosa.jpg", this);
    private BufferedImage baldosaCocina = Map.getBufferedImage("img/world/baldosaCocina.jpg", this);
    private BufferedImage meson         = Map.getBufferedImage("img/world/meson.jpg", this);
    private BufferedImage cleanerbot    = Map.getBufferedImage("img/agents/cleanerbot.jpg", this);
    private BufferedImage suciedad      = Map.getBufferedImage("img/objects/suciedad.jpg", this);

    private List<Rectangle> imagesRect;
    private List<TexturePaint> imagesPaint;
    private List<Rectangle> imagesDust;
    private List<TexturePaint> imagesPaintDust;
    private List<Rectangle> imagesBots;
    private List<TexturePaint> imagesPaintBots;

    public Map(int sizex, int sizey, int numOfDust , int square) {
        this.sizex = sizex;
        this.sizey = sizey;

        int corte = 15; // inicio de la cocina

        this.bots       = new ArrayList<>();
        this.dust       = new ArrayList<>();
        imagesRect      = new ArrayList<>();
        imagesPaint     = new ArrayList<>();
        imagesDust      = new ArrayList<>();
        imagesPaintDust = new ArrayList<>();
        imagesBots      = new ArrayList<>();
        imagesPaintBots = new ArrayList<>();

        width  = square;
        height = square;

        // organizacion del polvo de manera aleatoria
        for (int i = 0; i < numOfDust; i++) {
            Random r = new Random();
            int x = r.nextInt(sizex);
            int y = r.nextInt(sizey);
            dust.add(new WorldObject(x, y, "dust_"+i));
            Rectangle rec = new Rectangle( x*width, y*height + (height/2), width/2, height/2 );
            imagesDust.add(rec);
            imagesPaintDust.add(new TexturePaint(suciedad, rec));
        }

        // organizacion de los tiles
        for (int i = 0; i < sizex*sizey; i++) {
            Rectangle rec = new Rectangle( (i%sizex)*width, (i/sizex)*height, width, height );
            imagesRect.add(rec);
            if ( i%sizex < corte )
            {
                imagesPaint.add(new TexturePaint(image, rec));
            }
            else if ( i%sizex == corte )
            {
                imagesPaint.add(new TexturePaint(meson, rec));
            }
            else
            {
                imagesPaint.add(new TexturePaint(baldosaCocina, rec));
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // dibujar tiles
        for (int i = 0; i < sizex*sizey; i++) {
            g2d.setPaint(imagesPaint.get(i));
            g2d.fill(imagesRect.get(i));
            g2d.setPaint(Color.blue);
            g2d.draw(imagesRect.get(i));
        }
        // dibujar polvo
        for (int i = 0; i < dust.size(); i++) {
            g2d.setPaint(imagesPaintDust.get(i));
            g2d.fill(imagesDust.get(i));
            g2d.setPaint(Color.gray);
            g2d.draw(imagesDust.get(i));
        }
        // dibujar agentes
        for (int i = 0; i < bots.size(); i++) {
            g2d.setPaint(imagesPaintBots.get(i));
            g2d.fill(imagesBots.get(i));
            g2d.setPaint(Color.gray);
            g2d.draw(imagesBots.get(i));
        }
    }

    public static BufferedImage getBufferedImage(String imageFile, Component c) {
        Image image = c.getToolkit().getImage(imageFile);
        waitForImage(image, c);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(c), image.getHeight(c), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, c);
        return(bufferedImage);
    }

    public static boolean waitForImage(Image image, Component c) {
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

    public void addBot(String alias, int x, int y) {
        bots.add(new WorldObject(x, y, alias));
        Rectangle rec = new Rectangle(x*width + (width/2), y*height , width/2, height/2 );
        imagesBots.add(rec);
        imagesPaintBots.add(new TexturePaint(cleanerbot, rec));
    }

    public void clean(String alias) {
        int botIndex = find(this.bots, alias);
        int dustIndex = findDust(this.dust, bots.get(botIndex).getXpos(), bots.get(botIndex).getYpos());
        if(dustIndex!=-1){
            dust.remove(dustIndex);
            imagesDust.remove(dustIndex);
            imagesPaintDust.remove(dustIndex);
        }
        repaint();
    }

    public void move(String alias, int x, int y) {
        int botIndex = find(this.bots, alias);
        bots.get(botIndex).setXpos(x);
        bots.get(botIndex).setYpos(y);
        if(botIndex!=-1){
            Rectangle rec = new Rectangle(x*width + (width/2), y*height , width/2, height/2 );
            imagesBots.set(botIndex, rec);
            imagesPaintBots.set(botIndex, new TexturePaint(cleanerbot, rec));
            repaint();
        }

    }

    private int find(List<WorldObject> bots, String alias) {
        for (int i = 0; i < bots.size(); i++) {
            if(bots.get(i).getAlias().equals(alias)){
                return i;
            }
        }
        return -1;
    }


    private int findDust(List<WorldObject> dust, int xpos, int ypos) {

        for (int i = 0; i < dust.size(); i++) {
            if(xpos == dust.get(i).getXpos() && ypos == dust.get(i).getYpos()){
                return i;
            }
        }
        return -1;
    }

    public static class ExitListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }

    public List<WorldObject> getBots() {
        return bots;
    }

    public void setBots(List<WorldObject> bots) {
        this.bots = bots;
    }

    public List<WorldObject> getDust() {
        return dust;
    }

    public void setDust(List<WorldObject> dust) {
        this.dust = dust;
    }

}


