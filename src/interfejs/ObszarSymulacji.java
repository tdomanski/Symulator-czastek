package interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.CzastkaStacjonarna;
import czastki.parametry.Czastki;
import matematyczna.Obliczenia;
import matematyczna.SymulacjaCzastki;

public class ObszarSymulacji extends JPanel implements MouseListener, MouseMotionListener{
	
	private List<CzastkaStacjonarna> czastkiStacjonarne = new ArrayList<CzastkaStacjonarna>();
	private List<CzastkaProbna> czastkiProbne = new ArrayList<CzastkaProbna>();
	private int draggableCzastkaIndex;
	private String draggedCzastka;
	private boolean isMouseOverCzastkaProbna, isMouseOverCzastkaStacjonarna;
	private double targetedCzastkaStacjonarnaLadunek, targetedCzastkaProbnaMasa, targetedCzastkaProbnaLadunek;
	private int czastkaStacjonarnaRadius, czastkaProbnaRadius;
	private Obliczenia obliczenia;
	private List<SymulacjaCzastki> symulacje = new ArrayList<SymulacjaCzastki>();
	private Czastki cz;
	private String aktualnyContent;//informacja, czy aktualnie wyœwietlane jest pole wektorowe, czy trajektorie cz¹stek
	private ExecutorService exec;
	private final int ARR_SIZE = 3;
	
	
	public ObszarSymulacji() {
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new EtchedBorder(Color.black,Color.black)));
		aktualnyContent = "Pole";//Domyslnie ustawiamy pokazywanie pola wektorowego
		draggedCzastka = null;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		cz = new Czastki();
		obliczenia = new Obliczenia();
	}
