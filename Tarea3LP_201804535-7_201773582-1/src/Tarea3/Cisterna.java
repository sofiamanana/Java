package tarea3;

public class Cisterna extends Vehiculo{
//cantCamiones: cantidad de camiones cisternas usados.
    int cantCamiones = 0;

    public void setCantCamiones(int cantCamiones){
        this.cantCamiones = cantCamiones;
    }

    public int getCantCamiones(){
        return cantCamiones;
    }
    public int nuevosCamiones(int nCamiones){
        cantCamiones+=nCamiones;
        return cantCamiones;
    }
}
