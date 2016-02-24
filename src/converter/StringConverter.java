package converter;

/**
 * Конвертер строки
 * 
 * @author Admin
 *
 */
public class StringConverter implements Converter {

	public <T> String toString(T value) {
		return (String) value;
	}

	public <T> T toValue(String str) {
		return null;
	}

}
