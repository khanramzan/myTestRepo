package rams.app.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.897+0000")
@StaticMetamodel(EvtTbl.class)
public class EvtTbl_ {
	public static volatile SingularAttribute<EvtTbl, Long> idEvent;
	public static volatile SingularAttribute<EvtTbl, String> eventcomments;
	public static volatile SingularAttribute<EvtTbl, Date> eventDate;
	public static volatile SingularAttribute<EvtTbl, String> eventName;
	public static volatile SingularAttribute<EvtTbl, String> eventPosterPath;
	public static volatile SingularAttribute<EvtTbl, Promoter> promoter;
	public static volatile SingularAttribute<EvtTbl, Country> countryBean;
	public static volatile ListAttribute<EvtTbl, Fight> fights;
	public static volatile ListAttribute<EvtTbl, MediaRelation> mediaRelations;
}
