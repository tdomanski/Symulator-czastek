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
		obszarSymulacji = new ObszarSymulacji();
		this.add(menuGorne,BorderLayout.PAGE_START);
		this.add(menuBoczne,BorderLayout.LINE_END);
		this.add(obszarSymulacji,BorderLayout.LINE_START);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600,600);
		this.setTitle("Program");
	}
	

}
