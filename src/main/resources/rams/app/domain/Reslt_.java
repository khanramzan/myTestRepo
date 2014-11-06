package rams.app.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.907+0000")
@StaticMetamodel(Reslt.class)
public class Reslt_ {
	public static volatile SingularAttribute<Reslt, Long> idReslt;
	public static volatile SingularAttribute<Reslt, String> announcedResult;
	public static volatile SingularAttribute<Reslt, String> resultComment;
	public static volatile SingularAttribute<Reslt, BigInteger> winner;
	public static volatile ListAttribute<Reslt, MediaRelation> mediaRelations;
	public static volatile SingularAttribute<Reslt, Fight> fight;
}
