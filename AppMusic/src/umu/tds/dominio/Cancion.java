package umu.tds.dominio;

public class Cancion {
	
	private String titulo;
	private String ruta;
	private int numRepros;
	private String interprete;
	private String estilo;
	private int id;
	
	public Cancion(String titulo, String ruta, String interprete, String estilo) {
		
		this.titulo = titulo;
		this.ruta = ruta;
		this.numRepros = 0;
		this.interprete = interprete;
		this.estilo = estilo;
	}
	
	public void reproducida() {
		numRepros++;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public int getNumRepros() {
		return numRepros;
	}
	public void setNumRepros(int numRepros) {
		this.numRepros = numRepros;
	}
	public String getInterprete() {
		return interprete;
	}
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
