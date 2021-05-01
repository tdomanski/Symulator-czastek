package matematyczna;

import java.util.ArrayList;
import java.util.List;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.Czastki;

public class SymulacjaCzastki implements Runnable {
	private Obliczenia oblicz;
	private CzastkaProbna cz;
	private Czastki czastki;
	private int id=-1;
	private double dt=-1;
	private double dx=0;
	private double dy=0;
	private double dvx = 0;
	private double dvy = 0;
	private boolean simGoing=true;
	public SymulacjaCzastki(CzastkaProbna cz) {
		id=cz.getId();
		this.cz=cz;
		oblicz = new Obliczenia();
	}
	
	public void Symulacja()
	{
		double sumaEx=0;
		double sumaEy=0;
		for(int j=0;j<czastki.getIloscCzProbnych()-1;j++)
		{
			if(j!=id)
			{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(id).getX() , czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeProbna(j).getLadunek());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(id).getX() , czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeProbna(j).getLadunek());
			}
		}
		for(int i=0;i<czastki.getIloscCzStacjon()-1;i++)
		{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(id).getX() ,czastki.getCzastkeStacjonarna(id).getY(), czastki.getCzastkeStacjonarna(i).getLadunek());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(id).getX() ,czastki.getCzastkeStacjonarna(id).getY(), czastki.getCzastkeStacjonarna(i).getLadunek());
		}
		double masa = cz.getMasa();
		double q = cz.getLadunek();
		dvx = q/masa*sumaEx*dt;
		dvy = q/masa*sumaEy*dt;
		cz.setVx(cz.getVx() + dvx);
		cz.setVy(cz.getVy() + dvy);
		dx = cz.getVx()*dt;
		dy = cz.getVy()*dt;
	}


	public int nowyX()
	{
		return (int)dx+cz.getX();
	}
	public int nowyY()
	{
		return (int)dy+cz.getY();
	}
	public void setCzastki(Czastki czastki)
	{
		this.czastki=czastki;
	}
	public void setdt(double dt)
	{
		this.dt=dt;
	}
	public void ustawParametry(Czastki czastki,double dt)
	{
		this.czastki=czastki;
		this.dt=dt;
	}
	
	@Override
	public void run() {
		while(true)
		{
			Symulacja();
			cz.setX(nowyX());
			cz.setY(nowyY());
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
