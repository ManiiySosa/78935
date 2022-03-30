package mx.uv.t4is.Saludos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BorrarSaludoRequest;
import https.t4is_uv_mx.saludos.BorrarSaludoResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;


@Endpoint
public class SaludosEndPoint {
    
    int contadorId = 1;
    List<Saludo> saludos = new ArrayList<>();
    
    @PayloadRoot(localPart = "saludarRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola " + peticion.getNombre());
        Saludo saludo = new Saludo();
        saludo.setNombre(peticion.getNombre());
        saludo.setId(contadorId);
        saludos.add(saludo);
        contadorId ++;
        return respuesta;
    }

    @PayloadRoot(localPart = "buscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse buscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        for(Saludo saludo : saludos){
            BuscarSaludosResponse.Saludos bsr = new BuscarSaludosResponse.Saludos();
            bsr.setId(saludo.getId());
            bsr.setNombre(saludo.getNombre());
            respuesta.getSaludos().add(bsr);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "modificarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludo element = new Saludo();
        element.setId(peticion.getId());
        element.setNombre(peticion.getNombre());
        saludos.set(peticion.getId()-1, element);
        respuesta.setRespuesta(true);
        return respuesta;

    }
    
    @PayloadRoot(localPart = "borrarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludo(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //eliminar de la lista
        //saludos.remove(peticion.getId());

        for(Saludo o : saludos){
            if(o.getId() == peticion.getId()){
                saludos.remove(o);
                break;
            }
        }

        /*Saludo o = new Saludo();
        o.setId(peticion.getId());
        saludos.remove(saludos.indexOf(o));*/
        respuesta.setRespuesta(true);
        return respuesta;

    }

}
