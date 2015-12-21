package model;

import annotation.Stored;
import converter.*;

@Stored(name = "BUS")
public class Bus {
	
	@Stored(name = "idBus", converter = IntegerConverter.class)
	private int idBus;
	
	@Stored(name = "NAME", converter = StringConverter.class)
	private String name;
	
	@Stored(name = "Places", converter = IntegerConverter.class)
	private int places;
	
	@Stored(name = "Price", converter = DoubleConverter.class)
	private double price;
	
	public Bus(int idBus, String name, int places, double price){
		this.idBus = idBus;
		this.name = name;
		this.places = places;
		this.price = price;
	}
	
	public int getIdBus(){
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
		sb.append("idBus:");
		sb.append(idBus);
		sb.append(" name:");
		sb.append(name);
		sb.append(" places:");
		sb.append(places);
		sb.append(" price:");
		sb.append(price);
		return sb.toString();
	}
}
