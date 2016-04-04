package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import businesslogic.Ft;
import model.Bus;
import model.City;
import model.ConstantsCity;
import model.Route;
import model.Table;
import ua.ii.db.DBAccessor;
import ua.iih.graphics.Graphic;

// BE CAREFUL, MAKE BACKUPS
public class Program {
	
	@SuppressWarnings("finally")
	public static String[] readFromFileLines(String FileName){
		File file = new File(FileName);
		if (!file.exists()){
			try {
				throw new FileNotFoundException(file.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else{
			ArrayList<String> sb = new ArrayList<String>();
			try{
				BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
				try{
					String s;
					while( (s = in.readLine()) != null){
						sb.add(s);
					}
				} finally{
					in.close();
				}
			} catch(IOException e){
				throw new RuntimeException(e);
			} finally{
				return sb.toArray(new String[0]);
			}
		}
		return null;
	}

	public static void main_asd(String[] args) throws InstantiationException, IllegalAccessException {
		DBAccessor db = new DBAccessor();
		db.open();
		
		db.insert(new ConstantsCity(1, "2011", 1000));
		db.insert(new ConstantsCity(1, "2012", 1500));
		db.insert(new ConstantsCity(2, "2011", 750));
		db.insert(new ConstantsCity(2, "2012", 500));
//		db.insert(new City("Kiev", 1));
//		db.insert(new City("Chernigov", 1));
//		db.insert(new Route(1, 2, 0.5, 0.6, 0.7));
		
		
		
		db.close();
	}
	
	public static void main_db(String[] args) {
		DBAccessor db = new DBAccessor();
		db.open();
		File file = new File("src/KCH2.txt");// CHK
		
		if (!file.exists()){
			try {
				throw new FileNotFoundException(file.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			try {
				BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
				try{
					String s;
					while( (s = in.readLine()) != null && !s.isEmpty()){
						String[] values = s.split("\t");
// TEMPORARY NOT WORKING
						String [] dateNums = values[0].split("\\.");
						String newDate = String.format("%s-%s-%s", dateNums[2], dateNums[1], dateNums[0]);
						db.insert(new Table(newDate, values[1], 1, Integer.valueOf(values[2])));// swap Kiev and Chernigov
					}
				} finally{
					in.close();
				}
			} catch(IOException e){
				throw new RuntimeException(e);
			}
		}

		db.close();
	}

	public static void main_Sel(String[] args) {
		DBAccessor db = new DBAccessor();
		db.open();
		List<City> cities = db.select(City.class);
		db.close();
		Ft f = new Ft(cities.get(0), cities.get(1), "2011-05-01", 1);
		System.out.println(f.calcG(0.5));
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		double [] y = new double[15];
		for (int i = 0; i < y.length; i++) {
			y[i] = 30 + r.nextDouble()*50;
		}
		String [] X = new String[15];
		Calendar cal = new GregorianCalendar();
		cal.set(2011, 1, 1);
		for (int i = 0; i < X.length; i++) {
			X[i] = String.format("%d.%d.%d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
			cal.add(cal.get(3), 1);
		}
		X[14] = "15.1.2011"; //костыль
		
		Graphic.paint(y, X);
	}
}
