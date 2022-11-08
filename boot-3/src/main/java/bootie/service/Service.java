package bootie.service;


import bootie.model.User;
import bootie.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final UserRepository userRepository;

    public void start() {

        User user1 = new User("Ira", "ira@gmail.com");
        User user2 = new User("Oleh", "oleh@gmail.com");
        User user3 = new User("Kate", "kate@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


    }


}
