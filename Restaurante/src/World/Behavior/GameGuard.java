package World.Behavior;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Cliente.Behavior.ClienteSensorGuard;
import Data.SensorData;
import World.State.WorldState;

public class GameGuard extends PeriodicGuardBESA{

    @Override
    public void funcPeriodicExecGuard(EventBESA ebesa) {
        WorldState ws = (WorldState)this.getAgent().getState();
        for (int i = 0; i < ws.getBotsAlias().size(); i++)
        {
            DataBESA data = new SensorData( ws.getMap().getFood() , ws.getMap().getSillas() );
            EventBESA event = new EventBESA(ClienteSensorGuard.class.getName(), data);
            AgHandlerBESA ah;
            try {
                ah = getAgent().getAdmLocal().getHandlerByAlias(ws.getBotsAlias().get(i));
                ah.sendEvent(event);
            } catch (ExceptionBESA e) {
                ReportBESA.error(e);
            }
        }
    }

}
