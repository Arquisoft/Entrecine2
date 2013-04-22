package models;

import play.db.ebean.Model;
import play.db.jpa.JPA;

@SuppressWarnings("serial")
public abstract class ModeloPersistente extends Model {
	
	public ModeloPersistente() {
		super();
	}

	public void update() {
		JPA.em().merge(this);
	}

	public void save() {
		JPA.em().persist(this);
	}

	public void delete() {
		JPA.em().remove(this);
	}
}
