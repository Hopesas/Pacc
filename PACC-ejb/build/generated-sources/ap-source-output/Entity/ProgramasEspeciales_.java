package Entity;

import Entity.ProgramasPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(ProgramasEspeciales.class)
public class ProgramasEspeciales_ { 

    public static volatile SingularAttribute<ProgramasEspeciales, Long> id;
    public static volatile SingularAttribute<ProgramasEspeciales, String> descripcion;
    public static volatile CollectionAttribute<ProgramasEspeciales, ProgramasPlan> programasPlanCollection;

}