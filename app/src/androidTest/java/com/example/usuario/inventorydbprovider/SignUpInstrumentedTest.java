package com.example.usuario.inventorydbprovider;


import android.support.annotation.StringRes;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test de pruebas de la vista Login. Consta de las siguientes pruebas:
 * 1. El usuario introduce usuario/email (Instrumentales)
 * 2. Segunda comprobación de contraseña (Instrumentales)
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @see AndroidJUnit4
 *
 * 1. Comprobación de formato de email (Instrumentales)
 * 2. Segunda comprobación de contraseña (Instrumentales)
 */

@RunWith(AndroidJUnit4.class)
public class SignUpInstrumentedTest {

    /**
     * Método que comprueba que el campo contraseña no está vacío
     * @throws Exception
     */
    @Test
    public void emailFormat_isCorrect() throws Exception {
        //TODO: End this test with email format
        onView(withId(com.example.usuario.inventorydbprovider.R.id.edtUser)).perform(typeText("lourdes"), closeSoftKeyboard());
        checkSnackBarDisplayByMessage(com.example.usuario.inventorydbprovider.R.string.errorEmailFormat);
    }

    //TODO: Add passwordDoubleCheck() method

    private void checkSnackBarDisplayByMessage(@StringRes int messageId) {
        //Obligatoriamente debe estar visible el elemento mientras no se cumpla la regla
        onView(withText(messageId)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
