package me.ritesh.wallpapers.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import me.ritesh.wallpapers.view.presenter.Presenter;
import org.parceler.Parcels;

/**
 * @author Ritesh Shakya
 */

public abstract class BaseActivity<M> extends AppCompatActivity implements IView<M> {
    private final String SAVE_INSTANCE_STATE = "SaveInstanceState";

    private Dialog errorDialog;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectActivity();
        setContentView(getLayout());
        ButterKnife.bind(this);
        if (getToolbarId() != null) setSupportActionBar(getToolbarId());

        initViews();
    }

    protected abstract void initViews();

    protected abstract void injectActivity();

    protected abstract Toolbar getToolbarId();

    protected abstract @LayoutRes int getLayout();

    @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(SAVE_INSTANCE_STATE,
                Parcels.wrap(getPresenter().getModel()));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            getPresenter().setModel(
                    Parcels.unwrap(savedInstanceState.getParcelable(SAVE_INSTANCE_STATE)));
        }
    }

    @Override protected void onStart() {
        super.onStart();
        getPresenter().setView(this);
    }

    protected abstract Presenter<IView<M>, M> getPresenter();

    @Override protected void onStop() {
        super.onStop();
        getPresenter().stop();
    }

    @Override protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    protected boolean checkPlayServices() {
        final int playServicesStatus =
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (playServicesStatus != ConnectionResult.SUCCESS) {
            if (errorDialog == null || !errorDialog.isShowing()) {
                //If google play services in not available show an error dialog and return
                errorDialog = GoogleApiAvailability.getInstance()
                        .getErrorDialog(this, playServicesStatus, 0, dialogInterface -> finish());
                errorDialog.show();
            }
            return false;
        }

        return true;
    }

    @Override public void onComplete(M model) {

    }

    @Override public void onStartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override public void onError(String error) {
        if (getCoordinatorLayout() != 0) {
            Snackbar.make(findViewById(getCoordinatorLayout()), error, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    protected abstract @IdRes int getCoordinatorLayout();
}
