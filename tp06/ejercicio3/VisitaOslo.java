package tp06.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class VisitaOslo {

	public ListaGenerica<Vertice<String>> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo,ListaGenerica<String> lugaresRestringidos){
		boolean [] marca= new boolean [lugares.listaDeVertices().tamanio()];
		for (int i=0; i< lugares.listaDeVertices().tamanio();i++)marca[i]=false;
		int i=buscarAyuntamiento (lugares);
		ListaGenerica<Vertice<String>>recorrido= new ListaEnlazadaGenerica();
		if (i!= -1) {
			ListaGenerica<Vertice<String>>actual= new ListaEnlazadaGenerica();
			this.recorridoDFS(lugares,actual, recorrido,destino, maxTiempo, lugaresRestringidos, lugares.vetice(i),i, marca); 
		}
		return recorrido;
	}
	
	private void recorridoDFS (Grafo <String> lug, ListaGenerica<Vertice<String>> act, ListaGenerica<Vertice<String>>definitiva,String destino, int maxTiempo,ListaGenerica<String> lugaresRestringidos, Vertice<String>v,int i,boolean []marca) {
		marca[i]=false;
		act.agregarFinal(v);
		boolean valido = true;
		lugaresRestringidos.comenzar();
		while ((!lugaresRestringidos.fin()) && (valido)){
			String ciu= lugaresRestringidos.proximo();
			if (ciu.equals(v.dato())) valido=false;
		}
		if (valido) {
			if (v.dato().equals(destino)) {
				definitiva= act.clonar();
			}
			ListaGenerica <Arista<String>>ady= lug.listaDeAdyacentes(v);
			while ((!ady.fin())&&(definitiva.esVacia())) {
				Arista<String>a= ady.proximo();
				int j= a.verticeDestino().getPosicion();
				if ((!marca[j])&&((maxTiempo-a.peso())>0)) {
					this.recorridoDFS(lug,act, definitiva,destino, maxTiempo-a.peso(), lugaresRestringidos, a.verticeDestino(),j, marca); 
				}
			}
		}
		act.eliminarEn(act.tamanio());
	}
	
	private int buscarAyuntamiento(Grafo<String>g) {
		ListaGenerica <Vertice<String>> v= g.listaDeVertices();
		int i=-1;
		while ((!v.fin()) &&(i==-1)){
			Vertice<String>ver= v.proximo();
			if (ver.dato().equals("Ayuntamiento")) {
				i= ver.getPosicion();
			}
		}
		return i;
	}
}
