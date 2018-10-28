package Entity;

import Entity.Plan;
import Entity.ProteccionEspecifica;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ProteccionPlan.class)
public class ProteccionPlan_ { 

    public static volatile SingularAttribute<ProteccionPlan, BigDecimal> id;
    public static volatile SingularAttribute<ProteccionPlan, ProteccionEspecifica> idProteccion;
    public static volatile SingularAttribute<ProteccionPlan, Plan> idPlan;

}