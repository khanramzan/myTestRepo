package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.906+0000")
@StaticMetamodel(Promoter.class)
public class Promoter_ {
	public static volatile SingularAttribute<Promoter, Long> idPromoter;
	public static volatile SingularAttribute<Promoter, String> promoterAddress;
	public static volatile SingularAttribute<Promoter, String> promoterComments;
	public static volatile SingularAttribute<Promoter, String> promoterName;
	public static volatile SingularAttribute<Promoter, String> promoterPosterPath;
	public static volatile ListAttribute<Promoter, EvtTbl> evtTbls;
	public static volatile ListAttribute<Promoter, MediaRelation> mediaRelations;
	public static volatile SingularAttribute<Promoter, Country> countryBean;
}
