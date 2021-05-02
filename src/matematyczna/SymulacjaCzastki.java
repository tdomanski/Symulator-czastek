package matematyczna;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.Czastki;
import interfejs.ObszarSymulacji;

public class SymulacjaCzastki implements Runnable {
	private ObszarSymulacji obszarSymulacji;
	private Obliczenia oblicz;
	private CzastkaProbna cz;
	private Czastki czastki;
	private int id=-1;
	private double dt=-1;
	private double dx=0;
	private double dy=0;
	private double dvx = 0;
	private double dvy = 0;
	private boolean onSymulacja = false;
	public SymulacjaCzastki(CzastkaProbna cz) {
		id=cz.getId();
		this.cz=cz;
		oblicz = new Obliczenia();
	}
	
	public void Symulacja()
	{
		double sumaEx=0;
		double sumaEy=0;
		/*
		for(int j=0;j<czastki.getIloscCzProbnych()-1;j++)
		{
			if(j!=id)
			{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(id).getX() , czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeProbna(j).getLadunek());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(id).getX() , czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeProbna(j).getLadunek());
			}
		}
		*/
		for(int i=0;i<czastki.getIloscCzStacjon();i++)
		{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeProbna(id).getX() ,czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeStacjonarna(i).getLadunek());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeProbna(id).getX() ,czastki.getCzastkeProbna(id).getY(), czastki.getCzastkeStacjonarna(i).getLadunek());
		}
		System.out.println("sumaEx: " + sumaEx);
		System.out.println("iloscCzStacjonarnych: " + czastki.getIloscCzStacjon());
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
	
	
	
	public void setOnSymulacja(boolean onSymulacja) {
		this.onSymulacja = onSymulacja;
	}

	@Override
	public void run() {
		while(onSymulacja)
		{
			Symulacja();
			cz.setX(nowyX());
			cz.setY(nowyY());
			obszarSymulacji.repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ustawObszarSymulacji(ObszarSymulacji os) { // przypisanie obszaru symulacji, za ktory odpowiedzialne jest to menu
		this.obszarSymulacji = os;
	}
	
}
