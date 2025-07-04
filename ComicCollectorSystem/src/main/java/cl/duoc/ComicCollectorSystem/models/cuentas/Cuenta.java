/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.ComicCollectorSystem.models.cuentas;

/**
 *
 * @author Home
 */
public abstract class Cuenta { 
    protected int contadorCuenta = 1000;   
    protected int numeroCuenta; 
    protected int pago;

    public Cuenta(int pago) {
        this.numeroCuenta = ++contadorCuenta;
        this.pago = pago; 
    } 
  
    public void mostrarCuenta() {
        System.out.println("Cuenta Corriente: " + numeroCuenta);
    } 
      
    public void PagarCompra(int pago) {
        if (pago <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }  
        
        // Sumar pago al Manager
    }

    public int getContadorCuenta() {
        return contadorCuenta;
    }

    public void setContadorCuenta(int contadorCuenta) {
        this.contadorCuenta = contadorCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }
    
}
