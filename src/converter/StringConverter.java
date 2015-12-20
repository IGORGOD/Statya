package converter;
/**
 * Конвертер строки
 * @author Admin
 *
 */
public class StringConverter implements Converter{

	@Override
	public <T> String toString(T value) {
		// TODO Auto-generated method stub
		return (String)value;
	}

	@Override
	public <T> T toValue(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