//	public void actionPerformed(ActionEvent e) {
//		
//	}
// Pomocniczo: do wykorzystania w pozniejszym czasie
//	private class Draw extends JComponent{
//
//        @Override
//        protected void paintComponent(Graphics g){
//            super.paintComponent(g);
//
//            Graphics2D graph2 = (Graphics2D) g;
//
//            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			// polecenia rysowania: graph2.setColor, graph2.draw ....
//        }
////    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			cs.paint(g, 2 * czastkaStacjonarnaRadius, 2 *czastkaStacjonarnaRadius);
		}
		
		for (CzastkaProbna cp : czastkiProbne) {
			cp.paint(g, 2 *czastkaProbnaRadius, 2 *czastkaProbnaRadius);
		}
		if (aktualnyContent == "Pole" && czastkiStacjonarne.isEmpty() == false)
			this.obliczINarysujPoleElektryczne(g);
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		draggedCzastka = null;
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		for (CzastkaProbna cp : czastkiProbne) {
			if (e.getX() - cp.getX() < czastkaProbnaRadius && e.getX() - cp.getX() > -czastkaProbnaRadius && 
				e.getY() - cp.getY() < czastkaProbnaRadius && e.getY() - cp.getY() > -czastkaProbnaRadius) {
					draggableCzastkaIndex = czastkiProbne.indexOf(cp);
					draggedCzastka = "probna";
					break;
			}
		}
		
		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			if (e.getX() - cs.getX() < czastkaStacjonarnaRadius/2 && e.getX() - cs.getX() > -czastkaStacjonarnaRadius/2 && 
				e.getY() - cs.getY() < czastkaStacjonarnaRadius/2 && e.getY() - cs.getY() > -czastkaStacjonarnaRadius/2) {
					draggableCzastkaIndex = czastkiStacjonarne.indexOf(cs);
					draggedCzastka = "stacjonarna";
					break;
			}
		}
		
		if (draggedCzastka == "probna") {
			if (e.getX() > 2 * this.czastkaProbnaRadius && e.getX() < this.getWidth() - 2 * this.czastkaProbnaRadius &&
				e.getY() > 2 * this.czastkaProbnaRadius && e.getY() < this.getHeight() - 2 * this.czastkaProbnaRadius) {
					czastkiProbne.get(draggableCzastkaIndex).setX(e.getX());
					czastkiProbne.get(draggableCzastkaIndex).setY(e.getY());
					repaint();
			}
		}
		
		else if (draggedCzastka == "stacjonarna") {
			if (e.getX() > 1.5 * this.czastkaStacjonarnaRadius && e.getX() < this.getWidth() - 1.5 * this.czastkaStacjonarnaRadius &&
				e.getY() > 1.5 * this.czastkaStacjonarnaRadius && e.getY() < this.getHeight() - 1.5 * this.czastkaStacjonarnaRadius) {
					czastkiStacjonarne.get(draggableCzastkaIndex).setX(e.getX());
					czastkiStacjonarne.get(draggableCzastkaIndex).setY(e.getY());
					repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { //Jesli uzytkownik przesunie kursor nad czastke, to strzalka zmieni sie na lapke
		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			if (e.getX() - cs.getX() < czastkaStacjonarnaRadius/2 && e.getX() - cs.getX() > -czastkaStacjonarnaRadius/2 && 
				e.getY() - cs.getY() < czastkaStacjonarnaRadius/2 && e.getY() - cs.getY() > -czastkaStacjonarnaRadius/2) {
					isMouseOverCzastkaStacjonarna = true;
					targetedCzastkaStacjonarnaLadunek = cs.getLadunek();
			}
		}
		
		for (CzastkaProbna cp : czastkiProbne) {
			if (e.getX() - cp.getX() < czastkaProbnaRadius && e.getX() - cp.getX() > -czastkaProbnaRadius && 
				e.getY() - cp.getY() < czastkaProbnaRadius && e.getY() - cp.getY() > -czastkaProbnaRadius) {
					isMouseOverCzastkaProbna = true;
					targetedCzastkaProbnaMasa = cp.getMasa();
					targetedCzastkaProbnaLadunek = cp.getLadunek();
			}
		}
		
		if (isMouseOverCzastkaStacjonarna == true || isMouseOverCzastkaProbna == true)
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		else
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		isMouseOverCzastkaStacjonarna = false;
		isMouseOverCzastkaProbna = false;
	}
	
	public void przelaczPoleTrajektorie() {
		if (aktualnyContent == "Pole") {
			aktualnyContent = "Trajektorie";
			//System.out.println("Teraz trajektorie");
			repaint();
		}
		
		else if (aktualnyContent == "Trajektorie") {
			aktualnyContent = "Pole";
			//System.out.println("Teraz pole");
			repaint();
		}
	}
	
	public void dodajCzastkeStacjonarna(CzastkaStacjonarna cs) {
		czastkiStacjonarne.add(cs);
		cz.addCzastkaStacjonarna(cs);
		repaint();
		
	}
	
	public void dodajCzastkeProbna(CzastkaProbna cp) {
		czastkiProbne.add(cp);
		cz.addCzastkaProbna(cp);
		symulacje.add(cp.getSym());
		symulacje.get(symulacje.size()-1).setCzastki(cz);
		symulacje.get(symulacje.size()-1).setdt(0.001);
		repaint();
	}
	
	public void uruchomExecutor()
	{
		exec = Executors.newFixedThreadPool(symulacje.size());
		for (SymulacjaCzastki sym : symulacje) {
			exec.execute(sym);
		}
	}
	
	public void wylaczExecutor()
	{
//		awaitTerminationAfterShutdown(exec)
		exec.shutdown();
		try {
			exec.awaitTermination(1, null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void repaintObszarSymulacji() 
	{
		repaint();
	}
	
//	public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
//	    threadPool.shutdown();
//	    try {
//	        if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
//	            threadPool.shutdownNow();
//	        }
//	    } catch (InterruptedException ex) {
//	        threadPool.shutdownNow();
//	        Thread.currentThread().interrupt();
//	    }
//	}

	public int getCzastkaStacjonarnaRadius() {
		return this.czastkaStacjonarnaRadius;
	}
	
	public int getCzastkaProbnaRadius() {
		return this.czastkaProbnaRadius;
	}
	
	public void setCzastkaStacjonarnaRadius(int r) {
		this.czastkaStacjonarnaRadius = r;
	}
	
	public void setCzastkaProbnaRadius(int r) {
		this.czastkaProbnaRadius = r;
	}
	
	public double obliczExWPunkcie(int x0, int y0) {
		double Ex = 0;
		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			Ex += obliczenia.czastkaEx(cs.getX(), cs.getY(), x0, y0, cs.getLadunek());
		}
		//System.out.println(Ex);
		return Ex;
	}
	
	public double obliczEyWPunkcie(int x0, int y0) {
		double Ey = 0;
		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			Ey += obliczenia.czastkaEy(cs.getX(), cs.getY(), x0, y0, cs.getLadunek());
		}
		//System.out.println(Ey);
		return Ey;
	}
	
	void drawArrow(Graphics g, int x, int y, double theta) {
        Graphics2D g2D = (Graphics2D) g.create();

        int dlugosc = 10;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.concatenate(AffineTransform.getRotateInstance(theta));
        g2D.transform(at);
        //Ponizej rysujemy domyslna strzalke, ktora zostanie przetransformowana
        g2D.drawLine(0, 0, dlugosc, 0);
        g2D.fillPolygon(new int[] {dlugosc, dlugosc-ARR_SIZE, dlugosc-ARR_SIZE, dlugosc}, new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
	
	public void obliczINarysujPoleElektryczne(Graphics g) {
		double Ex, Ey;
		for (int xi = 20; xi < this.getWidth() - 20; xi += 20) {
			for (int yi = 20; yi < this.getHeight() - 20; yi += 20) {
				Ex = this.obliczExWPunkcie(xi, yi);
				Ey = this.obliczEyWPunkcie(xi, yi);
				double theta = Math.atan2(Ey, Ex);
				this.drawArrow(g, xi, yi, theta);
			}
		}
	}
}
