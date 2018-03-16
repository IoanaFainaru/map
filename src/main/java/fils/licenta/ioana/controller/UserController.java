package fils.licenta.ioana.controller;

import fils.licenta.ioana.model.UserModel;
import fils.licenta.ioana.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(final UserService userService) {
        this.userService = Objects.requireNonNull(userService, "userService must not be null");
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody final UserModel userModel) {
        return ResponseEntity.ok(userService.save(userModel));
    }
}
