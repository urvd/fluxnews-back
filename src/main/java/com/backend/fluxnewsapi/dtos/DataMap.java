package com.backend.fluxnewsapi.dtos;

import com.backend.fluxnewsapi.exceptions.MyMappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class DataMap<T,U> {
    //T : entity, U: dto
    private ModelMapper modelMapper;
    public DataMap(){
        modelMapper = new ModelMapper();
    }

    public U convertToDto(T entity, Class<U> u) throws MyMappingException{
        //ModelMapper modelMapper = new ModelMapper();
        try{
            return modelMapper.map(entity,u);
        }catch (Exception e) {
            throw new MyMappingException("fail to convert to dto");
        }
    }
    public T convertToEntity(U dto, Class<T> t) throws MyMappingException{
        //ModelMapper modelMapper = new ModelMapper();
        try{
            return modelMapper.map(dto,t);
        }catch (Exception e){
            throw new MyMappingException("fail to convert to entity");
        }

    }
}
