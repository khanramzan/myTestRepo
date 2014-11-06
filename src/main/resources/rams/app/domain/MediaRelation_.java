package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.903+0000")
@StaticMetamodel(MediaRelation.class)
public class MediaRelation_ {
	public static volatile SingularAttribute<MediaRelation, Long> idMediaRelation;
	public static volatile SingularAttribute<MediaRelation, String> mediaComments;
	public static volatile SingularAttribute<MediaRelation, Fighter> fighter;
	public static volatile SingularAttribute<MediaRelation, EvtTbl> evtTbl;
	public static volatile SingularAttribute<MediaRelation, Fight> fight;
	public static volatile SingularAttribute<MediaRelation, Sanctioner> sanctioner;
	public static volatile SingularAttribute<MediaRelation, Promoter> promoter;
	public static volatile SingularAttribute<MediaRelation, Reslt> reslt;
	public static volatile SingularAttribute<MediaRelation, MediaTbl> mediaTbl;
}
