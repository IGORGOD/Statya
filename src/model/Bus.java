package model;

import annotation.AutoincrementPK;
import annotation.Stored;
import converter.*;

@Stored(name = "tbl_bus")
public class Bus {
	
	@AutoincrementPK(name = "id_bus", converter = IntegerConverter.class)
	private int idBus;
	
	@Stored(name = "NAME", converter = StringConverter.class)
	private String name;
	
	@Stored(name = "Places", converter = IntegerConverter.class)
	private int places;
	
	@Stored(name = "Price", converter = DoubleConverter.class)
	private double price;
	
	public Bus(){}
	
	public Bus(String name, int places, double price){
		this.name = name;
		this.places = places;
		this.price = price;
	}
	
	public Bus(int idBus, String name, int places, double price){
		this(name, places, price);
		this.idBus = idBus;
	}
	
	public void setIdBus(int idBus) {
		this.idBus = idBus;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdBus() {
		return idBus;
	}

	public String getName(){
		return name;
	}
	
	public int getPlaces(){
		return places;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if (idBus != 0){
			sb.append(" idBus:");
			sb.append(idBus);
		}
		sb.append(" name:");
		sb.append(name);
		sb.append(" places:");
		sb.append(places);
		sb.append(" price:");
		sb.append(price);
		return sb.toString();
	}
}
