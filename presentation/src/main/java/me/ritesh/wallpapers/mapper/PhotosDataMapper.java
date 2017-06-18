package me.ritesh.wallpapers.mapper;

import javax.inject.Inject;
import me.ritesh.wallpapers.data.model.module.PhotosModule;
import me.ritesh.wallpapers.data.model.objects.PhotoModel;
import me.ritesh.wallpapers.data.net.response.objects.PhotoResponse;
import me.ritesh.wallpapers.data.net.response.objects.PhotosListResponse;

/**
 * @author Ritesh Shakya
 */

public class PhotosDataMapper implements IModelDataMapper<PhotosListResponse, PhotosModule> {
    @Inject PhotosDataMapper() {
    }

    @Override public PhotosModule transform(PhotosListResponse photoResponseList) {
        PhotosModule photosModule = new PhotosModule();
        if (photoResponseList == null || photoResponseList.getList().isEmpty()) {
            return photosModule;
        }
        PhotoModel photoModel;

        for (PhotoResponse photoResponse : photoResponseList.getList()) {
            photoModel = new PhotoModel();
            photoModel.setId(photoResponse.getId());
            photoModel.setImgSrc(photoResponse.getWebformatURL());
            //            photoModel.setTags(photoResponse.getTags().split(","));
            photoModel.setUser(photoResponse.getUser());
            photoModel.setUserImageURL(photoResponse.getUserImageURL());
            photosModule.addPhotoModel(photoModel);
        }
        return photosModule;
    }
}
