package by.clevertec.mapper;

import by.clevertec.dto.UserDto;
import by.clevertec.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToDto(User user);

    User DtoToUser(UserDto userDto);

}
