package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Table;
import ua.ii.db.DBAccessor;

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

	public static void main(String[] args) {
		DBAccessor db = new DBAccessor();
		db.open();
		File file = new File("src/KCH.txt");// CHK
		
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
					int id = 1428;//0
					while( (s = in.readLine()) != null && !s.isEmpty()){
						String[] values = s.split("\t");
// TEMPORARY NOT WORKING
//						db.insert(new Table(id, values[0], values[1], Byte.valueOf(values[2]), values[3],
//								"Київ", "Чернігів", Integer.valueOf(values[4])));// swap Kiev and Chernigov
						id++;
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

}
