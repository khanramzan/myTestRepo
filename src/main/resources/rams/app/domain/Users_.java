package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T12:50:58.290+0000")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> username;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, Boolean> enabled;
	public static volatile ListAttribute<Users, Authorities> authoritiesList;
}
