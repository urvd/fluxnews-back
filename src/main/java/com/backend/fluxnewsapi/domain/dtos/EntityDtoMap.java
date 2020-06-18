package com.backend.fluxnewsapi.domain.dtos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.backend.fluxnewsapi.domain.exceptions.MyMappingException;

public class EntityDtoMap<T,U> {
    //T : entity, U: dto
    private ModelMapper modelMapper;
    public EntityDtoMap(){
        modelMapper = new ModelMapper();
    }
/*    private Map<String, U> map;
    public U getObject(final String key) {
        return map.get(key);
    }*/

    public U convertToDto(T entity, Class<U> u) throws MyMappingException{
        try{
            return modelMapper.map(entity,u);
        }catch (Exception e) {
            throw new MyMappingException("fail to convert to dto");
        }
    }
    public T convertToEntity(U dto, Class<T> t) throws MyMappingException{
        try{
            return modelMapper.map(dto,t);
        }catch (Exception e){
            throw new MyMappingException("fail to convert to entity");
        }
    }

    public List<U> convertToDto(List<T> entities, Class<U> u) throws MyMappingException, ClassNotFoundException {
        List<U> dtos = new ArrayList<>();
        for (T entity : entities) {
            dtos.add(modelMapper.map((T) entity, (Type) u) );
        }
        return dtos;
    }
    public List<T> convertToEntity(List<U> dtos, Class<T> t) throws MyMappingException{
        List<T> entities = new ArrayList<>();
        for (U dto : dtos) {
            entities.add(modelMapper.map((U) dto, (Type) t) );
        }
        return entities;
    }
}
