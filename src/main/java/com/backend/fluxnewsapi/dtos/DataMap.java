package com.backend.fluxnewsapi.dtos;

import com.backend.fluxnewsapi.exceptions.MyMappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class DataMap<T,U> {
    private T OT;
    private U OU;
    @Autowired
    private ModelMapper modelMapper;
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public U convertToDto(T entity, Class<U> u) throws MyMappingException{
        if(u == OU){
            return modelMapper.map(entity,u);
        }
        throw new MyMappingException("fail to convert to dto");
    }
    public T convertToEntity(U dto, Class<T> t) throws MyMappingException{
        if(t == OT){
            return modelMapper.map(dto,t);
        }
        throw new MyMappingException("fail to convert to entity");
    }
}
