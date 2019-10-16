/**
 * 
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import App.RandomGenerator;
import DTO.Candidat;
import Exceptions.InvalidBussinesLogicException;
import Exceptions.InvalitFormException;
import Validation.SearchValidation;
import Validation.UpdateValidation;;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 7:19:44 PM
 * @Swing_Project
 * @MainFrame.java
 */
public class MainFrame extends JFrame {

	final static String ADD_BUTTON = "add";
	final static String SEARCH_BUTTON = "search";
	final static String REFRESH_BUTTON = "refresh";
	final static String SAVE_BUTTON = "save";
	final static String ADD_RANDOM_BUTTON = "add.random";
	final static String NAME_LABLE = "lable.name";
	final static String FIRST_NAME_LABEL = "lable.first_name";
	final static String DATE_LABEL = "add.date";

	public static List<Candidat> database = new LinkedList<Candidat>();
	public static List<Candidat> searched_Candidats = new ArrayList<Candidat>();
	private static boolean searchFlag = false;
	private JTable table;
	private JButton button_add;
	private JButton button_generate;
	public static JButton button_refresh;
	private JButton button_search;
	private JButton button_save;
	private RandomGenerator generator;
	private DefaultTableModel model;
	private JScrollPane pane;
	private JLabel LName;
	private JLabel LPrenume;
	private JLabel LData;
	private JTextField numeField;
	private JTextField prenumeField;
	private JTextField dataField;
	private Candidat selectedCandidat;
	private JPopupMenu popupMenu;
	private JMenuItem showDetailsItem;
	private JMenuItem deleteItem;
	private int currentid;
	private JComboBox jBox;
	public static ResourceBundle resourceBundle;
	private static Locale locale;
	private Object[] RO_columns = { "Nume", "Prenume", "Data", "Nota Matematica", "Nota Romana", "Adresa" };
	private Object[] EN_columns = { "Name", "First Name", "Date", "Math Grade", "English grade", "Address" };

	public MainFrame() {
		super("App");
		initComponents();

		makeTable();

		/**
		 * Adauga in table toti candidatii din lista de candidati
		 * 
		 */

		/**
		 * Adauga in lista candidati 10 candidati random
		 * 
		 * 
		 */
		button_generate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				database = generator.addRandomCandidats(10, database);
				// model.setRowCount(0);
				addDatatoTable();

			}
		});

		/**
		 * 
		 * Deschide fereastra AdaufareCandidati
		 */
		button_add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AdaugareCandidatiExamen();

			}
		});

		jBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				setText(jBox.getSelectedItem().toString());
				Object[] columns;
				if (jBox.getSelectedItem().toString().equals("RO")) {
					model.setColumnIdentifiers(RO_columns);
				} else {
					model.setColumnIdentifiers(EN_columns);
				}
			}

		});

		button_search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Candidat tempCandidat = new Candidat();
				tempCandidat.setNume(numeField.getText());
				tempCandidat.setPrenume(prenumeField.getText());
				tempCandidat.setDataNasterii(dataField.getText());
				try {
					SearchValidation.validSearch(tempCandidat);
					addDatatoSearchList(tempCandidat);
					searchFlag = true;

					button_generate.setEnabled(false);
					model.setRowCount(0);
					addDatatoTable();
					searched_Candidats.clear();
				} catch (InvalitFormException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidForm", JOptionPane.ERROR_MESSAGE);
					System.out.println("INVALID FORMAT@@");
				} catch (DataFormatException e1) {
					JOptionPane.showMessageDialog(null, "Invalid date", "InvalidDate", JOptionPane.ERROR_MESSAGE);
					System.out.println("INVALID DATE@@");
					e1.printStackTrace();
				}

			}
		});

		button_refresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				searchFlag = false;
				searched_Candidats.clear();
				button_generate.setEnabled(true);
				model.setRowCount(0);
				addDatatoTable();
				clearFilds();

			}
		});

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();

				for (int i = 0; i < database.size(); i++) {
					if (database.get(i).getNume().compareTo(model.getValueAt(row, 0).toString()) == 0
							&& database.get(i).getPrenume().compareTo(model.getValueAt(row, 1).toString()) == 0
							&& database.get(i).getDataNasterii().compareTo(model.getValueAt(row, 2).toString()) == 0) {

						currentid = i;

					}
					if (SwingUtilities.isRightMouseButton(e)) {

						popupMenu.add(showDetailsItem);
						popupMenu.add(deleteItem);
						popupMenu.show(e.getComponent(), e.getX(), e.getY());

					}

				}

				// Bussines and Form validation miss here
				numeField.setText(model.getValueAt(row, 0).toString());
				prenumeField.setText(model.getValueAt(row, 1).toString());
				dataField.setText(model.getValueAt(row, 2).toString());

			}
