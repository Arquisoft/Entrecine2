package infrastructure;

import persistence.PersistenceService;
import persistence.impl.PersistenceServiceJpa;

public class Factories {

	public static PersistenceService persistence = new PersistenceServiceJpa();
}
