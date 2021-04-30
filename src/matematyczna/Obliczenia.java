package matematyczna;

import java.util.ArrayList;
import java.util.List;

import czastki.parametry.CzastkaStacjonarna;
import czastki.parametry.Czastki;

public class Obliczenia {
	private static double PI = 3.14159265;
	private static double epsilon0 = 8.85417*Math.pow(10,-12); //przenikalnosc elektryczna prozni 
//	public Obliczenia() {
//		
//	}
	// Obliczanie natezania w danym punkcie (x0,y0) w punkcie obserwacji (x,y)
	// (x0,y0) - po³o¿enie cz¹stki (x,y) - punkt obserwacji, q - ³adunek
	public double czastkaE(int x0,int y0,int x,int y,double q)
	{
		double ret = q/(4*PI*epsilon0);
		ret = ret/(Math.pow(x-x0,2)+Math.pow(y-y0,2));
		return ret;
	}
	//Obliczanie natezenia Ex i Ey
	public double czastkaEx(int x0,int y0,int x,int y,double q)
	{
		double ret = q/(4*PI*epsilon0);
		ret = ret/(Math.pow(Math.pow(x-x0,2)+Math.pow(y-y0,2),1.5));
		ret=ret*(x-x0);
		return ret;
	}	
	public double czastkaEy(int x0,int y0,int x,int y,double q)
	{
		double ret = q/(4*PI*epsilon0);
		ret = ret/(Math.pow(Math.pow(x-x0,2)+Math.pow(y-y0,2),1.5));
		ret=ret*(y-x0);
		return ret;
	}	
}
