package aeropuertos_interfaz.objetos;

import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;

public class VueloIdaVuelta extends Vuelo{
    
    private float tarifa;
    private float impuesto;

    public VueloIdaVuelta()
    {}

    public VueloIdaVuelta(float tarifa, float impuesto, String codigoVuelo, String origen, String destino, Date fechaSalida, Time horaSalida, Time horaLlegada, Time embarque, boolean oferta, float precio, Avion avion)
    {
        super(codigoVuelo,origen,destino,fechaSalida,horaSalida,horaLlegada,embarque,oferta,precio,avion);
        this.tarifa = tarifa;
        this.impuesto = impuesto;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }   
    
}

