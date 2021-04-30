package czastki.parametry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CzastkaStacjonarna {
	
	private int x;
	private int y;
	private double ladunek;
	public final BufferedImage plusChargeImage = loadBufferedImage("/pluscharge.png");
	public final BufferedImage minusChargeImage = loadBufferedImage("/minuscharge.png");
	
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
	}
}
