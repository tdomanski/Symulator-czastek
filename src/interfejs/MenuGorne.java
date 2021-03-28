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
		uruchomButton.setToolTipText("Uruchom symulacj�");
		this.add(uruchomButton);
		
		zatrzymajButton = new JButton("Zatrzymaj");
		zatrzymajButton.setToolTipText("Zatrzymaj symulacj�");
		this.add(zatrzymajButton);
		
		poleTrajektorieButton = new JButton("Pole / Trajektorie cz�stek");
		poleTrajektorieButton.setToolTipText("Prze��cz pomi�dzy widokiem pola wektorowego i widokiem trajektorii zakre�lanych przez cz�stki");
		this.add(poleTrajektorieButton);
		
	}
}
