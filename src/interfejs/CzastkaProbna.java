package interfejs;

import matematyczna.SymulacjaCzastki;

public class CzastkaProbna {
	
	
	private int x;
	private int y;
	private double masa;
	private double ladunek;
	private double vx; //prêdkoœæ x
	private double vy; // prêdkoœæ y
	private int id; // numer czastki
	private double Ex;
	private double Ey;
	private SymulacjaCzastki sym;
	
	public CzastkaProbna(int x, int y, double m, double q, double vx, double vy, int id) {
		this.x = x;
		this.y = y;
		this.masa = m;
		this.ladunek = q;
		this.vx = vx;
		this.vy = vy;
		sym = new SymulacjaCzastki(this);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getMasa() {
		return masa;
	}

	public void setMasa(double masa) {
		this.masa = masa;
	}

	public double getLadunek() {
		return ladunek;
	}

	public void setLadunek(double ladunek) {
		this.ladunek = ladunek;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getEx() {
		return Ex;
	}

	public void setEx(double ex) {
		Ex = ex;
	}

	public double getEy() {
		return Ey;
	}

	public void setEy(double ey) {
		Ey = ey;
	}

}
