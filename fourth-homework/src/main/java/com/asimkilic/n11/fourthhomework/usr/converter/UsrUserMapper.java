package com.asimkilic.n11.fourthhomework.usr.converter;

import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserSaveRequestDto;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {
    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUserDto convertToUsrUserDto(UsrUser usrUser);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);


    UsrUser convertToUsrUser(UsrUserSaveRequestDto usrUserSaveRequestDto);

}
