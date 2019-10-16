/**
 * 
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DTO.Adresa;
import DTO.Candidat;
import Exceptions.InvalidBussinesLogicException;
import Exceptions.InvalidGradeException;
import Exceptions.InvalitFormException;
import Validation.BusinessValidation;
import Validation.FormValidation;

/**
 * @author Yllub-pc
 * @date Jul 11, 2019 8:44:26 PM
 * @Swing_Project
 * @AdaugareCandidatiExamen.java
 */
public class AdaugareCandidatiExamen extends JFrame {

	public static String ADDRESS = "address";
	public static String DISTRICT = "district";
	public static String TOWN = "town";
	public static String MATH_GRADE = "math";
	public static String LANG_GRADE = "lang";
	public static String HEADER = "title";

	private Adresa adresa;
	private Candidat candidat;

	private JLabel header;
	private JButton addButton;
	private JLabel LName;
	private JTextField numeField;
	private JLabel LPrenume;
	private JTextField prenumeField;
	private JLabel LData;
	private JTextField dataField;
	private JLabel LAdresa;
	private JTextField adresaField;
	private JTextField judetField;
	private JTextField OrasField;
	private JLabel Ljudet;
	private JLabel LOras;
	private JLabel LNota_mate;
	private JTextField notaMateField;
	private JLabel LNota_Romana;
	private JTextField notaRomanaField;

	public AdaugareCandidatiExamen() {
		super("ADAUGA  CANDIDATI");

		initComponents();
		setText();

		addButton.addActionListener(new ActionListener() {

			/**
			 * La apasarea butonului Adauga: Sunt setati proprietatile obiectului Candidat
			 * cu valorile luate din TextFilds corespunzatoare Se verifica daca Candidatul
			 * imdeplineste ValidateaFormala si Validarea de Bussines daca nu se arunca
			 * excepti. Daca satisface candidatul este adaugat in List de candidati din Main
			 * care este statica. Se revine la fereastra MainFrame
			 *
			 *
			 *
			 */
			public void actionPerformed(ActionEvent e) {

				try {

					candidat.setNume(numeField.getText());
					candidat.setPrenume(prenumeField.getText());
					candidat.setDataNasterii(dataField.getText());
					candidat.setNotaMatematica(Float.parseFloat(notaMateField.getText()));
					candidat.setNotaRomana(Float.parseFloat(notaRomanaField.getText()));
					adresa.setJudet(judetField.getText());
					adresa.setOra(OrasField.getText());
					adresa.setStrada(adresaField.getText());
					candidat.setAdresa(adresa);

					FormValidation.ValidForm(candidat);
					BusinessValidation.validBussines(candidat);

					BusinessValidation.validGrade(candidat.getNotaMatematica(), candidat.getNotaRomana());

					MainFrame.addCandidat(candidat);
					MainFrame.button_refresh.doClick();
					dispose();

				} catch (InvalitFormException e1) {

					numeField.setText(null);
					prenumeField.setText(null);
					dataField.setText(null);
					notaMateField.setText(null);
					notaRomanaField.setText(null);

					System.err.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidFormat", JOptionPane.ERROR_MESSAGE);

				} catch (InvalidBussinesLogicException e1) {

					System.err.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidBussines", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidGradeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "InvalidGrade", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					notaMateField.setText(null);
					notaRomanaField.setText(null);
					JOptionPane.showMessageDialog(null, "Campul nota nu poate remane necompletat", "InvalidGrade",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		/**
		 * Adaugarea componentelor in fereastra
		 */

	}

	public void initComponents() {

		candidat = new Candidat();
		adresa = new Adresa();

		/**
		 * Declararea componentelor
		 * 
		 */

		header = new JLabel("Adauga Elev");

		addButton = new JButton("Adauga");
		LName = new JLabel("Nume");
		numeField = new JTextField(5);
		LPrenume = new JLabel("Prenume");
		prenumeField = new JTextField(5);
		LData = new JLabel("Data Nasterii");
		dataField = new JTextField(5);
		LAdresa = new JLabel("Adresa");
		adresaField = new JTextField(5);
		judetField = new JTextField(5);
		OrasField = new JTextField(5);
		Ljudet = new JLabel("Judet");
		LOras = new JLabel("Oras");
		LNota_mate = new JLabel("Matematica");
		notaMateField = new JTextField(5);
		LNota_Romana = new JLabel("Romana");
		notaRomanaField = new JTextField(5);

		header.setBounds(20, 15, 270, 65);

		addButton.setBounds(430, 355, 105, 35);
		LName.setBounds(20, 80, 185, 25);
		numeField.setBounds(20, 105, 185, 25);
		LPrenume.setBounds(20, 130, 185, 25);
		prenumeField.setBounds(20, 150, 185, 25);
		LData.setBounds(20, 175, 185, 25);
		dataField.setBounds(20, 195, 185, 25);
		LAdresa.setBounds(230, 170, 185, 25);
		adresaField.setBounds(230, 195, 290, 25);
		judetField.setBounds(230, 150, 100, 25);
		OrasField.setBounds(335, 150, 100, 25);
		Ljudet.setBounds(230, 125, 100, 25);
		LOras.setBounds(335, 125, 100, 25);
		LNota_mate.setBounds(20, 250, 100, 25);
		notaMateField.setBounds(20, 275, 100, 25);
		LNota_Romana.setBounds(20, 300, 100, 25);
		notaRomanaField.setBounds(20, 320, 100, 25);

		add(header);

		add(addButton);
		add(LName);
		add(numeField);
		add(LPrenume);
		add(prenumeField);
		add(LData);
		add(dataField);
		add(LAdresa);
		add(adresaField);
		add(judetField);
		add(OrasField);
		add(Ljudet);
		add(LOras);
		add(LNota_mate);
		add(notaMateField);
		add(LNota_Romana);
		add(notaRomanaField);

		setSize(700, 480);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public void setText() {

		addButton.setText(MainFrame.resourceBundle.getString(MainFrame.ADD_BUTTON));

		LName.setText(MainFrame.resourceBundle.getString(MainFrame.NAME_LABLE));
		LPrenume.setText(MainFrame.resourceBundle.getString(MainFrame.FIRST_NAME_LABEL));
		LData.setText(MainFrame.resourceBundle.getString(MainFrame.DATE_LABEL));
		LNota_mate.setText(MainFrame.resourceBundle.getString(MATH_GRADE));
		LNota_Romana.setText(MainFrame.resourceBundle.getString(LANG_GRADE));
		LAdresa.setText(MainFrame.resourceBundle.getString(ADDRESS));
		header.setText(MainFrame.resourceBundle.getString(HEADER));
		LOras.setText(MainFrame.resourceBundle.getString(TOWN));
		Ljudet.setText(MainFrame.resourceBundle.getString(DISTRICT));

	}
}
