package Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.AdmBESA;
import Data.PedidoData;

public class Utils {

	// en esta clase están los datos y funciones para que puedan ser llamados por
	// cualquier agente

	public static int PERIODIC_TIME = 1000;
	public static int PERIODIC_DELAY_TIME = 100;

	// nombres de los agentes
	public static String Cocinero = "Cocinero";
	public static String EntregaPedido = "EntregaPedido";
	public static String tomaPedido = "tomaPedido";
	public static String Puerta = "Puerta";
	public static String Caja = "Caja";

	// metaparametro
	public static int cantidadCriticaComida = 3;
	public static int tiempoMaximoComida = 15; // realmente el maximo puede ser 20, ya que termina siendo mas bien min + max
	public static int tiempoMinimoComida = 5;

	// funciones genericas

	// se envía un broadcast a todos los agentes con un rol en especifico, en envío
	// contiene data y la guarda que le responderá
	// retorna la cantidad de agentes a los que les envio mensaje
	public static int broadcast(AdmBESA admLocal, String rol, String guardaRespuesta, DataBESA data) {
		Iterator cos = admLocal.searchAidByService(rol);
		int cantidad = 0;

		while (cos.hasNext()) {
			Object element = cos.next();
			send(admLocal, element, guardaRespuesta, data);
			cantidad++;
		}

		return cantidad;
	}

	// dado un id y una data, se crea un evento y se le envía al agente
	// correspondiente a el id
	public static void send(AdmBESA admLocal, Object id, String clase, DataBESA data) {
		try {
			(admLocal.getHandlerByAid((String) id)).sendEvent(new EventBESA(clase, data));
		} catch (ExceptionBESA e) {
			e.printStackTrace();
		}
	}

	// para evitar confision con nombres de las guardas
	public static void agregarAEstructura(StructBESA c1Struct, Class classS) throws ExceptionBESA {
		c1Struct.addBehavior(classS.getName());
		c1Struct.bindGuard(classS.getName(), classS);
	}

	public static String ListaAString(List<String> lista) {
		String imp = "";
		for (String var : lista) {
			imp += var + " | ";
		}
		return (imp);
	}

	public static String pedidosAString(List<PedidoData> lista) {
		String imp = "";
		for (PedidoData var : lista) {
			imp += var + " | ";
		}
		return (imp);
	}

	public String queueAString(Queue<String> q) {
		String val = "";
		for (String var : q) {
			val += var + " | ";
		}
		return val;
	}

	// con el objetivo de poder cambiar la forma en la que se imprime
	// ej, en vez de imprimir,dirigir a un archivo
	public static void imp(Object obj) {
//		System.out.println(obj);
	}


}
