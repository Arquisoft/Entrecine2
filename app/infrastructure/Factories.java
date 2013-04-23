package infrastructure;

import persistence.PersistenceService;
import persistence.impl.PersistenceServiceEbean;

public class Factories {

	public static PersistenceService persistence = new PersistenceServiceEbean();
}
