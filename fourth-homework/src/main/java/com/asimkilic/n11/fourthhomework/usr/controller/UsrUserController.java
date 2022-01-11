package com.asimkilic.n11.fourthhomework.usr.controller;

import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserDto;
import com.asimkilic.n11.fourthhomework.usr.dto.UsrUserSaveRequestDto;
import com.asimkilic.n11.fourthhomework.usr.service.UsrUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<UsrUserDto>> getAllUsers(){
        List<UsrUserDto> usrUserDtoList=usrUserService.findAllUsrUsers();
        return ResponseEntity.ok(usrUserDtoList);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Id ile bulunan kullanıcıyı döner.",description = "Kullanıcı bulunamazsa Exception fırlatır.")
    public ResponseEntity<UsrUserDto> getUsrUserById(@PathVariable String id){
        UsrUserDto usrUserById = usrUserService.findUsrUserById(id);
        return ResponseEntity.ok(usrUserById);
    }
    @GetMapping("/tckn/{tckn}")
    @Operation(summary = "TCKN ile bulunan kullanıcıyı döner.",description = "Kullanıcı bulunamazsa Exception fırlatır.")
    public ResponseEntity<UsrUserDto> getUsrUserByTckn(@PathVariable Long tckn){
        UsrUserDto usrUserByTckn = usrUserService.findUsrUserByTckn(tckn);
        return ResponseEntity.ok(usrUserByTckn);
    }
    @GetMapping("/username/{username}")
    @Operation(summary = "Username ile bulunan kullanıcıyı döner.",description = "Kullanıcı bulunamazsa Exception fırlatır.")
    public ResponseEntity<UsrUserDto> getUsrUserByUsername(@PathVariable String username){
        UsrUserDto usrUserByUsername = usrUserService.findUsrUserByUsername(username);
        return ResponseEntity.ok(usrUserByUsername);
    }


    @PostMapping
    @Operation(summary = "Yeni kullanıcı kaydeder.")
    public ResponseEntity<UsrUserDto> saveUsrUser(@RequestBody @Valid @Parameter(name = "Yeni Kullanıcı",description="Yeni kullanıcı") UsrUserSaveRequestDto usrUserSaveRequestDto){
        UsrUserDto userDto = usrUserService.saveUsrUser(usrUserSaveRequestDto);
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Id'si gönderilen kullanıcıyı siler")
    public void deleteUsrUserById(@PathVariable String id){
        usrUserService.deleteUsrUserById(id);
    }
    @DeleteMapping("/tckn/{tckn}")
    @Operation(summary = "TCKN ile gönderilen kullanıcıyı siler")
    public void deleteUsrUserById(@PathVariable Long tckn){
        usrUserService.deleteUsrUserByTckn(tckn);
    }

}