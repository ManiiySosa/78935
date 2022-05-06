package mx.uv.SaludarRest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SaludarControlador {

    @RequestMapping("/")
    public String home(){
        return("hola mundo home");
    }
   /* @RequestMapping(value ="/saludar", method = RequestMethod.GET)
    public String saludarGET(){
        return "hola/saludar GET ";
    }
    @RequestMapping(value ="/saludar", method = RequestMethod.POST)
    public String saludarPOST(){
        return "hola/saludar POST ";
    }

    }
    @RequestMapping(value ="/saludar2/{nombre}", method = RequestMethod.GET)
    public String saludarPath2(@PathVariable String nombre){
        return "hola nombre="+nombre;

    */
    List <Saludos> listaSaludos =new ArrayList<Saludos>();
    int id =1;

    //@RequestMapping(value= "/saludar/{nombre}", method= RequestMethod.GET)
    @GetMapping("/saludar")
    public String saludar(@RequestParam(name="nombre", defaultValue="Default" ) String nombre){
        Saludos saludo = new Saludos();
        saludo.setNombre(nombre);
        saludo.setId(id);
        listaSaludos.add(saludo);
        id++;
    
        return "hola " + nombre;

    }

    @GetMapping("/saludadores")
    public List<Saludos> saludadores(){
        List<Saludos> ls = new ArrayList<>();
        for(Saludos s:listaSaludos){
            Saludos slds = new Saludos();
            slds.setId(s.getId());
            slds.setNombre(s.getNombre());
            ls.add(s);
        }
        return ls;
    }

    @RequestMapping(value= "/borrar/{id}", method= RequestMethod.GET)
    public boolean borrarSaludo(@PathVariable int id){
        boolean res = false;
        for(Saludos s:listaSaludos){
            if(s.getId()== id){
                listaSaludos.remove(s);
                res=true;
                break;
            }
        }
        return res;

    }

    
    @RequestMapping(value= "/modificar", method= RequestMethod.GET)
   // @GetMapping("/modificar/id")
    public boolean modificarSaludo(@RequestParam("id") int id ,@RequestParam(name="nombre") String nombre){
            boolean res = false;
            Saludos s = new Saludos();
            s.setId(id);
            s.setNombre(nombre);
            listaSaludos.set(s.getId()-1, s);
            res = true;

            return res;
    }


}