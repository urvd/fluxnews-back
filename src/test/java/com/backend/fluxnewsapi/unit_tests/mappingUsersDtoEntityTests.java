package com.backend.fluxnewsapi.unit_tests;

import com.backend.fluxnewsapi.dtos.EntityDtoMap;
import com.backend.fluxnewsapi.dtos.models.ArticleDto;
import com.backend.fluxnewsapi.dtos.models.UserDto;
import com.backend.fluxnewsapi.exceptions.MyMappingException;
import com.backend.fluxnewsapi.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class mappingUsersDtoEntityTests {

    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    EntityDtoMap<User, UserDto> entityDtoMapUser;
    @InjectMocks
    EntityDtoMap<User, ArticleDto> entityDtoMapNotGood;

    @BeforeEach
    void setUp(){
        entityDtoMapUser = new EntityDtoMap<>();
        entityDtoMapNotGood = new EntityDtoMap<>();
    }

    @Test
    public void mappingUserDtoConvertToEntityTest() throws MyMappingException {
        //given
        UserDto userDto = new UserDto();
        userDto.setId(0);
        userDto.setEmail("pascalcoum@gjf.fr");
        userDto.setUsername("pascoum_077");
        userDto.setPassword("mypswd");

        //when
        User user = entityDtoMapUser.convertToEntity(userDto,User.class);

        //Then
        assertThat(user.getId()).isEqualTo(userDto.getId());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
    }
    @Test
    public void mappingUserConvertToDtoTest() throws MyMappingException {
        //given
        User user = new User();
        user.setId(0);
        user.setEmail("pascalcoum@gjf.fr");
        user.setUsername("pascoum_077");
        user.setPassword("mypswd");

        //when
        UserDto userDto = entityDtoMapUser.convertToDto(user,UserDto.class);

        //Then
        assertThat(user.getId()).isEqualTo(userDto.getId());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(user.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
    }
    @Test
    public void mappingUserConvertToDtoThrowExceptionTest() throws MyMappingException {
        //given
        User user = new User();
        user.setId(0);
        user.setEmail("pascalcoum@gjf.fr");
        user.setUsername("pascoum_077");
        user.setPassword("mypswd");

        try {
            //when
            ArticleDto articleDto = entityDtoMapNotGood.convertToDto(user, ArticleDto.class);
        }catch (Exception e) {
            //Then
            org.junit.jupiter.api.Assertions.assertTrue(e instanceof MyMappingException);
        }
    }
}
