/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cocinero.State;

import BESA.Kernell.Agent.StateBESA;

public class CocineroState extends  StateBESA{

	private static final long serialVersionUID = 1L;
	int x;
    int y;

    public CocineroState(int x, int y) {
        this.x = x;
        this.y = y;
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
