package ua.ii.db;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> select(String SQLString, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> select(DAOFilter filter, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> readAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
