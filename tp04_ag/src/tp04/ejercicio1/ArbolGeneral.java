package tp04_ag.src.tp04.ejercicio1;

import tp04_ag.src.tp02.ejercicio2.ColaGenerica;
import tp04_ag.src.tp02.ejercicio2.ListaEnlazadaGenerica;
import tp04_ag.src.tp02.ejercicio2.ListaGenerica;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public Integer altura() {
		ColaGenerica <ArbolGeneral<T>> a= new ColaGenerica <ArbolGeneral<T>>();
		int niv=0;
		a.encolar(this);
		a.encolar(null);
		while (!this.esVacio()) {
			ArbolGeneral <T> aux= a.desencolar();
			if (aux!=null) {
				if (aux.tieneHijos()) {
					a.encolar(aux.getHijos().elemento(0));
				}else {
					a.encolar(aux.getHijos().proximo());
				}
			}else {
				if (!a.esVacia()) {
					a.encolar(null);
					niv++;
				}
			}
		}
		return niv;
	}

	public Integer nivel(T dato) {
		
		return -1;
	}

	
	private int anch (int ancho, int aux, ArbolGeneral <T> auxAb) {
		if (!this.esVacio()) {	
			if (this.tieneHijos()) {
				while (!this.hijos.esVacia()) {
					aux+=this.hijos.getTamanio();
					if (this.hijos.proximo()==null) {
						this.hijos.proximo();
					}
				}
			}
			if (aux>ancho) {
				ancho=aux;
			}
			aux=0;
			this.anch(ancho, aux, auxAb.getHijos().elemento(0));
		}
		return ancho;
	}
	
	public Integer ancho() {
		int ancho=0;
		int aux=0;
		ArbolGeneral auxAb= this;
		if (!this.esVacio()) {
			anch(ancho,aux,auxAb);
		}
		return 0;
	}

	public void inOrden () {
		ArbolGeneral <T>aux;
		if (!this.esVacio()) {
			if (this.tieneHijos()) {
				ListaGenerica<ArbolGeneral<T>> Laux= this.getHijos();
				Laux.comenzar();
				aux=Laux.proximo();
				aux.inOrden();
				System.out.println(aux.getDato());
				while (!Laux.fin()) {
					aux=Laux.proximo()	;
					aux.inOrden();
				}
			}
		}
	}
	
	public boolean esAncestro (T a, T b) {
		if (this.tieneHijos()) {
			ListaGenerica <ArbolGeneral<T>> aux = this.getHijos();
			aux.comenzar();
			while (((aux.proximo()!= b)||(aux.proximo()!= a)) && (!aux.esVacia())) {
				aux.proximo();
			}
			if (aux.proximo()==a) {
					return true;
			}else {
					this.getHijos().proximo();
					this.esAncestro(a, b);
			}
		}else {
			if ((this.esHoja())&&(this.getDato()!=a)){
				return false;
			}
		}
		return this.esAncestro(a, b);
	}
}