package Pacc;

import Entity.*;
import SessionBeans.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
 
  
/**
 * Esta clase es un servlet que crea un nuevo html con la información correspondiente
 * al plan realizado.
 * @author Freddy
 */
public class PdfDiagnostico extends HttpServlet {
    @EJB private ServicesBean servicesBean;
    @EJB private CuidadosNutricionalesFacade cuidadosNutricionalesFacade;
    @EJB private PacienteFacade pacienteFacade;
    @EJB private UserasFacade userasFacade;
    @EJB private PlanFacade planFacade;
    @EJB private GruposFacade gruposFacade;
     
    /**
     * Crea el pdf y lo convierte en stream para poder mostrarlo desde los navegadores
     * Chrome y Firefox, en Internet Explorer permite descargar el pdf.
     * @param request
     * @param response
     */   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            ByteArrayOutputStream pdfOutputStream = 
                 new ByteArrayOutputStream();
            
            Document document =  new Document(PageSize.A4.rotate(), 36, 36, 20, 50);
            PdfWriter writer = PdfWriter.getInstance(document, pdfOutputStream);

            TableHeader event = new TableHeader();
            writer.setPageEvent(event);
            event.setHeader("Clínica Montserrat\nPlan de Atención de Cuidado en Casa");
 
            document.open();
            
            if((Long)session.getAttribute("plan") != -1){
                
                //Desde aquí
                ColumnText column = new ColumnText(writer.getDirectContent());
                int status;
                float[][] columnas = {
                    { document.left(), document.left() + 250 },
                    { document.left() + 260, document.left() + 510},
                    { document.right() - 250, document.right() }
                };
                //Hasta Aquí
                
                Plan planAtencion =  planFacade.find(session.getAttribute("plan"));
                Useras enfermero = new Useras();
                enfermero.setNombre(planAtencion.getIdUseras());
                enfermero.setUsername(planAtencion.getIdUseras());
                
                Paciente paciente = servicesBean.buscarPaciente(planAtencion.getIdPaciente());

                document.addTitle("Plan de Atención de Cuidado en Casa");
                document.addAuthor("Clínica Montserrat");
                
                Image image = Image.getInstance(this.getClass().getResource("images/header.jpg"));
                image.scaleToFit(250, 100);
                Chunk k = new Chunk(image, 0, -15, true);
                
                Paragraph img = new Paragraph(k);
                
                Paragraph p = new Paragraph("Plan de Atencion de Cuidado en Casa",
                    FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 10));
                //document.add(p);
                column.setText(img);
                column.addText(p);
                
                Calendar plan = Calendar.getInstance();
                plan.setTime(planAtencion.getFecha());

                Paragraph negrilla = new Paragraph("\nPACC Nº: " + planAtencion.getId() + "     Fecha: " + 
                        plan.get(Calendar.DAY_OF_MONTH) + "/" + (plan.get(Calendar.MONTH) + 1) + "/" + plan.get(Calendar.YEAR)
                        + "\n\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 9));
                negrilla.add("Enfermero: \n");
                String segundoNombre = enfermero.getSegundoNombre();
                if(segundoNombre == null){
                    segundoNombre = "";
                }
                
                String segundoApellido = enfermero.getSegundoApellido();
                if(segundoApellido == null){
                    segundoApellido = "";
                }
                
                Paragraph paragraph = new Paragraph("Nombre: " + enfermero.getNombre() + " " + segundoNombre +
                        "   Apellido: " + enfermero.getApellido() + " " + segundoApellido, FontFactory.getFont(FontFactory.TIMES, 9));

                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                column.addText(negrilla);
                column.addText(paragraph);

                negrilla.clear();
                paragraph.clear();
                
                
                Date fecha = paciente.getFechaDeNacimiento();
                Calendar cal = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
                cal.setTime(fecha);

                int edad = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR) ;

