package interfejs;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObszarSymulacji extends JPanel {
	private int dlugoscX;
	private int szerokoscY;
	private int sizeX, sizeY;
	private JPanel[][] kwadratySymulacji;
	public ObszarSymulacji(int dlugoscX, int szerokoscY) 
	{
		this.dlugoscX=dlugoscX;
		this.szerokoscY=szerokoscY;
		System.out.println(this.getSize());
		this.setLayout(new GridLayout(szerokoscY,dlugoscX));
		kwadratySymulacji = new JPanel[szerokoscY][dlugoscX];
		for(int i=0;i<szerokoscY;i++) // i - wiersze, j - kolumny
		{
			for(int j=0;j<dlugoscX;j++)
			{
				kwadratySymulacji[i][j]= new JPanel();
				kwadratySymulacji[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				kwadratySymulacji[i][j].setBackground(new Color(50,50,50));
				JLabel label = new JLabel(i+","+j);
				label.setForeground(Color.WHITE);
				kwadratySymulacji[i][j].add(label);
				this.add(kwadratySymulacji[i][j]);
			}
		}
		
	}
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
//    }
}
