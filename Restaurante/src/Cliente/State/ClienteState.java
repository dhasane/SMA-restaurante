/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.State;

import BESA.Kernell.Agent.StateBESA;

public class ClienteState extends  StateBESA{

    private static final long serialVersionUID = 1L;

    private boolean food;
    private boolean seated;

    private int x;
    private int y;

    public ClienteState(int x, int y) {
        this.food = false;
        this.seated = false;
        this.x = x;
        this.y = y;
    }

    public boolean hasEaten()
    {
        return this.food;
    }

    public void eat()
    {
        this.food = true;
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
    
    public boolean getSeated() {
    	return this.seated;
    }

    public void sit() {
		this.seated = true;
	}
    
    public void getUp() {
		this.seated = false;
	}
}
