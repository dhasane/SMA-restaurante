/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cocinero.Behavior;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Cocinero.State.CocineroState;
import Data.ActionData;
import Data.SensorData;
import World.Behavior.UpdateGuard;

public class CocineroSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        SensorData data = (SensorData) ebesa.getData();
        CocineroState cs = (CocineroState) this.getAgent().getState();

        int nearestDust = -1;
        double nearestDistance = Double.MAX_VALUE;
        for (int i = 0; i < data.getComida().size(); i++) {
            double distance =  Math.sqrt(Math.pow(cs.getX() - data.getComida().get(i).getXpos(), 2)
                                + Math.pow(cs.getY() - data.getComida().get(i).getYpos(), 2));
            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestDust = i;
            }
        }

        if(nearestDust==-1)
        {
            return;
        }

        int nuevox = cs.getX();
        int nuevoy = cs.getY();
        int dustx = data.getComida().get(nearestDust).getXpos();
        int dusty = data.getComida().get(nearestDust).getYpos();

        if(dustx - cs.getX() > 0)
        {
            nuevox = cs.getX() + 1;
        }
        else if (dustx - cs.getX() < 0)
        {
            nuevox = cs.getX() - 1;
        }

        if(dusty - cs.getY() > 0)
        {
            nuevoy = cs.getY() + 1;
        }
        else if(dusty - cs.getY() < 0)
        {
            nuevoy = cs.getY() - 1;
        }

        DataBESA dataAction;
        if(nuevox == cs.getX() && nuevoy == cs.getY())
        {
            dataAction = new ActionData(this.getAgent().getAlias(), "clean");
        }
        else
        {
            dataAction = new ActionData(this.getAgent().getAlias(), "move", nuevox, nuevoy);
            cs.setX(nuevox);
            cs.setY(nuevoy);
        }

        EventBESA event = new EventBESA(UpdateGuard.class.getName(), dataAction);
        AgHandlerBESA ah;
        try {
            ah = getAgent().getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }
    }

}
