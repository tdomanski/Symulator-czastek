package interfejs;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
		
		top = new JPanel(new GridLayout(5,1));
		
		czastkaStacjonarnaButton = new JButton("Nowa cz¹stka stacjonarna");
		ActionListener czastkaStacjonarnaButtonListener = event -> {
			czastkaStacjonarnaSelected = true;
			czastkaProbnaSelected = false;
			parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz¹stki<br/> stacjonarnej<html>");
			masaField.setEditable(false);
			ladunekField.setEditable(true);
		};
		czastkaStacjonarnaButton.addActionListener(czastkaStacjonarnaButtonListener);
		top.add(czastkaStacjonarnaButton);
		
		czastkaProbnaButton = new JButton("Nowa cz¹stka próbna");
		ActionListener czastkaProbnaButtonListener = event -> {
			czastkaProbnaSelected = true;
			czastkaStacjonarnaSelected = false;
			parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz¹stki<br/> próbnej<html>");
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
		
		this.add(top);
		
		spacing1 = new JPanel();
		dodajCzastkeButton = new JButton("Dodaj cz¹stkê");
		ActionListener dodajCzastkeButtonListener = event -> {
			System.out.println("Przycisk 'Dodaj cz¹stkê' zosta³ klikniêty'");
		};
		dodajCzastkeButton.addActionListener(dodajCzastkeButtonListener);
		spacing1.add(dodajCzastkeButton);
		this.add(spacing1);
		
		center = new JPanel (new GridLayout(6,1));
		
		importujCzastkiButton = new JButton("Importuj cz¹stki");
		ActionListener importujCzastkiButtonListener = event -> {
			System.out.println("Przycisk 'Importuj cz¹stki' zosta³ klikniêty");
		};
		importujCzastkiButton.addActionListener(importujCzastkiButtonListener);
		center.add(importujCzastkiButton);
		
		eksportujCzastkiButton = new JButton("Eksportuj cz¹stki");
		ActionListener eksportujCzastkiButtonListener = event -> {
			System.out.println("Przycisk 'Eksportuj cz¹stki' zosta³ klikniêty");
		};
		eksportujCzastkiButton.addActionListener(eksportujCzastkiButtonListener);
		center.add(eksportujCzastkiButton);
		
		this.add(center);
		
		spacing2 = new JPanel();
		this.add(spacing2);
		
		bottom = new JPanel(new GridLayout(6,1));
		
		eksportujObrazButton = new JButton("Eksportuj Obraz");
		ActionListener eksportujObrazButtonListener = event -> {
			System.out.println("Jeszcze nie jest zbyt piêkny, ale w porz¹dku.");
		};
		eksportujObrazButton.addActionListener(eksportujObrazButtonListener);
		bottom.add(eksportujObrazButton);
		
		this.add(bottom);
	}
}
