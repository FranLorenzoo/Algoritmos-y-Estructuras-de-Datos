package tp04_ag.src.tp02.ejercicio2;

public class ColaGenerica<T> extends ListaEnlazadaGenerica{
	private ListaEnlazadaGenerica <T> cola;
	private int sp;
	private int fin;
	
	public void encolar (T dato) {
		if (cola.esVacia()) {
			cola.comenzar();
			sp=0;
			fin= 0;
		}
		cola.agregarFinal(dato);
		fin++;
	}
	
	public T desencolar () {
		T aux=cola.elemento(sp);
		cola.eliminarEn(sp);
		return aux;
	}
	
	public T tope () {
		return cola.elemento(fin-1);
	}
	
	public boolean esVacia () {
		return cola==null;
	}
}
