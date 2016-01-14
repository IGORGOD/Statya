package model;

import java.util.Calendar;

import annotation.Stored;
import converter.DoubleConverter;
import converter.IntegerConverter;
import converter.StringConverter;

@Stored(name = "tbl_consts_city")
public class ConstantsCity {

	@Stored(name = "id_consts_city", converter = IntegerConverter.class)
	private int idConstantsCity;
	
	@Stored(name = "date_year", converter = StringConverter.class)
	private String year;
	
	@Stored(name = "density", converter = DoubleConverter.class)
	private double density;
	
	public ConstantsCity(int id, String year, double density){
		idConstantsCity = id;
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
		// DENSITY
		if (density >= 0.0){
			this.density = density;
		}
		else{
			throw new IllegalArgumentException("Density can't be negative");
		}
	}

	public void setIdConstantsCity(int idConstantsCity) {
		this.idConstantsCity = idConstantsCity;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public int getIdConstantsCity() {
		return idConstantsCity;
	}

	public String getYear() {
		return year;
	}

	public double getDensity() {
		return density;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("idConstantsCity: ");
		str.append(idConstantsCity);
		str.append(" Year: ");
		str.append(year);
		str.append(" Density: ");
		str.append(density);
		return str.toString();
	}
}
