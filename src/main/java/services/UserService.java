package services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;
import requestModels.UserRequest;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User addUser(UserRequest userRequest){
        String userName = userRequest.getUserName();
        String pw = userRequest.getPassword();
        String email = userRequest.getEmail();

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassWord(pw);
        return this.userRepo.save(user);
    }

    public User findUser(UserRequest userRequest) {
        try {
            this.userRepo.getOne(userRequest.getId());
        } catch (Exception e) {
            System.out.println("User does not exist");
        }
        return this.userRepo.getOne(userRequest.getId());
    }

    public User findUserById(Long id){
        Optional<User> user = this.userRepo.findById(id);
        if(!user.isPresent()){
            System.out.println("User does not exist");
        }
        return user.get();
    }

    public Iterable<User> findAllUsers(){
        return this.userRepo.findAll();
    }

    public User updateUser(Long id, UserRequest newUser){
        User user = this.userRepo.getOne(id);
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPassWord(newUser.getPassword());

        return this.userRepo.save(user);
    }

    public void deleteUser(UserRequest userRequest){
        try {
            this.userRepo.delete(this.userRepo.getOne(userRequest.getId()));
        } catch (Exception e){
            System.out.println("User does not exist");
        }
    }

    public void deleteUserById(Long id){
        try {
           this.userRepo.deleteById(id);
        } catch (Exception e){
            System.out.println("User does not exist");
        }
    }
}
