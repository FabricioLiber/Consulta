package daoDB4O;

import java.util.List;

public interface DAOInterface <T> {
	
	public void create (T object);
	public T update (T object);
	public void delete (T object);
	public List<T> readAll ();

}
