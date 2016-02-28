package model;

import annotation.AutoincrementPK;
import annotation.Stored;
import converter.IntegerConverter;
import converter.StringConverter;

@Stored(name = "tbl_buspark")
public class BusPark {
	
	@AutoincrementPK(name = "id_buspark", converter = IntegerConverter.class)
	private int idBusPark;

	@Stored(name = "name", converter = StringConverter.class)
	private String name;
	
	@Stored(name = "port", converter = StringConverter.class)
	private String port;
	
	public BusPark(){}
	
	public BusPark(String name, String port){
		// NAME
		if (name != null && !name.isEmpty()){
			this.name = name;
		}
		else{
			throw new IllegalArgumentException("Name can't be null or empty");
		}
		// PORT
		if (port != null && !port.isEmpty()){
			this.name = port;
		}
		else{
			throw new IllegalArgumentException("Port can't be null or empty");
		}
	}
	
	public BusPark(int id, String name, String port){
		this(name, port);
		this.idBusPark = id;
	}

	public void setIdBusPark(int idBusPark) {
		this.idBusPark = idBusPark;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getIdBusPark() {
		return idBusPark;
	}

	public String getName() {
		return name;
	}

	public String getPort() {
		return port;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		if (idBusPark != 0){
			str.append(" idBusPark:");
			str.append(idBusPark);
		}
		str.append(" Name: ");
		str.append(name);
		str.append(" Port: ");
		str.append(port);
		return str.toString();
	}
}
