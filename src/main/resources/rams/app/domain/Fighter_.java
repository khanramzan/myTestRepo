package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.899+0000")
@StaticMetamodel(Fighter.class)
public class Fighter_ {
	public static volatile SingularAttribute<Fighter, Long> idFighter;
	public static volatile SingularAttribute<Fighter, String> comments;
	public static volatile SingularAttribute<Fighter, String> fighterClub;
	public static volatile SingularAttribute<Fighter, String> fighterName;
	public static volatile SingularAttribute<Fighter, String> fighterProfilePicPath;
	public static volatile ListAttribute<Fighter, Fight> fights1;
	public static volatile ListAttribute<Fighter, Fight> fights2;
	public static volatile SingularAttribute<Fighter, Country> countryBean;
	public static volatile ListAttribute<Fighter, MediaRelation> mediaRelations;
}
