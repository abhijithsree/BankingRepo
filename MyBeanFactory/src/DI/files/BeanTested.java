package DI.files;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import DI.annotations.BeanForAutoWire;
import DI.files.BeanConsumer;
import DI.files.BeanInjector;
import mybean.factory.MyBeanInstanceFactory;
import mybean.factory.MyBeanXMLFactory;


public class BeanTested {
	
	static BeanInjector beanInjector;
	
	@BeanForAutoWire
	public void setBeanInjector(BeanInjector beanInjector) {
		System.out.println("Bean Injector bean injected to Client"+beanInjector);
		this.beanInjector = beanInjector;
	}


	static {
		//Injecting needed class objects
		new MyBeanInstanceFactory("src/instanceconfig.xml");
	}
	public static void main(String[] args)  {

		BeanConsumer consumer = beanInjector.getInjectedConsumer("employee","singleton");
		consumer.consumer();
		
		//For testing
		BeanConsumer consumer1 = beanInjector.getInjectedConsumer("employee","singleton");
		consumer1.consumer();
	

	}

}
