package com.example.usuario.inventorydbprovider.ui.login;

/**
 * Clase presententador de las vistas Login
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see LoginPresenter
 * @see LoginInteractorImpl
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractorImpl loginInteractorImpl;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        //Cada presentador tiene UN objeto de su Interactor
        loginInteractorImpl = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String user, String password) {
        loginInteractorImpl.validateCredentials(user, password, this);
    }
    @Override
    public void onUserEmptyError() {
        loginView.setUserEmptyError();
    }
    @Override
    public void onPasswordEmptyError() {
        loginView.setPasswordEmptyError();
    }
    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
    }
    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }
    /**
     * Destruye los objetos inicializados en el constructor
     * poniéndolos a nulo porque LA VISTA SE VA A DESTRUIR
     */
    @Override
    public void onDestroy() {
        loginView = null;
        loginInteractorImpl = null;
    }

}
