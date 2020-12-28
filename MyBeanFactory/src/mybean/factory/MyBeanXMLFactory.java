package mybean.factory;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.print.DocFlavor.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MyBeanXMLFactory {

	public MyBeanXMLFactory() {
		super();
	}

	public List<HashMap<String, Object>> inputValues = null;
	public String packagePathNeeded="";

	
	public MyBeanXMLFactory(String configLoc,String id) {
		try {
			readXML(configLoc);
			Class<?> classneeded = getClassInfo(packagePathNeeded,id);
			Field[] field=classneeded.getFields();
			HashMap<String,Object> transformedData = new HashMap<String, Object>();
			for(int i=0;i<field.length;i++) {
				for(Map<String,Object> inputdata:inputValues) {
					if(inputdata.get(field[i].toString().split("\\.")[5].toLowerCase())!=null) {
						transformedData.put(field[i].toString().split("\\.")[5].toLowerCase(), inputdata.get(field[i].toString().split("\\.")[5].toLowerCase()));
					}
				}
			}
			transformedData.put("package-scan", packagePathNeeded);
			inputValues=new ArrayList<HashMap<String,Object>>();
			inputValues.add(transformedData);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
				  Element element = (Element)node;
				 NamedNodeMap attributes = element.getAttributes();
				 
			        int numAttrs = attributes.getLength();
					WeakHashMap<String, Object> tempmap = new WeakHashMap();

			        for (int i = 0; i < numAttrs; i++) {
			            Attr attr = (Attr) attributes.item(i);
			             
			            String attrName = attr.getNodeName();
			            String attrValue = attr.getNodeValue();
			            tempmap.put(attrName, attrValue);
			             
			        }		
			        inputmap=new HashMap<String, Object>();
			        inputmap.put((String) tempmap.get("key"), tempmap.get("value"));
			        if( inputmap.get("package-scan")!=null) {
			        packagePathNeeded=(String) inputmap.get("package-scan");
			        }
					inputValues.add(inputmap);

			}
			
		}
	}

	
	
	public <T> T getMyBean(String id, String scope) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IOException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		HashMap<String, Object> filteredMap = inputValues.get(0);
		if ("singleton".equals(scope)) {
			return createSingleTonBean(MyBeanXMLFactory.class.getClassLoader(), (String) filteredMap.get("package-scan"),
					filteredMap,id);
		} else {
			return createProtoTypeBeans(MyBeanXMLFactory.class.getClassLoader(), (String) filteredMap.get("package-scan"),
					filteredMap,id);
		}

	}


	private static <T> T createProtoTypeBeans(ClassLoader cl, String packagePath,
			HashMap<String, Object> filteredMap, String id)
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		filteredMap.remove("package-scan");
		Class<?> classinfo=getClassInfo(packagePath,id);
		return getAnyInstance(classinfo, filteredMap);
	}

	private static <T> T createSingleTonBean(ClassLoader cl, String packagePath,
			HashMap<String, Object> filteredMap, String id)
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		filteredMap.remove("package-scan");
		Class<?> classinfo=getClassInfo(packagePath,id);
		return new MyBeanSingleTonXMLFactory().getAnyInstance(classinfo, filteredMap);
	}
	
	public static Class<?> getClassInfo(String packagePath,String beanId) throws IOException, ClassNotFoundException{
		java.net.URL upackage = MyBeanXMLFactory.class.getClassLoader().getResource(packagePath);
		String dotted = packagePath.replaceAll("[/]", ".");
		URLConnection conn = upackage.openConnection();
		String paths = IOUtils.toString(conn.getInputStream(), "UTF-8");
		if (paths != null) {
			String[] pathsArr = paths.split("\n");
			for (int i = 0; i < pathsArr.length; i++) {
				if (beanId.equals(pathsArr[i].split("\\.")[0].toLowerCase())) {
					return Class.forName(dotted + "." + pathsArr[i].split("\\.")[0]);
					
				}

			}

		}
		return null;
		
	}
	
	public static <T> T getAnyInstance(Class<?> classs,Map<String,Object> input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Integer inputlength = input.size();

		LinkedList<String> fields = new LinkedList<String>();

		Field[] field = classs.getFields();
		for(int i=0;i<field.length;i++) {
			fields.add(field[i].toString().split("\\.")[5]);
		}
		Constructor<?>[] construtors=classs.getConstructors();
		Constructor<?> neededConstruct =null;
		for(Constructor<?> construct:construtors) {
			if(construct.getParameterCount()==inputlength) {
				neededConstruct=construct;
				break;
			}
		}
	

		LinkedList<T> values = new LinkedList<T>();
		for(String s:fields) {
			values.add((T) input.get(s.toLowerCase()));
		}
		Object[] initargs = new Object[values.size()];

		for(int i=0;i<values.size();i++) {
			initargs[i]=values.get(i);
		}
		return (T) neededConstruct.newInstance(initargs);
		
	}
}
