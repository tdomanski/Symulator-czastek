package interfejs;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

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
		ActionListener dodajCzastkeButtonListener = event -> {
			this.dodajCzastke();
		};
		dodajCzastkeButton.addActionListener(dodajCzastkeButtonListener);
		spacing1.add(dodajCzastkeButton);
		this.add(spacing1);
		
		GridLayout layout3 = new GridLayout(6,1);
		layout3.setHgap(1);
		layout3.setVgap(1);
		center = new JPanel (layout3);
		
		
		importujCzastkiButton = new JButton("Importuj cz¹stki");
		ActionListener importujCzastkiButtonListener = event -> {
			this.importujCzastki();
		};
		importujCzastkiButton.addActionListener(importujCzastkiButtonListener);
		center.add(importujCzastkiButton);
		
		eksportujCzastkiButton = new JButton("Eksportuj cz¹stki");
		ActionListener eksportujCzastkiButtonListener = event -> {
			this.eksportujCzastki();
		};
		eksportujCzastkiButton.addActionListener(eksportujCzastkiButtonListener);
		center.add(eksportujCzastkiButton);
		
		
		this.add(center);
		
		
		
		
		spacing2 = new JPanel();
		this.add(spacing2);
		
		
		
		
		bottom = new JPanel(new GridLayout(6,1));
		
		
		eksportujObrazButton = new JButton("Eksportuj Obraz");
		ActionListener eksportujObrazButtonListener = event -> {
			this.eksportujObraz();
		};
		eksportujObrazButton.addActionListener(eksportujObrazButtonListener);
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
		if (czastkaStacjonarnaSelected == true) {
			CzastkaStacjonarna cs = new CzastkaStacjonarna(obszarSymulacji.getWidth()/2, obszarSymulacji.getHeight()/2, Double.valueOf(ladunekField.getText()));
			obszarSymulacji.dodajCzastkeStacjonarna(cs);
		}
		
		else if (czastkaProbnaSelected == true) {
			CzastkaProbna cp = new CzastkaProbna(obszarSymulacji.getWidth()/2, obszarSymulacji.getHeight()/2,
												 Double.valueOf(masaField.getText()), Double.valueOf(ladunekField.getText()), 0, 0,id);
			obszarSymulacji.dodajCzastkeProbna(cp);
			id++;
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
