package com.asimkilic.n11.fourthhomework.usr.controller;

import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserSaveRequestDto;
import com.asimkilic.n11.fourthhomework.usr.service.UsrUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Kullanıcı İşlemleri", description = "UsrUser API")
public class UsrUserController {
    private final UsrUserService usrUserService;

    @GetMapping
    @Operation(summary="Bütün kullanıcıları döndürür" , description="Kullanıcıları DTO olarak döndürür.")
    public ResponseEntity getAll(){
        List<UsrUserDto> usrUserDtoList=usrUserService.findAll();
        return ResponseEntity.ok(usrUserDtoList);
    }

    @PostMapping
    public ResponseEntity saveUsrUser(@RequestBody @Valid @Parameter(name = "Yeni Kullanıcı",description="Yeni kullanıcı") UsrUserSaveRequestDto usrUserSaveRequestDto){
        UsrUserDto userDto = usrUserService.save(usrUserSaveRequestDto);
        return ResponseEntity.ok(userDto);
    }
}