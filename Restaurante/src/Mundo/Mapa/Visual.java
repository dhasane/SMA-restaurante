package Mundo.Mapa;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import BESA.Kernell.Agent.AgentBESA;
import CO.State.COState;
import Utils.VisualUtils;

public class Visual extends JPanel {

	private static final long serialVersionUID = 484030263412506365L;
	private int sizex; // tamaño en x
	private int sizey; // tamaño en y

	private BufferedImage baldosa = VisualUtils.getBufferedImage("img/world/baldosa.jpg", this);
	private BufferedImage baldosaCocina = VisualUtils.getBufferedImage("img/world/baldosaCocina.jpg", this);
	private BufferedImage meson = VisualUtils.getBufferedImage("img/world/meson.jpg", this);

	private List<Rectangle> imagesRect;
	private List<TexturePaint> imagesPaint;

	private Map<String, BufferedImage> imgs;

	private int cocinaInicio;
	private int square;

	public Visual(int cocinaInicio, int square) {

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

		// dibujar todo
		for (int i = 0; i < Mapa.getsizex() * Mapa.getsizey(); i++) {
			Rectangle rec = new Rectangle((i % sizex) * square, (i / sizex) * square, square, square);
			g2d.setPaint(new TexturePaint(getImage(i), rec));
			g2d.fill(imagesRect.get(i));
			g2d.setPaint(Color.blue);
			g2d.draw(imagesRect.get(i));
		}
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
