package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.905+0000")
@StaticMetamodel(Person.class)
public class Person_ {
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> fname;
	public static volatile SingularAttribute<Person, String> lname;
	public static volatile SingularAttribute<Person, Integer> age;
	public static volatile SingularAttribute<Person, Float> salary;
}
