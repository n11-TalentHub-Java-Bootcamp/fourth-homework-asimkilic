package com.asimkilic.n11.fourthhomework.usr.service;

import com.asimkilic.n11.fourthhomework.usr.converter.UsrUserMapper;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserSaveRequestDto;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import com.asimkilic.n11.fourthhomework.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.asimkilic.n11.fourthhomework.usr.converter.UsrUserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class UsrUserService {
    private final UsrUserEntityService usrUserEntityService;

    public List<UsrUserDto> findAll() {
        List<UsrUser> usrUserList = usrUserEntityService.findAll();

        List<UsrUserDto> userDtoList = INSTANCE.convertToUsrUserDtoList(usrUserList);

        return userDtoList;
    }

    public UsrUserDto save(UsrUserSaveRequestDto userSaveRequestDto) {
        UsrUser usrUser = INSTANCE.convertToUsrUser(userSaveRequestDto);
        usrUser = usrUserEntityService.save(usrUser);
        UsrUserDto usrUserDto = INSTANCE.convertToUsrUserDto(usrUser);
        return usrUserDto;
    }

}
