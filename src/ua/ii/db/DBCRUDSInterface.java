package ua.ii.db;

import java.util.List;

public interface DBCRUDSInterface {

	/**
	 * ����� �������� ������� � ���� ������
	 */
	public void open(); 

	/**
	 *  ����� �������� ������� � ���� ������
	 */
	public void close();

	/**
	 * ����� ��� ���������� ������� ������ � ���� ������
	 * @param instance ������ ������
	 */
	public <T> void insert(T instance);

	/**
	 * ����� ���������� ������� ������ � ���� ������
	 * @param instance ������ ������
	 */
	public <T> void update(T instance);

	/**
	 * ����� ������ � ���� ������ �������� ���� �
	 * @param SQLString SQL-������� ������
	 * @param entityClass ��� �������
	 * @return ��������� �������� �
	 */
	public <T> List<T> select(String SQLString, Class<T> entityClass);

	/**
	 *  ����� ������ � ���� ������ �������� ���� � � ������� �������
	 * @param filter ������ ������
	 * @param entityClass ��� �������
	 * @return ��������� �������� �
	 */
	public <T> List<T> select(DAOFilter filter, Class<T> entityClass);

	/**
	 * ����� ������ � ���� ������ ���� �������� ���� �
	 * @param entityClass ��� �������
	 * @return ��������� �������� �
	 */
	public <T> List<T> readAll(Class<T> entityClass);
}
