package tp06.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class Mapa{
	private Grafo<Destino> mapaCiudades;
	
	public ListaGenerica<Vertice<Destino>> devolverCamino (String ciudad1, String ciudad2){
		int i= buscarCiudad(ciudad1);
		ListaGenerica<Vertice<Destino>> recor= new ListaEnlazadaGenerica ();
		if (i!=-1) {
			boolean []marca= new boolean [this.mapaCiudades.listaDeVertices().tamanio()];
			for (int j=0; j< this.mapaCiudades.listaDeVertices().tamanio();j++) marca[j]=false;
			ListaGenerica<Vertice<Destino>> act= new ListaEnlazadaGenerica ();
			act.comenzar();
			this.recorridoCamin(this.mapaCiudades.vetice(i), i, marca, recor, act,ciudad2);
		}
		return recor;
	}
	
	private void recorridoCamin (Vertice<Destino>v, int i, boolean []marca, ListaGenerica<Vertice<Destino>> recor, ListaGenerica<Vertice<Destino>> recoAct, String ciudad2){
		marca[i]=true;
		recoAct.agregarFinal(v);
		if (v.dato().equals(ciudad2)) {
			recor= recoAct.clonar();
		}
		ListaGenerica<Arista<Destino>>ady=mapaCiudades.listaDeAdyacentes(v);
		while (!ady.fin()) {
			Arista<Destino>a= ady.proximo();
			int j= a.verticeDestino().getPosicion();
			if (!marca[j]) {
				this.recorridoCamin(a.verticeDestino(), j, marca, recor, recoAct, ciudad2);
			}
		}
		recoAct.eliminarEn(recoAct.tamanio());
	}
	
	private int buscarCiudad( String ciudad) {
		ListaGenerica <Vertice<Destino>> v= this.mapaCiudades.listaDeVertices();
		int i=-1;
		while (!v.fin()) {
			Vertice<Destino>ver= v.proximo();
			if (ver.dato().getCiudad().equals(ciudad)) {
				i= ver.getPosicion();
			}
		}
		return i;
	}
	
	public ListaGenerica <Vertice<Destino>> devolverCaminoExceptuando (ListaGenerica<Destino>ciudadesExcep, String ciudad1, String ciudad2){
		int i= buscarCiudad(ciudad1);
		ListaGenerica<Vertice<Destino>> recor= new ListaEnlazadaGenerica ();
		if (i!=-1) {
			boolean []marca= new boolean [this.mapaCiudades.listaDeVertices().tamanio()];
			for (int j=0; j< this.mapaCiudades.listaDeVertices().tamanio();j++) marca[j]=false;
			ListaGenerica<Vertice<Destino>> act= new ListaEnlazadaGenerica ();
			act.comenzar();
			this.recorridoCaminExcep(ciudadesExcep,this.mapaCiudades.vetice(i), i, marca, recor, act,ciudad2);
		}
		return recor;
	}
	
	private void recorridoCaminExcep (ListaGenerica<Destino> ciudadesE,Vertice<Destino>v, int i, boolean []marca, ListaGenerica<Vertice<Destino>> recor, ListaGenerica<Vertice<Destino>> recoAct, String ciudad2){
		marca[i]=true;
		recoAct.agregarFinal(v);
		if (v.dato().getCiudad().equals(ciudad2)) {
			recor= recoAct.clonar();
		}
		ListaGenerica<Arista<Destino>>ady=mapaCiudades.listaDeAdyacentes(v);
		boolean valido= true;
		while ((!ady.fin())&&(recor.esVacia())) {
			Arista<Destino>a= ady.proximo();
			int j= a.verticeDestino().getPosicion();
			while (!ciudadesE.fin()) {
				Destino ciudad= ciudadesE.proximo();
				if (ciudad.getCiudad().equals(a.verticeDestino().dato())) valido=false;
			}
			if ((!marca[j])&&(valido)) {
				this.recorridoCamin(a.verticeDestino(), j, marca, recor, recoAct, ciudad2);
			}
		}
		recoAct.eliminarEn(recoAct.tamanio());
	}
	
	public ListaGenerica<Vertice<Destino>> recorridoMasCorto (String ciudad1, String ciudad2){
		int i= buscarCiudad(ciudad1);
		int distancia=0;
		ListaGenerica<Vertice<Destino>> recor= new ListaEnlazadaGenerica ();
		if (i!=-1) {
			boolean []marca= new boolean [this.mapaCiudades.listaDeVertices().tamanio()];
			for (int j=0; j< this.mapaCiudades.listaDeVertices().tamanio();j++) marca[j]=false;
			ListaGenerica<Vertice<Destino>> act= new ListaEnlazadaGenerica ();
			act.comenzar();
			this.recorridoCaminoMenor(this.mapaCiudades.vetice(i), i, marca, recor, act,ciudad2,distancia);
		}
		return recor;
	}
	
	private void recorridoCaminoMenor (Vertice<Destino>v, int i, boolean []marca, ListaGenerica<Vertice<Destino>> recor, ListaGenerica<Vertice<Destino>> recoAct, String ciudad2,int distancia) {
		marca[i]=true;
		recoAct.agregarFinal(v);
		if (v.dato().getCiudad().equals(ciudad2)) {
			int act=0;
			while (!recor.fin()) {
				act+= recor.proximo().dato().getDistancia();
			}
			if (distancia<act)
			recor= recoAct.clonar();
		}
		ListaGenerica<Arista<Destino>>ady=mapaCiudades.listaDeAdyacentes(v);
		while (!ady.fin()) {
			Arista<Destino>a= ady.proximo();
			int j= a.verticeDestino().getPosicion();
			if (!marca[j]) {
				this.recorridoCaminoMenor(a.verticeDestino(), j, marca, recor, recoAct, ciudad2,distancia+a.peso());
			}
		}
		recoAct.eliminarEn(recoAct.tamanio());
	}
	public ListaGenerica<Vertice<Destino>>caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
		int i= buscarCiudad(ciudad1);
		ListaGenerica<Vertice<Destino>> recor= new ListaEnlazadaGenerica ();
		if (i!=-1) {
			boolean []marca= new boolean [this.mapaCiudades.listaDeVertices().tamanio()];
			for (int j=0; j< this.mapaCiudades.listaDeVertices().tamanio();j++) marca[j]=false;
			ListaGenerica<Vertice<Destino>> act= new ListaEnlazadaGenerica ();
			act.comenzar();
			this.recorridoCombustible(this.mapaCiudades.vetice(i), i, marca, recor, act,ciudad2,tanqueAuto);
		}
		return recor;
	}
	private void recorridoCombustible (Vertice<Destino>v, int i, boolean []marca, ListaGenerica<Vertice<Destino>> recor, ListaGenerica<Vertice<Destino>> recoAct, String ciudad2,int tanque) {
		marca[i]=true;
		recoAct.agregarFinal(v);
		if (v.dato().getCiudad().equals(ciudad2)) {
			recor=recoAct.clonar();
		}
		ListaGenerica<Arista<Destino>>ady=mapaCiudades.listaDeAdyacentes(v);
		while ((!ady.fin()) && (recor.esVacia())){
			Arista<Destino>a= ady.proximo();
			int j= a.verticeDestino().getPosicion();
			if ((!marca[j]) && ((tanque-a.peso())>0)) {
				this.recorridoCaminoMenor(a.verticeDestino(), j, marca, recor, recoAct, ciudad2,tanque-a.peso());
			}
		}
		recoAct.eliminarEn(recoAct.tamanio());
	}
	
	public ListaGenerica<Vertice<Destino>> caminoConMenorCarga (String ciudad1, String ciudad2, int tanqueAuto){
		int i= buscarCiudad(ciudad1);
		ListaGenerica<Vertice<Destino>> recor= new ListaEnlazadaGenerica ();
		if (i!=-1) {
			int cantCargas=0;
			boolean []marca= new boolean [this.mapaCiudades.listaDeVertices().tamanio()];
			for (int j=0; j< this.mapaCiudades.listaDeVertices().tamanio();j++) marca[j]=false;
			ListaGenerica<Vertice<Destino>> act= new ListaEnlazadaGenerica ();
			act.comenzar();
			this.recorridoConCarga(this.mapaCiudades.vetice(i), i, marca, recor, act,ciudad2,tanqueAuto, cantCargas);
		}
		return recor;
	}
	
	private void recorridoConCarga (Vertice<Destino>v, int i, boolean []marca, ListaGenerica<Vertice<Destino>> recor, ListaGenerica<Vertice<Destino>> recoAct, String ciudad2,int tanque, int Cant) {
		marca[i]=true;
		recoAct.agregarFinal(v);
		if (v.dato().getCiudad().equals(ciudad2)) {
			recor=recoAct.clonar();
		}
		ListaGenerica<Arista<Destino>>ady=mapaCiudades.listaDeAdyacentes(v);
		while ((!ady.fin()) && (recor.esVacia())){
			Arista<Destino>a= ady.proximo();
			int j= a.verticeDestino().getPosicion();
			if ((!marca[j]) && ((tanque-a.peso())>0)) {
				this.recorridoConCarga(a.verticeDestino(), j, marca, recor, recoAct, ciudad2,tanque-a.peso(), Cant+1);
			}
		}
		recoAct.eliminarEn(recoAct.tamanio());
	}
}
