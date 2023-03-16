package shop.mtcoding.hibernate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.hibernate.model.User;
import shop.mtcoding.hibernate.model.UserRepository;

import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<?> addUser(User user) {
        User userPS = userRepository.save(user);
        return new ResponseEntity<>(userPS, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, User user) {
        User userPS = userRepository.findById(id);
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        userPS.update(user.getPassword(), user.getEmail());
        User updateUserPS = userRepository.update(userPS);
        return new ResponseEntity<>(updateUserPS, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User userPS = userRepository.findById(id);
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(userPS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findUsers(@RequestParam(defaultValue = "0") int page) {
        List<User> userListPS = userRepository.findAll(page, 2);
        return new ResponseEntity<>(userListPS, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserOne(@PathVariable Long id) {
        User userPS = userRepository.findById(id);
        if (userPS == null) {
            return new ResponseEntity<>("해당 유저가 없습니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPS, HttpStatus.OK);
    }
}