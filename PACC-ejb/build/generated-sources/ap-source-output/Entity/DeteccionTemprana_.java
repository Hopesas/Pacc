package Entity;

import Entity.DeteccionPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(DeteccionTemprana.class)
public class DeteccionTemprana_ { 

    public static volatile SingularAttribute<DeteccionTemprana, Long> id;
    public static volatile SingularAttribute<DeteccionTemprana, String> descripcion;
    public static volatile CollectionAttribute<DeteccionTemprana, DeteccionPlan> deteccionPlanCollection;

}