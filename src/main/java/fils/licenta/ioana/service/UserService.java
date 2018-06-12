package fils.licenta.ioana.service;

import fils.licenta.ioana.exception.EntityAlreadyExistsException;
import fils.licenta.ioana.exception.EntityNotFoundException;
import fils.licenta.ioana.mapper.UserMapper;
import fils.licenta.ioana.model.UserModel;
import fils.licenta.ioana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static fils.licenta.ioana.mapper.UserMapper.toEntity;
import static fils.licenta.ioana.mapper.UserMapper.toModel;

@Service
public class UserService {

    public static final String USERNAME_ALREADY_EXISTS_ERROR_MESSAGE = "Username already exists!";

    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "userRepository must not be null");
//        this.passwordEncoder = Objects.requireNonNull(passwordEncoder, "passwordEncoder must not be null");
    }

    public UserModel save(final UserModel userModel) {
//        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        checkIfUsernameAlreadyExists(userModel);
        return toModel(userRepository.save(toEntity(userModel)));
    }

    public UserModel getUserCredentials(final String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("This username does not exist!"));
    }

    private void checkIfUsernameAlreadyExists(final UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername().trim()).isPresent()) {
            throw new EntityAlreadyExistsException(USERNAME_ALREADY_EXISTS_ERROR_MESSAGE);
        }
    }
}
