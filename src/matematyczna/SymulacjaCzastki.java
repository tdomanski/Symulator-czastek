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
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(j).getX() , czastki.getCzastkeProbna(id).getX(), czastki.getCzastkeProbna(j).getLadunek());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(j).getX() , czastki.getCzastkeProbna(id).getX(), czastki.getCzastkeProbna(j).getLadunek());
			}
		}
		double tempEx=sumaEx;
		double tempEy=sumaEy;
		System.out.println("Probna:"+id+" "+sumaEx+" "+sumaEy);
		for(int i=0;i<czastki.getIloscCzStacjon()-1;i++)
		{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(i).getX() ,czastki.getCzastkeStacjonarna(id).getX(), czastki.getCzastkeStacjonarna(i).getLadunek());
				sumaEy+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(i).getX() ,czastki.getCzastkeStacjonarna(id).getX(), czastki.getCzastkeStacjonarna(i).getLadunek());
		}
		System.out.println("Stacjonarna:"+(sumaEx-tempEx)+" "+(sumaEy-tempEy));
		double masa = czastki.getCzastkeProbna(id).getMasa();
		double q = czastki.getCzastkeProbna(id).getLadunek();
		dx=q/masa*sumaEx;
		dx=dx/dt;
		dy=q/masa*sumaEy;
		dy=dy/dt;
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
			
//			System.out.println("Cz+"+cz.getId()+"(x,y)=("+cz.getX()+","+cz.getY()+")");
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
