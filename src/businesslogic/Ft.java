package businesslogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.City;
import model.ConstantsCity;
import model.Route;
import model.Table;

public class Ft {

	private City city1;
	private City city2;
	private Route route;
	private ConstantsCity constCity1;
	private ConstantsCity constCity2;
	private double a0 = 0;
	private double[] a;
	private double[] b;
	private int n = 0;
	private Map<Double, Integer> F;
	private Table first;
	private double step;

	private static final double E = 0.5;

	public Ft(City city1, City city2, String date, int numOfYears) {
		this.city1 = city1;
		this.city2 = city2;
		F = new HashMap<Double, Integer>();
		init(date, numOfYears);
	}

	private void init(String date, int numOfYears) {
		int[] buf = new int[1000];// nu tip 1000 tam ne budet, nu eto tebe
									// rabotat'
		// mnogo sql'a AHAHAHA
		// pervije dannie pihaesh v Table first
		// ostal'nie pihaesh massiv (tol'ko kol=vo passazhirov)
		step = 2 * Math.PI / buf.length;
		for (int i = 0; i < buf.length; i++)
			F.put(i * step - Math.PI, buf[i]);
	}

	private double getF(double t) {
		calcConsts();
		do {
			n++;
			a = new double[n];
			b = new double[n];
			calcConsts();
		} while (!check());
		return 0.0;
	}

	private void calcConsts() {
		a0 = integrate();
		for (int i = 0; i < n; i++) {
			a[i] = integrate(false, n);
			b[i] = integrate(true, n);
		}
	}

	private double integrate() {
		double buf = 0.0;
		double a = -Math.PI;
		while (a < 2 * Math.PI) {
			buf += step * (F.get(new Double(a)) + F.get(new Double(a + step)));
			a += step;
		}
		return buf / Math.PI;
	}

	private double integrate(boolean b, int n) {
		double buf = 0.0;
		double a = -Math.PI;
		double c = a + step;
		while (a < 2 * Math.PI) {
			buf += step * (F.get(new Double(a) * add(n * a, b)) + F.get(new Double(c) * add(n * c, b)));
			a = c;
			c += step;
		}
		return buf / Math.PI;
	}

	private Double add(double a, boolean b) {
		if (b)
			return Math.sin(a);
		else
			return Math.cos(a);
	}

	private boolean check() {
		Set<Entry<Double, Integer>> s = F.entrySet();
		for (Entry<Double, Integer> entry : s) {
			double d = getFurje(entry.getValue().intValue());
			double key = entry.getKey().doubleValue();
			if (!(d >= key - E && d <= key + E))
				return false;
		}
		return true;
	}

	private double getFurje(int t) {
		double buf = 0.0;
		buf += a0 / 2.0;
		for (int i = 0; i < n; i++)
			buf += a[i] * Math.cos(i * t) + b[i] * Math.sin(i * t);
		return buf;
	}

	/**
	 * 
	 * @param T
	 *            тип время прогноза
	 * 
	 * @return
	 */
	public double calcG(double T) {
		double k12 = route.getK();
		double ro1 = constCity1.getDensity();
		double ro2 = constCity2.getDensity();
		double s1 = city1.getArea();
		double s2 = city2.getArea();
		double r12 = route.getR();
		double c12 = route.getC();
		return k12 * ro1 * ro2 * s1 * s2 * Math.pow(r12, 2.0) * Math.exp(-c12 * r12) * (1.0 + 1.0 / 3.745 * getF(T));
	}
}
