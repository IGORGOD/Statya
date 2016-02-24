package converter;

public class DoubleConverter implements Converter {

	public <T> String toString(T value) {
		return (String) value;
	}

	@SuppressWarnings("unchecked")
	public <T> T toValue(String str) {
		return (T) new Double(Double.parseDouble(str));
	}

}
