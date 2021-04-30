package interfejs;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Okno extends JFrame {
	private MenuGorne menuGorne;
	private ObszarSymulacji obszarSymulacji;
	private MenuBoczne menuBoczne;
	private BorderLayout layout;
	public Okno()
	{
		layout = new BorderLayout();
		layout.setHgap(4);
		layout.setVgap(4);
		this.setLayout(layout);
		menuGorne = new MenuGorne();
		menuBoczne = new MenuBoczne();
		obszarSymulacji = new ObszarSymulacji(); //(dlugosc,szerokosc)
		menuBoczne.ustawObszarSymulacji(obszarSymulacji);
		menuGorne.ustawObszarSymulacji(obszarSymulacji);
		this.add(menuGorne,BorderLayout.PAGE_START);
		this.add(menuBoczne,BorderLayout.LINE_END);
		this.add(obszarSymulacji,BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setSize(1000, 1000);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		//this.setResizable(false);
		this.setTitle("Symulator cz¹stek w polu elektrycznym");
	}
	
	public static void main(String[] args) {
		Okno okno = new Okno();
	}
}