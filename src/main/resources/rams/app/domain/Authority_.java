package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.774+0000")
@StaticMetamodel(Authority.class)
public class Authority_ {
	public static volatile SingularAttribute<Authority, Long> idAuthorities;
	public static volatile SingularAttribute<Authority, String> authority;
	public static volatile SingularAttribute<Authority, User> user;
}
