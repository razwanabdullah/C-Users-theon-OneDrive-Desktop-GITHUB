package Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PrintData extends JFrame {

	private JPanel contentPane;
	private JTextField txtDatePrint;
	private JTextField txtCityPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintData frame = new PrintData();
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
	public PrintData() {
		setTitle("Print COVID Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrintDate = new JLabel("Date: ");
		lblPrintDate.setBounds(35, 28, 61, 16);
		contentPane.add(lblPrintDate);
		
		JLabel lblCityPrint = new JLabel("City: ");
		lblCityPrint.setBounds(35, 73, 61, 16);
		contentPane.add(lblCityPrint);
		
		txtDatePrint = new JTextField();
		txtDatePrint.setBounds(133, 23, 229, 26);
		contentPane.add(txtDatePrint);
		txtDatePrint.setColumns(10);
		
		txtCityPrint = new JTextField();
		txtCityPrint.setColumns(10);
		txtCityPrint.setBounds(133, 68, 229, 26);
		contentPane.add(txtCityPrint);
		
		JButton btnPrintWhole = new JButton("Print whole data");
		btnPrintWhole.setBounds(35, 169, 145, 46);
		contentPane.add(btnPrintWhole);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(288, 169, 126, 38);
		contentPane.add(btnPrint);
	}
}
