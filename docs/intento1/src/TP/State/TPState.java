/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TP.State;

import BESA.Kernell.Agent.StateBESA;
import Model.WorldObject;
import java.util.ArrayList;
import java.util.List;

public class TPState extends  StateBESA{

    int x;
    int y;

    public TPState(int x, int y) {
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
