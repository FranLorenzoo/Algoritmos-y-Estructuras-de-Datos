package tp06.ejercicio3;

import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class Recorridos<T> {
	private GrafoImplListAdy grafo;
	
	public Recorridos () {
		
	}
	
	public void DFS () {
		boolean [] marca= new boolean [grafo.listaDeVertices().tamanio()];
		for (int i=0; i< grafo.listaDeVertices().tamanio(); i++) {
			marca[i]=false;
		}
		for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i]) this.DfsImpl(this.grafo.vetice(i), marca,i);
		}
		
	}
	
	private void DfsImpl (Vertice<T> vertice, boolean [] marca, int i) {
		marca[i]=true;
		System.out.println(vertice);
		ListaGenerica <Arista<T>> ady= grafo.listaDeAdyacentes(vertice);
		ady.comenzar();
		int j=0;
		while (!ady.fin()) {
			Arista <T> ad= ady.proximo();
			j= ad.verticeDestino().getPosicion();
			if (!marca[j]) {
				this.DfsImpl(ad.verticeDestino(), marca, j);
			}
		}
	}
	
	public void BFS () {
		boolean [] marca = new boolean [this.grafo.listaDeVertices().tamanio()];
		for (int i=0; i< this.grafo.listaDeVertices().tamanio();i++) marca[i]=false;
		for (int i=0; i< this.grafo.listaDeVertices().tamanio();i++) {
			if (!marca[i]) {
				BfsReco (i+1, this.grafo.vetice(i+1),marca);
			}
		}
	}
	
	private void BfsReco (int i, Vertice<T>ver, boolean []marca) {
		marca[i]=true;
		ColaGenerica <Vertice<T>> vert= new ColaGenerica<>();
		vert.encolar(ver);
		ListaGenerica <Arista<T>> ady= null;
		while (!vert.esVacia()) {
			Vertice<T> vertice= vert.desencolar();
			System.out.println(vertice);
			ady= this.grafo.listaDeAdyacentes(vertice);
			while (!ady.fin()) {
				Arista<T> des= ady.proximo();
				int j= des.verticeDestino().getPosicion();
				if (!marca[j]) {
					vert.encolar(des.verticeDestino());
					marca[j]=true;
				}
			}
		}
	}
}
