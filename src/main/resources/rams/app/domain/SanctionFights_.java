package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T12:50:58.288+0000")
@StaticMetamodel(SanctionFights.class)
public class SanctionFights_ {
	public static volatile SingularAttribute<SanctionFights, Long> idSanctionFights;
	public static volatile ListAttribute<SanctionFights, Fight> fightList;
	public static volatile SingularAttribute<SanctionFights, Sanctioner> sanctioner;
	public static volatile SingularAttribute<SanctionFights, FightTypes> fightType;
}
