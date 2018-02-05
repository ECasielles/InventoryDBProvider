package com.example.usuario.inventorydbprovider.ui.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usuario.inventorydbprovider.R;

import mehdi.sakout.aboutpage.AboutPage;

/**
 * Muestra la información de los creadores. Crea su propia vista
 * usando la librería About Page: https://github.com/medyo/android-about-page
 * Todos los derechos reservados a sus respectivos creadores.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see AppCompatActivity
 * @see AboutPage
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.about_icon_email)
                .setDescription("Somos una gran empresa de inventarios")
                .addEmail("enriquecasielles@gmail.com")
                .addWebsite("http://ecasielles.github.io/")
                .addFacebook("Enrique Casielles")
                .addTwitter("icenri")
                .addYoutube("Icenri")
                .addGitHub("ECasielles")
                .create();

        setContentView(aboutPage);

    }
}
