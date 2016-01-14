package model;

import annotation.Stored;
import converter.DoubleConverter;
import converter.IntegerConverter;

@Stored(name = "tbl_route_consts")
public class RouteConstants {
	
	@Stored(name = "id_route_consts", converter = IntegerConverter.class)
	private int idRouteConstants;
	
	@Stored(name = "r", converter = DoubleConverter.class)
	private double r;
	
	@Stored(name = "c", converter = DoubleConverter.class)
	private double c;
	
	@Stored(name = "k", converter = DoubleConverter.class)
	private double k;
	
	public RouteConstants(int id, double r, double c, double k){
		idRouteConstants = id;
		this.r = r;
		this.c = c;
		this.k = k;
	}

	public void setIdRouteConstants(int idRoute) {
		this.idRouteConstants = idRoute;
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

	public int getIdRouteConstants() {
		return idRouteConstants;
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
		str.append("id: ");
		str.append(idRouteConstants);
		str.append(" r: ");
		str.append(r);
		str.append(" c: ");
		str.append(c);
		str.append(" k: ");
		str.append(k);
		return str.toString();
	}

}
