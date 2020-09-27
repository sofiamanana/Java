package tarea3;

public class Camioneta extends Vehiculo{
//cantCamioneta: cantidad de camionetas usadas.
    int cantCamioneta = 0;

    public void setCantCamioneta(int cantCamioneta){
        this.cantCamioneta = cantCamioneta;
    }

    public int getCantCamioneta(){
        return cantCamioneta;
    }
    public int nuevosCamiones(int nCamioneta){
        cantCamioneta+=nCamioneta;
        return cantCamioneta;
    }
    
}
