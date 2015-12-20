package configuration;

import java.io.File;
import java.io.IOException;
/**
 * ����� ��� �������� ����� ������������
 * @author Ivan
 *
 */
public class ConfigLoader {

	/**
	 * ����� ��������� � ������ ���������������� ����
	 * @param obj ���� ������������ (.xml)
	 * @return ������ ������������
	 */
	public Configuration loadXMLConfig(Object obj){
		
		if (obj instanceof File){
			if (((File)obj).getName().endsWith(".xml")){
				try {
					return new XMLReader((File)obj).parse();
				} catch (IOException e) {e.printStackTrace();}
			}
			else{
				System.out.println("Wrong file type");
				return null;
			}
		}
		else{
			System.out.println("Wrong object, you must load a file");
			return null;
		}
		return null;
	}
}
