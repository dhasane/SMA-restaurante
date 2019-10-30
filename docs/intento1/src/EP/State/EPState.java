/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.State;


import java.util.Deque;

import BESA.Kernell.Agent.StateBESA;
import Model.Food;

public class EPState extends  StateBESA{

	private static final long serialVersionUID = 1L;

	int x;
    int y;
    Deque<Food> comida;

    public EPState(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void add( Food f )
    {
    	this.comida.add(f);
    }
    
    public int size( )
    {
    	return this.comida.size();
    }
    
    public Food get() {
    	return this.comida.pop();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
