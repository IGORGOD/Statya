package model;

import java.util.Calendar;

import annotation.AutoincrementPK;
import annotation.Stored;
import converter.IntegerConverter;
import converter.StringConverter;

@Stored(name = "tbl_data")
public class Table {
	
	@AutoincrementPK(name = "id_data", converter = IntegerConverter.class)
	private int idTable;
	
	@Stored(name = "date", converter = StringConverter.class) //yyyy-mm-dd
	private String date;
	
	@Stored(name = "date_time", converter = StringConverter.class)
	private String time;
	
	@Stored(name = "id_route", converter = IntegerConverter.class)
	private int idRoute;
	
	@Stored(name = "numOfPassengers", converter = IntegerConverter.class)
	private int numOfPassengers;
	
	public Table(String date, String time, int idRoute, int numOfPassengers){
		// YEAR
		if (date != null && !date.isEmpty()){
			String [] dateNums = date.split("-");
			long millis = System.currentTimeMillis();
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(millis);
			int buf = Integer.valueOf(dateNums[0]);
			if (buf >= 2000 && buf <= c.get(1)){
				// MONTH
				byte buf2 = Byte.valueOf(dateNums[1]);
				if (buf >= 0 && buf2 <=11){
					// DAY OF MONTH
					byte dayOfWeek = Byte.valueOf(dateNums[2]);
					if (dayOfWeek >= 0 && dayOfWeek < 32){
						this.date = date;
					}
					else{
						throw new IllegalArgumentException("Day of week out of range [1..31]");
					}
				}
				else{
					throw new IllegalArgumentException("Month out of range [01 - 12]");
				}
			}
			else{
				throw new IllegalArgumentException("Year out of range [2000 - now]");
			}
		}
		else{
			throw new IllegalArgumentException("Year can't be null or empty");
		}
		// TIME
		this.time = time;
		// ID ROUTE
		this.idRoute = idRoute;
		// NUMBER OF PASSENGERS
		this.numOfPassengers = numOfPassengers;
	}
	
	public Table(){}
	
	public Table(int id, String date, String time, int idRoute, int numOfPassengers){
		this(date, time, idRoute, numOfPassengers);
		this.idTable = id;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}

	public int getIdTable() {
		return idTable;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getIdRoute() {
		return idRoute;
	}

	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		if (idTable != 0){
			str.append(" idTable:");
			str.append(idTable);
		}
		str.append(" Date: ");
		str.append(date);
		str.append(" Time: ");
		str.append(time);
		str.append(" idRoute: ");
		str.append(idRoute);
		str.append(" Number of passengers: ");
		str.append(numOfPassengers);
		return str.toString();
	}

}
