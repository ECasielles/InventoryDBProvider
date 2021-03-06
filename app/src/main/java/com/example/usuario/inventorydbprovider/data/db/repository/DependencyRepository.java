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
    //Si hubiera una conexión, habría un dependencyDaoWebService, etc.
    private DependencyDao dependencyDao;

    static {
        dependencyRepository = new DependencyRepository();
    }

    private DependencyRepository() {
        //IMPORTANTE: ESTAMOS IMPORTANDO DE PROVIDER (DependencyDaoImpl)
        dependencyDao = new DependencyDaoImpl();
    }

    public static DependencyRepository getInstance(){
        //Nunca va a valer null si no se hace inicialización estática
        if (dependencyRepository == null)
            dependencyRepository = new DependencyRepository();
        return dependencyRepository;
    }

    public ArrayList<Dependency> getDependencies() {
        return dependencyDao.loadAll();
    }

    public void addDependency(Dependency dependency, DependencyCallback callback) {
        long id = dependencyDao.add(dependency);
        if (id == -1)
            callback.onError(new Throwable());
        else
            callback.onSuccess();
    }

    public void updateDependency(Dependency dependency, DependencyCallback callback) {
        int result = dependencyDao.update(dependency);
        if (result == -1) {
            //ATENCION: Hemos cambiado el Error por Throwable para generalizar
            //lo va a capturar la vista
            callback.onError(new Throwable());
        } else
            callback.onSuccess();
    }

    public void deleteDependency(Dependency dependency, DependencyCallback callback) {
        int result = dependencyDao.delete(dependency);
        if (result == -1)
            callback.onError(new Throwable());
        else
            callback.onSuccess();
    }

    public boolean exists(Dependency dependency) {
        return dependencyDao.exists(dependency);
    }

    public Dependency search(int id) {
        return dependencyDao.search(id);
    }


}
