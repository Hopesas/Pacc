package Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-24T21:23:39")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Long> id;
    public static volatile SingularAttribute<Paciente, String> nombre;
    public static volatile SingularAttribute<Paciente, String> apellido;
    public static volatile SingularAttribute<Paciente, String> eps;
    public static volatile SingularAttribute<Paciente, String> servicio;
    public static volatile SingularAttribute<Paciente, String> documento;
    public static volatile SingularAttribute<Paciente, String> segundoNombre;
    public static volatile SingularAttribute<Paciente, String> observaciones;
    public static volatile SingularAttribute<Paciente, Date> fechaDeNacimiento;
    public static volatile SingularAttribute<Paciente, String> segundoApellido;

}