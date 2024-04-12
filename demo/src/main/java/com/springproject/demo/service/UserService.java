package com.springproject.demo.service;

import com.springproject.demo.model.UserModel;
import com.springproject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel addUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        return userModelOptional.orElse(null);
    }

    public UserModel updateUser(UserModel userModel, Long id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            userModel.setId(id);
            return userRepository.save(userModel);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserModel> getchild(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return userRepository.findAll(sort);
    }

    public List<UserModel> gettchild(int offset, int pagesize) {
        Pageable page = PageRequest.of(offset, pagesize);
        return userRepository.findAll(page).getContent();
    }

    public List<UserModel> getttchild(int offset, int pagesize, String field) {
        return userRepository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }

    public UserModel findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel getUserById1(Long userId) throws NotFoundException {
        Optional<UserModel> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // Handle the case when the user with the given ID is not found
            throw new NotFoundException();
        }
    }
}
