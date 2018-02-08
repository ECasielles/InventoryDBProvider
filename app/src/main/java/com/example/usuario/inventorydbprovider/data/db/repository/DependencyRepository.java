package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.DependencyDao;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.provider.dao.DependencyDaoImpl;
import com.example.usuario.inventorydbprovider.ui.dependency.interactor.DependencyCallback;

import java.util.ArrayList;

/**
 * Gestor de datos de Dependency. Extrae los datos
 * de distintos orígenes de datos.
 *
 * @author Enrique Casielles Lapeira
 * @version 3.0
 * @see ArrayList
 * @see Dependency
 * @see DependencyDao
 */

public class DependencyRepository {

    private static DependencyRepository dependencyRepository;

    static {
        dependencyRepository = new DependencyRepository();
    }

    //Si hubiera una conexión, habría un dependencyDaoWebService, etc.
    private DependencyDao dependencyDao;


    private DependencyRepository() {
        //IMPORTANTE: ESTAMOS IMPORTANDO DE PROVIDER (DependencyDaoImpl)
        dependencyDao = new DependencyDaoImpl();
    }

    /**
     * Accesor de la clase DependencyRepository
     * @return Devuelve la instancia de la clase como objeto DependencyRepository
     */
    public static DependencyRepository getInstance(){
        //Nunca va a valer null si no se hace inicialización estática
        if (dependencyRepository == null)
            dependencyRepository = new DependencyRepository();
        return dependencyRepository;
    }

    public ArrayList<Dependency> getDependencies() {
        return dependencyDao.loadAll();
    }

    public void updateDependency(Dependency dependency, DependencyCallback callback) {
        int result = dependencyDao.update(dependency);
        if (result == 0) {
            //ATENCION: Hemos cambiado el Error por Throwable para generalizar
            //lo va a capturar la vista
            callback.onError(new Throwable());
        } else
            callback.onSuccess();
    }

    /**
     * Devuelve el id del elemento añadido o -1.
     * @param dependency
     * @return
     */
    public void addDependency(Dependency dependency, DependencyCallback callback) {
        long id = dependencyDao.add(dependency);
        if (id == -1)
            callback.onError(new Throwable());
        else
            callback.onSuccess();
    }

    public void deleteDependency(Dependency dependency, DependencyCallback callback) {
        int result = dependencyDao.delete(dependency);
        if (result == 0)
            callback.onError(new Throwable());
        else
            callback.onSuccess();
    }

    public boolean exists(Dependency dependency) {
        return dependencyDao.exists(dependency);
    }

}
