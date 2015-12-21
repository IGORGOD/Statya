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
import converter.IntegerConverter;
import converter.StringConverter;

/**
 * ����� � �������� ��� ������ � ��������������� ������
 * @author Ivan
 */
public class DAOAnnotationUtils {
	
	static FileInputStream stream;
	
	/**
	 * ����� ���������� ��� ������, � ������� ����� ���������� ����
	 * @param c ����
	 * @return ��� ������
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getStorageName(Class c){
		Stored t = (Stored) c.getAnnotation(Stored.class);
		return (t!=null) ? t.name() : null;
	}
	
	/**
	 * ����� �������� ��� ���� � ���������� Stored
	 * @param c ����� �� ������
	 * @return ��������� �����
	 */
	@SuppressWarnings("rawtypes")
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
	 *  ���������� �������� ���� f �� ������ instance
	 * @param instance �����
	 * @param f ���� 
	 * @return �������� ���� (������)
	 */
	@SuppressWarnings("rawtypes")
	public static <T> String getStringValue(T instance, Field f){
		try {
			PropertyDescriptor p = new PropertyDescriptor(f.getName(), instance.getClass());
			Class fieldClass = p.getPropertyType();
			if (fieldClass.getCanonicalName().equals("int")){
				return ((Integer) p.getReadMethod().invoke(instance)).toString();
			}
			if (fieldClass.getCanonicalName().equals("double")){
				return ((Double) p.getReadMethod().invoke(instance)).toString();
			}
			Object value = p.getReadMethod().invoke(instance);
			
			if(value==null) return "null";
			
			if (fieldClass.equals(String.class) || fieldClass.equals(UUID.class)) return "\""+value.toString()+"\"";
			return	(value!=null) ? value.toString() : "NULL";
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
	 * ����� ��� ���������� �������� ����
	 * @param instance �����, � ������� ��������� ������ ����
	 * @param f ����
	 * @return ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(T instance, Field f){
			PropertyDescriptor p;
			try {
				p = new PropertyDescriptor(f.getName(), instance.getClass());
				return ((T) p.getReadMethod().invoke(instance));
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
	 * ���������� ��������� ����������� ����
	 * @param field ����
	 * @return ����� ����������
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends Converter> T getValueConverter(Field field){
		Stored s = (Stored) field.getAnnotation(Stored.class);
		Class converterClass = s.converter();
		try {
			Converter res = (Converter) converterClass.newInstance();
			return (T) res;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����� ��������� ����������, ������������� ����� ������
	 * @param rs ��������� ������� � ��
	 * @param entityClass ����� ������, � ������� ����� �������� ���������
	 * @return ������ ������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
