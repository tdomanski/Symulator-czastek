package interfejs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuGorne extends JPanel {
	
	private JButton uruchomButton;
	private JButton zatrzymajButton;
	private JButton poleTrajektorieButton;
	
	private ObszarSymulacji obszarSymulacji;//Obszar symulacji, za który odpowiedzialne jest to menu
	
	public MenuGorne() {//KONSTRUKTOR
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		uruchomButton = new JButton("Uruchom");
		uruchomButton.setToolTipText("Uruchom symulacjê");
		uruchomButton.addActionListener(event -> this.uruchomSymulacje());
		this.add(uruchomButton);
		
		zatrzymajButton = new JButton("Zatrzymaj");
		zatrzymajButton.setToolTipText("Zatrzymaj symulacjê");
		zatrzymajButton.addActionListener(event -> this.zatrzymajSymulacje());
		this.add(zatrzymajButton);
		
		poleTrajektorieButton = new JButton("Pole / Trajektorie cz¹stek");
		poleTrajektorieButton.setToolTipText("Prze³¹cz pomiêdzy widokiem pola wektorowego i widokiem trajektorii zakreœlanych przez cz¹stki");
		poleTrajektorieButton.addActionListener(event -> obszarSymulacji.przelaczPoleTrajektorie());
		this.add(poleTrajektorieButton);
		
	}//KONIEC KONSTRUKTORA
	
	public void uruchomSymulacje() {
		obszarSymulacji.uruchomExecutor();
	}
	
	public void zatrzymajSymulacje() {
		obszarSymulacji.wylaczExecutor();
	}
	
	public void ustawObszarSymulacji (ObszarSymulacji os) { // przypisanie obszaru symulacji, za ktory odpowiedzialne jest to menu
		this.obszarSymulacji = os;
	}
}
