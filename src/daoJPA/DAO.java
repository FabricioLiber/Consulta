package daoJPA;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class DAO<T> implements DAOInterface<T> { 

    protected static EntityManager manager;
    protected static EntityManagerFactory factory;
    public DAO(){}
 
    public static void open(){
        if(manager==null){
            //propriedades do persistence.xml  que podem ser sobrescritas       
            HashMap<String,String> properties = new HashMap<String,String>();       
            //          properties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver" );    
            //          properties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:5432/loja");
            //          properties.put(PersistenceUnitProperties.JDBC_USER, "postgres");
            //          properties.put(PersistenceUnitProperties.JDBC_PASSWORD, "ifpb");
            //          properties.put(PersistenceUnitProperties.LOGGING_LEVEL, "off");
            //          properties.put(PersistenceUnitProperties.LOGGING_FILE, "log.txt");
            //          properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION, "create");
            //          properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_SCRIPTS_ACTION, "create");                       
            //          properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_SCRIPTS_ACTION, "create");
            //          properties.put("hibernate.hbm2ddl.auto", "update");
            factory = Persistence.createEntityManagerFactory("consulta-eclipselink", properties);
            //factory = Persistence.createEntityManagerFactory("consulta-hibernate", properties);
            manager = factory.createEntityManager();
        }
    }
    public static void close(){
        if(manager != null) {
            manager.close();
            factory.close();
        }
    }
    public void create(T obj){
    	System.out.println(obj);
        manager.persist(obj);
    }
     
    @SuppressWarnings("unchecked")
    public T read(Object chave){    // localizar pela chave primaria
        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return manager.find(type, chave);
    }
    public T update(T obj){
        return manager.merge(obj);
    }
    public void delete(T obj) {
        manager.remove(obj);
    }
 
 
    @SuppressWarnings("unchecked")
    public List<T> readAll(){
        Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Query query = manager.createQuery("select x from " + type.getSimpleName() + " x");
        return (List<T>) query.getResultList();
    }
 
    //----------------------- TRANSAÇÃO   ----------------------
    public static void begin(){
        if(!manager.getTransaction().isActive())
            manager.getTransaction().begin();
    }
    public static void commit(){
        if(manager.getTransaction().isActive()){
            manager.getTransaction().commit();
            manager.clear();        // ---- esvaziar o cache de objetos
        }
    }
    public static void rollback(){
        if(manager.getTransaction().isActive())
            manager.getTransaction().rollback();
    }
}
