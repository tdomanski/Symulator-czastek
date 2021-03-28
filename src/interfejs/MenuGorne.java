package interfejs;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuGorne extends JPanel {
	
	JButton uruchomButton;
	JButton zatrzymajButton;
	JButton poleTrajektorieButton;
	
	public MenuGorne() 
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		uruchomButton = new JButton("Uruchom");
		uruchomButton.setToolTipText("Uruchom symulacjê");
		this.add(uruchomButton);
		
		zatrzymajButton = new JButton("Zatrzymaj");
		zatrzymajButton.setToolTipText("Zatrzymaj symulacjê");
		this.add(zatrzymajButton);
		
		poleTrajektorieButton = new JButton("Pole / Trajektorie cz¹stek");
		poleTrajektorieButton.setToolTipText("Prze³¹cz pomiêdzy widokiem pola wektorowego i widokiem trajektorii zakreœlanych przez cz¹stki");
		this.add(poleTrajektorieButton);
		
	}
}
