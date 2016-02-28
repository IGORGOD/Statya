package businesslogic;

import model.City;
import model.ConstantsCity;
import model.Route;

public class Ft {

	private City city1;
	private City city2;
	private Route route;
	private ConstantsCity constCity1;
	private ConstantsCity constCity2;
	
	public Ft(City city1, City city2, ConstantsCity constsCity) {

	}

	private double getF(double t) {
		return 0.0;
	}
	
	/**
	 * 
	 * @param T тип время прогноза
	 * @return
	 */
	private double calcG(double T) {
		double k12 = route.getK();
		double ro1 = constCity1.getDensity();
		double ro2 = constCity2.getDensity();
		double s1 = city1.getArea();
		double s2 = city2.getArea();
		double r12 = route.getR();
		double c12 = route.getC();
		
		return k12 * ro1 * ro2 * s1 * s2 * Math.pow(r12, 2.0) * Math.exp(-c12 * r12) * (1 + 1 / 3.745 * getF(T));
	}
}
