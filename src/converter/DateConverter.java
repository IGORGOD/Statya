package converter;

import java.text.SimpleDateFormat;
/**
 * Конвертth даты
 * @author Ivan
 *
 */
public class DateConverter implements Converter {

	@Override
	public <Date> String toString(Date value) {
	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(value);
	}
	@Override
	public <T> T toValue(String str) {
		return null;
	}
	

}
