package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.898+0000")
@StaticMetamodel(Fight.class)
public class Fight_ {
	public static volatile SingularAttribute<Fight, Long> idFight;
	public static volatile SingularAttribute<Fight, String> fightcomments;
	public static volatile SingularAttribute<Fight, EvtTbl> evtTbl;
	public static volatile SingularAttribute<Fight, Fighter> fighter1Bean;
	public static volatile SingularAttribute<Fight, Fighter> fighter2Bean;
	public static volatile SingularAttribute<Fight, SanctionFight> sanctionFight;
	public static volatile SingularAttribute<Fight, WeightCatagory> weightCatagory;
	public static volatile ListAttribute<Fight, MediaRelation> mediaRelations;
	public static volatile SingularAttribute<Fight, Reslt> reslt;
}
