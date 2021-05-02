package czastki.parametry;

import java.util.ArrayList;
import java.util.List;

public class Czastki {
	private List<CzastkaProbna> czastkiProbne;
	private List<CzastkaStacjonarna> czastkiStacjonarne;
	private int iloscCzProbnych=0;
	private int iloscCzStacjon=0;
	public Czastki() {
		czastkiProbne = new ArrayList<CzastkaProbna>();
		czastkiStacjonarne = new ArrayList<CzastkaStacjonarna>();
	}
	
	public void addCzastkaProbna(CzastkaProbna cz)
	{
		czastkiProbne.add(cz);
		iloscCzProbnych++;
	}
	public void addCzastkaStacjonarna(CzastkaStacjonarna cz)
	{
		czastkiStacjonarne.add(cz);
		iloscCzStacjon++;
	}
	public void clearCzastkiProbne()
	{
		czastkiProbne.clear();
		iloscCzProbnych=0;
	}
	public void clearCzastkiStacjonarne()
	{
		czastkiStacjonarne.clear();
		iloscCzStacjon=0;
	}
	public List<CzastkaProbna> getCzastkiProbne() {
		return czastkiProbne;
	}
	public CzastkaProbna getCzastkeProbna(int i) {
		return czastkiProbne.get(i);
	}
	public List<CzastkaStacjonarna> getCzastkiStacjonarne() {
		return czastkiStacjonarne;
	}
	public CzastkaStacjonarna getCzastkeStacjonarna(int i) {
		return czastkiStacjonarne.get(i);
	}

	public int getIloscCzProbnych() {
		return iloscCzProbnych;
	}

	public int getIloscCzStacjon() {
		return iloscCzStacjon;
	}
}
