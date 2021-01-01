package mybean.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import mybean.impl.Car;

public class MyBeanSingleTonXMLFactory {
	
	private static MyBeanSingleTonXMLFactory instance= new MyBeanSingleTonXMLFactory();
	
	private Map<Class,Object> mapbyclass = new HashMap<Class, Object>();
		
	
	public  static <T> T getAnyInstance(Class<?> classs,Map<String,Object> input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
		 synchronized(instance) {
		 if(!instance.mapbyclass.containsKey(classs)) {
			 T object =(T) neededConstruct.newInstance(initargs);
			 instance.mapbyclass.put(classs, object);
		 }
	
		return (T) instance.mapbyclass.get(classs);
		 }

		
	}

}
