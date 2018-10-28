package Entity;

import Entity.ActividadesPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ActividadesEspecificas.class)
public class ActividadesEspecificas_ { 

    public static volatile SingularAttribute<ActividadesEspecificas, Long> id;
    public static volatile SingularAttribute<ActividadesEspecificas, String> grupo;
    public static volatile SingularAttribute<ActividadesEspecificas, String> definicion;
    public static volatile SingularAttribute<ActividadesEspecificas, String> descripcion;
    public static volatile CollectionAttribute<ActividadesEspecificas, ActividadesPlan> actividadesPlanCollection;

}