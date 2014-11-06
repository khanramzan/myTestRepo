package rams.app.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.904+0000")
@StaticMetamodel(MediaTbl.class)
public class MediaTbl_ {
	public static volatile SingularAttribute<MediaTbl, Long> idMedia;
	public static volatile SingularAttribute<MediaTbl, String> mediaName;
	public static volatile SingularAttribute<MediaTbl, byte[]> mediaOnServer;
	public static volatile SingularAttribute<MediaTbl, String> mediaPath;
	public static volatile SingularAttribute<MediaTbl, BigInteger> mediaType;
	public static volatile ListAttribute<MediaTbl, MediaRelation> mediaRelations;
}
