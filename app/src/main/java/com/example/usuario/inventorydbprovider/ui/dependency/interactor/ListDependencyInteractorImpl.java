package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

import android.os.AsyncTask;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.db.repository.DependencyRepository;
import com.example.usuario.inventorydbprovider.utils.Error;

import java.util.ArrayList;

/**
 * Interactor de ListDependencyFragment. Controla la comunicación con el repositorio.
 */
public class ListDependencyInteractorImpl implements ListDependencyInteractor {

    private final OnLoadFinishedListener listener;

    public ListDependencyInteractorImpl(OnLoadFinishedListener listener) {
        this.listener = listener;
    }

    @Override
    public void deleteDependency(Dependency dependency) {
        DependencyRepository.getInstance().deleteDependency(dependency, this);
        new ListDependencyInteractorAsyncTask(listener).execute();
    }

    public void loadDependencies() {
        new ListDependencyInteractorAsyncTask(listener).execute();
    }

    @Override
    public void onSuccess() {
        listener.onDependencyDeleted();
    }

    @Override
    public void onError(Error error) {
        listener.onDatabaseError(error);
    }


    private static class ListDependencyInteractorAsyncTask extends AsyncTask<Void, Void, Void> {
        private final OnLoadFinishedListener listener;
        private ArrayList<Dependency> dependencies;

        private ListDependencyInteractorAsyncTask(OnLoadFinishedListener listener) {
            this.dependencies = new ArrayList<>();
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dependencies = DependencyRepository.getInstance().getDependencies();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onDependenciesLoaded(dependencies);
        }

    }

}
