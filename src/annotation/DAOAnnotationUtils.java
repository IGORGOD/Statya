package annotation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.UUID;

import annotation.Stored;
import converter.Converter;
import converter.DateConverter;
import converter.IntegerConverter;
import converter.StringConverter;

/**
 * Класс с методами для работы с аннотированными полями
 * @author Ivan
 */
public class DAOAnnotationUtils {
	static FileInputStream stream;
	
	/**
	 * Метод возвращает имя класса, в котором лежит переданное поле
	 * @param c Поле
	 * @return Имя класса
	 */
	public static String getStorageName(Class c){
		Stored t = (Stored) c.getAnnotation(Stored.class);
		return (t!=null) ? t.name() : null;
	}
	
	/**
	 * Метод выбирает все поля с аннотацией Stored
	 * @param c Класс из модели
	 * @return Коллекция полей
	 */
	public static HashMap<String, Field> getStoredFields(Class c){
		HashMap<String, Field> res = new HashMap<String,Field>();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			Stored p = field.getAnnotation(Stored.class);
			if (p!=null){
				res.put(p.name(),field);
			}
		}
		return res;
	}
	
	/**
	 *  Возвращает значение поля f из класса instance
	 * @param instance Класс
	 * @param f Поле 
	 * @return Значение поля (Строка)
	 */
	public static <T> String getStringValue(T instance, Field f){
		try {
			PropertyDescriptor p = new PropertyDescriptor(f.getName(), instance.getClass());
			Class fieldClass = p.getPropertyType();
			if (fieldClass.getCanonicalName().equals("int")){
				return ((Integer) p.getReadMethod().invoke(instance,null)).toString();
			}
			if (fieldClass.getCanonicalName().equals("double")){
				return ((Double) p.getReadMethod().invoke(instance,null)).toString();
			}
			Object value = p.getReadMethod().invoke(instance,null);
			
			if(value==null) return "null";
			
			if (fieldClass.equals(String.class) || fieldClass.equals(UUID.class)) return "\""+value.toString()+"\"";
			
			String prefix = ""; // (isReference(f)) ? "^"+f.getType().getCanonicalName()+" : " : "";
			return	(value!=null) ? prefix+value.toString() : prefix+"NULL";
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Метод для доставания значения поля
	 * @param instance Класс, в котором находится нужное поле
	 * @param f Поле
	 * @return Значение
	 */
	public static <T> T getFieldValue(T instance, Field f){
			PropertyDescriptor p;
			try {
				p = new PropertyDescriptor(f.getName(), instance.getClass());
				return ((T) p.getReadMethod().invoke(instance,null));
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
			
	}
	
	/**
	 * Возвращает конвертер переданного поля
	 * @param field Поле
	 * @return Класс конвертера
	 */
	public static <T extends Converter> T getValueConverter(Field field){
		Stored s = (Stored) field.getAnnotation(Stored.class);
		Class converterClass = s.converter();
		try {
			Converter res = (Converter) converterClass.newInstance();
			return (T) res;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Метод обработки результата, возвращенного базой данных
	 * @param rs Результат запроса к БД
	 * @param entityClass Класс модели, в который будет сохранен результат
	 * @return Обьект модели
	 */
	public static <T> T fromResultSet(ResultSet rs,Class entityClass){
		try{
			T instance = (T)entityClass.newInstance();
			Field[] fields = entityClass.getDeclaredFields();
			for(Field field:fields){
				PropertyDescriptor p = new PropertyDescriptor(field.getName(), entityClass);
				if(field.getAnnotation(Stored.class).converter().equals(IntegerConverter.class)){
					int i=rs.getInt(field.getAnnotation(Stored.class).name());
					p.getWriteMethod().invoke(instance, i);		
				}
				if(field.getAnnotation(Stored.class).converter().equals(DateConverter.class)){	
					Date i=rs.getDate(field.getAnnotation(Stored.class).name());
					p.getWriteMethod().invoke(instance, i);
				}
				if(field.getAnnotation(Stored.class).converter().equals(StringConverter.class)){
					String i=rs.getString(field.getAnnotation(Stored.class).name());
					p.getWriteMethod().invoke(instance, i);
				}
			}
			return instance;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public DAOAnnotationUtils() {}
}
