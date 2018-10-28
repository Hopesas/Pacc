/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Paciente;
import Entity.Parametros;
import Operaciones_pkg.Campo;
import Operaciones_pkg.Indicador;
import Operaciones_pkg.OperacionesLocator;
import Operaciones_pkg.Registro;
import Operaciones_pkg.Variable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import javax.xml.namespace.QName;



/**
 *
 * @author Freddy
 */
@Stateless
@LocalBean
public class ServicesBean {

    @EJB
    private ParametrosFacade parametrosFacade;

    public boolean loguearUsuarioMonserrat(String userName, String passMd5) {
        
        try{
            
            Parametros p = parametrosFacade.findByNombre("EndpointMonserrat");
            
            QName q = new javax.xml.namespace.QName("Operaciones", "Operaciones");
            OperacionesLocator oper = new OperacionesLocator(p.getValor(), q);

            Indicador i = new Indicador();
            i.setIndicador("acceso_sistema");
            Variable[] variables = new Variable[2];
            Variable v = new Variable();
            v.setVariable("Usuario");
            v.setValor(userName);
            v.setTipo("Variable");
            v.setObservaciones("");
            Variable v2 = new Variable();
            v2.setVariable("Clave");
            v2.setValor(passMd5);
            v2.setTipo("Variable");
            v2.setObservaciones("");
            variables[0] = v;
            variables[1] = v2;
            i.setVariables(variables);
            Registro[] r = oper.getOperacionesPort().consultas(i);
            Registro rr = r[0];
            return ((Campo) rr.getCampos()[0]).getValor().equals("1");
        } catch (Exception e){
            System.err.println(e.getMessage());
            StackTraceElement[] es = e.getCause().getStackTrace();
            for(int i = 0; i < es.length; i++){
                StackTraceElement s = es[i];
                System.out.println("\tat " + s.getClassName() + "." + s.getMethodName()
        + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
            }
            return false;
        }
    }
    
    public Paciente buscarPaciente(String numeroDocumento) {
        
        try{
            
            Parametros p = parametrosFacade.findByNombre("EndpointMonserrat");
            
            QName q = new javax.xml.namespace.QName("Operaciones", "Operaciones");
            OperacionesLocator oper = new OperacionesLocator(p.getValor(), q);

            Indicador i = new Indicador();
            i.setIndicador("datos_tercero");
            Variable[] variables = new Variable[2];
            Variable v = new Variable();
            v.setVariable("Identificacion");
            v.setValor(numeroDocumento);
            v.setTipo("Variable");
            v.setObservaciones("");
            variables[0] = v;
            i.setVariables(variables);
            Registro[] r = oper.getOperacionesPort().consultas(i);
            Registro rr = r[0];
            return transformarRespuestaMonserrat(rr.getCampos());
        } catch (Exception e){
            return new Paciente();
        }
    }
    
    private Paciente transformarRespuestaMonserrat(Campo[] campos){
        Paciente p = new Paciente();
        
        for (int i = 0; i < campos.length; i++){
            Campo c = campos[i];
            if(c.getNombre().equals("tipo_id")){
                
            }else if(c.getNombre().equals("numero_id")){
                p.setDocumento(c.getValor());
            }else if(c.getNombre().equals("primer_nombre")){
                p.setNombre(c.getValor());
            }else if(c.getNombre().equals("segundo_nombre")){
                p.setSegundoNombre(c.getValor());
            }else if(c.getNombre().equals("primer_apellido")){
                p.setApellido(c.getValor());
            }else if(c.getNombre().equals("segundo_apellido")){
                p.setSegundoApellido(c.getValor());
            }else if(c.getNombre().equals("fecha_nacimiento")){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    p.setFechaDeNacimiento(df.parse(c.getValor()));
                } catch (Exception e){
                    p.setFechaDeNacimiento(new Date());
                }
            }else if(c.getNombre().equals("aseguradora")){
                p.setEps(c.getValor());
            }else if(c.getNombre().equals("servicio")){
                p.setServicio(c.getValor());
            }
        
        }
        
        return p;
    }
    
}
