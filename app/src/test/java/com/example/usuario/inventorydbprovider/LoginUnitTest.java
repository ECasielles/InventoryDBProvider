package com.example.usuario.inventorydbprovider;

import com.example.usuario.inventorydbprovider.data.db.model.User;
import com.example.usuario.inventorydbprovider.data.db.repository.UserRepository;
import com.example.usuario.inventorydbprovider.utils.CommonUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase de pruebas unitarias que trabajan con la clase Repository
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @see UserRepository
 * @see CommonUtils
 */
public class LoginUnitTest {

    //Base de datos de prueba
    private ArrayList<User> users;

    @Mock   //Notación Mock para que esté compartido por los tests (si no, da NullPointerEx.)
    private UserRepository userRepository;

    @Before //Este decorador hace que se ejecute antes de cualquier método de prueba
    public void initialize() {
        //Añadimos el objeto virtual
        userRepository = mock(UserRepository.class);
        users = new ArrayList<User>();
        users.add(new User(1, "Lourdes", "lourdes", "Lourdes Rodríguez",
                "moronlu18@gmail.com", true, true));
        users.add(new User(2, "María", "maría", "María Serrano",
                "maría20@gmail.com", false, false));
        users.add(new User(3, "Ángela", "angela", "Ángela Rodríguez",
                "angela16@gmail.com", false, false));
    }
    /**
     * Para realizar esta prueba se tienen que comparar dos objetos User
     * Debemos sobrecargar el método equals en User con las reglas usuario/email en la BD
     */
    @Test
    public void isUserExists_isCorrect() {
        //Cuando se llame al método userExists, devuelve un valor predeterminado a mano
        when(userRepository.userExists(users.get(0))).thenReturn(true);
        assertTrue(userRepository.userExists(users.get(0)));
    }
    @Test
    public void getUsers_isCorrect() {
        when(userRepository.getUsers()).thenReturn(users);
        assertEquals(users.get(0), userRepository.getUsers().get(0));
    }
    @Test
    public void password_isCorrect() {
        when(userRepository.getUsers()).thenReturn(users);
        assertEquals(users.get(0).getPassword(), userRepository.getUsers().get(0).getPassword());
    }
    /**
     * Comprueba los distintos casos de error en la introducción de la contraseña
     * según el patrón indicado en CommonUtils para el método validateCredentials de
     * UserRepository
     * Contraseña11     #Correcta
     * Contraseña       #No tiene dígitos
     * contraseña11     #No tiene mayúsculas
     * CONTRASEÑA11     #No tiene minúsculas
     * Pwd11            #Longitud menor que 6
     *
     * @see UserRepository
     * @see CommonUtils
     */
    @Test
    public void passwordFormat_isCorrect() throws Exception {
        when(userRepository.validateCredentials("User", "Contraseña11")).thenReturn(true);
        assertTrue(userRepository.validateCredentials("User", "Contraseña11"));
        assertFalse(userRepository.validateCredentials("User", "Contraseña"));
        assertFalse(userRepository.validateCredentials("User", "contraseña11"));
        assertFalse(userRepository.validateCredentials("User", "CONTRASEÑA11"));
        assertFalse(userRepository.validateCredentials("User", "Pwd11"));
    }
    /**
     * Ejemplo de prueba donde se ve el conjunto de pruebas posibles.
     */
    @Test
    public void testAssertions() {
        //Compara si dos objetos son iguales
        User user1 = new User(1, "lourdes", "Lourdes", "Lourdes Rodríguez",
                "moronlu18@gmail.com", true, true);
        assertEquals(user1, users.get(0));
        //Compara si un objeto es nulo
        User user2 = null;
        User user3 = user1;
        assertNull(user2);
        //Un objeto no es unilo
        assertNotNull(user1);
        //Dos objetos apuntan a la misma referencia
        assertSame(user1, user3);

        //assertTrue, etc.
    }
}