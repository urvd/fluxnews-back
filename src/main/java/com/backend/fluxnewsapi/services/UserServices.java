package com.backend.fluxnewsapi.services;

import com.backend.fluxnewsapi.domain.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.domain.dtos.models.UserDto;
import com.backend.fluxnewsapi.domain.exceptions.RessourceException;
import com.backend.fluxnewsapi.domain.exceptions.ErrorCode;
import com.backend.fluxnewsapi.infrastucture.models.User;
import com.backend.fluxnewsapi.infrastucture.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServices {

    private UsersRepository usersRepository;

    @Autowired
    public UserServices(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public User findUser(Long id) throws RessourceException {
        return usersRepository.findById(id)
                    .orElseThrow(() -> new RessourceException(ErrorCode.NOT_FOUND));
    }

    public User findUserWithUsername(String name) throws RessourceException {
        try{
            return usersRepository.findByUsername(name);
        }catch (Exception e){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }
    }
    public User findUserWithEmail(String email) throws RessourceException {
        try{
            return usersRepository.findByEmail(email);
        }catch (Exception e){
            throw new RessourceException(ErrorCode.NOT_FOUND);
        }
    }

    public Boolean saveUser(UserDto userDto) throws RessourceException {
        EntityDtoMap<User,UserDto> mapIn= new EntityDtoMap();

        try{
            User u = usersRepository.save(mapIn.convertToEntity(userDto,User.class));
            return u != null;

        }catch(Exception e){
            throw new RessourceException(ErrorCode.UNSAVED);
        }
    }
    public Boolean saveUser(User user) throws RessourceException {
        try{
            User u = usersRepository.save(user);
            return u != null;

        }catch(Exception e){
            throw new RessourceException(ErrorCode.UNSAVED);
        }
    }

    public void deleteUser(Long userid) throws RessourceException {

        User u = usersRepository.findById(userid)
                    .orElseThrow(() -> new RessourceException(ErrorCode.NOT_FOUND));
        try{
            usersRepository.delete(u);
        }catch(Exception e){
            throw new RessourceException(ErrorCode.WHILE_DELETING);
        }
    }
}
