package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;

public interface PlayListDAO {
	
	void create(PlayList c);
	boolean delete(PlayList c);
	void update(PlayList c);
	PlayList get(int id);
	List<PlayList> getAll();
	
}
