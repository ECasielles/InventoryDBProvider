package com.example.usuario.inventorydbprovider.ui.dependency.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.base.BaseFragment;
import com.example.usuario.inventorydbprovider.ui.dependency.contract.AddEditDependencyContract;
import com.example.usuario.inventorydbprovider.ui.dependency.presenter.AddEditDependencyPresenter;
import com.example.usuario.inventorydbprovider.utils.AddEdit;


public class AddEditDependencyFragment extends BaseFragment implements AddEditDependencyContract.View {

    public static final String TAG = "AddEditDependencyFragment";
    private AddEditDependencyContract.Presenter presenter;
    private AddEditDependencyListener callback;
    private FloatingActionButton fab;
    private TextInputLayout tilName, tilShortName, tilDescription;
    private EditText edtName, edtShortName, edtDescription;
    private AddEdit addEditMode;

    //CONSTRUCTOR
    public static AddEditDependencyFragment newInstance(Bundle arguments) {
        AddEditDependencyFragment addEditDependencyFragment = new AddEditDependencyFragment();
        if(arguments != null){
            addEditDependencyFragment.setArguments(arguments);
        }
        return addEditDependencyFragment;
    }

    //CICLO DE VIDA: INICIO
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (AddEditDependencyListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().getLocalClassName() + " must implement ListDependencyListener");
        }
    }

    @SuppressLint("LongLogTag")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addeditdependency, container, false);

        tilName = rootView.findViewById(R.id.tilName);
        tilShortName = rootView.findViewById(R.id.tilShortName);
        tilDescription = rootView.findViewById(R.id.tilDescription);

        fab = rootView.findViewById(R.id.fab);
        edtName = rootView.findViewById(R.id.edtName);
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tilShortName = rootView.findViewById(R.id.tilShortName);
        edtShortName = rootView.findViewById(R.id.edtShortName);
        edtShortName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilShortName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tilDescription = rootView.findViewById(R.id.tilDescription);
        edtDescription = rootView.findViewById(R.id.edtDescription);
        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDescription.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Por defecto está en ADD_MODE
        addEditMode = new AddEdit();
        if(getArguments() != null) {
            addEditMode.setMode(AddEdit.EDIT_MODE);
            Dependency dependency = getArguments().getParcelable(Dependency.TAG);
            tilName.getEditText().setText(dependency.getName());
            tilShortName.getEditText().setText(dependency.getShortname());
            tilDescription.getEditText().setText(dependency.getDescription());
            tilName.setEnabled(false);
            tilShortName.setEnabled(false);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addEditMode.getMode() == AddEdit.ADD_MODE)
                    presenter.saveDependency(
                            tilName.getEditText().getText().toString(),
                            tilShortName.getEditText().getText().toString(),
                            tilDescription.getEditText().getText().toString(), "");
                if(addEditMode.getMode() == AddEdit.EDIT_MODE){
                    Dependency dependency = getArguments().getParcelable(Dependency.TAG);
                    presenter.editDependency(dependency, tilDescription.getEditText().getText().toString());
                }
            }
        });
    }

    //GUARDAR ESTADO
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AddEditDependencyPresenter.TAG, presenter);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null)
            this.presenter = (AddEditDependencyContract.Presenter) savedInstanceState.get(AddEditDependencyPresenter.TAG);
    }

    //COMUNICACION MVPI
    @Override
    public void setPresenter(AddEditDependencyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void navigateToListDependency() {
        callback.listDependency();
    }

    //COMUNICACIONES MVPI: ERRORES
    @Override
    public void setNameEmptyError() {
        tilName.setError(getResources().getString(R.string.errorDependencyNameEmptyError));
    }

    @Override
    public void setShortNameEmptyError() {
        tilShortName.setError(getResources().getString(R.string.errorDependencyShortNameEmptyError));
    }

    @Override
    public void setShortNameLengthError() {
        tilShortName.setError(getResources().getString(R.string.errorDependencyShortNameLengthError));
    }

    @Override
    public void setDescriptionEmptyError() {
        tilDescription.setError(getResources().getString(R.string.errorDependencyDescriptionEmptyError));
    }

    @Override
    public void setDatabaseError(String message) {
        onError(message);
    }

    //CICLO DE VIDA: FIN
    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //INTERFAZ COMUNICACION CON ACTIVITY/FRAGMENT
    public interface AddEditDependencyListener {
        void listDependency();
    }
}
