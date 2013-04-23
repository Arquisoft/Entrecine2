package infrastructure;

import business.BusinessFactory;
import business.impl.BusinessFactoryImpl;

public class Factories {

	public static BusinessFactory business = new BusinessFactoryImpl();
}
