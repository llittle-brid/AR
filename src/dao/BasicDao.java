package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import pojo.JdbcUtils;



 

/**
 * 
 * 
 * @author jy_meng
 *
 * @param <T>
 */
public class BasicDao<T> {
	
	private QueryRunner qr=new QueryRunner();
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BasicDao(){
		Type superClass=getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType=(ParameterizedType)superClass;
			
			Type [] typeArgs=parameterizedType.getActualTypeArguments();
			if(typeArgs !=null && typeArgs.length>0){
				if(typeArgs[0] instanceof Class){
					clazz=(Class<T>)typeArgs[0];
				}
			}
		}
	}
     
	
	
	@SuppressWarnings("unchecked")
	public <E> E getForValue(String sql,Object ... args){
		Connection connection=null;
		try{
			connection=JdbcUtils.getConnection();
			return (E) qr.query(connection, sql,new ScalarHandler(),args);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	
	
	public List<T> getForList(String sql,Object ... args){
		Connection connection=null;
		try{
			connection=JdbcUtils.getConnection();
			return qr.query(connection, sql, new BeanListHandler<>(clazz), args);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	


	
	public T get(String sql,Object ... args){
		Connection connection=null;
		try{
			connection=JdbcUtils.getConnection();
			return qr.query(connection, sql,new BeanHandler<>(clazz),args);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
		
		
	}
	


	
	public void update(String sql,Object ... args){
		Connection connection=null;
		try{
			connection=JdbcUtils.getConnection();
			qr.update(connection, sql, args);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
	}
	public void save(String sql,Object ... args){
		
	}
}
