package Model;

import java.util.ArrayList;

/** La clase que contiene la informacion relacionada con el cliente.
 * Contiene el ID y la deuda de este.
 * 
 * @author Luis M Ponce de leon
 */
public class Cliente {
    /** 
     * Este sera unico y sera la manera de identificar al cliente.
     * Este sera su cedula, tarjeta de identidad
     * u otro documento de identidad.
     */
    private int idCliente;
    
    /**
     * Lista de todos los clientes registrados.
     */
    private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    /** 
     * La deuda que posee el cliente con el parqueadero. Esta se genera 
     * cuando el vehiculo es retirado por el sistemas a las 23:59
     */
    private int deudaAcumulada=0;

    /**
     * Constructor de la clase CLiente. Adiciona el cliente a la lista de 
     * clientes.
     * 
     * @param idCliente id del cliente a crear.
     */
    public Cliente(int idCliente){
        this.idCliente=idCliente;
        this.deudaAcumulada=0;
        Cliente.listaClientes.add(this);
    }
    
    /**
     * Metodo llamado cuando ingresa un cliente. Si el cliente ya existe en el
     * sistema retorna la referencia a este. Si no existe en el sitema crea 
     * un nuevo cliente con el id suministrado.
     * 
     * @param idCliente id del cliente a ingresar.
     * @return El objeto cliente resultado del ingreso.
     */
    public static Cliente ingresarCliente(int idCliente){
        Cliente resBusqueda = buscarClienteEnLista(idCliente);
        if (resBusqueda == null){
            return new Cliente(idCliente);
        } else {
            return resBusqueda;
        }
    }
    
    /**
     * Metodo para buscar si existe un cliente identificado por el id 
     * suministrado. Si se encuentra retorna la referencia al cliente. Si no se
     * encuentra retorna null.
     * 
     * @param idCliente Numero de identificaion del cliente a buscar.
     * @return El cliente si se encuentra en el sistema, de otro modo null.
     */
    public static Cliente buscarClienteEnLista(int idCliente){
        for (int i=0; i<listaClientes.size(); i++){
            if (Cliente.listaClientes.get(i).getID() == idCliente){
                return Cliente.listaClientes.get(i);
            }
        }
        
        return null;
    }
    
    /** Geter del Id del cliente
     * 
     * @return el ID del cliente como entero.
     */
    public int getID() {
        return idCliente;
    }

    /** Geter de la deuda del cliente.
     * 
     * @return La deuda como entero.
     */
    public int getDeuda() {
        return deudaAcumulada;
    }

    /** Seter de la deuda del cliente.
     * 
     * @param deuda El valor que tendra la deuda del cliente.
     */
    public void setDeuda(int deuda) {
        this.deudaAcumulada = deuda;
    }
}
