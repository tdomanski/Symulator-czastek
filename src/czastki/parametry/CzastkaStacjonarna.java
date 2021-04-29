package czastki.parametry;

public class CzastkaStacjonarna {
	
	private int x;
	private int y;
	private double ladunek;
	
	public CzastkaStacjonarna(int x, int y, double q) {
		this.x = x;
		this.y = y;
		this.ladunek = q;
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

	public double getLadunek() {
		return ladunek;
	}

	public void setLadunek(double ladunek) {
		this.ladunek = ladunek;
	}
}
