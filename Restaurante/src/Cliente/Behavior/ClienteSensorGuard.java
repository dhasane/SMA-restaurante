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
import Data.Pedido;
import Data.SensorData;
import Model.Food;
import TP.Behavior.PedidoGuard;
import World.Behavior.UpdateGuard;

public class ClienteSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        ClienteState cs = (ClienteState) this.getAgent().getState();
        
        if ( cs.hambre() )
        {
        	System.out.println("busca comida");
//        	hacerPedido( cs, ebesa );
        	
        	Food food = new Food(); // perdon esto, necesitaba burlarme de algo
        	food.add("arrocito asi que chinga");
        	food.add("papitas bien sabrosongas");
        	food.add("filetico de carne asi bien ricolino");
        	food.add("limonadita asi que ahhhh");

        	DataBESA pedido = new Pedido( food );
        			
        	EventBESA event = new EventBESA( PedidoGuard.class.getName(), pedido );
        	
            AgHandlerBESA ah;
            try {
                ah = getAgent().getAdmLocal().getHandlerByAlias("SupervisorPedidos");
                ah.sendEvent(event);
            }
            catch (ExceptionBESA e) {
                ReportBESA.error(e);
            }

        }
        else if( cs.pedidoHecho() )
        {
        	buscarComida(cs, ebesa);
        }
        else if ( !cs.getSeated() )
        {
            System.out.println("busca silla");
        	goSit(cs, ebesa);
        }
        else return;

    }
    
    // busca una silla, va y se sienta
//    private void hacerPedido(ClienteState cs, EventBESA ebesa)
//    {
//    	
//        SensorData data = (SensorData) ebesa.getData();
//
//        int nearestChair = -1;
//        double nearestDistance = Double.MAX_VALUE;
//
//        // se consigue el objeto mas cercano
//        for (int i = 0; i < data.getSillas().size(); i++) 
//        {
//            double distance =  Math.sqrt(Math.pow(cs.getX() - data.getTPs().get(i).getXpos(), 2)
//                                + Math.pow(cs.getY() - data.getTPs().get(i).getYpos(), 2));
//            if(distance < nearestDistance)
//            {
//                nearestDistance = distance;
//                nearestChair = i;
//            }
//        }
//
////        System.out.println("guard cliente");
//
//        if( nearestChair ==-1 )
//        {
//            return;
//        }
//
//        int nuevox = cs.getX();
//        int nuevoy = cs.getY();
//        int dustx = data.getSillas().get(nearestChair).getXpos();
//        int dusty = data.getSillas().get(nearestChair).getYpos();
//
//        if(dustx - cs.getX() > 0)
//        {
//            nuevox = cs.getX() + 1;
//        }
//        else if (dustx - cs.getX() < 0)
//        {
//            nuevox = cs.getX() - 1;
//        }
//
//        if(dusty - cs.getY() > 0)
//        {
//            nuevoy = cs.getY() + 1;
//        }
//        else if(dusty - cs.getY() < 0)
//        {
//            nuevoy = cs.getY() - 1;
//        }
//
//        DataBESA dataAction;
//        if(nuevox == cs.getX() && nuevoy == cs.getY())
//        {
////            System.out.println("sentandose");
//            cs.sit();
//            dataAction = new ActionData(this.getAgent().getAlias(), "sit", nuevox, nuevoy);
//        }
//        else
//        {
//            dataAction = new ActionData(this.getAgent().getAlias(), "move", nuevox, nuevoy);
//            cs.setX(nuevox);
//            cs.setY(nuevoy);
//        }
//
//        EventBESA event = new EventBESA(UpdateGuard.class.getName(), dataAction);
//        AgHandlerBESA ah;
//        try {
//            ah = getAgent().getAdmLocal().getHandlerByAlias("WORLD");
//            ah.sendEvent(event);
//        }
//        catch (ExceptionBESA e) {
//            ReportBESA.error(e);
//        }
//    }


    // busca una silla, va y se sienta
    private void goSit(ClienteState cs, EventBESA ebesa)
    {


        SensorData data = (SensorData) ebesa.getData();

        int nearestChair = -1;
        double nearestDistance = Double.MAX_VALUE;

        // se consigue el objeto mas cercano
        for (int i = 0; i < data.getSillas().size(); i++) 
        {
            double distance =  Math.sqrt(Math.pow(cs.getX() - data.getSillas().get(i).getXpos(), 2)
                                + Math.pow(cs.getY() - data.getSillas().get(i).getYpos(), 2));
            if(distance < nearestDistance)
            {
                nearestDistance = distance;
                nearestChair = i;
            }
        }

//        System.out.println("guard cliente");

        if( nearestChair ==-1 )
        {
            return;
        }

        int nuevox = cs.getX();
        int nuevoy = cs.getY();
        int dustx = data.getSillas().get(nearestChair).getXpos();
        int dusty = data.getSillas().get(nearestChair).getYpos();

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
//            System.out.println("sentandose");
            cs.sit();
            dataAction = new ActionData(this.getAgent().getAlias(), "sit", nuevox, nuevoy);
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

    private void buscarComida(ClienteState cs, EventBESA ebesa)
    {
//        System.out.println("guard cliente");

        SensorData data = (SensorData) ebesa.getData();

        int nearestFood = -1;
        double nearestDistance = Double.MAX_VALUE;
        
        System.out.println( " hay cosa esta " + data.getComida().size() );

        // se consigue el objeto mas cercano
        for (int i = 0; i < data.getComida().size(); i++)
        {
            double distance =  Math.sqrt(Math.pow(cs.getX() - data.getComida().get(i).getXpos(), 2)
                                + Math.pow(cs.getY() - data.getComida().
                                		get(i).getYpos(), 2));
            if(distance < nearestDistance)
            {
                nearestDistance = distance;
                nearestFood = i;
            }
        }

//        System.out.println("guard cliente");

        if( nearestFood==-1 )
        {
            return;
        }

        int nuevox = cs.getX();
        int nuevoy = cs.getY();
        int dustx = data.getComida().get(nearestFood).getXpos();
        int dusty = data.getComida().get(nearestFood).getYpos();

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
//            System.out.println("comiendo");
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
