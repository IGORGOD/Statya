package converter;
/**
 * Конвертер класса-обертки Integer
 * @author Ivan
 */
public class IntegerConverter implements Converter {

	@Override
	public <Integer> String toString(Integer value) {
		return ""+value;
	}

	@Override
	public <T> T toValue(String str) {
		int x=Integer.parseInt(str);
		return (T)new Integer(Integer.parseInt(str));
		
	}

}
