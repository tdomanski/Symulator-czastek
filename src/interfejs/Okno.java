package interfejs;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Okno extends JFrame {
	MenuGorne menuGorne;
	ObszarSymulacji obszarSymulacji;
	MenuBoczne menuBoczne;
	public Okno()
	{
		this.setLayout(new BorderLayout());
		menuGorne = new MenuGorne();
		menuBoczne = new MenuBoczne();
		obszarSymulacji = new ObszarSymulacji(16,8); //(szerokosc,dlugosc)
		this.add(menuGorne,BorderLayout.PAGE_START);
		this.add(menuBoczne,BorderLayout.LINE_END);
		this.add(obszarSymulacji,BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(800,800);
		this.setTitle("Symulator cz¹stek w polu elektrycznym");
	}
	

}
