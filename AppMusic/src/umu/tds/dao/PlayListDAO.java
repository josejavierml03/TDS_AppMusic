package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface PlayListDAO {
	
	void create(PlayList pl);
	boolean delete(PlayList pl);
	void update(PlayList pl);
	PlayList get(int id);
	List<PlayList> getAll();
	
}
