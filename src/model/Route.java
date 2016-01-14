package model;

import annotation.Stored;
import converter.IntegerConverter;

@Stored(name = "tbl_route")
public class Route {
	
	@Stored(name = "id_route", converter = IntegerConverter.class)
	private int idRoute;
	
	@Stored(name = "id_city1", converter = IntegerConverter.class)
	private int idCity1;
	
	@Stored(name = "id_city2", converter = IntegerConverter.class)
	private int idCity2;
	
	@Stored(name = "id_route_consts", converter = IntegerConverter.class)
	private int idRouteConstants;
	
	public Route(int id, int idCity1, int idCity2, int idRouteConstants){
		idRoute = id;
		this.idCity1 = idCity1;
		this.idCity2 = idCity2;
		this.idRouteConstants = idRouteConstants;
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

	public void setIdRouteConstants(int idRouteConstants) {
		this.idRouteConstants = idRouteConstants;
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

	public int getIdRouteConstants() {
		return idRouteConstants;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("idRoute: ");
		str.append(idRoute);
		str.append(" idCity1: ");
		str.append(idCity1);
		str.append(" idCity2: ");
		str.append(idCity2);
		str.append(" idRouteConstants: ");
		str.append(idRouteConstants);
		return str.toString();
	}

}
