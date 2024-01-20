package tp04_ag.src.tp04.ejercicio1;

import tp04_ag.src.tp02.ejercicio2.ListaEnlazadaGenerica;

public class RecorridoAG {


	public void preorden (ListaEnlazadaGenerica <Integer> aux, ArbolGeneral <Integer>a, Integer N, Integer cont) {
		aux.agregarFinal(a.getDato());
		if (!a.esVacio()) {
			if (cont % 2==1) {
				if (a.getDato()>N) {
					aux.eliminarEn(cont);
				}
			}
		}else {
			if (a.tieneHijos()) {
				preorden(aux,a.getHijos().elemento(0),N,cont++);
			}
		}
	}
	
	public ListaEnlazadaGenerica <Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral <Integer>a, Integer N){
		Integer aux;
		Integer cont=1;
		ListaEnlazadaGenerica <Integer> L= new ListaEnlazadaGenerica <Integer>();
		L.comenzar();
		if (!a.esVacio()) {
			preorden (L,a,N,cont);
		}
		return L;
	}
	
	public void inorden (ListaEnlazadaGenerica <Integer> aux, ArbolGeneral <Integer>a, Integer N, Integer cont) {
		if (!a.esVacio()) {
			if (cont % 2==1) {
				if (a.getDato()>N) {
					aux.agregarFinal(a.getDato());
				}
			}
		}else {
			if (a.tieneHijos()) {
				inorden(aux,a.getHijos().elemento(0),N,cont++);
			}
			if (a.getHijos().proximo()!=null) {
				inorden (aux,a.getHijos().proximo(),N,cont++);
			}
		}
	}
	
	public ListaEnlazadaGenerica <Integer> numerosImparesMayoresQueInOrden (ArbolGeneral <Integer>a, Integer N){
		Integer aux;
		Integer cont=1;
		ListaEnlazadaGenerica <Integer> L= new ListaEnlazadaGenerica <Integer>();
		L.comenzar();
		if (!a.esVacio()) {
			inorden (L,a,N,cont);
		}
		return L;
	}
	
	public void posorden (ListaEnlazadaGenerica <Integer> aux, ArbolGeneral <Integer>a, Integer N, Integer cont) {
		if (a.tieneHijos()) {
			posorden(aux,a.getHijos().elemento(0),N,cont++);
		} else {
			if (!a.esVacio()) {
				if (cont % 2==1) {
					if (a.getDato()>N) {
						aux.agregarFinal(a.getDato());
					}
				}
			}
		}
	}
	
	public ListaEnlazadaGenerica <Integer> numerosImparesMayoresQuePosOrden (ArbolGeneral <Integer>a, Integer N){
		Integer aux;
		Integer cont=1;
		ListaEnlazadaGenerica <Integer> L= new ListaEnlazadaGenerica <Integer>();
		L.comenzar();
		if (!a.esVacio()) {
			posorden (L,a,N,cont);
		}
		return L;
	}
	
}
