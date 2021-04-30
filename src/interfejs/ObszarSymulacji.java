package interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.CzastkaStacjonarna;
import czastki.parametry.Czastki;
import matematyczna.SymulacjaCzastki;

public class ObszarSymulacji extends JPanel implements MouseListener, MouseMotionListener{
	
	private List<CzastkaStacjonarna> czastkiStacjonarne = new ArrayList<CzastkaStacjonarna>();
	private List<CzastkaProbna> czastkiProbne = new ArrayList<CzastkaProbna>();
	private int draggableCzastkaIndex;
	private String draggedCzastka;

	private List<SymulacjaCzastki> symulacje = new ArrayList<SymulacjaCzastki>();
	private Czastki cz;
	private String aktualnyContent;//informacja, czy aktualnie wyœwietlane jest pole wektorowe, czy trajektorie cz¹stek
	private ExecutorService exec;
	private int czastkaStacjonarnaRadius, czastkaProbnaRadius;
	
	public ObszarSymulacji() {
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new EtchedBorder(Color.black,Color.black)));
		aktualnyContent = "Pole";//Domyslnie ustawiamy pokazywanie pola wektorowego
		draggedCzastka = null;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		cz = new Czastki();
		
		//exec = Executors.newCachedThreadPool(); //Pozwala dodawac nowe procesy
//		awaitTerminationAfterShutdown(exec); //Pozwala na dokonczenie wszystkich threadow do konca
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
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		for (CzastkaProbna cp : czastkiProbne) {
			if (e.getX() - cp.getX() < czastkaProbnaRadius/2 && e.getX() - cp.getX() > -czastkaProbnaRadius/2 && 
				e.getY() - cp.getY() < czastkaProbnaRadius/2 && e.getY() - cp.getY() > -czastkaProbnaRadius/2) {
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
			if (e.getX() > 2 * this.czastkaStacjonarnaRadius && e.getX() < this.getWidth() - 2 * this.czastkaStacjonarnaRadius &&
				e.getY() > 2 * this.czastkaStacjonarnaRadius && e.getY() < this.getHeight() - 2 * this.czastkaStacjonarnaRadius) {
					czastkiStacjonarne.get(draggableCzastkaIndex).setX(e.getX());
					czastkiStacjonarne.get(draggableCzastkaIndex).setY(e.getY());
					repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void przelaczPoleTrajektorie() {
		if (aktualnyContent == "Pole") {
			aktualnyContent = "Trajektorie";
			//System.out.println("Teraz trajektorie");
		}
		
		else if (aktualnyContent == "Trajektorie") {
			aktualnyContent = "Pole";
			//System.out.println("Teraz pole");
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
}
