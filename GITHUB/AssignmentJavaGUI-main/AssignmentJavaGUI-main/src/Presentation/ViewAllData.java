package Presentation;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Business.City;
import Data.fileData;

public class ViewAllData extends JInternalFrame{
	public ViewAllData() {
		this.initialize();
	}
	
	protected void initialize() {
		this.setTitle("All Data View");
		this.setSize(450, 300);
		this.setClosable(true);
		this.setIconifiable(true);
		
		
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Date of reporting");
		model.addColumn("Name of City");
		model.addColumn("Number of Cases");
		model.addColumn("Number of Deaths");
		model.addColumn("Number of Recoveries");
		
		City[] c= fileData.readData();
		
		for(int i = 0; i < c.length; i++){
				
			 Object[] rowData = new Object[]{
				        c[i].getDate(),
				        c[i].getCity(),
				       c[i].getCases(),
				        c[i].getDeaths(),
				        c[i].getRecoveries()
				
		        };
			model.addRow(rowData);
		}
		JTable table = new JTable(model);
		
		this.add(new JScrollPane(table));
		
	}

	private void readData() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
