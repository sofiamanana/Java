package tarea3;

public class Empresa extends Pais{
//PrecioBalon: precio de balon de gas.
    int PrecioBalon;
//PrecioLitro: precio de cada litro de gas.
    int PrecioLitro;

    public void setPrecioLitro(int Precio){
        this.PrecioLitro = Precio;
    }
    public int getPrecioLitro(){
        return this.PrecioLitro;
    }
    public void setPrecioBalon(int Precio){
        this.PrecioBalon = Precio;
    }
    public int getPrecioBalon(){
        return this.PrecioBalon;
    }
}