                if(now.get(Calendar.MONTH) < cal.get(Calendar.MONTH)){
                    edad--;
                }
                if(now.get(Calendar.MONTH) == cal.get(Calendar.MONTH)){
                    if(now.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH)){
                        edad--;
                    }
                }
                negrilla.add("\nPaciente:\n");
                
                segundoNombre = paciente.getSegundoNombre();
                if(segundoNombre == null){
                    segundoNombre = "";
                }

                segundoApellido = paciente.getSegundoApellido();
                if(segundoApellido == null){
                    segundoApellido = "";
                }

                paragraph.add("Documento: " + paciente.getDocumento() + "   Nombre: " + paciente.getNombre() + " " + segundoNombre 
                        + "   Apellido: " + paciente.getApellido() + " " + segundoApellido + "\nFecha de Nacimiento: " + cal.get(Calendar.DAY_OF_MONTH) + "/" 
                        + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR) + "   Edad: " + edad);

                column.addText(negrilla);
                column.addText(paragraph);

                negrilla.clear();
                paragraph.clear();

                Grupos grupo =  gruposFacade.find(planAtencion.getIdGrupo());
                
                negrilla.add("\nGrupo: ");
                paragraph.add(grupo.getCapitulo()+ " - " + grupo.getCodigo()+ " - " + grupo.getDescripcion());

                column.addText(negrilla);
                column.addText(paragraph);
                negrilla.clear();
                paragraph.clear();

                List<ActividadesPlan> actividades = (List<ActividadesPlan>) planAtencion.getActividadesPlanCollection();
                
                if(!actividades.isEmpty()){
                    negrilla.add("\nActividades Específicas de Cuidados:\n");
                    for(ActividadesPlan i : actividades){
                        ActividadesEspecificas ae = i.getIdActividad();
                        paragraph.add("-" + ae.getDescripcion() + "\n");
                    }

                    column.addText(negrilla);
                    column.addText(paragraph);
                    negrilla.clear();
                    paragraph.clear();
                }

                CuidadosNutricionales cuidadoNutricional =  cuidadosNutricionalesFacade.find(planAtencion.getIdCuidado());
            
                negrilla.add("Cuidado Nutricional: \n");
                paragraph.add(cuidadoNutricional.getDeficion() + "\n");

                column.addText(negrilla);
                column.addText(paragraph);
                negrilla.clear();
                paragraph.clear();
                
                List<ProteccionPlan> proteccion = (List<ProteccionPlan>) planAtencion.getProteccionPlanCollection();

                if(!proteccion.isEmpty()){
                    negrilla.add("Programas de Proteccion Específicos:\n");
                    for(ProteccionPlan i : proteccion){
                        ProteccionEspecifica pe = i.getIdProteccion();
                        paragraph.add(pe.getDescripcion() + "\n");
                    }
                    column.addText(negrilla);
                    column.addText(paragraph);
                    negrilla.clear();
                    paragraph.clear();
                }
                
                
                List<DeteccionPlan> deteccion = (List<DeteccionPlan>) planAtencion.getDeteccionPlanCollection();

                if(!deteccion.isEmpty()){
                    negrilla.add("Detección Temprana:\n");
                    for(DeteccionPlan i : deteccion){
                        DeteccionTemprana pe = i.getIdDeteccion();
                        paragraph.add(pe.getDescripcion() + "\n");
                    }
                    column.addText(negrilla);
                    column.addText(paragraph);
                    negrilla.clear();
                    paragraph.clear();
                }
                
                List<ProgramasPlan> programasEspeciales = (List<ProgramasPlan>) planAtencion.getProgramasPlanCollection();

                if(!programasEspeciales.isEmpty()){
                    negrilla.add("Programas  Especiales:\n");
                    for(ProgramasPlan i : programasEspeciales){
                        ProgramasEspeciales pe = i.getIdPrograma();
                        paragraph.add(pe.getDescripcion() + "\n");
                    }
                    column.addText(negrilla);
                    column.addText(paragraph);
                    negrilla.clear();
                    paragraph.clear();
                }
                                
                column.setSimpleColumn(
                    columnas[0][0], document.bottom(),
                    columnas[0][1], document.top() - 0 - 10);
                
                
                status = column.go();
                
                int columna = 1;
                while(status > 1){
                    
                    column.setSimpleColumn(
                        columnas[columna][0], document.bottom(),
                        columnas[columna][1], document.top() - 0 - 10);
                    status = column.go();
                    
                    columna++;
                    if(columna > 2){
                        columna = 0;
                        document.newPage();
                    }
                }
                
                if(planAtencion.getCuidadosEspeciales() != 9){
                    String resource = "images/" + planAtencion.getCuidadosEspeciales() + ".png";

                    Image cuidadosEspeciales = Image.getInstance(this.getClass().getResource(resource));
                    cuidadosEspeciales.scaleToFit(200, 600);
                    Chunk kCuidadosEspeciales = new Chunk(cuidadosEspeciales, 0, -15, true);
                    Paragraph imgCuidadosEspeciales = new Paragraph(kCuidadosEspeciales);

                    column.addText(imgCuidadosEspeciales);

                    column.setSimpleColumn(
                        columnas[columna][0], document.bottom(),
                        columnas[columna][1], document.top() - 0 - 10);

                    columna++;

                    column.go();
                }
                else{
                    String resource1 = "images/" + planAtencion.getCuidadosEspeciales() + "_1.png";
                    String resource2 = "images/" + planAtencion.getCuidadosEspeciales() + "_2.png";

                    Image cuidadosEspeciales1 = Image.getInstance(this.getClass().getResource(resource1));
                    Image cuidadosEspeciales2 = Image.getInstance(this.getClass().getResource(resource2));
                    cuidadosEspeciales1.scaleToFit(250, 700);
                    cuidadosEspeciales2.scaleToFit(250, 700);
                    
                    Chunk kCuidadosEspeciales1 = new Chunk(cuidadosEspeciales1, 0, -15, true);
                    Chunk kCuidadosEspeciales2 = new Chunk(cuidadosEspeciales2, 0, -15, true);
                    
                    Paragraph imgCuidadosEspeciales1 = new Paragraph(kCuidadosEspeciales1);

                    column.addText(imgCuidadosEspeciales1);

                    column.setSimpleColumn(
                        columnas[columna][0], document.bottom(),
                        columnas[columna][1], document.top() - 0 - 10);
                    
                    column.go();
                    
                    columna++;
                    
                    if(columna > 2){
                        columna = 0;
                        document.newPage();
                    }
                    
                    Paragraph imgCuidadosEspeciales2 = new Paragraph(kCuidadosEspeciales2);

                    column.addText(imgCuidadosEspeciales2);

                    column.setSimpleColumn(
                        columnas[columna][0], document.bottom(),
                        columnas[columna][1], document.top() - 0 - 10);

                    columna++;

                    column.go();
                }
                
                Image cuidados = Image.getInstance(this.getClass().getResource("images/cuidadosGenerales.png"));
                Chunk kCuidados = new Chunk(cuidados, 0, -15, true);
                Paragraph imgCuidados = new Paragraph(kCuidados);
                
                if(columna == 1)
                {
                    column.setSimpleColumn(
                        columnas[1][0], document.bottom(),
                        columnas[1][1], document.top() - 0 - 10);
                }else{
                    document.newPage();
                    column.setSimpleColumn(
                        columnas[0][0], document.bottom(),
                        columnas[0][1], document.top() - 0 - 10);
                }
                column.addText(imgCuidados);
                status = column.go();
                System.err.println(column.getLinesWritten());
                
            }else{
                document.addTitle("Plan de Atención de Cuidado en Casa");
                document.addAuthor("Clínica Montserrat");
                document.add(new Paragraph("Por Favor elija un plan.",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));

            }
            
            document.close();
            response.setHeader("Expires", "0");
            response.setContentType("application/pdf");
            response.setContentLength(pdfOutputStream.size());

            // Write the PDF
            ServletOutputStream responseOutputStream = 
                                response.getOutputStream();
            responseOutputStream.write(pdfOutputStream.toByteArray());
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    /**
     * Clase que contiene el Header y Footer del pdf.
     */
    class TableHeader extends PdfPageEventHelper {
        /** The header text. */
        String header;
        /** The template with the total number of pages. */
        PdfTemplate total;
 
        /**
         * Allows us to change the content of the header.
         * @param header The new header String
         */
        public void setHeader(String header) {
            this.header = header;
        }
 
        /**
         * Creates the PdfTemplate that will hold the total number of pages.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
  
        /**
         * Fills out the total number of pages before the document is closed.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }
        
    @Override
    public String getServletInfo() {
        return "My PDF Generator";
    }
}