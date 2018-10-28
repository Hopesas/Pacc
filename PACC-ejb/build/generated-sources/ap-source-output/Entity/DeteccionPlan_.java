package Entity;

import Entity.DeteccionTemprana;
import Entity.Plan;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(DeteccionPlan.class)
public class DeteccionPlan_ { 

    public static volatile SingularAttribute<DeteccionPlan, BigDecimal> id;
    public static volatile SingularAttribute<DeteccionPlan, Plan> idPlan;
    public static volatile SingularAttribute<DeteccionPlan, DeteccionTemprana> idDeteccion;

}