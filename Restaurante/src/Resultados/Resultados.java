package Resultados;

import Utils.Utils;

public class Resultados {
//	private static float tiempoClienteVisitaCompletaPromedio;
//	private static float tiempoClienteEsperaFilaTPPromedio;
//	private static float tiempoClienteEsperaFilaPagosPromedio;
//	private static float tiempoClienteEsperaFilaExternaPromedio;

	private static long tiempoClienteEsperaFilaExterna;
	private static int tiempoClienteVisitaCompleta;

	public static synchronized void iniciar() {
//		tiempoClienteEsperaFilaTPPromedio = 0;
//		tiempoClienteEsperaFilaPagosPromedio = 0;

		tiempoClienteEsperaFilaExterna = 0;
		tiempoClienteVisitaCompleta = 0;
	}

	// creo que esto va a sobrepasar el maximo...
	public static synchronized void agregarATiempoClienteVisitaCompleta(long t) {
		tiempoClienteVisitaCompleta += t/Utils.clientesAAtender;
	}

	public static synchronized void agregarTiempoClienteEsperaFilaExterna(long tt) {
		tiempoClienteEsperaFilaExterna += tt/Utils.clientesAAtender;
	}

	public static void mostrarResultados() {
		System.out.println("promedio visita completa : " + tiempoClienteVisitaCompleta);
		System.out.println("promedio espera externa al restaurante" + tiempoClienteEsperaFilaExterna);
	}
}
