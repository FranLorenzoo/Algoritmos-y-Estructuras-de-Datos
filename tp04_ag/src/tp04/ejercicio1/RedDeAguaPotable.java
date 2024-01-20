package tp04_ag.src.tp04.ejercicio1;

import tp04_ag.src.tp02.ejercicio2.ListaGenerica;

public class RedDeAguaPotable {
	private ArbolGeneral <Caudal> redCaudal;
	private double min=5000;
	
	public RedDeAguaPotable (ArbolGeneral <Caudal>a) {
		redCaudal=a;
	}
	
	public double minimoCaudal (double caud) {
		if (!this.redCaudal.esVacio()) {
			ListaGenerica <ArbolGeneral<Caudal>>Laux=this.redCaudal.getHijos();
			Laux.comenzar();
			Caudal aux = null;
			aux.setCaudal(caud);
			if (caud<min) {
				min=caud;
			}
			this.redCaudal.setDato(aux);
			while (!Laux.esVacia()) {
				if (Laux.proximo()!=null) {
					int tamaño=redCaudal.getHijos().getTamanio();
					this.redCaudal.getHijos();
					minimoCaudal(caud/tamaño);
				}
				Laux.proximo();
			}
		}
		return min;
	}
}
