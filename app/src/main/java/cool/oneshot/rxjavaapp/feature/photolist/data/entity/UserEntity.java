package cool.oneshot.rxjavaapp.feature.photolist.data.entity;

import cool.oneshot.rxjavaapp.annotations.ApiSerializable;
import cool.oneshot.rxjavaapp.state.data.Result;

/**
 * Created by brettn on 10/17/16.
 */
@ApiSerializable
public class UserEntity extends Result {

    int id;

    String username;
}
