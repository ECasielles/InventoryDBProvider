package com.example.usuario.inventorydbprovider.ui.login;

/**
 * Contrato de las vistas Login. Contiene los métodos
 * necesarios/expuestos necesarios para la comunicación
 * de la vista con el presentador.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 */
public interface LoginView {

    void navigateToHome();
    void setUserEmptyError();
    void setPasswordEmptyError();
    void setPasswordError();

}
