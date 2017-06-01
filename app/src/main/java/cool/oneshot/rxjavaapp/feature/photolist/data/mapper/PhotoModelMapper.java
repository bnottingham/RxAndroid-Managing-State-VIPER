package cool.oneshot.rxjavaapp.feature.photolist.data.mapper;

import cool.oneshot.rxjavaapp.feature.photolist.data.entity.PhotoEntity;
import cool.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brettn on 5/23/17.
 */
public class PhotoModelMapper {

    public static PhotoModel transform(PhotoEntity photoEntity) {
        return new PhotoModel(photoEntity);
    }

    public static List<PhotoModel> transform(List<PhotoEntity> photoEntityList) {
        List<PhotoModel> list = new ArrayList<>();
        for (PhotoEntity entity : photoEntityList) {
            list.add(transform(entity));
        }
        return list;
    }
}
