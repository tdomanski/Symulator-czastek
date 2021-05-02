package interfejs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import czastki.parametry.CzastkaProbna;
import czastki.parametry.CzastkaStacjonarna;
import czastki.parametry.Czastki;

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
	TextPrompt masaInitialText, ladunekInitialText;
	private JButton dodajCzastkeButton;
	private JButton importujCzastkiButton;
	private JButton eksportujCzastkiButton;
	private JButton eksportujObrazButton;
	private int id=0; //Numer czastki
	private ObszarSymulacji obszarSymulacji;//Obszar symulacji, za kt�ry odpowiedzialne jest to menu
	
	public MenuBoczne() {//KONSTRUKTOR
		GridLayout layout1 = new GridLayout(5,1);
		//layout1.setHgap(2);
		//layout1.setVgap(2);
		GridLayout layout2 = new GridLayout(5,1);
		layout2.setHgap(1);
		layout2.setVgap(1);
		this.setLayout(layout1);		
		top = new JPanel(layout2);
		top.setBackground(Color.white);
		
		
		czastkaStacjonarnaButton = new JButton("Nowa cz�stka stacjonarna");
		ActionListener czastkaStacjonarnaButtonListener = event -> {
			this.ustawWyborNowaCzastkaStacjonarna();
			masaField.setText(null);
			ladunekField.setText(null);
		};
		czastkaStacjonarnaButton.addActionListener(czastkaStacjonarnaButtonListener);
		top.add(czastkaStacjonarnaButton);
		
		
		czastkaProbnaButton = new JButton("Nowa cz�stka pr�bna");
		ActionListener czastkaProbnaButtonListener = event -> {
			this.ustawWyborNowaCzastkaProbna();
			masaField.setText(null);
			ladunekField.setText(null);
		};
		czastkaProbnaButton.addActionListener(czastkaProbnaButtonListener);
		top.add(czastkaProbnaButton);
		
		
		
		
		parametryNowejCzastkiLabel = new JLabel("Parametry nowej cz�stki");
		parametryNowejCzastkiLabel.setPreferredSize(getPreferredSize());
		top.add(parametryNowejCzastkiLabel);
		
		
		
		
		masaField = new JTextField();
		masaField.setEditable(false);
		masaField.setBackground(Color.white);
		masaInitialText = new TextPrompt("masa", masaField);
		masaInitialText.changeAlpha(128);
		top.add(masaField);
		ladunekField = new JTextField();
		ladunekField.setEditable(false);
		ladunekField.setBackground(Color.white);
		ladunekInitialText = new TextPrompt("�adunek", ladunekField);
		ladunekInitialText.changeAlpha(128);
		top.add(ladunekField);
		
		
		this.add(top);
		
		
		spacing1 = new JPanel();
		spacing1.setBackground(Color.white);
		dodajCzastkeButton = new JButton("Dodaj cz�stk�");
		dodajCzastkeButton.addActionListener(event -> this.dodajCzastke());
		spacing1.add(dodajCzastkeButton);
		this.add(spacing1);
		
		GridLayout layout3 = new GridLayout(6,1);
		layout3.setHgap(1);
		layout3.setVgap(1);
		center = new JPanel (layout3);
		center.setBackground(Color.white);
		
		
		importujCzastkiButton = new JButton("Importuj cz�stki");
		importujCzastkiButton.addActionListener(event -> {
			try {
				this.importujCzastki();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		center.add(importujCzastkiButton);
		
		eksportujCzastkiButton = new JButton("Eksportuj cz�stki");
		eksportujCzastkiButton.addActionListener(event -> this.eksportujCzastki());
		center.add(eksportujCzastkiButton);
		
		
		this.add(center);
		
		
		
		
		spacing2 = new JPanel();
		spacing2.setBackground(Color.white);
		this.add(spacing2);
		
		
		
		
		bottom = new JPanel(new GridLayout(6,1));
		bottom.setBackground(Color.white);
		
		
		eksportujObrazButton = new JButton("Eksportuj Obraz");
		eksportujObrazButton.addActionListener(event -> this.eksportujObraz());
		bottom.add(eksportujObrazButton);
		
		this.add(bottom);
	}//KONIEC KONSTRUKTORA
	
	public void ustawWyborNowaCzastkaStacjonarna() {
		czastkaStacjonarnaSelected = true;
		czastkaProbnaSelected = false;
		parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz�stki<br/> stacjonarnej<html>");
		masaField.setEditable(false);
		ladunekField.setEditable(true);
	}
	
	public void ustawWyborNowaCzastkaProbna() {
		czastkaProbnaSelected = true;
		czastkaStacjonarnaSelected = false;
		parametryNowejCzastkiLabel.setText("<html>Parametry nowej cz�stki<br/> pr�bnej<html>");
		masaField.setEditable(true);
		ladunekField.setEditable(true);
	}
	
	public void dodajCzastke() {
		obszarSymulacji.setCzastkaStacjonarnaRadius(obszarSymulacji.getHeight()/50); // Skalowanie rozmiarow czastek do rozmiaru obszaru symulacji
		obszarSymulacji.setCzastkaProbnaRadius(obszarSymulacji.getHeight()/100);
		
		int randomX = 2 *obszarSymulacji.getCzastkaStacjonarnaRadius() + (int)(Math.random() * (obszarSymulacji.getWidth() - 4 * obszarSymulacji.getCzastkaStacjonarnaRadius())); // Losowe pozycje poczatkowe czastek
		int randomY = 2 *obszarSymulacji.getCzastkaStacjonarnaRadius() + (int)(Math.random() * (obszarSymulacji.getHeight() - 4 * obszarSymulacji.getCzastkaStacjonarnaRadius()));
		
		if (czastkaStacjonarnaSelected == true) { // Dodawanie czastki stacjonarnej
			if (ladunekField.getText().isBlank() == false) {
				try {
					CzastkaStacjonarna cs = new CzastkaStacjonarna(randomX, randomY, Double.valueOf(ladunekField.getText()));
					obszarSymulacji.dodajCzastkeStacjonarna(cs);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz �adunek w postaci liczby rzeczywistej");
				}
			}
			else
				JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz �adunek w postaci liczby rzeczywistej");
		}
		
		else if (czastkaProbnaSelected == true) { // Dodawanie czastki probnej
			if (masaField.getText().isBlank() == false && ladunekField.getText().isBlank() == false) {
				try {
					double m = Double.valueOf(masaField.getText());
					double q = Double.valueOf(ladunekField.getText());
					if (m > 0) {
						CzastkaProbna cp = new CzastkaProbna(randomX, randomY, m, q, 0, 0, id);
						obszarSymulacji.dodajCzastkeProbna(cp);
						id++;
					}
					else
						JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz mas� i �adunek w postaci liczb rzeczywistych (masa musi by� dodatnia)");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz mas� i �adunek w postaci liczb rzeczywistych (masa musi by� dodatnia)");
				}
			}
			else
				JOptionPane.showMessageDialog(obszarSymulacji, "Wprowadz mas� i �adunek w postaci liczb rzeczywistych (masa musi by� dodatnia)");
		}
	}
	
	public void importujCzastki() throws IOException {
		File selectedFile = null;
		JFileChooser jfc = new JFileChooser();
		int returnValue = jfc.showOpenDialog(null);
  		if (returnValue == JFileChooser.APPROVE_OPTION) {
	  		if(jfc.getSelectedFile()!=null)
	  		{
	  	  	    selectedFile=jfc.getSelectedFile();
	  	  	    int nieWczytane=0;
	  	  	    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
	  	  	    	String line;
	  	  	    	while ((line = br.readLine()) != null) {
	  	  	    		//System.out.println(line);
	  	  	    		String[] parametry = line.split(" ");
	  	  	    		System.out.print(parametry[0]+"\n");
	  	  	    		if(parametry[0].equals("S")) // Wczytywanie czastki stacjonarnej
	  	  	    		{
	  	  	    			CzastkaStacjonarna cs = new CzastkaStacjonarna(Integer.parseInt(parametry[1]), Integer.parseInt(parametry[1]), Double.parseDouble(parametry[3]));
	  	  	    			obszarSymulacji.dodajCzastkeStacjonarna(cs);
	  	  	    		}
	  	  	    		else if(parametry[0].equals("F")) // Wczytywanie czastki swobodnej
	  	  	    		{
	  	  	    			CzastkaProbna cp = new CzastkaProbna(Integer.parseInt(parametry[1]), Integer.parseInt(parametry[1]), Double.parseDouble(parametry[3]), Double.parseDouble(parametry[4]), 0, 0, id);
	  	  	    			obszarSymulacji.dodajCzastkeProbna(cp);
	  	  	    			id++;
	  	  	    		}
	  	  	    		else
	  	  	    		{
	  	  	    			nieWczytane++;
	  	  	    		}
	  	  	    		
	  	  	    	}
	  	  	    }
	  	  	    if(nieWczytane!=0)
	  	  	    {
	  	  	    	JOptionPane.showMessageDialog (null, "Nie wczytano "+nieWczytane+" parametr�w cz�stek!");
	  	  	    }
	  	  	    else
	  	  	    {
	  	  	    	obszarSymulacji.repaint();
	  	  	    }
	  		}

  	    selectedFile=jfc.getSelectedFile();
  	    int nieWczytane=0;
  	    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
  	    	String line;
  	    	while ((line = br.readLine()) != null) {
  	    		//System.out.println(line);
  	    		String[] parametry = line.split(" ");
  	    		System.out.print(parametry[0]+"\n");
  	    		if(parametry[0].equals("S")) // Wczytywanie czastki stacjonarnej
  	    		{
  	    			CzastkaStacjonarna cs = new CzastkaStacjonarna(Integer.parseInt(parametry[1]), Integer.parseInt(parametry[2]), Double.parseDouble(parametry[3]));
  	    			obszarSymulacji.dodajCzastkeStacjonarna(cs);
  	    		}
  	    		else if(parametry[0].equals("F")) // Wczytywanie czastki swobodnej
  	    		{
  	    			CzastkaProbna cp = new CzastkaProbna(Integer.parseInt(parametry[1]), Integer.parseInt(parametry[2]), Double.parseDouble(parametry[3]), Double.parseDouble(parametry[4]), 0, 0, id);
  	    			obszarSymulacji.dodajCzastkeProbna(cp);
  	    			id++;
  	    		}
  	    		else
  	    		{
  	    			nieWczytane++;
  	    		}
  	    		
  	    	}
  	    }
  	    if(nieWczytane!=0)
  	    {
  	    	JOptionPane.showMessageDialog (null, "Nie wczytano "+nieWczytane+" parametr�w cz�stek!");
  	    }
  	    else
  	    {
  	    	obszarSymulacji.repaint();
  	    }
  		}
  		else
  		{
  			JOptionPane.showMessageDialog (null, "Brak wybranego pliku!");
  		}
	}
	
	public void eksportujCzastki() {
		Czastki czastki = obszarSymulacji.getCz(); 
		int sizeProbne = czastki.getIloscCzProbnych();
		int sizeStacjonarne = czastki.getIloscCzStacjon();
		if(sizeProbne==0&&sizeStacjonarne==0)
		{
			JOptionPane.showMessageDialog (null, "Brak cz�stek do zapisania z obszaru symulacji!");
		}
		else
		{
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
			File selectedFile = null;
			int returnValue = jfc.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
	            selectedFile = jfc.getSelectedFile();
	            if (!jfc.getSelectedFile().getAbsolutePath().endsWith(".txt"))
	            	selectedFile = new File(jfc.getSelectedFile() + ".txt");
	            System.out.println(selectedFile.getAbsolutePath());
	        }
			if (selectedFile!=null) {
				FileWriter fw = null;
				String line = null;
						try {
						fw = new FileWriter(selectedFile);
						//Stacjonarne - wczytywanie do pliku
						for(int i=0;i<sizeStacjonarne;i++) // Format: S X Y Ladunek
						{
							if(i!=sizeStacjonarne-1)
							{
								line ="S "+czastki.getCzastkeStacjonarna(i).getX()+" "+czastki.getCzastkeStacjonarna(i).getY()+" "+czastki.getCzastkeStacjonarna(i).getLadunek()+"\n";		
							}
							else
							{
								line ="S "+czastki.getCzastkeStacjonarna(i).getX()+" "+czastki.getCzastkeStacjonarna(i).getY()+" "+czastki.getCzastkeStacjonarna(i).getLadunek();
							}
							fw.write(line);		
						}
						//Probne - wczytywanie do pliku
						for(int i=0;i<sizeProbne;i++)// Format: F X Y Masa Ladunek
						{
							if(i==0)
							{
								fw.write("\n");
							}
							if(i!=sizeProbne-1)
							{
								line ="F "+czastki.getCzastkeProbna(i).getX()+" "+czastki.getCzastkeProbna(i).getY()+" "+czastki.getCzastkeProbna(i).getMasa()+" "+czastki.getCzastkeProbna(i).getLadunek()+"\n";
								fw.write(line);
							}
							else
							{
								line ="F "+czastki.getCzastkeProbna(i).getX()+" "+czastki.getCzastkeProbna(i).getY()+" "+czastki.getCzastkeProbna(i).getMasa()+" "+czastki.getCzastkeProbna(i).getLadunek();
								fw.write(line);
							}
						}
						fw.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
			else
			{
				JOptionPane.showMessageDialog (null, "Plik nie zosta� zapisany - nie wybrano miejsca docelowego!");
			}
		}
		
	}
	
	public void eksportujObraz() {
		BufferedImage image = new BufferedImage(obszarSymulacji.getWidth(), obszarSymulacji.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = image.createGraphics();
		obszarSymulacji.paintAll(g2D);
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileFilter(new FileNameExtensionFilter("*.png", ".png"));
		File selectedFile = null;
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
            if (!jfc.getSelectedFile().getAbsolutePath().endsWith(".png"))
            	selectedFile = new File(jfc.getSelectedFile() + ".png");
            System.out.println(selectedFile.getAbsolutePath());
        }
		if (selectedFile != null) {
			try {
				ImageIO.write(image, "PNG", selectedFile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void ustawObszarSymulacji(ObszarSymulacji os) { // przypisanie obszaru symulacji, za ktory odpowiedzialne jest to menu
		this.obszarSymulacji = os;
	}
}
