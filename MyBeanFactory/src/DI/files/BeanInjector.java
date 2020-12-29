package DI.files;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import DI.annotations.BeanForAutoWire;
import mybean.factory.MyBeanInstanceFactory;
import mybean.factory.MyBeanXMLFactory;

public class BeanInjector {

	static BeanConsumer beanConsumer;
	
	@BeanForAutoWire
	public static void setBeanConsumer(BeanConsumer beanConsumer) {
		System.out.println("BeanConsumer injected to BeanInjector"+beanConsumer);
		BeanInjector.beanConsumer = beanConsumer;
	}



	public BeanConsumer getInjectedConsumer(String beanName, String scope) {
		MyBeanXMLFactory beanFactory=	new MyBeanXMLFactory("src/myconfig.xml",beanName);
		try {
			beanConsumer.setMybean(beanFactory.getMyBean(beanName,scope));
		} catch (SecurityException | IllegalArgumentException | InvocationTargetException | IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Bean Creation Failed");
		}
		return beanConsumer;
		
	}
}
