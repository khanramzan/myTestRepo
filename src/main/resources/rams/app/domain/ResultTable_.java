package rams.app.domain;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T17:35:53.541+0000")
@StaticMetamodel(ResultTable.class)
public class ResultTable_ {
	public static volatile SingularAttribute<ResultTable, Long> idReslt;
	public static volatile SingularAttribute<ResultTable, String> announcedResult;
	public static volatile SingularAttribute<ResultTable, BigInteger> winner;
	public static volatile SingularAttribute<ResultTable, String> resultComment;
	public static volatile SingularAttribute<ResultTable, Fight> fight;
	public static volatile CollectionAttribute<ResultTable, MediaRelation> mediaRelationCollection;
}
