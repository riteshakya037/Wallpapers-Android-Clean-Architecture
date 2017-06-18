package me.ritesh.wallpapers.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.database.DataSnapshot;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import me.ritesh.wallpapers.R;
import me.ritesh.wallpapers.data.model.PhotoModel;
import me.ritesh.wallpapers.imageloader.IImageLoader;
import me.ritesh.wallpapers.view.screen.photo_listing.PhotosPresenter;

/**
 * @author Ritesh Shakya
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {
    private final IImageLoader imageLoader;
    private final PhotosPresenter photosPresenter;

    public PhotosAdapter(IImageLoader imageLoader, PhotosPresenter photosPresenter) {
        this.imageLoader = imageLoader;
        this.photosPresenter = photosPresenter;
    }

    @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photos_screen, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override public void onBindViewHolder(MyViewHolder holder, int position) {
        PhotoModel photoModel = photosPresenter.getModel().getPhotoModels().get(position);
        holder.setData(photoModel);
    }

    @Override public int getItemCount() {
        if (photosPresenter.getModel() == null) {
            return 0;
        }
        return photosPresenter.getModel().getPhotoModels() == null ? 0
                : photosPresenter.getModel().getPhotoModels().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_photos_screen_user) TextView tvUser;
        @BindView(R.id.item_photos_screen_comments) TextView tvComments;
        @BindView(R.id.activity_comments_image) ImageView ivPhotos;
        @BindView(R.id.item_photos_screen_user_image) CircleImageView ivUserImage;
        int comments = 0;
        PhotoModel photoModel;
        Observer subscriber;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_photos_screen_comments) public void onCommentClick() {
            photosPresenter.onCommentClick(photoModel);
        }

        public void setData(PhotoModel photoModel) {
            if (this.photoModel != null && subscriber != null) {
                subscriber.onComplete();
                this.photoModel = null;
            }
            this.photoModel = photoModel;

            comments = 0;
            tvUser.setText(photoModel.getUser());
            imageLoader.loadImage(photoModel.getImgSrc(), ivPhotos);
            imageLoader.loadImage(photoModel.getUserImageURL(), ivUserImage);
            subscriber = new Observer<DataSnapshot>() {

                @Override public void onError(Throwable e) {

                }

                @Override public void onComplete() {

                }

                @Override public void onSubscribe(@NonNull Disposable d) {

                }

                @Override public void onNext(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getKey()
                            .equals(String.valueOf(MyViewHolder.this.photoModel.getId()))) {
                        comments = (int) dataSnapshot.getChildrenCount();
                        updateCommentsNumber();
                    }
                }
            };

            updateCommentsNumber();
            photosPresenter.getComments(subscriber, String.valueOf(this.photoModel.getId()));
        }

        private void updateCommentsNumber() {
            if (comments == 0) {
                tvComments.setText(R.string.no_comments);
            } else {
                tvComments.setText(tvComments.getContext()
                        .getResources()
                        .getQuantityString(R.plurals.number_of_comments, comments, comments));
            }
        }
    }
}
