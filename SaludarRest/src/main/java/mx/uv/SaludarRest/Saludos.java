package mx.uv.SaludarRest;

    public class Saludos {
        private int id;
        private String nombre;

    public Saludos(){
        
    }    

    public int getId(){
            return id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setId(int id){
        this.id =id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

}
