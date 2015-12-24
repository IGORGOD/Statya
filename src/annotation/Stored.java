package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import converter.StringConverter;

@Retention(RetentionPolicy.RUNTIME)
public @interface Stored {
	
	/**
	 * Имя аннотированного поля
	 * @return
	 */
	public String name() default "";
	
	/**
	 * Тип конвертера аннотированного поля
	 * @return Конвертер
	 */
	@SuppressWarnings("rawtypes")
	public Class converter() default StringConverter.class;
	
}