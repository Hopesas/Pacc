package Entity;

import Entity.ActividadesEspecificas;
import Entity.Plan;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ActividadesPlan.class)
public class ActividadesPlan_ { 

    public static volatile SingularAttribute<ActividadesPlan, BigDecimal> id;
    public static volatile SingularAttribute<ActividadesPlan, Plan> idPlan;
    public static volatile SingularAttribute<ActividadesPlan, ActividadesEspecificas> idActividad;

}