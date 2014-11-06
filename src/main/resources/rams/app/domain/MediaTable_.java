package rams.app.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T18:20:09.695+0000")
@StaticMetamodel(MediaTable.class)
public class MediaTable_ {
	public static volatile SingularAttribute<MediaTable, Long> idMedia;
	public static volatile SingularAttribute<MediaTable, BigInteger> mediaType;
	public static volatile SingularAttribute<MediaTable, String> mediaName;
	public static volatile SingularAttribute<MediaTable, String> mediaPath;
	public static volatile SingularAttribute<MediaTable, byte[]> mediaOnServer;
	public static volatile CollectionAttribute<MediaTable, MediaRelation> mediaRelationCollection;
}
