package com.example.usuario.inventorydbprovider.ui.sector;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.utils.AddEdit;

public class ViewSectorFragment extends Fragment implements ViewSectorContract.View {
    public static final String TAG = "ViewSectorFragment";
    private ViewSectorFragment.OnSectorsUpdatedListener callback;
    private ViewSectorContract.Presenter presenter;
    private TextInputLayout tilName, tilShortName, tilDescription;
    private FloatingActionButton fab;
    private TextView txvDependencyName;
    private AddEdit addEditMode;


    public static ViewSectorFragment newInstance(Bundle bundle) {
        ViewSectorFragment viewSectorFragment = new ViewSectorFragment();
        if (bundle != null)
            viewSectorFragment.setArguments(bundle);
        return viewSectorFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnSectorsUpdatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + "must implement OnSectorsUpdatedListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setPresenter(new ViewSectorPresenter(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view_sector, container, false);

        tilName = rootView.findViewById(R.id.tilName);
        tilShortName = rootView.findViewById(R.id.tilShortName);
        tilDescription = rootView.findViewById(R.id.tilDescription);


        addEditMode = new AddEdit();
        if (getArguments() != null) {
            addEditMode.setMode(AddEdit.EDIT_MODE);
            Sector sector = getArguments().getParcelable(Sector.TAG);
            tilName.getEditText().setText(sector.getName());
            tilShortName.getEditText().setText(sector.getShortname());
            tilDescription.getEditText().setText(sector.getDescription());
            tilName.setEnabled(false);
            tilShortName.setEnabled(false);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SectorActivity) getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "WOLOLO", Toast.LENGTH_SHORT).show();
                if (addEditMode.getMode() == AddEdit.ADD_MODE)
                    presenter.addSector(new Sector(
                            -1, 1,
                            tilName.getEditText().getText().toString(),
                            tilShortName.getEditText().getText().toString(),
                            tilDescription.getEditText().getText().toString(),
                            "sin imagen",
                            false, false)
                    );
                if (addEditMode.getMode() == AddEdit.EDIT_MODE) {
                    Sector sector = getArguments().getParcelable(Sector.TAG);
                    presenter.updateSector(sector);
                }
            }
        });
    }

    @Override
    public void setPresenter(ViewSectorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSectorsUpdated() {
        callback.onSectorsUpdated();
    }


    public interface OnSectorsUpdatedListener {
        void onSectorsUpdated();
    }

}
