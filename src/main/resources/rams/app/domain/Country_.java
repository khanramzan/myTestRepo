package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.896+0000")
@StaticMetamodel(Country.class)
public class Country_ {
	public static volatile SingularAttribute<Country, String> code;
	public static volatile SingularAttribute<Country, String> continent;
	public static volatile SingularAttribute<Country, String> name;
	public static volatile ListAttribute<Country, City> cities;
	public static volatile ListAttribute<Country, EvtTbl> evtTbls;
	public static volatile ListAttribute<Country, Fighter> fighters;
	public static volatile ListAttribute<Country, Promoter> promoters;
	public static volatile ListAttribute<Country, Sanctioner> sanctioners;
}
