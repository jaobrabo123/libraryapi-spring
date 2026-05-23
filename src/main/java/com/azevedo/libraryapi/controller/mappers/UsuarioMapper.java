package com.azevedo.libraryapi.controller.mappers;

import com.azevedo.libraryapi.controller.dto.UsuarioDTO;
import com.azevedo.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
