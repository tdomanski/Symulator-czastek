package interfejs;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
		this.setVisible(true);
		this.setSize(800,800);
		//this.setResizable(false);
		this.setTitle("Symulator cz¹stek w polu elektrycznym");
	}
	
	public static void main(String[] args) {
		Okno okno = new Okno();
	}
	
}
