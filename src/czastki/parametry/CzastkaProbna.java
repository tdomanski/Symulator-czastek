package czastki.parametry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
	private List<Integer> listX;
	private List<Integer> listY;
	private SymulacjaCzastki sym;
	private boolean trajektorieOn = false;
	private boolean symulacjaOn = false;
	public final BufferedImage plusChargeImage = loadBufferedImage("/pluscharge.png");
	public final BufferedImage minusChargeImage = loadBufferedImage("/minuscharge.png");
	public final BufferedImage zeroChargeImage = loadBufferedImage("/zerocharge.png");
	
	public CzastkaProbna(int x, int y, double m, double q, double vx, double vy, int id) {
		this.x = x;
		this.y = y;
		this.masa = m;
		this.ladunek = q;
		this.vx = vx;
		this.vy = vy;
		this.id=id;
		listX=new ArrayList<Integer>();
		listX.add(x);
		listY=new ArrayList<Integer>();
		listY.add(y);
		sym = new SymulacjaCzastki(this);
	}
	
	public SymulacjaCzastki getSym() {
		return sym;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(symulacjaOn)
		{
			listX.add(x);
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(symulacjaOn)
		{
			listY.add(y);
		}
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
	
	public void setTrajektorieOn(boolean trajektorieOn) {
		this.trajektorieOn = trajektorieOn;
	}
	
	public void setSymulacjaOn(boolean symulacjaOn) {
		this.symulacjaOn = symulacjaOn;
		if(symulacjaOn)
		{
			listX.clear();
			listY.clear();
		}
	}

	private BufferedImage loadBufferedImage(String string)
	{
	    try
	    {
	        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
	        return bi;
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void paint(Graphics g,int width, int height) {
		if (this.ladunek > 0) {
			g.drawImage(plusChargeImage, x - width/2, y - height/2, width, height, null);
		}
		else if (this.ladunek < 0)
			g.drawImage(minusChargeImage, x - width/2, y - height/2, width, height, null);
		else if (this.ladunek == 0)
			g.drawImage(zeroChargeImage, x - width/2, y - height/2, width, height, null);
		
		if(symulacjaOn==true&&trajektorieOn==true)
		{
			if(listX.size()>1)
			{
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(2));
				g2.setColor(Color.black);
				for(int i=0;i<listX.size()-1;i++)
				{
					g.drawLine(listX.get(i),listY.get(i),listX.get(i+1),listY.get(i+1));
				}
			}
		}
	}

}
