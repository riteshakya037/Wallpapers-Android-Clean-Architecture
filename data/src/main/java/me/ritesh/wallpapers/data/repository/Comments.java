package me.ritesh.wallpapers.data.repository;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import me.ritesh.wallpapers.data.model.objects.CommentsModel;

/**
 * @author Ritesh Shakya
 */

public class Comments implements IComments {
    private DatabaseReference databaseReference;

    public Comments(@NonNull DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        this.databaseReference.keepSynced(true);
    }

    @Override
    public Observable getComments(String photoId) {
        Query query = databaseReference.child(DatabaseNames.TABLE_COMMENTS).child(photoId).orderByChild("date");
        return observe(query);
    }

    @Override
    public Observable sendComment(final String photoId, final CommentsModel message) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<Boolean> emitter) throws Exception {
                databaseReference.child(DatabaseNames.TABLE_COMMENTS)
                        .child(String.valueOf(photoId))
                        .push()
                        .setValue(message)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                emitter.onNext(task.isSuccessful());
                                emitter.onComplete();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                emitter.onError(new FirebaseException(e.getMessage()));
                            }
                        });
            }
        });
    }

    private Observable<DataSnapshot> observe(final Query ref) {
        return Observable.create(new ObservableOnSubscribe<DataSnapshot>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<DataSnapshot> emitter) throws Exception {
                final ValueEventListener listener = ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        emitter.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        ref.removeEventListener(listener);
                    }
                });
            }
        });
    }
}
