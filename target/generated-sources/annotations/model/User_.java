package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile ListAttribute<User, Kweet> mentionedIn;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> website;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile ListAttribute<User, User> followers;
	public static volatile ListAttribute<User, User> following;
	public static volatile SingularAttribute<User, String> bio;
	public static volatile SetAttribute<User, Kweet> kweets;
	public static volatile SingularAttribute<User, String> location;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> profilePicturePath;

}

