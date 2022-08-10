package mentoring.service;

import mentoring.exceptions.RecordNotFoundException;
import mentoring.exceptions.RecordsNotFoundForSearchCriteriaException;
import mentoring.model.User;
import mentoring.storingData.Storage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private Storage storage;


    @Override
    public User getUserById(long userId) {
        User user = storage.getUsers().get("user:" + userId);
        if (user != null) {
            return user;
        } else throw new RecordNotFoundException("User with such id does not exist!");
    }

    @Override
    public User getUserByEmail(String email) {
        return storage.getUsers()
                .values()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findAny()
                .orElseThrow(() -> new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!"));
    }

    @Override
    public List<User> getUsersByName(String name, long pageSize, long pageNum) {
        List<User> users = storage.getUsers()
                .values()
                .stream()
                .filter(user -> user.getName().equals(name))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        if (users.size() > 0) {
            return users;
        } else throw new RecordsNotFoundForSearchCriteriaException("User with such email does not exist!");

    }

    @Override
    public User createUser(User user) {
        return storage.save(user);
    }

    @Override
    public User updateUser(long id, User userNew) {
        User user = getUserById(id);
        user.setName(userNew.getName());
        user.setEmail(userNew.getEmail());
        return storage.save(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        getUserById(userId);
        return storage.removeUser(userId);
    }
}
