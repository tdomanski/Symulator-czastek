package matematyczna;

import java.util.ArrayList;
import java.util.List;

import interfejs.CzastkaProbna;
import interfejs.Czastki;

public class SymulacjaCzastki {
	Obliczenia oblicz;
	int id=-1;
	
	double dVx=0;
	double dVy=0;
	
	public SymulacjaCzastki(CzastkaProbna cz) {
		id=cz.getId();
	}
	
	public void Symulacja(Czastki czastki, double dt)
	{
		double sumaEx=0;
		double sumaEy=0;
		for(int j=0;j<czastki.getIloscCzProbnych();j++)
		{
			if(j!=id)
			{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(j).getX() , czastki.getCzastkeProbna(id).getX(), czastki.getCzastkeProbna(id).getY());
				sumaEy+=oblicz.czastkaEy(czastki.getCzastkeProbna(j).getX(), czastki.getCzastkeProbna(j).getY(),czastki.getCzastkeProbna(j).getX() , czastki.getCzastkeProbna(id).getX(), czastki.getCzastkeProbna(id).getY());
			}
		}
		for(int i=0;i<czastki.getIloscCzStacjon();i++)
		{
				sumaEx+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(i).getX() ,czastki.getCzastkeStacjonarna(id).getX(), czastki.getCzastkeStacjonarna(id).getY());
				sumaEy+=oblicz.czastkaEx(czastki.getCzastkeStacjonarna(i).getX(), czastki.getCzastkeStacjonarna(i).getY(),czastki.getCzastkeStacjonarna(i).getX() ,czastki.getCzastkeStacjonarna(id).getX(), czastki.getCzastkeStacjonarna(id).getY());
		}
		double masa = czastki.getCzastkeProbna(id).getMasa();
		double q = czastki.getCzastkeProbna(id).getLadunek();
		dVx=q/masa*sumaEx*dt;
		dVy=q/masa*sumaEy*dt;
	}

	public double getdVx() {
		return dVx;
	}

	public double getdVy() {
		return dVy;
	}
}
