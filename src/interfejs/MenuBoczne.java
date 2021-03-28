package interfejs;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuBoczne extends JPanel {
	
	boolean czastkaStacjonarnaSelected;
	boolean czastkaProbnaSelected;
	
	JPanel top;
	JPanel spacing1;
	JPanel center;
	JPanel spacing2;
	JPanel bottom;
	
	JButton czastkaStacjonarnaButton;
	JButton czastkaProbnaButton;
	JLabel parametryNowejCzastkiLabel;
	JTextField masaField;
	JTextField ladunekField;
	JButton dodajCzastkeButton;
	JButton importujCzastkiButton;
	JButton eksportujCzastkiButton;
	JButton eksportujObrazButton;
	
	public MenuBoczne() 
	{
		this.setLayout(new GridLayout(5,1));
		
		top = new JPanel(new GridLayout(6,1));
		
		czastkaStacjonarnaButton = new JButton("Nowa cz¹stka stacjonarna");
		ActionListener czastkaStacjonarnaButtonListener = event -> {
			czastkaStacjonarnaSelected = true;
			czastkaProbnaSelected = false;
			parametryNowejCzastkiLabel.setText("Parametry nowej cz¹stki stacjonarnej");
			masaField.setEditable(false);
			ladunekField.setEditable(true);
		};
		czastkaStacjonarnaButton.addActionListener(czastkaStacjonarnaButtonListener);
		top.add(czastkaStacjonarnaButton);
		
		czastkaProbnaButton = new JButton("Nowa cz¹stka próbna");
		ActionListener czastkaProbnaButtonListener = event -> {
			czastkaProbnaSelected = true;
			czastkaStacjonarnaSelected = false;
			parametryNowejCzastkiLabel.setText("Parametry nowej cz¹stki próbnej");
			masaField.setEditable(true);
			ladunekField.setEditable(true);
		};
		czastkaProbnaButton.addActionListener(czastkaProbnaButtonListener);
		top.add(czastkaProbnaButton);
		
		parametryNowejCzastkiLabel = new JLabel("Parametry nowej cz¹stki");
		parametryNowejCzastkiLabel.setPreferredSize(getPreferredSize());
		top.add(parametryNowejCzastkiLabel);
		
		masaField = new JTextField("masa");
		masaField.setEditable(false);
		top.add(masaField);
		
		ladunekField = new JTextField("³adunek");
		ladunekField.setEditable(false);
		top.add(ladunekField);
		
		dodajCzastkeButton = new JButton("Dodaj cz¹stkê");
		top.add(dodajCzastkeButton);
		
		this.add(top);
		
		spacing1 = new JPanel();
		this.add(spacing1);
		
		center = new JPanel (new GridLayout(6,1));
		
		importujCzastkiButton = new JButton("Importuj cz¹stki");
		center.add(importujCzastkiButton);
		
		eksportujCzastkiButton = new JButton("Eksportuj cz¹stki");
		center.add(eksportujCzastkiButton);
		
		this.add(center);
		
		spacing2 = new JPanel();
		this.add(spacing2);
		
		bottom = new JPanel(new GridLayout(6,1));
		
		eksportujObrazButton = new JButton("Eksportuj Obraz");
		bottom.add(eksportujObrazButton);
		
		this.add(bottom);
	}
}
