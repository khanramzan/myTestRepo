package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.908+0000")
@StaticMetamodel(SanctionFight.class)
public class SanctionFight_ {
	public static volatile SingularAttribute<SanctionFight, Long> idSanctionFights;
	public static volatile ListAttribute<SanctionFight, Fight> fights;
	public static volatile SingularAttribute<SanctionFight, FightType> fightTypeBean;
	public static volatile SingularAttribute<SanctionFight, Sanctioner> sanctionerBean;
}