//			public void mouseRelease(MouseEvent e) {
//				
//				
//				
//				
//			} 
		});

		button_save.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					UpdateValidation.validFields(numeField, prenumeField, dataField);

					int confirm = JOptionPane.showConfirmDialog(null, "Do you want to update the current candidat?", "",
							JOptionPane.YES_NO_OPTION);
					if (confirm == 0) { // The ISSUE is here
						selectedCandidat.setNume(numeField.getText());
						selectedCandidat.setPrenume(prenumeField.getText());
						selectedCandidat.setDataNasterii(dataField.getText());
						selectedCandidat.setAdresa(database.get(currentid).getAdresa());
						selectedCandidat.setNotaMatematica(database.get(currentid).getNotaMatematica());
						selectedCandidat.setNotaRomana(database.get(currentid).getNotaRomana());

						System.out.println(selectedCandidat.toString());
						database.set(currentid, selectedCandidat);
						button_refresh.doClick();
						System.out.print("Confirm");
					} else {
						System.out.print("Cancel");
					}

				} catch (InvalitFormException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidForm", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidBussinesLogicException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidBussines", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		deleteItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete current candidat?", "",
						JOptionPane.YES_NO_OPTION);
				if (confirm == 0) { // The ISSUE is here
					database.remove(currentid);
					button_refresh.doClick();
				} else {

				}

			}
		});

	}

	/**
	 * adauga un candidat in lista de candidati
	 * 
	 * @param candidat
	 */
	public static void addCandidat(Candidat candidat) {
		database.add(candidat);

	}

	/**
	 * creaza un model pentru tabel
	 * 
	 * @return
	 */

	/**
	 * seteaza parametrii tabelului
	 */
	private void makeTable() {

		table.setForeground(Color.black);
		Font font = new Font("", 1, 13);
		table.setFont(font);
		table.setRowHeight(25);

		setText(jBox.getSelectedItem().toString());
		model.setColumnIdentifiers(EN_columns);
		table.setModel(model);
	}

	/**
	 * Adauga pe fiecare linie cate un candidat. Daca searchFlag este True se adauga
	 * doar candidatii cautati; Daca seatchFlag este False se afiseaza toti
	 * candidati;
	 * 
	 */
	private void addDatatoTable() {

		if (searchFlag) {
			for (Candidat candidat : searched_Candidats) {

				addDatatoRow(candidat);

			}
		}

		else {

			for (Candidat candidat : database) {

				addDatatoRow(candidat);

			}
		}

	}

	/**
	 * Adauga toti candidatii gasiti intr.o lista
	 * 
	 * @param tempCandidat
	 */
	private void addDatatoSearchList(Candidat tempCandidat) {
		boolean nameFlag = tempCandidat.getNume().isBlank();
		boolean dateFlag = tempCandidat.getDataNasterii().isBlank();

		// if name is empty search by date ->0K
		if (!nameFlag && dateFlag) {
			for (Candidat candidat : database) {
				if (tempCandidat.getNume().compareTo(candidat.getNume()) == 0) {
					searched_Candidats.add(candidat);
				}
			}

		}
		if (nameFlag && !dateFlag) {
			for (Candidat candidat : database) {
				if (tempCandidat.getDataNasterii().compareTo(candidat.getDataNasterii()) == 0) {
					searched_Candidats.add(candidat);
				}
			}

		}
		if (!nameFlag && !dateFlag) {
			for (Candidat candidat : database) {
				if (tempCandidat.getDataNasterii().compareTo(candidat.getDataNasterii()) == 0
						&& tempCandidat.getNume().compareTo(candidat.getNume()) == 0) {
					searched_Candidats.add(candidat);
				}
			}

		}

		// if date is empty search by name->ok

		// if name and date is empty ->error

	}

	public void setText(String language) {

		locale = new Locale(language.toLowerCase(), language.toUpperCase());
		resourceBundle = ResourceBundle.getBundle("Config.Bundle", locale);
		button_add.setText(resourceBundle.getString(ADD_BUTTON));
		button_refresh.setText(resourceBundle.getString(REFRESH_BUTTON));
		button_save.setText(resourceBundle.getString(SAVE_BUTTON));
		button_generate.setText(resourceBundle.getString(ADD_RANDOM_BUTTON));
		button_search.setText(resourceBundle.getString(SEARCH_BUTTON));

		LName.setText(resourceBundle.getString(NAME_LABLE));
		LPrenume.setText(resourceBundle.getString(FIRST_NAME_LABEL));
		LData.setText(resourceBundle.getString(DATE_LABEL));
	}

	private void initComponents() {
		popupMenu = new JPopupMenu();
		showDetailsItem = new JMenuItem("Show Details");
		deleteItem = new JMenuItem("Delete");
		jBox = new JComboBox();
		generator = new RandomGenerator();
		table = new JTable();
		button_add = new JButton("Add");
		button_save = new JButton("Save");
		button_generate = new JButton("Add Random Candidats");
		button_search = new JButton("Search");
		button_refresh = new JButton("Refresh");
		LName = new JLabel("Name");
		numeField = new JTextField(5);
		LPrenume = new JLabel("First Name");
		prenumeField = new JTextField(5);
		LData = new JLabel("Date");
		dataField = new JTextField(5);
		pane = new JScrollPane(table);
		selectedCandidat = new Candidat();
		jBox.setBounds(1000, 50, 100, 25);
		LName.setBounds(20, 30, 185, 25);
		numeField.setBounds(20, 50, 185, 25);
		LPrenume.setBounds(220, 30, 185, 25);
		prenumeField.setBounds(220, 50, 185, 25);
		LData.setBounds(420, 30, 185, 25);
		dataField.setBounds(420, 50, 185, 25);
		button_search.setBounds(650, 50, 100, 25);
		button_add.setBounds(20, 430, 100, 40);
		button_generate.setBounds(1000, 430, 200, 90);
		button_save.setBounds(150, 430, 100, 40);
		button_refresh.setBounds(800, 50, 100, 25);
		pane.setBounds(0, 100, 1200, 300);
		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		jBox.addItem("EN");
		jBox.addItem("RO");

		add(jBox);
		add(LName);
		add(numeField);
		add(LPrenume);
		add(prenumeField);
		add(LData);
		add(dataField);
		add(pane);
		add(button_generate);
		add(button_add);
		add(button_search);
		add(button_save);
		add(button_refresh);

		setLayout(null);
		setSize(1280, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 
	 * Adauga pe un rand al tabelului datele candidatului
	 * 
	 * @param candidat
	 */
	private void addDatatoRow(Candidat candidat) {
		Object[] row = new Object[6];
		row[0] = candidat.getNume();
		row[1] = candidat.getPrenume();
		row[2] = candidat.getDataNasterii();
		row[3] = candidat.getNotaMatematica();
		row[4] = candidat.getNotaRomana();
		row[5] = candidat.getAdresa().adresatoString();
		model.addRow(row);
	}

	private void clearFilds() {
		numeField.setText(null);
		prenumeField.setText(null);
		dataField.setText(null);
	}

}
