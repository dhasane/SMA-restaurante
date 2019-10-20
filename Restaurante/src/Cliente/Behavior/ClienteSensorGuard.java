/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Behavior;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Cliente.State.ClienteState;
import Data.ActionData;
import Data.SensorData;
import World.Behavior.UpdateGuard;

public class ClienteSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        ClienteState cs = (ClienteState) this.getAgent().getState();
        if ( cs.hasEaten() )
        {
            System.out.println("ya comio");
            return;
        }
        else
        {
            System.out.println("busca comida");
            buscarComida(cs, ebesa);
        }


    }

    private void buscarComida(ClienteState cs, EventBESA ebesa)
    {
        System.out.println("guard cliente");

        SensorData data = (SensorData) ebesa.getData();

        int nearestDust = -1;
        double nearestDistance = Double.MAX_VALUE;

        // se consigue el objeto mas cercano
        for (int i = 0; i < data.getComida().size(); i++)
        {
            double distance =  Math.sqrt(Math.pow(cs.getX() - data.getComida().get(i).getXpos(), 2)
                                + Math.pow(cs.getY() - data.getComida().get(i).getYpos(), 2));
            if(distance < nearestDistance)
            {
                nearestDistance = distance;
                nearestDust = i;
            }
        }

        System.out.println("guard cliente");

        if( nearestDust==-1 )
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
            System.out.println("comiendo");
            cs.eat();
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
