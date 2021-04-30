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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.CzastkaStacjonarna;

public class ObszarSymulacji extends JPanel implements MouseListener, MouseMotionListener {
	
	private List<CzastkaStacjonarna> czastkiStacjonarne = new ArrayList<CzastkaStacjonarna>();
	private List<CzastkaProbna> czastkiProbne = new ArrayList<CzastkaProbna>();
	private int draggableCzastkaIndex;
	private String draggedCzastka;

	private String aktualnyContent;//informacja, czy aktualnie wy�wietlane jest pole wektorowe, czy trajektorie cz�stek
	
	public ObszarSymulacji() 
	{
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new EtchedBorder(Color.black,Color.black)));
		aktualnyContent = "Pole";//Domy�lnie ustawiamy pokazywanie pola wektorowego
		draggedCzastka = null;
		this.setBorder(new LineBorder(Color.black, 2,true));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
			cs.paint(g, this.getHeight()/25, this.getHeight()/25);
		}
		
		for (CzastkaProbna cp : czastkiProbne) {
			cp.paint(g, this.getHeight()/45, this.getHeight()/45);
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
			if (e.getX() - cp.getX() < this.getHeight()/50 && e.getX() - cp.getX() > -this.getHeight()/50 && 
				e.getY() - cp.getY() < this.getHeight()/50 && e.getY() - cp.getY() > -this.getHeight()/50) {
					draggableCzastkaIndex = czastkiProbne.indexOf(cp);
					draggedCzastka = "probna";
					break;
			}
		}
		
		for (CzastkaStacjonarna cs : czastkiStacjonarne) {
			if (e.getX() - cs.getX() < this.getHeight()/50 && e.getX() - cs.getX() > -this.getHeight()/50 && 
				e.getY() - cs.getY() < this.getHeight()/50 && e.getY() - cs.getY() > -this.getHeight()/50) {
					draggableCzastkaIndex = czastkiStacjonarne.indexOf(cs);
					draggedCzastka = "stacjonarna";
					break;
			}
		}
		
		if (draggedCzastka == "probna") {
			czastkiProbne.get(draggableCzastkaIndex).setX(e.getX());
			czastkiProbne.get(draggableCzastkaIndex).setY(e.getY());
			repaint();
		}
		
		else if (draggedCzastka == "stacjonarna") {
			czastkiStacjonarne.get(draggableCzastkaIndex).setX(e.getX());
			czastkiStacjonarne.get(draggableCzastkaIndex).setY(e.getY());
			repaint();
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
		repaint();
	}
	
	public void dodajCzastkeProbna(CzastkaProbna cp) {
		czastkiProbne.add(cp);
		repaint();
	}
}
