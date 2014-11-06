package rams.app.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:35:53.530+0000")
@StaticMetamodel(EventTable.class)
public class EventTable_ {
	public static volatile SingularAttribute<EventTable, Long> idEvent;
	public static volatile SingularAttribute<EventTable, String> eventName;
	public static volatile SingularAttribute<EventTable, Date> eventDate;
	public static volatile SingularAttribute<EventTable, String> eventPosterPath;
	public static volatile SingularAttribute<EventTable, String> eventcomments;
	public static volatile SingularAttribute<EventTable, Country> eventCountry;
	public static volatile SingularAttribute<EventTable, Promoter> eventPromoter;
	public static volatile CollectionAttribute<EventTable, Fight> fightCollection;
	public static volatile CollectionAttribute<EventTable, MediaRelation> mediaRelationCollection;
}
