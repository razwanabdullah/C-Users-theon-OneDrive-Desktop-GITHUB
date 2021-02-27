package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Business.CovidCase;

public class CovidUpdater {
	
	String row = "";
	private static final String FILE_PATH = "src/covidData.txt";
	List<String> lines = new ArrayList<>();
	String[] extractedCityAndDate;
	CovidWriter cw = new CovidWriter();
	
	public boolean updateData(CovidCase c) {
		
		if (cw.isCaseAlreadyStored(c)) {
			
			try(FileReader fr = new FileReader(FILE_PATH);
					BufferedReader br = new BufferedReader(fr)) {
					
					while((row = br.readLine()) != null)
					{
						lines.add(row + "\n");
					}
			} catch (IOException e) {
					System.out.println(e.getStackTrace());
			}
			
			for (String line : lines) {
				extractedCityAndDate = getDataFromRow(line);
				
				if (extractedCityAndDate[0].equals(c.getDate()) && extractedCityAndDate[1].equals(c.getCity())) {
					String newRow = c.getDate() + " " + c.getCity() + " " + c.getCases() + " " + c.getDeaths() + " " 
				+ c.getRecoveries() + "\n";
					
					lines.set(lines.indexOf(line), newRow);
					break;
				}
			}
			
			try (FileWriter fw = new FileWriter(FILE_PATH);
				 BufferedWriter bw = new BufferedWriter(fw)) {
				
				for(String line : lines) {
					bw.write(line);
				}
			} catch(IOException e) {
				System.out.println(e.getStackTrace());
			}
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public static String[] getDataFromRow(String row) {
		String[] data = {"", "", "", "", ""};
		int index = 0;
		
		for (char c : row.toCharArray()) {
			if (c != ' ')
			{
				data[index] += c;
			}
			else
			{
				index++;
				if (index > 4) 
				{
					break;
				}
			}
		}
		return data;
	}
}
