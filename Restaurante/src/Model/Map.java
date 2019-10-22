package Model;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.util.Pair;

public class Map extends JPanel{


	private static final long serialVersionUID = 1L;

	private int sizex;              // tamaño en x
    private int sizey;              // tamaño en y
    private int width;
    private int height;

    private List<WorldObject> comida;
    private List<WorldObject> sillas;
    private List<Boolean> 	  enUso;
    private List<WorldObject> bots;

    private BufferedImage image         = Map.getBufferedImage("img/world/baldosa.jpg", this);
    private BufferedImage baldosaCocina = Map.getBufferedImage("img/world/baldosaCocina.jpg", this);
    private BufferedImage meson         = Map.getBufferedImage("img/world/meson.jpg", this);

//    private BufferedImage cleanerbot    = Map.getBufferedImage("img/agents/cleanerbot.jpg", this);
    private BufferedImage cliente    	= Map.getBufferedImage("img/agents/cliente.jpg", this);
    private BufferedImage clienteSentado= Map.getBufferedImage("img/agents/clienteSentado.jpg", this);
    private BufferedImage cocinero   	= Map.getBufferedImage("img/agents/cocinero.jpg", this);
    private BufferedImage tp    		= Map.getBufferedImage("img/agents/tp.jpg", this);
    private BufferedImage ep    		= Map.getBufferedImage("img/agents/ep.jpg", this);

    private BufferedImage comidaImg     = Map.getBufferedImage("img/objects/comida.jpg", this);
    private BufferedImage sillaImg      = Map.getBufferedImage("img/objects/silla.jpg", this);

    private List<Rectangle> 	imagesRect;
    private List<TexturePaint> 	imagesPaint;
    private List<Rectangle> 	imagesComida;
    private List<TexturePaint> 	imagesPaintComida;
    private List<Rectangle> 	imagesSilla;
    private List<TexturePaint> 	imagesPaintSilla;
    private List<Rectangle> 	imagesBots;
    private List<TexturePaint> 	imagesPaintBots;

    public Map(int sizex, int sizey,int cocinaInicio, List< Pair<Integer, Integer> > posSillas , int square ) {
        this.sizex = sizex;
        this.sizey = sizey;

        this.bots       		= new ArrayList<>();
        this.comida       		= new ArrayList<>();
        this.sillas 			= new ArrayList<>();
        this.imagesRect      	= new ArrayList<>();
        this.imagesPaint     	= new ArrayList<>();
        this.imagesComida      	= new ArrayList<>();
        this.imagesPaintComida 	= new ArrayList<>();
        this.imagesSilla      	= new ArrayList<>();
        this.enUso				= new ArrayList<>();
        this.imagesPaintSilla 	= new ArrayList<>();
        this.imagesBots      	= new ArrayList<>();
        this.imagesPaintBots 	= new ArrayList<>();

        width  = square;
        height = square;
        
     // organizacion de los tiles
        for (int i = 0; i < sizex*sizey; i++) {
            Rectangle rec = new Rectangle( (i%sizex)*width, (i/sizex)*height, width, height );
            imagesRect.add(rec);
            if ( i%sizex < cocinaInicio )
            {
                imagesPaint.add(new TexturePaint(image, rec));
            }
            else if ( i%sizex == cocinaInicio )
            {
                imagesPaint.add(new TexturePaint(meson, rec));
            }
            else
            {
                imagesPaint.add(new TexturePaint(baldosaCocina, rec));
            }
        }

        // organizacion de las sillas
        for (int i = 0; i < posSillas.size() ; i++) {
            int x = posSillas.get(i).getKey();
            int y = posSillas.get(i).getValue();
            enUso.add(false);
            sillas.add(new WorldObject(x, y, "chair_"+i));
            Rectangle rec = new Rectangle( x*width, y*height + (height/2), width/2, height/2 );
            imagesSilla.add(rec);
            imagesPaintSilla.add(new TexturePaint(sillaImg, rec));
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
        for (int i = 0; i < comida.size(); i++) {
            g2d.setPaint(imagesPaintComida.get(i));
            g2d.fill(imagesComida.get(i));
            g2d.setPaint(Color.gray);
            g2d.draw(imagesComida.get(i));
        }
        // dibujar sillas
        for (int i = 0; i < sillas.size(); i++) {
            g2d.setPaint(imagesPaintSilla.get(i));
            g2d.fill(imagesSilla.get(i));
            g2d.setPaint(Color.gray);
            g2d.draw(imagesSilla.get(i));
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
    
    public void addAgent(String alias, int x, int y ) {
    	
    	String nom = "" + alias.charAt(0) + alias.charAt(1);
    	
    	BufferedImage bi = null;
    	
    	switch ( nom )
    	{
    	case "CL":
    		bi = cliente;
    		break;
    	case "TP":
    		bi = tp;
    		break;
    	case "EP":
    		bi = ep;
    		break;
    	case "CO":
    		bi = cocinero;
    		break;
    	}
    	
        bots.add(new WorldObject(x, y, alias));
        Rectangle rec = new Rectangle(x*width + (width/2), y*height , width/2, height/2 );
        imagesBots.add(rec);
        imagesPaintBots.add(new TexturePaint( bi , rec));
    }

    public void clean(String alias) {
        int botIndex = find(this.bots, alias);
        int foodIndex = findDust(this.comida, bots.get(botIndex).getXpos(), bots.get(botIndex).getYpos());
        if( foodIndex!=-1 ){
            comida.remove( foodIndex );
            imagesComida.remove( foodIndex );
            imagesPaintComida.remove( foodIndex );
        }
        repaint();
    }
    
    public void ponerComida(String alias, int x, int y) {

        comida.add( new WorldObject(x, y, "food_"+comida.size()) );
        Rectangle rec = new Rectangle( x*width, y*height + (height/2), width/2, height/2 );
        imagesComida.add( rec );
        imagesPaintComida.remove( new TexturePaint(sillaImg, rec) );
        
        repaint();
    }
    

    public void move(String alias, int x, int y) {
        int botIndex = find(this.bots, alias);
        bots.get(botIndex).setXpos(x);
        bots.get(botIndex).setYpos(y);
        if(botIndex!=-1){
            Rectangle rec = new Rectangle(x*width + (width/2), y*height , width/2, height/2 );
            imagesBots.set(botIndex, rec);
            // TODO hacer para que el movimiento no afecte al agente que se mueve
            imagesPaintBots.set(botIndex, new TexturePaint(cliente, rec));
            repaint();
        }

    }

    // aqui solo puede sentarse el cliente
    public void sit(String alias, int x, int y) {
        int botIndex = find(this.bots, alias);
        bots.get(botIndex).setXpos(x);
        bots.get(botIndex).setYpos(y);
        if(botIndex!=-1){
//        	sillas.remove( botIndex );
            Rectangle rec = new Rectangle(x*width + (width/2), y*height , width/2, height/2 );
            imagesBots.set(botIndex, rec);
            imagesPaintBots.set(botIndex, new TexturePaint(clienteSentado, rec));
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
        return comida;
    }

    public void setDust(List<WorldObject> dust) {
        this.comida = dust;
    }
    
    public void setSillas(List<WorldObject> sillas) {
        this.sillas = sillas;
    }

	public List<WorldObject> getSillas() {
		return sillas;
	}
	
	

}


