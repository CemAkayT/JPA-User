package cem.service;

import cem.model.User;
import cem.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service // skal dukke op i Spring miljøet så vi kan bruge den fra controlleren
public class UserService implements IUserService {

    //skal have et objekt fra UserReposityory interface
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) { // vi får et UserRepository objekt
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long aLong) { // ved ikke helt hvad meningen er med den her?
        return Optional.empty();
    }

    @Override
    public Set<User> findAll() {
        // opretter tomt set
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Id not found");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void delete(User user) { // Ved ikke hvordan man skal implementere metoden
        userRepository.delete(user);
    }

    public boolean existsById(Long aLong) {
      return userRepository.existsById(aLong);
    }
}
