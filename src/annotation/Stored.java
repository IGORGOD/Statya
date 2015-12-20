package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import converter.StringConverter;

@Retention(RetentionPolicy.RUNTIME)
public @interface Stored {
	/**
	 * ��� ��������������� ����
	 * @return
	 */
	public String name() default "";
	/**
	 * ��� ���������� ��������������� ����
	 * @return ���������
	 */
	public Class  converter() default StringConverter.class;
	
}