package Entity;

import Entity.Plan;
import Entity.ProgramasEspeciales;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ProgramasPlan.class)
public class ProgramasPlan_ { 

    public static volatile SingularAttribute<ProgramasPlan, Long> id;
    public static volatile SingularAttribute<ProgramasPlan, Plan> idPlan;
    public static volatile SingularAttribute<ProgramasPlan, ProgramasEspeciales> idPrograma;

}