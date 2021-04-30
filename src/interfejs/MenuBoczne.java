package interfejs;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.CzastkaStacjonarna;

public class MenuBoczne extends JPanel {
	
	private boolean czastkaStacjonarnaSelected;
	private boolean czastkaProbnaSelected;
	
	private JPanel top;
	private JPanel spacing1;
	private JPanel center;
	private JPanel spacing2;
	private JPanel bottom;
	
	private JButton czastkaStacjonarnaButton;
	private JButton czastkaProbnaButton;
	private JLabel parametryNowejCzastkiLabel;
	private JTextField masaField;
	private JTextField ladunekField;
	private JButton dodajCzastkeButton;
	private JButton importujCzastkiButton;
	private JButton eksportujCzastkiButton;
	private JButton eksportujObrazButton;
	private int id=0; //Numer czastki
	private ObszarSymulacji obszarSymulacji;//Obszar symulacji, za który odpowiedzialne jest to menu
	
	public MenuBoczne() {//KONSTRUKTOR
		GridLayout layout1 = new GridLayout(5,1);
		layout1.setHgap(2);
		layout1.setVgap(2);
		GridLayout layout2 = new GridLayout(5,1);
		layout2.setHgap(1);
		layout2.setVgap(1);
		this.setLayout(layout1);
		top = new JPanel(layout2);
		
		
		
		
		czastkaStacjonarnaButton = new JButton("Nowa cz¹stka stacjonarna");
		ActionListener czastkaStacjonarnaButtonListener = event -> {
			this.ustawWyborNowaCzastkaStacjonarna();
		};
		czastkaStacjonarnaButton.addActionListener(czastkaStacjonarnaButtonListener);
		top.add(czastkaStacjonarnaButton);
		
		
		czastkaProbnaButton = new JButton("Nowa cz¹stka próbna");
		ActionListener czastkaProbnaButtonListener = event -> {
			this.ustawWyborNowaCzastkaProbna();
		};
		czastkaProbnaButton.addActionListener(czastkaProbnaButtonListener);
		top.add(czastkaProbnaButton);
		
		
		
		
		parametryNowejCzastkiLabel = new JLabel("Parametry nowej cz¹stki");
		parametryNowejCzastkiLabel.setPreferredSize(getPreferredSize());
		top.add(parametryNowejCzastkiLabel);
		
		
		
		
		masaField = new JTextField();
		masaField.setEditable(false);
		TextPrompt masaInitialText = new TextPrompt("masa", masaField);
		masaInitialText.changeAlpha(128);
		top.add(masaField);
		ladunekField = new JTextField();
		ladunekField.setEditable(false);
		TextPrompt ladunekInitialText = new TextPrompt("³adunek", ladunekField);
		ladunekInitialText.changeAlpha(128);
		top.add(ladunekField);
		
		
		this.add(top);
		
		
		spacing1 = new JPanel();
		dodajCzastkeButton = new JButton("Dodaj cz¹stkê");
		dodajCzastkeButton.addActionListener(event -> this.dodajCzastke());
		spacing1.add(dodajCzastkeButton);
		this.add(spacing1);
		
		GridLayout layout3 = new GridLayout(6,1);
		layout3.setHgap(1);
		layout3.setVgap(1);
		center = new JPanel (layout3);
		
		
		importujCzastkiButton = new JButton("Importuj cz¹stki");
		importujCzastkiButton.addActionListener(event -> this.importujCzastki());
		center.add(importujCzastkiButton);
		
		eksportujCzastkiButton = new JButton("Eksportuj cz¹stki");
		eksportujCzastkiButton.addActionListener(event -> this.eksportujCzastki());
		center.add(eksportujCzastkiButton);
		
		
		this.add(center);
		
		
		
		
		spacing2 = new JPanel();
		this.add(spacing2);
		
		
		
		
		bottom = new JPanel(new GridLayout(6,1));
		
		
		eksportujObrazButton = new JButton("Eksportuj Obraz");
		eksportujObrazButton.addActionListener(event -> this.eksportujObraz());
		bottom.add(eksportujObrazButton);
		
		this.add(bottom);
	}//KONIEC KONSTRUKTORA
	
	public void ustawWyborNowaCzastkaStacjonarna() {
		czastkaStacjonarnaSelected = true;
		czastkaProbnaSelected = false;
		parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz¹stki<br/> stacjonarnej<html>");
		masaField.setEditable(false);
		ladunekField.setEditable(true);
	}
	
	public void ustawWyborNowaCzastkaProbna() {
		czastkaProbnaSelected = true;
		czastkaStacjonarnaSelected = false;
		parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz¹stki<br/> próbnej<html>");
		masaField.setEditable(true);
		ladunekField.setEditable(true);
	}
	
	public void dodajCzastke() {
		Random r = new Random();
		if (czastkaStacjonarnaSelected == true) {
			if (ladunekField.getText().isBlank() == false) {
				try {
					CzastkaStacjonarna cs = new CzastkaStacjonarna(r.nextInt(obszarSymulacji.getWidth()), r.nextInt(obszarSymulacji.getHeight()), Double.valueOf(ladunekField.getText()));
					obszarSymulacji.dodajCzastkeStacjonarna(cs);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz ³adunek w postaci liczby rzeczywistej");
				}
			}
		}
		
		else if (czastkaProbnaSelected == true) {
			if (masaField.getText().isBlank() == false && ladunekField.getText().isBlank() == false) {
				try {
					CzastkaProbna cp = new CzastkaProbna(r.nextInt(obszarSymulacji.getWidth()), r.nextInt(obszarSymulacji.getHeight()),
							 Double.valueOf(masaField.getText()), Double.valueOf(ladunekField.getText()), 0, 0, id);
					obszarSymulacji.dodajCzastkeProbna(cp);
					id++;
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz masê i ³adunek w postaci liczb rzeczywistych");
				}
			}
		}
	}
	
	public void importujCzastki() {
		
	}
	
	public void eksportujCzastki() {
		
	}
	
	public void eksportujObraz() {
		BufferedImage image = new BufferedImage(obszarSymulacji.getWidth(), obszarSymulacji.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = image.createGraphics();
		obszarSymulacji.paintAll(g2D);
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File selectedFile = null;
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
        }
		try {
			ImageIO.write(image, "png", selectedFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ustawObszarSymulacji(ObszarSymulacji os) {
		this.obszarSymulacji = os;
	}
}
