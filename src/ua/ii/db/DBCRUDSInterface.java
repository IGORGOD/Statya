package ua.ii.db;

import java.util.List;

public interface DBCRUDSInterface {

	/**
	 * Метод открытия доступа к базе данных
	 */
	public void open(); 

	/**
	 *  Метод закрытия доступа к базе данных
	 */
	public void close();

	/**
	 * Метод для добавления обьекта модели в базу данных
	 * @param instance Обьект модели
	 */
	public <T> void insert(T instance);

	/**
	 * Метод обновления обьекта модели в базе данных
	 * @param instance Обьект модели
	 */
	public <T> void update(T instance);

	/**
	 * Метод выбора с базы данных обьектов типа Т
	 * @param SQLString SQL-Команда выбора
	 * @param entityClass Тип обьекта
	 * @return Коллекция обьектов Т
	 */
	public <T> List<T> select(String SQLString, Class<T> entityClass);

	/**
	 *  Метод выбора с базы данных обьектов типа Т с помощью фильтра
	 * @param filter Фильтр выбора
	 * @param entityClass Тип обьекта
	 * @return Коллекция обьектов Т
	 */
	public <T> List<T> select(Class<T> entityClass);

	/**
	 * Метод выбора с базы данных всех обьектов типа Т
	 * @param entityClass Тип обьекта
	 * @return Коллекция обьектов Т
	 */
	public <T> List<T> readAll(Class<T> entityClass);
}
