package mybean.factory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DI.annotations.BeanForAutoWire;
import DI.files.BeanConsumer;
import DI.files.BeanInjector;
import mybean.impl.Car;

public class MyBeanInstanceFactory {

	static MyBeanXMLFactory xmlFactory;

	@BeanForAutoWire
	public void setXmlFactory(MyBeanXMLFactory xmlFactory) {
		this.xmlFactory = xmlFactory;
	}

	static {
		
		try {
			getAnyInstance(MyBeanInstanceFactory.class, MyBeanXMLFactory.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public List<HashMap<String, Object>> inputValues = null;
	
	public String packagePathNeeded = "";


	public MyBeanInstanceFactory(String configLoc) {
		try {
			readXML(configLoc);
			for (HashMap<String, Object> inputmap : inputValues) {
				for(Map.Entry<String, Object> inputentry:inputmap.entrySet() ) {
					if(inputentry.getKey()!=null && inputentry.getValue()!=null) {
				Class<?> classService = xmlFactory.getClassInfo(packagePathNeeded, (String) inputentry.getValue());
				Class<?> classTarget = xmlFactory.getClassInfo(packagePathNeeded, inputentry.getKey());
				getAnyInstance(classService, classTarget);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static <T> T getAnyInstance(Class<?> classs, Class<?> classsforinjects)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?>[] construtors = classs.getConstructors();
		Constructor<?>[] injectconstructors = classsforinjects.getConstructors();
		int j = 0;
		for (int i = 0; i < construtors.length; i++) {
			try {
				if (construtors[i].toString().split("\\(")[1].split("\\)")[0] != null) {

				}
			} catch (Exception e) {
				j = i;
			}
		}
		int k = 0;
		for (int i = 0; i < injectconstructors.length; i++) {
			try {
				if (injectconstructors[i].toString().split("\\(")[1].split("\\)")[0] != null) {

				}
			} catch (Exception e) {
				k = i;
			}
		}
		Method[] method = classs.getMethods();
		for (int i = 0; i < method.length; i++) {
			if (method[i].getAnnotation(BeanForAutoWire.class) != null) {
				return (T) method[i].invoke(construtors[j].newInstance(),
						classsforinjects.getConstructors()[k].newInstance());
			}
		}
		return null;

	}

	public MyBeanInstanceFactory() {
		super();
	}

	public void readXML(String configLoc) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(configLoc);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("bean");
		inputValues = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> inputmap = null;
		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NamedNodeMap attributes = element.getAttributes();

				int numAttrs = attributes.getLength();
				WeakHashMap<String, Object> tempmap = new WeakHashMap();

				for (int i = 0; i < numAttrs; i++) {
					Attr attr = (Attr) attributes.item(i);

					String attrName = attr.getNodeName();
					String attrValue = attr.getNodeValue();
					tempmap.put(attrName, attrValue);

				}
				inputmap = new HashMap<String, Object>();
				inputmap.put((String) tempmap.get("service"), tempmap.get("consumer"));
				if ((String) tempmap.get("key") != null && (String) tempmap.get("value") != null) {
					inputmap.put((String) tempmap.get("key"), tempmap.get("value"));
				}

				if (inputmap.get("package-scan") != null) {
					packagePathNeeded = (String) inputmap.get("package-scan");
					inputmap.remove("package-scan");
				}
				inputValues.add(inputmap);

			}

		}
	}

}
