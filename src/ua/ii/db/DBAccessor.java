package ua.ii.db;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import annotation.AutoincrementPK;
import annotation.DAOAnnotationUtils;
import annotation.Stored;
import configuration.ConfigLoader;
import configuration.Configuration;
import model.City;

public class DBAccessor implements DBCRUDSInterface {

	/**
	 * Обьект для выполнения sql-запросов
	 */
	private Statement statement;

	/**
	 * Возвращенный базой результат выполнения запроса
	 */
	private ResultSet resultSet;

	/**
	 * Соединение с БД
	 */
	private Connection connection;

	/**
	 * Конфигурация соединения
	 */
	private Configuration config;

	public void open() {
		try {
			config = new ConfigLoader().loadXMLConfig(new File("src/BD.xml"));
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(config.getProperty("config.bd.url"),
					config.getProperty("config.bd.user"), config.getProperty("config.bd.password"));
			statement = (Statement) connection.createStatement();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void insert(T instance) {
		try {
			StringBuilder str = new StringBuilder("INSERT INTO ");
			StringBuilder str1 = new StringBuilder("(");
			str.append(DAOAnnotationUtils.getStorageName(instance.getClass()));
			Field[] fields = instance.getClass().getDeclaredFields();
			str.append("(");
			for (Field field : fields) {
				if (field.isAnnotationPresent(Stored.class)){
					str.append(field.getAnnotation(Stored.class).name() + ", ");
					str1.append("\"" + DAOAnnotationUtils.getFieldValue(instance, field) + "\", ");
				}
			}
			str1 = new StringBuilder(str1.substring(0, str1.length() - 2));
			str1.append(")");
			str = new StringBuilder(str.substring(0, str.length() - 2));
			str.append(") VALUES");
			str.append(str1);
			System.out.println(str);
			statement.execute(str.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void update(T instance) {
		try {
			Field[] fields = instance.getClass().getDeclaredFields();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ");
			sql.append(DAOAnnotationUtils.getStorageName(instance.getClass()) + " SET ");
			Field idField = null;
			for (Field field : fields) {
				if (field.isAnnotationPresent(Stored.class)){
					sql.append(field.getAnnotation(Stored.class).name());
					sql.append("=\"" + DAOAnnotationUtils.getFieldValue(instance, field) + "\", ");
					continue;
				}
				if (field.isAnnotationPresent(AutoincrementPK.class)){
					idField = field;
				}
			}
			sql = new StringBuilder(sql.substring(0, sql.length() - 2));
			sql.append(" WHERE ");
			sql.append(idField.getAnnotation(AutoincrementPK.class).name());
			sql.append(" = ");
			sql.append(DAOAnnotationUtils.getFieldValue(instance, idField));
			System.out.println(sql.toString());
			statement.execute(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> select(String SQLString, Class<T> entityClass) {
		try {
			resultSet = statement.executeQuery(SQLString);
			ArrayList<T> result = new ArrayList<T>();
			while (resultSet.next()) {
				T instance = DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
				result.add(instance);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> List<T> select(Class<T> entityClass) {
		try {
			ArrayList<T> result = new ArrayList<T>();
			Field[] fields = entityClass.newInstance().getClass().getDeclaredFields();
			StringBuilder str = new StringBuilder("SELECT ");
			for (Field field : fields) {
				if (field.isAnnotationPresent(AutoincrementPK.class)){
					str.append(field.getAnnotation(AutoincrementPK.class).name() + ", ");
				}
				if (field.isAnnotationPresent(Stored.class)){
					str.append(field.getAnnotation(Stored.class).name() + ", ");
				}
			}
			str = new StringBuilder(str.substring(0, str.length() - 2));
			str.append(" FROM ");
			str.append(DAOAnnotationUtils.getStorageName(entityClass));
			resultSet = statement.executeQuery(str.toString());
			while (resultSet.next()) {
				T instance = DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
				result.add(instance);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> List<T> readAll(Class<T> entityClass) {
		try {
			ArrayList<T> result = new ArrayList<T>();
			Field[] fields = entityClass.newInstance().getClass().getDeclaredFields();
			StringBuilder str = new StringBuilder("SELECT ");
			for (Field field : fields) {
				if (field.isAnnotationPresent(AutoincrementPK.class)){
					str.append(field.getAnnotation(AutoincrementPK.class).name() + ", ");
				}
				if (field.isAnnotationPresent(Stored.class)){
					str.append(field.getAnnotation(Stored.class).name() + ", ");
				}
			}
			str = new StringBuilder(str.substring(0, str.length() - 2));
			str.append(" FROM ");
			str.append(DAOAnnotationUtils.getStorageName(entityClass));
			System.out.println(str);
			resultSet = statement.executeQuery(str.toString());
			while (resultSet.next()) {
				T instance = DAOAnnotationUtils.fromResultSet(resultSet, entityClass);
				result.add(instance);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
