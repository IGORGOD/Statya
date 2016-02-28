package model;

import annotation.AutoincrementPK;
import annotation.Stored;
import converter.DoubleConverter;
import converter.IntegerConverter;

@Stored(name = "tbl_route")
public class Route {
	
	@AutoincrementPK(name = "id_route", converter = IntegerConverter.class)
	private int idRoute;
	
	@Stored(name = "id_city1", converter = IntegerConverter.class)
	private int idCity1;
	
	@Stored(name = "id_city2", converter = IntegerConverter.class)
	private int idCity2;
	
	@Stored(name = "r", converter = DoubleConverter.class)
	private double r;
	
	@Stored(name = "c", converter = DoubleConverter.class)
	private double c;
	
	@Stored(name = "k", converter = DoubleConverter.class)
	private double k;
	
	public Route(){}
	
	public Route(int idCity1, int idCity2, double r, double c, double k){
		this.idCity1 = idCity1;
		this.idCity2 = idCity2;
		this.r = r;
		this.c = c;
		this.k = k;
	}
	
	public Route(int id, int idCity1, int idCity2, double r, double c, double k){
		this(idCity1, idCity2, r, c, k);
		this.idRoute = id;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public void setIdCity1(int idCity1) {
		this.idCity1 = idCity1;
	}

	public void setIdCity2(int idCity2) {
		this.idCity2 = idCity2;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setC(double c) {
		this.c = c;
	}

	public void setK(double k) {
		this.k = k;
	}

	public int getIdRoute() {
		return idRoute;
	}

	public int getIdCity1() {
		return idCity1;
	}

	public int getIdCity2() {
		return idCity2;
	}

	public double getR() {
		return r;
	}

	public double getC() {
		return c;
	}

	public double getK() {
		return k;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		if (idRoute != 0){
			str.append(" idRoute:");
			str.append(idRoute);
		}
		str.append(" idCity1: ");
		str.append(idCity1);
		str.append(" idCity2: ");
		str.append(idCity2);
		str.append(" r: ");
		str.append(r);
		str.append(" c: ");
		str.append(c);
		str.append(" k: ");
		str.append(k);
		return str.toString();
	}

}
