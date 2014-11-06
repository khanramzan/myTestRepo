package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-30T17:21:36.910+0000")
@StaticMetamodel(WeightCatagory.class)
public class WeightCatagory_ {
	public static volatile SingularAttribute<WeightCatagory, Long> idWeightCatagory;
	public static volatile SingularAttribute<WeightCatagory, String> weightCatagory;
	public static volatile SingularAttribute<WeightCatagory, String> weightCatagoryComments;
	public static volatile ListAttribute<WeightCatagory, Fight> fights;
}
