
package Model;

/** 
 * Clase que guarda la informacion relacionada con el evento de ingreso. Permite
 * generar un recibo con el que se identifica el vehiculo, cliente y evento todo
 * junto.
 * 
 * @author Luis M Ponce de leon
 */
public class Recibo {
    /**
     * Si el recibo a sido pagado o no.
     */
    private boolean activo;
    
    /**
     * El Vehiculo del que se tiene control desde el recibo.
     */
    private Vehiculo vehiculo;

    /** 
     * El cliente dueño del Vehiculo registrado en el recibo.
     */
    private Cliente cliente;
    
    /**
     * Porcentaje del valor de la hora del tipo de vehiculo a pagar como multa
     * por tener que retirar el vehiculo a las 24horas.
     * <P>
     * Por ser porcentaje el 0
     * es 0%, 0.5 es 50%, 1 es 100%, 2 es 200%, etc.
     */
    private static final double MULTARETIRO= 0.4;

    /** 
     * Numero unico del recibo.
     */
    private int numeroRecibo;

    /** 
     * Constructor del recibo, recibe el vehiculo y cliente relacionados, ademas
     * del numero unico del recibo.
     * 
     * @param vehiculo El vehiculo registrado en el recibo.
     * @param cliente El dueño del vehiculo.
     * @param numeroRecibo Numero unico que identifica el recibo.
     */
    public Recibo(Vehiculo vehiculo, Cliente cliente, int numeroRecibo) {
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.numeroRecibo = numeroRecibo;
        
        this.activo=true;
        
        this.imprimirRecibo();
    }

    /** 
     * Metodo para imprimir el recibo despues de ingresar un vehiculo.
     */
    public void imprimirRecibo() {
        System.out.println("Numero de recibo:   " + numeroRecibo);
        System.out.println("ID del cliente:     " + cliente.getID());
        System.out.println("Placa del vehiculo: " + vehiculo.getPlaca());
        System.out.println("Tipo de vehiculo: " + vehiculo.getTipo().name());
        System.out.println("Ubicacion del cehiculo:");
        System.out.print("  Piso: ");
        if (vehiculo.getFilaParqueo()==-1) {
            System.out.println("Sotano");
            System.out.println("  Celda: " + vehiculo.getColumnaParqueo());
        }
        else {
            System.out.println((char) (65 + vehiculo.getFilaParqueo()));
            System.out.println("  Entrada: " + vehiculo.getColumnaParqueo());
        }
        System.out.println("Hora de entrada: " + vehiculo.getHoraIngreso().getHoraEn24());
        System.out.println("Hora estimada de retiro: " + vehiculo.getHoraEstRetiro().getHoraEn24());
                
    }

    /** 
     * Calcula cuanto debe de pagar el cliente para retirar su vehiculo.
     * Esto se calcula deacuerdo a las horas que estuvo el carro en el
     * parqueadero y el costo relacionado con el tipo de vehiculo.
     * 
     * @param horaDeRetiro hora en que el vehiculo es retirado.
     * @return La cantidad a pagar para retirar el vehiculo.
     */
    public int generarCobro(HoraDelDia horaDeRetiro) {
        int horasCobro= horaDeRetiro.getHoras()-vehiculo.getHoraIngreso().getHoras();
        if (horaDeRetiro.getMinutos()-vehiculo.getHoraIngreso().getMinutos() > 0){
            horasCobro++;
        }
        if (cliente.getDeuda()>0)
            return horasCobro*vehiculo.getTipo().getCosto()+cliente.getDeuda();
        else 
            return horasCobro * vehiculo.getTipo().getCosto();
    }

    /**
     * Este metodo es llamado cuando el vehiculo es retirado por la grua a las 23:59.
     * Se calcula el cobro por las horas que estuvo parqueado el vehiculo y se 
     * adiciona un valor por tener que retirar el vehiculo. Este cobro se le 
     * asigna al cliente.
     * 
     * @see Cliente
     */
    public void generarDeudaAcumulada(){
        int cobro=this.generarCobro(RelojInterno.getInstance().getHoraActual());
        cobro += Recibo.MULTARETIRO*vehiculo.getTipo().getCosto();
        cliente.setDeuda(cobro);
        this.activo=false;
    }

    /**
     * geter del vehiculo relacionado con el recibo.
     * 
     * @return El vehiculo relacionado con el recibo,
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Geter del numero unico del recibo que identifica el recibo.
     * 
     * @return El numero del recibo.
     */
    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    /**
     * geter del cliente relacionado en el recibo.
     * 
     * @return El cliente relacionado en el recibo. 
     */
    public Cliente getCliente() {
        return cliente;
    }
    
    /**
     * geter de la placa del vehiculo asociada con el recibo.
     * 
     * @return la placa del vehiculo asociado.
     */
    public String getPlaca(){
        return vehiculo.getPlaca();
    }
    
    /**
     * geter del id del cliente asociado con el recibo.
     * 
     * @return el id del cliente asociado.
     */
    public int getIdCliente(){
        return cliente.getID();
    }
    
    /**
     * geter de la hora de entrada del vehiculo asociado con el recibo.
     * 
     * @return la hora de entrada del vehoculo asociado.
     */
    public String getHoraEntrada(){
        return vehiculo.getHoraIngreso().getHoraEn24();
    }
    
    /**
     * geter del tipo de vehiculo asociado con el recibo.
     * 
     * @return el tipo de vehiculo asociado con el recibo.
     */
    public String getTipoVehiculo(){
        return vehiculo.getTipo().name();
    }
    
    public boolean isActive(){
        return activo;
    }
    
    public void pagarRecibo(){
        this.activo=false;
        
        this.cliente.setDeuda(0);
    }
}

