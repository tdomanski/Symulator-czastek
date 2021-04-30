package interfejs;

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
		
		uruchomButton = new JButton("Uruchom");
		uruchomButton.setToolTipText("Uruchom symulacjê");
		ActionListener uruchomButtonListener = event -> {
			this.uruchomSymulacje();
		};
		uruchomButton.addActionListener(uruchomButtonListener);
		this.add(uruchomButton);
		
		zatrzymajButton = new JButton("Zatrzymaj");
		zatrzymajButton.setToolTipText("Zatrzymaj symulacjê");
		ActionListener zatrzymajButtonListener = event -> {
			this.zatrzymajSymulacje();
		};
		zatrzymajButton.addActionListener(zatrzymajButtonListener);
		this.add(zatrzymajButton);
		
		poleTrajektorieButton = new JButton("Pole / Trajektorie cz¹stek");
		poleTrajektorieButton.setToolTipText("Prze³¹cz pomiêdzy widokiem pola wektorowego i widokiem trajektorii zakreœlanych przez cz¹stki");
		poleTrajektorieButton.addActionListener(event -> obszarSymulacji.przelaczPoleTrajektorie());
		this.add(poleTrajektorieButton);
		
	}//KONIEC KONSTRUKTORA
	
	public void uruchomSymulacje() {
		
	}
	
	public void zatrzymajSymulacje() {
		
	}
	
	public void ustawObszarSymulacji (ObszarSymulacji os) {
		this.obszarSymulacji = os;
	}
}
