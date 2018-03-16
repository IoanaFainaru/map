package fils.licenta.ioana.mapper;

import fils.licenta.ioana.entity.User;
import fils.licenta.ioana.model.UserModel;

public final class UserMapper {

    private UserMapper() {

    }

    public static UserModel toModel(final User entity) {
        return UserModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .type(entity.getType().toString())
                .build();
    }

    public static User toEntity(final UserModel model) {
        return User.builder()
                .id(model.getId())
                .name(model.getName())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .type(User.Type.valueOf(model.getType()))
                .build();
    }
}
