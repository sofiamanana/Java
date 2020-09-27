package tarea3;

public class Ciudad extends Pais{
//id: identificador de la ciudad.
    int id;
//nEdificios: numero de edificios de la ciudad.
    int nEdificios;
//nCasa: numero de casas de la ciudad.
    int nCasa;
//cantKm: suma de la cantidad de kilometros entre la ciudad y la otras ciudades.
    int cantKm;
//utilidad: suma de las utilidades totales de la ciudad con respecto al mapa completo.
    int utilidad = 0;
//utilidadLocal: ganancias de la ciudad.
    int utilidadLocal = 0;
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setCasas(int Casas){
        this.nCasa = Casas;
    }
    public int getCasas(){
        return this.nCasa;
    }
    public void setEdificios(int edificios){
        this.nEdificios = edificios;
    }
    public int getEdificios(){
        return this.nEdificios;
    }
    public void setCantKm(int cantKm){
        this.cantKm = cantKm;
    }
    public int getCantKm(){
        return cantKm;
    }
    public void setUtilidad(int utilidad){
        this.utilidad = utilidad;
    }
    public int getUtilidad(){
        return utilidad;
    }
    public void actUtilidad(int utilidad){
        this.utilidad += utilidad;
    }
    public void setUtilidadLocal(int utilidadLocal){
        this.utilidadLocal = utilidadLocal;
    }
    public int getUtilidadLocal(){
        return utilidadLocal;
    }
}
