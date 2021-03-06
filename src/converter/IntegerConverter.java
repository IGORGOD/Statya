package converter;

/**
 * Конвертер класса-обертки Integer
 * 
 * @author Ivan
 */
public class IntegerConverter implements Converter {

	@SuppressWarnings("hiding")
	public <Integer> String toString(Integer value) {
		return String.valueOf(value);
	}

	@SuppressWarnings("unchecked")
	public <T> T toValue(String str) {
		return (T) new Integer(Integer.parseInt(str));
	}

}
