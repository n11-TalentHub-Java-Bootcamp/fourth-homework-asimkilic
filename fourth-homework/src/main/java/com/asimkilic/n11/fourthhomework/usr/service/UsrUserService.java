package com.asimkilic.n11.fourthhomework.usr.service;

import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserSaveRequestDto;
import com.asimkilic.n11.fourthhomework.usr.entity.UsrUser;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserCellPhoneAlreadyRegisteredException;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserNotFoundException;
import com.asimkilic.n11.fourthhomework.usr.exception.UsrUserTcknAlreadyRegisteredException;
import com.asimkilic.n11.fourthhomework.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.asimkilic.n11.fourthhomework.usr.converter.UsrUserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class UsrUserService {
    private final UsrUserEntityService usrUserEntityService;

    protected UsrUser findUsrUserEntityById(String id) {
        return usrUserEntityService.findById(id)
                .orElseThrow(() -> new UsrUserNotFoundException("User could not find by id : " + id));
    }

    protected UsrUser findUsrUserEntityByTckn(Long tckn) {
        return usrUserEntityService.findByTckn(tckn)
                .orElseThrow(() -> new UsrUserNotFoundException("User could not find by TCKN : " + tckn));
    }

    protected boolean isCellPhoneRegistered(String cellPhone) {
        return usrUserEntityService.countUsrUserByCellPhone(cellPhone) > 0;
    }

    protected boolean isTcknRegistered(Long tckn) {
        return usrUserEntityService.countUsrUserByTckn(tckn) > 0;
    }

    public List<UsrUserDto> findAllUsrUsers() {
        return usrUserEntityService
                .findAll()
                .stream()
                .map(INSTANCE::convertToUsrUserDto)
                .collect(Collectors.toList());
    }

    public UsrUserDto findUsrUserById(String id) {
        UsrUser usrUserById = usrUserEntityService.findById(id)
                .orElseThrow(() -> new UsrUserNotFoundException("User could not find by id : " + id));
        return INSTANCE.convertToUsrUserDto(usrUserById);
    }


    public UsrUserDto findUsrUserByTckn(Long tckn) {
        UsrUser usrUserByTckn = usrUserEntityService.findByTckn(tckn)
                .orElseThrow(() -> new UsrUserNotFoundException("User could not find by TCKN : " + tckn));
        return INSTANCE.convertToUsrUserDto(usrUserByTckn);
    }

    public UsrUserDto findUsrUserByUsername(String username) {

        UsrUser usrUserByUsername = usrUserEntityService.findByUsername(username)
                .orElseThrow(() -> new UsrUserNotFoundException("User could not find by username : " + username));
        return INSTANCE.convertToUsrUserDto(usrUserByUsername);
    }

    public UsrUserDto saveUsrUser(UsrUserSaveRequestDto userSaveRequestDto) {
        if (isCellPhoneRegistered(userSaveRequestDto.getCellPhone())) {
            throw new UsrUserCellPhoneAlreadyRegisteredException(userSaveRequestDto.getCellPhone() + " telefon numarası zaten kayıtlı.");
        }
        if (isTcknRegistered((userSaveRequestDto.getTckn()))) {
            throw new UsrUserTcknAlreadyRegisteredException(userSaveRequestDto.getTckn() + " TCKN zaten kayıtlı.");
        }
        UsrUser usrUser = INSTANCE.convertToUsrUser(userSaveRequestDto);
        usrUser = usrUserEntityService.save(usrUser);
        return INSTANCE.convertToUsrUserDto(usrUser);
    }

    public void deleteUsrUserById(String id) {

        UsrUser usrUserById = findUsrUserEntityById(id);
        usrUserEntityService.delete(usrUserById);
    }

    public void deleteUsrUserByTckn(Long tckn) {

        UsrUser usrUserById = findUsrUserEntityByTckn(tckn);
        usrUserEntityService.delete(usrUserById);
    }


}
