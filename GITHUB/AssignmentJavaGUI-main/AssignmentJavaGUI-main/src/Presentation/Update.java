package Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Business.CovidCase;
import Data.CovidUpdater;
import Data.CovidWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Update extends JFrame {

	private JPanel contentPane;
	private JTextField txtDateUpdate;
	private JTextField txtCityUpdate;
	private JTextField txtCases;
	private JTextField txtDeaths;
	private JTextField txtRecoveries;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Update() {
		setTitle("Update Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lblDateUpdate = new JLabel("Date: ");
		contentPane.add(lblDateUpdate);
		
		txtDateUpdate = new JTextField();
		txtDateUpdate.setText("mm/dd/yyyy");
		contentPane.add(txtDateUpdate);
		txtDateUpdate.setColumns(10);
		
		JLabel lblCityUpdate = new JLabel("City: ");
		contentPane.add(lblCityUpdate);
		
		txtCityUpdate = new JTextField();
		txtCityUpdate.setColumns(10);
		contentPane.add(txtCityUpdate);
		
		JLabel lblCases = new JLabel("Number of cases: ");
		contentPane.add(lblCases);
		
		txtCases = new JTextField();
		txtCases.setEnabled(false);
		contentPane.add(txtCases);
		txtCases.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = txtDateUpdate.getText();
				String city = txtCityUpdate.getText();
				String cases = txtCases.getText();
				String deaths = txtDeaths.getText();
				String recoveries = txtRecoveries.getText();
				
				CovidCase covidCase = new CovidCase(date, city, 0, 0, 0);
				
				if (covidCase.setCases(cases) && covidCase.setNumDeaths(deaths) && covidCase.setNumRecov(recoveries)) 
				{
					CovidUpdater cu = new CovidUpdater();
					cu.updateData(covidCase);
					JOptionPane.showMessageDialog(null, "Case is updated.");
					
					txtCases.setText("");
					txtDeaths.setText("");
					txtRecoveries.setText("");
					txtDateUpdate.setText("");
					txtCityUpdate.setText("");
					
					btnUpdate.setEnabled(false);
					txtCases.setEnabled(false);
					txtDeaths.setEnabled(false);
					txtRecoveries.setEnabled(false);
					txtDateUpdate.setEnabled(true);
					txtCityUpdate.setEnabled(true);
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Number of cases, deaths and recoveries must "
							+ "be positive numbers or 0.");
				}
			}
		});
		
		JLabel lblDeaths = new JLabel("Number of deaths: ");
		contentPane.add(lblDeaths);
		
		txtDeaths = new JTextField();
		txtDeaths.setEnabled(false);
		contentPane.add(txtDeaths);
		txtDeaths.setColumns(10);
		
		JLabel lblRecoveries = new JLabel("Number of recoveries: ");
		contentPane.add(lblRecoveries);
		
		txtRecoveries = new JTextField();
		txtRecoveries.setEnabled(false);
		contentPane.add(txtRecoveries);
		txtRecoveries.setColumns(10);
		contentPane.add(btnUpdate);
		
		JButton btnFindCase = new JButton("Find Case");
		btnFindCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String date = txtDateUpdate.getText();
				String city = txtCityUpdate.getText();
				CovidCase covidCase = new CovidCase(date, city, 0, 0, 0);
				CovidWriter cw = new CovidWriter();
				
				if (cw.isCaseAlreadyStored(covidCase)) 
				{
					txtCases.setEnabled(true);
					txtDeaths.setEnabled(true);
					txtRecoveries.setEnabled(true);
					txtDateUpdate.setEnabled(false);
					txtCityUpdate.setEnabled(false);
					btnUpdate.setEnabled(true);
				}	
				else 
				{
					JOptionPane.showMessageDialog(null, "This case is not stored in a file therefore you "
							+ "cannot update it.");
				}
			}
		});
		contentPane.add(btnFindCase);
	}

}
