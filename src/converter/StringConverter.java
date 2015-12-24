package converter;
/**
 * Конвертер строки
 * @author Admin
 *
 */
public class StringConverter implements Converter{

	@Override
	public <T> String toString(T value) {
		return (String)value;
	}

	@Override
	public <T> T toValue(String str) {
		return null;
	}

}
