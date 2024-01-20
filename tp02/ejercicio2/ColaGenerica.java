package tp02.ejercicio2;

public class ColaGenerica<T> {
	private ListaGenerica <T> cola;
	private int tam;
	
	public ColaGenerica () {
		tam=1;
		this.cola=new ListaEnlazadaGenerica <T>();
	}
	
	public void encolar (T dato) {
		this.cola.agregarFinal(dato);
		
	}
	
	public T desencolar () {
		T dato= this.cola.elemento(1);
		tam--;
		this.cola.elemento(1);
		return dato;
	}
	
	public boolean esVacia () {
		return cola.esVacia();
	}
}
