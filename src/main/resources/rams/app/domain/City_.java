package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.895+0000")
@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Integer> id;
	public static volatile SingularAttribute<City, String> district;
	public static volatile SingularAttribute<City, String> name;
	public static volatile SingularAttribute<City, Country> country;
}
