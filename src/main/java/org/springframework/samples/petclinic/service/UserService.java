package org.springframework.samples.petclinic.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	@Transactional
	public List<User> findAll() throws DataAccessException {
		return (List<User>) userRepository.findAll();
		
	}

	
	@Transactional
	public void deleteUser(User user) throws DataAccessException {
		userRepository.delete(user);
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}

}
