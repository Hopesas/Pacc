package Entity;

import Entity.ActividadesPlan;
import Entity.DeteccionPlan;
import Entity.ProgramasPlan;
import Entity.ProteccionPlan;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(Plan.class)
public class Plan_ { 

    public static volatile SingularAttribute<Plan, Long> id;
    public static volatile SingularAttribute<Plan, String> idPaciente;
    public static volatile SingularAttribute<Plan, Long> idCuidado;
    public static volatile SingularAttribute<Plan, Date> fecha;
    public static volatile SingularAttribute<Plan, Long> idGrupo;
    public static volatile CollectionAttribute<Plan, ActividadesPlan> actividadesPlanCollection;
    public static volatile SingularAttribute<Plan, String> idUseras;
    public static volatile CollectionAttribute<Plan, ProteccionPlan> proteccionPlanCollection;
    public static volatile SingularAttribute<Plan, Long> cuidadosEspeciales;
    public static volatile CollectionAttribute<Plan, DeteccionPlan> deteccionPlanCollection;
    public static volatile CollectionAttribute<Plan, ProgramasPlan> programasPlanCollection;

}