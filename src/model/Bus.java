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
}
