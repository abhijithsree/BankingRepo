package DI.files;

import DI.annotations.BeanForAutoWire;

public class BeanConsumer<T> {

	public T mybean;

	@BeanForAutoWire
	public void setMybean(T mybean) {
		this.mybean = mybean;
		System.out.println("Bean is injected in consumer"+mybean);
	}



	public void consumer() {
		System.out.println("Consumer Got Bean: "+this.mybean);
		System.out.println(this.mybean.hashCode());

	}
	
}
