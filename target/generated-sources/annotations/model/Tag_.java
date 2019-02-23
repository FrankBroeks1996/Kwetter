package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ {

	public static volatile SingularAttribute<Tag, String> name;
	public static volatile ListAttribute<Tag, Kweet> kweetsWithTag;
	public static volatile SingularAttribute<Tag, Integer> id;

}

