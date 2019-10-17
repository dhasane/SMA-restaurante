import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Cleaner.Behavior.SensorGuard;
import Cleaner.CleanerAgent;
import Cleaner.State.CleanerState;
import World.Behavior.GameGuard;
import World.Behavior.SubscribeGuard;
import World.Behavior.UpdateGuard;
import World.State.WorldState;
import restaurante.WorldAgent;

/**
 *
 * @author Andres
 */
public class EjemploBESA {

    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;

    public static void main(String[] args) throws ExceptionBESA {


        AdmBESA admLocal = AdmBESA.getInstance();

        int tamx = 11;
        int tamy = 5;
        int nsuciedad = 11;

        crearRestaurante( tamx, tamy, nsuciedad );

        crearAgentes( tamx, tamy , 5 );

        PeriodicDataBESA data  = new PeriodicDataBESA(GAME_PERIODIC_TIME, GAME_PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        EventBESA startPeriodicEv = new EventBESA(GameGuard.class.getName(), data);
        AgHandlerBESA ah = admLocal.getHandlerByAlias("WORLD");
        ah.sendEvent(startPeriodicEv);


    }

    public static void crearRestaurante( int tamx , int tamy , int nsuciedad ) throws ExceptionBESA
    {
        WorldState ws = new WorldState( tamx , tamy , nsuciedad );
        StructBESA wrlStruct = new StructBESA();
        wrlStruct.addBehavior("WorldBehavior");
        wrlStruct.bindGuard("WorldBehavior", GameGuard.class);
        wrlStruct.addBehavior("ChangeBehavior");
        wrlStruct.bindGuard("ChangeBehavior", SubscribeGuard.class);
        wrlStruct.bindGuard("ChangeBehavior", UpdateGuard.class);
        WorldAgent wa = new WorldAgent("WORLD", ws, wrlStruct, 0.91);
        wa.start();
    }

    public static void crearAgentes( int x , int y , int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            agente( x, "C"+Integer.toString( a ) );
    }

    public static void agente( int size, String name ) throws ExceptionBESA {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("playerPerception");
        c1Struct.bindGuard("playerPerception", SensorGuard.class);
        CleanerAgent cleaner = new CleanerAgent( name, new CleanerState(size), c1Struct, 0.91 );
        cleaner.start();
    }

}
