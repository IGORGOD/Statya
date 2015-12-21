package converter;

public class ByteConverter implements Converter {

	@Override
	public <T> String toString(T value) {
		return (String)value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T toValue(String str) {
		return (T)new Byte(Byte.parseByte(str));
	}

}
