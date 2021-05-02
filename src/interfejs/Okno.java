package interfejs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import matematyczna.Obliczenia;

public class Okno extends JFrame {
	private MenuGorne menuGorne;
	private ObszarSymulacji obszarSymulacji;
	private MenuBoczne menuBoczne;
	private BorderLayout layout;
	public final BufferedImage icon = loadBufferedImage("/electriccharges.png");
	public Okno()
	{
		layout = new BorderLayout();
		//layout.setHgap(4);
		//layout.setVgap(4);
		this.setLayout(layout);
		menuGorne = new MenuGorne();
		menuBoczne = new MenuBoczne();
		obszarSymulacji = new ObszarSymulacji(); //(dlugosc,szerokosc)
		menuBoczne.ustawObszarSymulacji(obszarSymulacji);
		menuGorne.ustawObszarSymulacji(obszarSymulacji);
		this.add(menuGorne,BorderLayout.PAGE_START);
		this.add(menuBoczne,BorderLayout.LINE_END);
		this.add(obszarSymulacji,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setSize(600, 600);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		//this.setResizable(false);
		this.setTitle("Symulator cz¹stek w polu elektrycznym");
		this.setIconImage(icon);
	}
	
	private BufferedImage loadBufferedImage(String string)
	{
	    try
	    {
	        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
	        return bi;
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void main(String[] args) {
		Okno okno = new Okno();
	}
}