package businesslogic;

public class Ft {

	public Ft(String g) {

	}

	public double getF(double t) {
		return 0.0;
	}

	private double calcG(double T) {
		double k12 = 0.0, ro1 = 0.0, ro2 = 0.0, s1 = 0.0, s2 = 0.0, r12 = 0.0, c12 = 0.0;
		Ft f3t = null;
		return k12 * ro1 * ro2 * s1 * s2 * Math.pow(r12, 2.0) * Math.exp(-c12 * r12) * (1 + 1 / 3.745 * f3t.getF(T));
	}
}
