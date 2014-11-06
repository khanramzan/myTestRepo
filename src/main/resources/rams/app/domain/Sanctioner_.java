package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.907+0000")
@StaticMetamodel(Sanctioner.class)
public class Sanctioner_ {
	public static volatile SingularAttribute<Sanctioner, Long> idSanctioner;
	public static volatile SingularAttribute<Sanctioner, String> sanctionerAddress;
	public static volatile SingularAttribute<Sanctioner, String> sanctionercomments;
	public static volatile SingularAttribute<Sanctioner, String> sanctionerLogoPath;
	public static volatile SingularAttribute<Sanctioner, String> sanctionerName;
	public static volatile ListAttribute<Sanctioner, MediaRelation> mediaRelations;
	public static volatile ListAttribute<Sanctioner, SanctionFight> sanctionFights;
	public static volatile SingularAttribute<Sanctioner, Country> countryBean;
}
