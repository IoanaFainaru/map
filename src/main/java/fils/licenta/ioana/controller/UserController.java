package fils.licenta.ioana.controller;

import fils.licenta.ioana.model.UserModel;
import fils.licenta.ioana.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(final UserService userService) {
        this.userService = Objects.requireNonNull(userService, "userService must not be null");
    }

    @GetMapping("/{username}")
    public UserModel getUser(@PathVariable("username") final String username) {
        return userService.getUserCredentials(username);
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody final UserModel userModel) {
        return ResponseEntity.ok(userService.save(userModel));
    }
}
