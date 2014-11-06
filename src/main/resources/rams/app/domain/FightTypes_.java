package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T12:50:58.282+0000")
@StaticMetamodel(FightTypes.class)
public class FightTypes_ {
	public static volatile SingularAttribute<FightTypes, Long> idFightTypes;
	public static volatile SingularAttribute<FightTypes, String> fightType;
	public static volatile SingularAttribute<FightTypes, String> fightTypeDescription;
	public static volatile ListAttribute<FightTypes, SanctionFights> sanctionFightsList;
}
