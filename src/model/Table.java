package model;

import annotation.Stored;
import converter.ByteConverter;
import converter.IntegerConverter;
import converter.StringConverter;

@Stored(name = "tbl_data")
public class Table {
	
	@Stored(name = "id_data", converter = IntegerConverter.class)
	private int id_data;
	
	@Stored(name = "date_year", converter = StringConverter.class)
	private String year;
	
	@Stored(name = "date_month", converter = StringConverter.class)
	private String month;
	
	@Stored(name = "date_dw", converter = ByteConverter.class)
	private byte dayOfWeek;
	
	@Stored(name = "date_time", converter = StringConverter.class)
	private String time;
	
	@Stored(name = "launchPoint", converter = StringConverter.class)
	private String launchPoint;
	
	@Stored(name = "destinationPoint", converter = StringConverter.class)
	private String destinationPoint;
	
	@Stored(name = "numOfPassengers", converter = IntegerConverter.class)
	private int numOfPassengers;

}
