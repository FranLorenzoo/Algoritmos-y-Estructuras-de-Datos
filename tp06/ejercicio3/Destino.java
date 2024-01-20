package tp06.ejercicio3;

public class Destino {
	private String ciudad;
	private int distancia;
	
	public Destino(String ciudad, int distancia) {
		this.ciudad = ciudad;
		this.distancia = distancia;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	
}
