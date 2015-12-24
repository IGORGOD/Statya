package converter;
/**
 * Интерфейс для конвертеров значений полей в строку и строки в значение типа Т
 * @author Ivan
 *
 */
public interface Converter {
	/**
	 * Метод конвертации значения типа Т в строку
	 * @param value значение
	 * @return значение записанное строкой
	 */
	public <T> String toString(T value);
	/**
	 * Метод конвертации строки в значение типа Т
	 * @param str значение записанное строкой
	 * @return значение типа Т
	 */
	public <T> T toValue(String str);

}