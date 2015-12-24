package model;

import java.util.Calendar;
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
	
	public Table(int id, String year, String month, byte dayOfWeek,
			String time, String launchPoint, String destinationPoint, int numOfPassengers){
		id_data = id;
		// YEAR
		if (year != null && !year.isEmpty()){
			long millis = System.currentTimeMillis();
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(millis);
			int buf = Integer.valueOf(year);
			if (buf >= 2000 && buf <= c.get(1)){
				this.year = year;
			}
			else{
				throw new IllegalArgumentException("Year out of range [2000 - now]");
			}
		}
		else{
			throw new IllegalArgumentException("Year can't be null or empty");
		}
		// MONTH
		if (month != null && !month.isEmpty()){
			byte buf = Byte.valueOf(month);
			if (buf >= 0 && buf <=11){
				this.month = month;
			}
			else{
				throw new IllegalArgumentException("Month out of range [01 - 12]");
			}
		}
		else{
			throw new IllegalArgumentException("Month can't be null or empty");
		}
		// DAY OF WEEK
		if (dayOfWeek >= 0 && dayOfWeek < 7){
			this.dayOfWeek = dayOfWeek;
		}
		else{
			throw new IllegalArgumentException("Day of week out of range [0..6]");
		}
		// TIME
		this.time = time;
		// LAUNCH POINT
		if (launchPoint != null && !launchPoint.isEmpty()){
			this.launchPoint = launchPoint;
		}
		else{
			throw new IllegalArgumentException("Launch point can't be null or empty");
		}
		// DESTINATION POINT
		if (destinationPoint != null && !destinationPoint.isEmpty()){
			this.destinationPoint = destinationPoint;
		}
		else{
			throw new IllegalArgumentException("Destination point can't be null or empty");
		}
		// NUMBER OF PASSENGERS
		this.numOfPassengers = numOfPassengers;
	}
	
	public void setId_data(int id_data) {
		this.id_data = id_data;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDayOfWeek(byte dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setLaunchPoint(String launchPoint) {
		this.launchPoint = launchPoint;
	}

	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}

	public int getId_data() {
		return id_data;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public byte getDayOfWeek() {
		return dayOfWeek;
	}

	public String getTime() {
		return time;
	}

	public String getLaunchPoint() {
		return launchPoint;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("idData: ");
		str.append(id_data);
		str.append(" Year: ");
		str.append(year);
		str.append(" Month: ");
		str.append(month);
		str.append(" Day of week: ");
		str.append(dayOfWeek);
		str.append(" Time: ");
		str.append(time);
		str.append(" Launch point: ");
		str.append(launchPoint);
		str.append(" Destination point: ");
		str.append(destinationPoint);
		str.append(" Number of passengers: ");
		str.append(numOfPassengers);
		return str.toString();
	}

}
