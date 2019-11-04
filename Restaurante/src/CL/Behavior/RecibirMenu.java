package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.State.ClienteState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.FilaData;
import Data.IDData;
import Data.MenuData;
import Data.PedidoData;
import TP.Behavior.IncluirEnFila;
import TP.Behavior.ProductRequest;
import Utils.Utils;

public class RecibirMenu extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		MenuData fd = (MenuData) ebesa.getData();
//		ClienteState cs = (ClienteState) getAgent().getState();

		System.out.println(getAgent().getAlias() + " mira el menu y pide ");

		Utils.imprimirLista(fd.getMenu());

		Random r = new Random();
		List<String> pedido = new ArrayList<String>();

		while (pedido.size() < 3) {
			pedido.add(fd.getMenu().get(r.nextInt(fd.getMenu().size())));
		}
		
		System.out.print( "pedido de " + getAgent().getAlias() + " : " );
		Utils.imprimirLista( pedido );

		Utils.send(getAgent().getAdmLocal(), fd.responder(), ProductRequest.class.getName() , new PedidoData(pedido));

		return;

	}

}
