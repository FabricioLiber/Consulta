package dao;

import java.util.List;

public interface InterfaceDAO <T> {
	
	public void create (T object);
	public T update (T object);
	public void delete (T object);
	public List<T> readAll ();

}
