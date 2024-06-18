package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Cancion;

public interface CancionDAO {
	
	void create(Cancion c);
	boolean delete(Cancion c);
	void update(Cancion c);
	Cancion get(int id);
	List<Cancion> getAll();
	

}
