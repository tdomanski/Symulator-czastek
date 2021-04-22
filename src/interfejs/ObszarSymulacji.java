package interfejs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObszarSymulacji extends JPanel implements MouseListener{
	private int dlugoscX;
	private int szerokoscY;
	private int sizeX, sizeY;
	private JPanel[][] kwadratySymulacji;
	
	List<CzastkaStacjonarna> czastkiStacjonarne = new ArrayList<CzastkaStacjonarna>();
	List<CzastkaProbna> czastkiProbne = new ArrayList<CzastkaProbna>();

	String aktualnyContent;//informacja, czy aktualnie wyœwietlane jest pole wektorowe, czy trajektorie cz¹stek
	
	public ObszarSymulacji(int dlugoscX, int szerokoscY) 
	{
		aktualnyContent = "Pole";//Domyœlnie ustawiamy pokazywanie pola wektorowego
		this.dlugoscX=dlugoscX;
		this.szerokoscY=szerokoscY;
		this.setLayout(new GridLayout(szerokoscY,dlugoscX));
		kwadratySymulacji = new JPanel[dlugoscX][szerokoscY];
		for(int i=0;i<szerokoscY;i++) // i - wiersze, j - kolumny
		{
			for(int j=0;j<dlugoscX;j++)
			{
				kwadratySymulacji[i][j]= new JPanel();
				kwadratySymulacji[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				kwadratySymulacji[i][j].setBackground(new Color(50,50,50));
				JLabel label= new JLabel(); //= new JLabel(i+","+j);
				//label.setForeground(Color.WHITE);
				int x=i;
				int y=j;
				kwadratySymulacji[x][y].addMouseListener(new MouseAdapter() {
			        public void mouseClicked(MouseEvent e) {
			        	kwadratySymulacji[x][y].setBackground(Color.BLUE);
			        	label.setForeground(Color.BLACK);
			        }
			    });
				kwadratySymulacji[i][j].add(label);
				this.add(kwadratySymulacji[i][j]);
			}
		}		
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	}
	
	public void dodajCzastkeProbna(CzastkaProbna cp) {
		czastkiProbne.add(cp);
	}
}
