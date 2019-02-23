package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kweet.class)
public abstract class Kweet_ {

	public static volatile SingularAttribute<Kweet, User> author;
	public static volatile ListAttribute<Kweet, User> mentions;
	public static volatile ListAttribute<Kweet, User> hearts;
	public static volatile SingularAttribute<Kweet, Integer> id;
	public static volatile SingularAttribute<Kweet, String> message;
	public static volatile ListAttribute<Kweet, Tag> tags;

}

