package interfejs;

public class CzastkaProbna {
	
	
	int x;
	int y;
	double masa;
	double ladunek;
	double vx; //prêdkoœæ x
	double vy; // prêdkoœæ y
	int id; // numer czastki
	double Ex;
	double Ey;

	
	public CzastkaProbna(int x, int y, double m, double q, double vx, double vy, int id) {
		this.x = x;
		this.y = y;
		this.masa = m;
		this.ladunek = q;
		this.vx = vx;
		this.vy = vy;
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
