package com.example.usuario.inventorydbprovider;

import android.support.annotation.StringRes;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Test de pruebas de la vista Login. Consta de las siguientes pruebas: *
 * 1 y 2. El usuario introduce usuario/email (Instrumentales)
 * 3. El usuario introduce contraseña (Instrumentales)
 * 4. La contraseña tiene al menos 6 caracteres (Instrumentales)
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @see AndroidJUnit4
 *
 */
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    /**
     * Método que comprueba que el campo usuario no está vacío
     */
    @Test
    public void isUserEmpty() throws Exception {
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtUser)).perform(click());
        checkSnackBarDisplayByMessage(com.example.usuario.inventorydbprovider.R.string.errorUserEmpty);
    }
    /**
     * Método que comprueba que el campo email no está vacío
     * @throws Exception
     */
    @Test
    public void isEmailEmpty() throws Exception {
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtUser)).perform(click());
        checkSnackBarDisplayByMessage(com.example.usuario.inventorydbprovider.R.string.errorEmailEmpty);
    }
    /**
     * Método que comprueba que el campo contraseña no está vacío
     * @throws Exception
     */
    @Test
    public void isPasswordEmpty() throws Exception {
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtUser)).perform(click());
        checkSnackBarDisplayByMessage(com.example.usuario.inventorydbprovider.R.string.errorPasswordEmpty);
    }
    /**
     * Caso de prueba que comprueba la longitud de contraseña
     * @throws Exception
     */
    @Test
    public void passwordLength() throws Exception {
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtUser)).perform(typeText("lourdes"), closeSoftKeyboard());
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtPassword)).perform(typeText("lu"), closeSoftKeyboard());
        onView(withId(com.example.usuario.inventorydbprovider.R.id.btnSignIn)).perform(click());
        checkSnackBarDisplayByMessage(com.example.usuario.inventorydbprovider.R.string.errorPassword);
    }

    //@StringRes realiza una inyección, es decir, que recoge el recurso
    //del fichero xml. Espresso recuerda a ButterKnife.
    private void checkSnackBarDisplayByMessage(@StringRes int messageId) {
        //Obligatoriamente debe estar visible el elemento mientras no se cumpla la regla
        onView(withText(messageId)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }
}
