package configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Класс конфигурации для работы с свойствами
 * @author Ivan
 *
 */
public class Configuration extends Properties{

	private static final long serialVersionUID = -8592063196785809743L;

	/**
	 * Метод для принятия свойств из обьектов конфигураций
	 * @param configurations обьекты конфигураций
	 */
	public void appendConfiguration(Configuration... configurations) {
		for(Configuration c : configurations){
			Object[] keys = c.keySet().toArray();
			for(Object obj : keys){
				String key = (String) obj;
				String value = c.getProperty(key);
				if (this.containsKey(key)){
					this.setProperty(key, this.getProperty(key)+","+value);
				}else{
					this.setProperty(key, value);
				}
			}
		}
	}
	
	@Override
	public String toString(){		
		StringBuffer result = new StringBuffer("Configuration:\n");
			for(Object obj : this.keySet()){
				String key = (String) obj;
				result.append(key +" = " +this.getProperty(key) +"\n");
			}
		return result.toString();
	}
	
	/**
	 * Метод для сохранения текущей конфигурации в xml-файл
	 * @param path путь сохранения файла
	 */
	public void saveToXML(String path){
		Element result = new Element("head");
		Map<String, Element> nodes = new HashMap<String, Element>();
		
		for(Object obj : this.keySet()){
			 String [] keys = ((String) obj).split("\\.");
			for (int j = 0; j < keys.length; j++) {
				if (!nodes.containsKey(keys[j])){
					nodes.put(keys[j], new Element(keys[j]));
					if (j>0){
						nodes.get(keys[j-1]).addContent(nodes.get(keys[j]));
					}
				}
				
				if (j == keys.length-1) nodes.get(keys[j]).setText(this.getProperty((String) obj));
				result.setContent(nodes.get(keys[0]));
			}
		}
				
		XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
		try {
			xout.outputElementContent(result, new FileWriter(path));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
