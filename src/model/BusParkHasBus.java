package model;

import annotation.Stored;
import converter.IntegerConverter;

@Stored(name = "tbl_buspark_has_tbl_bus")
public class BusParkHasBus {

	@Stored(name = "tbl_buspark_id_buspark", converter = IntegerConverter.class)
	private int idBusPark;
	
	@Stored(name = "tbl_bus_id_bus", converter = IntegerConverter.class)
	private int idBus;
	
	@Stored(name = "number", converter = IntegerConverter.class)
	private int number;
	
	public BusParkHasBus(){}
	
	public BusParkHasBus(int idBusPark, int idBus, int qty){
		this.idBusPark = idBusPark;
		this.idBus = idBus;
		this.number = qty;
	}

	public void setIdBusPark(int idBusPark) {
		this.idBusPark = idBusPark;
	}

	public void setIdBus(int idBus) {
		this.idBus = idBus;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getIdBusPark() {
		return idBusPark;
	}

	public int getIdBus() {
		return idBus;
	}

	public int getNumber() {
		return number;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(" ID bus park: ");
		str.append(idBusPark);
		str.append(" ID bus: ");
		str.append(idBus);
		str.append(" Number: ");
		str.append(number);
		return str.toString();
	}
	
}
