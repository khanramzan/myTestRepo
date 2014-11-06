package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.901+0000")
@StaticMetamodel(FightType.class)
public class FightType_ {
	public static volatile SingularAttribute<FightType, Long> idFightTypes;
	public static volatile SingularAttribute<FightType, String> fightType;
	public static volatile SingularAttribute<FightType, String> fightTypeDescription;
	public static volatile ListAttribute<FightType, SanctionFight> sanctionFights;
}
