package Mundo.Mapa;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import BESA.Kernell.Agent.AgentBESA;
import CA.State.CAState;
import CO.State.COState;
import DOOR.CLDoor;
import Mundo.Ingredientes;
import TP.TPAgent;
import TP.State.TPState;
import Utils.VisualUtils;

public class Visual extends JPanel {

	private static final long serialVersionUID = 484030263412506365L;
	private int sizex; // tamaño en x
	private int sizey; // tamaño en y

	private BufferedImage baldosa = VisualUtils.getBufferedImage("img/world/baldosa.jpg", this);
	private BufferedImage baldosaCocina = VisualUtils.getBufferedImage("img/world/baldosaCocina.jpg", this);
	private BufferedImage meson = VisualUtils.getBufferedImage("img/world/meson.jpg", this);
	private Font font = new Font("TimesRoman", Font.PLAIN, 40);

	private List<Rectangle> imagesRect;
	private List<TexturePaint> imagesPaint;

	private Map<String, BufferedImage> imgs;

	private int cocinaInicio;
	private int square;

	public Visual(int cocinaInicio, int square) {
		
		//this.getGraphics().setFont(font);
		this.cocinaInicio = cocinaInicio;
		this.square = square;

		imgs = new HashMap<String, BufferedImage>();

		imgs.put("CO", VisualUtils.getBufferedImage("img/agents/co.jpg", this));
		imgs.put("COT", VisualUtils.getBufferedImage("img/agents/cot.jpg", this));// en accion de cocinar
		imgs.put("EP", VisualUtils.getBufferedImage("img/agents/ep.jpg", this));
		imgs.put("TP", VisualUtils.getBufferedImage("img/agents/tp.jpg", this));
		imgs.put("CA", VisualUtils.getBufferedImage("img/agents/ca.jpg", this));

		this.sizex = Mapa.getsizex();
		this.sizey = Mapa.getsizey();

		imagesRect = new ArrayList<Rectangle>();
		imagesPaint = new ArrayList<TexturePaint>();

		// organizacion inicial del piso
		for (int i = 0; i < Mapa.getsizex() * Mapa.getsizey(); i++) {
			Rectangle rec = new Rectangle((i % sizex) * square, (i / sizex) * square, square, square);
			imagesRect.add(rec);
			imagesPaint.add(new TexturePaint(emptySpace(i), rec));
		}
		VisualUtils.openInJFrame(this, 50 * (sizex + 1), 50 * (sizey + 1));
	}

	private BufferedImage emptySpace(int pos) {
		if (pos % sizex < cocinaInicio) {
			return baldosa;
		} else if (pos % sizex == cocinaInicio) {
			return meson;
		} else {
			return baldosaCocina;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//g.setFont(font);
		// dibujar todo
		
		
		
		for (int i = 0; i < Mapa.getsizex() * Mapa.getsizey(); i++) {
			Rectangle rec = new Rectangle((i % sizex) * square, (i / sizex) * square, square, square);
			g2d.setPaint(new TexturePaint(getImage(i), rec));
			g2d.fill(imagesRect.get(i));
			g2d.setPaint(Color.blue);
			g2d.draw(imagesRect.get(i));
			if(Mapa.get(i)!=null) {
				g2d.setColor(Color.RED); 
				g2d.setFont(font);
				if(Mapa.get(i).getAlias().substring(0, 2).equals("TP")) {
					g2d.clearRect((i%sizex)*square, ((i-sizex)/sizex)*square, square, square);
				    g2d.drawString(((TPState) Mapa.get(i).getState()).getAtendiendo()+"", ((i%sizex)*square)+15, ((i/sizex)*square)-10);
				}else if(Mapa.get(i).getAlias().substring(0, 2).equals("CA")) {
					g2d.clearRect(((i-1)%sizex)*square, (((i+sizex)-sizex)/sizex)*square, square, square);
				    g2d.drawString(((CAState) Mapa.get(i).getState()).getAtendiendo()+"", (((i-1)%sizex)*square)+15, (((i+sizex)/sizex)*square)-10);
				}else if (Mapa.get(i).getAlias().substring(0, 2).equals("CO")) {
					for(int j = 95; j<99; j++) {
						g2d.clearRect(((j+sizex)%sizex)*square, ((j+sizex)/sizex)*square, square, square);
						rec = new Rectangle(((j+sizex)%sizex)*square, ((j+sizex)/sizex)*square, square, square);
						String fileName = "img/ing/"+((Ingredientes.getCantidad(Ingredientes.getIngredientes().get(98-j))>0)?Ingredientes.getIngredientes().get(98-j):Ingredientes.getIngredientes().get(98-j)+"EMP")+".jpg";
						g2d.setPaint(new TexturePaint(VisualUtils.getBufferedImage(fileName, this), rec));
						g2d.fill(rec);
						g2d.setPaint(Color.blue);
						g2d.draw(rec);
					}
				}
			}
		}
		
		g2d.clearRect((100%sizex)*square, (100/sizex)*square, square, square);
		g2d.setColor(Color.RED); 
		g2d.setFont(font);
	    g2d.drawString(CLDoor.cantidad+"", ((100%sizex)*square)+15, (((100+sizex)/sizex)*square)-10);
		
	}

	public BufferedImage getImage(int i) {
		AgentBESA ab = Mapa.get(i);
		if (ab == null) {
			return emptySpace(i);
		}

		String name = "" + ab.getAlias().charAt(0) + ab.getAlias().charAt(1);
		// en caso de ser cocinero y estar trabajando, se actualiza
		if (name.equals("CO") && ((COState) ab.getState()).getCocinando()) {
			name+="T";
		}

		return imgs.get(name);
	}

}
