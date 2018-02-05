package com.example.usuario.inventorydbprovider.ui.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.usuario.inventorydbprovider.ui.base.BaseActivity;
import com.example.usuario.inventorydbprovider.ui.dashboard.DashboardActivity;
import com.example.usuario.inventorydbprovider.R;

/**
 * Actividad que maneja el alta de usuarios y
 * el ingreso a la aplicación.
 *
 * Cuando ingresa un usuario se lanza la actividad Dashboard.
 *
 * Implementa las reglas de LoginView
 *
 * @author Enrique Casielles Lapeira
 * @version 3.0
 * @see AppCompatActivity
 * @see LoginView
 */
public class LoginViewImpl extends BaseActivity implements LoginView {
    private TextView txvSignUp;
    private Button btnSignIn;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);

        txvSignUp = (TextView) findViewById(R.id.txvSignUp);
        txvSignUp.setPaintFlags(txvSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.validateCredentials("Lourdes", "lourdes");
            }
        });
    }
    @Override
    public void navigateToHome() {
        startActivity(new Intent(LoginViewImpl.this, DashboardActivity.class));
    }
    @Override
    public void setUserEmptyError() {
        onError(R.string.errorUserEmpty);
    }
    @Override
    public void setPasswordEmptyError() {
        onError(R.string.errorPasswordEmpty);
    }
    @Override
    public void setPasswordError() {
        onError(R.string.errorPassword);
    }
    /**
     * Destruye objetos inicializados
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
        loginPresenter = null;
    }
}
