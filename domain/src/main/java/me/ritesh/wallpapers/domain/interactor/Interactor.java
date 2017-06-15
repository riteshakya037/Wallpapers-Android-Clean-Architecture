package me.ritesh.wallpapers.domain.interactor;

import io.reactivex.Observer;

/**
 * @author Ritesh Shakya
 */

interface Interactor {
    void execute(Observer UseCaseSubscriber, Object... params);

    void unsubscribe();
}
