package model;

import annotation.Stored;
import converter.IntegerConverter;
import converter.StringConverter;

@Stored(name = "tbl_city")
public class City {
	
	@Stored(name = "id_city", converter = IntegerConverter.class)
	private int idCity;
	
	@Stored(name = "city_name", converter = StringConverter.class)
	private String name;
	
	@Stored(name = "city_area", converter = IntegerConverter.class)
	private int area;

	public City(int id, String name, int area){
		idCity = id;
		// NAME
		if (name != null && !name.isEmpty()){
			this.name = name;
		}
		else{
			throw new IllegalArgumentException("Name can't be null or empty");
		}
		// AREA
		if (area >= 0){
			this.area = area;
		}
		else{
			throw new IllegalArgumentException("Area can't be negative");
		}
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getIdCity() {
		return idCity;
	}

	public String getName() {
		return name;
	}

	public int getArea() {
		return area;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("idCity: ");
		str.append(idCity);
		str.append(" Name: ");
		str.append(name);
		str.append(" Area: ");
		str.append(area);
		return str.toString();
	}
	
}
