package Entity;

import Entity.ProteccionPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ProteccionEspecifica.class)
public class ProteccionEspecifica_ { 

    public static volatile SingularAttribute<ProteccionEspecifica, Long> id;
    public static volatile SingularAttribute<ProteccionEspecifica, String> descripcion;
    public static volatile CollectionAttribute<ProteccionEspecifica, ProteccionPlan> proteccionPlanCollection;

}