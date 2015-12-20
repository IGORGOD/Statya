package ua.ii.db;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configuration.ConfigLoader;
import configuration.Configuration;
import annotation.Stored;
import annotation.DAOAnnotationUtils;

import com.mysql.jdbc.Statement;

public class DBAccessor implements DBCRUDSInterface{
	
	/**
	 * Обьект для выполнения sql-запросов
	 */
	private  Statement statement;
	
	/**
	 * Возвращенный базой результат выполнения запроса
	 */
	private  ResultSet resultSet;
	
	/**
	 * Соединение с БД
	 */
	private  Connection connection;
	
	/**
	 * Конфигурация соединения
	 */
	private  Configuration config;
	
	@Override
	public void open() {
		try
        {
			config = new ConfigLoader().loadXMLConfig(new File("Config_BD.xml"));
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(config.getProperty("config.bd.url"),
                    config.getProperty("config.bd.user"), config.getProperty("config.bd.password"));
                    statement = (Statement) connection.createStatement();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void close() {
		try{
			statement.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public <T> void insert(T instance) {
		try {
			StringBuilder str=new StringBuilder("INSERT INTO ");
			StringBuilder str1=new StringBuilder("(");
			str.append(DAOAnnotationUtils.getStorageName(instance.getClass()));
			Field[] fields = instance.getClass().getDeclaredFields();
			str.append("(");
			for(Field field:fields){
				if(!field.getAnnotation(Stored.class).name().equals("id")){
					str.append(field.getAnnotation(Stored.class).name()+", ");	
					str1.append("\"" +DAOAnnotationUtils.getFieldValue(instance,field) +"\"");
					str1.append(", ");
				}
				
			}
			str1=new StringBuilder(str1.substring(0, str1.length()-2));
			str1.append(")");
			str=new StringBuilder(str.substring(0, str.length()-2));
			str.append(") VALUES");
			str.append(str1);
			System.out.println(str);
			statement.execute(str.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public <T> void update(T instance) {
		try{
			Field[] fields = instance.getClass().getDeclaredFields();
			StringBuilder sql=new StringBuilder();
			sql.append("UPDATE ");
			sql.append(DAOAnnotationUtils.getStorageName(instance.getClass())+" "+"SET ");
			for(Field field:fields){
				sql.append(field.getAnnotation(Stored.class).name()+"=");
				sql.append("\"" +DAOAnnotationUtils.getFieldValue(instance,field) +"\"");
				sql.append(", ");
			}
			sql=new StringBuilder(sql.substring(0, sql.length()-2));
			sql.append(" WHERE id");
			sql.append(DAOAnnotationUtils.getStorageName(instance.getClass()));
			sql.append("=");
			sql.append(DAOAnnotationUtils.getFieldValue(instance,fields[0]));
			System.out.println(sql.toString());
			statement.execute(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public <T> List<T> select(String SQLString, Class<T> entityClass) {
		try{
			resultSet = statement.executeQuery(SQLString);
			ArrayList<T> result=new ArrayList<T>();

			while(resultSet.next()){  
				T instance=DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
				result.add(instance);   
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> select(Class<T> entityClass) {
		try{
	    	ArrayList<T> result=new ArrayList<T>();
	    	Field[] fields = entityClass.newInstance().getClass().getDeclaredFields();
	    	StringBuilder str=new StringBuilder("SELECT ");
	    	for(Field field:fields){
				str.append(field.getAnnotation(Stored.class).name()+", ");	
			}
	    	str=new StringBuilder(str.substring(0, str.length()-2));
	    	str.append(" FROM ");
	    	str.append(DAOAnnotationUtils.getStorageName(entityClass));
	    	resultSet = statement.executeQuery(str.toString());
	    	while(resultSet.next()){
	    		T instance=DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
	    		result.add(instance);
	    	}
	    	return result;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public <T> List<T> readAll(Class<T> entityClass) {
		try{
			ArrayList<T> result=new ArrayList<T>();
			Field[] fields = entityClass.newInstance().getClass().getDeclaredFields();
			StringBuilder str=new StringBuilder("SELECT ");
			for(Field field:fields){
				str.append(field.getAnnotation(Stored.class).name()+", ");	
			}
			str=new StringBuilder(str.substring(0, str.length()-2));
			str.append(" FROM ");
			str.append(DAOAnnotationUtils.getStorageName(entityClass));
			System.out.println(str);
			resultSet = statement.executeQuery(str.toString());
			while(resultSet.next()){
				T instance=DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
				result.add(instance);
			}
			return result;	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public <T> 	T read(Class<T> entityClass, int id){
		try{
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT ");
			sql.append(" * FROM ");
			sql.append(DAOAnnotationUtils.getStorageName(entityClass));
			sql.append(" WHERE id");
			sql.append(entityClass.getSimpleName());
			sql.append(" = ");
			sql.append(id);
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql.toString());
			rs.last();
			return DAOAnnotationUtils.fromResultSet(rs, entityClass);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public <T> 	T read(Class<T> entityClass, String text){
		try{
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT * FROM");
			sql.append(DAOAnnotationUtils.getStorageName(entityClass));
			sql.append(" WHERE text = ");
			sql.append(entityClass.getDeclaredField("Text"));
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql.toString());
			return DAOAnnotationUtils.fromResultSet(rs, entityClass);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
