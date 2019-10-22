import java.util.ArrayList;
import java.util.List;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Creator.ClientCreator;
import Creator.CocineroCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import World.WorldAgent;
import World.Behavior.GameGuard;
import World.Behavior.SubscribeGuard;
import World.Behavior.UpdateGuard;
import World.State.WorldState;
import javafx.util.Pair;

public class Restaurante {

    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;

    private static double clave = 0.91;

    
    // EP -> entrega pedido
    // TP -> toma pedido
    // CO -> cocinero
    // CL -> cliente 
    // CA -> caja
    // AY -> ayudante // TODO
    
    
    public static void main(String[] args) throws ExceptionBESA {

        AdmBESA admLocal = AdmBESA.getInstance();
        ClientCreator.setClave(clave);
        TPCreator.setClave(clave);
        EPCreator.setClave(clave);
        CocineroCreator.setClave(clave);


        int cocinaInicio = 15;
        int tamx = 20;
        int tamy = 5;

        List <Pair<Integer, Integer> > tpPos = new ArrayList< Pair<Integer,Integer> >();
        tpPos.add( new Pair<>( tamx/2     , tamy -1 ) );
        tpPos.add( new Pair<>( tamx/2 + 1 , tamy -1 ) );

        List <Pair<Integer, Integer> > sillas = new ArrayList< Pair<Integer,Integer> >();
        sillas.add( new Pair<>(1, 2) );
        sillas.add( new Pair<>(2, 2) );

        List <Pair<Integer, Integer> > entregas = new ArrayList< Pair<Integer,Integer> >();
        sillas.add( new Pair<>( cocinaInicio, 2) );
        sillas.add( new Pair<>( cocinaInicio, 3) );

        crearRestaurante( tamx, tamy, cocinaInicio, sillas );

        ClientCreator.crearClientes( tamx, tamy , 2 );

        TPCreator.crearTP(tamx , tamy, tpPos);

        EPCreator.crearEP(tamx, tamy, entregas);

        // crearCocineros( tamx, tamy , 3 );

        // creo que esto es para la "sincronizacion" de tiempo de los agentes
        PeriodicDataBESA data  = new PeriodicDataBESA(GAME_PERIODIC_TIME, GAME_PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        EventBESA startPeriodicEv = new EventBESA(GameGuard.class.getName(), data);
        AgHandlerBESA ah = admLocal.getHandlerByAlias("WORLD");
        ah.sendEvent(startPeriodicEv);

    }

    // crea el mapa (restaurante)
    public static void crearRestaurante( int tamx , int tamy, int cocinaInicio , List<Pair<Integer, Integer>> sillas ) throws ExceptionBESA
    {
        WorldState ws = new WorldState( tamx , tamy, cocinaInicio , sillas );
        StructBESA wrlStruct = new StructBESA();
        wrlStruct.addBehavior("WorldBehavior");
        wrlStruct.bindGuard("WorldBehavior", GameGuard.class);
        wrlStruct.addBehavior("ChangeBehavior");

        wrlStruct.bindGuard("ChangeBehavior", SubscribeGuard.class);
        wrlStruct.bindGuard("ChangeBehavior", UpdateGuard.class);
        ( new WorldAgent("WORLD", ws, wrlStruct, clave ) ).start();
    }




}
