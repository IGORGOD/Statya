package converter;
/**
 * ��������� ��� ����������� �������� ����� � ������ � ������ � �������� ���� �
 * @author Ivan
 *
 */
public interface Converter {
	/**
	 * ����� ����������� �������� ���� � � ������
	 * @param value ��������
	 * @return �������� ���������� �������
	 */
	public <T> String toString(T value);
	/**
	 * ����� ����������� ������ � �������� ���� �
	 * @param str �������� ���������� �������
	 * @return �������� ���� �
	 */
	public <T> T toValue(String str);

}