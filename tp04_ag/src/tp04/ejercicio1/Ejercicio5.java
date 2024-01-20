package tp04_ag.src.tp04.ejercicio1;

import tp04_ag.src.tp02.ejercicio2.ColaGenerica;
import tp04_ag.src.tp02.ejercicio2.ListaEnlazadaGenerica;

public class Ejercicio5 {
	
	
	private int promedio (int promaux, ColaGenerica <ArbolGeneral> cola) {
		int cont=0;
		ListaEnlazadaGenerica <AreaEmpresa>Laux=cola;
		while (Laux!=null) {
			AreaEmpresa elem=Laux.proximo();
			promaux+=elem.getTiempoMensaje();
			cont++;
		}
		promaux/=cont;
		return promaux;
	}
	
	public int devolverMaximoPromedio (ArbolGeneral<AreaEmpresa>arbol) {
		ColaGenerica <ArbolGeneral>cola=new ColaGenerica <ArbolGeneral>();
		cola.comenzar();
		int promMax=0;
		int promaux=0;
		cola.encolar(arbol);
		cola.encolar(null);
		while (!cola.esVacia()) {
			ArbolGeneral <AreaEmpresa>aux=cola.desencolar();
			if (aux!=null) {
				promedio (promaux, cola);
				if (promMax < promaux) {
					promMax=promaux;
				}
			}
			while (aux.tieneHijos()) {
				cola.encolar(aux.getHijos().proximo());
			}
			cola.encolar(null);
		}
		return promMax;
	}
}
