package me.ritesh.wallpapers.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import me.ritesh.wallpapers.view.presenter.Presenter;
import org.parceler.Parcels;

/**
 * @author Ritesh Shakya
 */

public abstract class BaseFragment<M> extends Fragment implements IView<M> {
    private final String SAVE_INSTANCE_STATE = "FragmentSaveInstanceState";

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectFragment();
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        getPresenter().setView(this);
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void injectFragment();

    @Override public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putParcelable(SAVE_INSTANCE_STATE,
                Parcels.wrap(getPresenter().getModel()));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            getPresenter().setModel(
                    Parcels.unwrap(savedInstanceState.getParcelable(SAVE_INSTANCE_STATE)));
        }
    }

    @NonNull protected abstract Presenter<IView<M>, M> getPresenter();

    @Override public void onStartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override public void onError(String message) {

    }

    @Override public void onStop() {
        super.onStop();
        getPresenter().stop();
    }

    @Override public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }
}
