package com.asimkilic.n11.fourthhomework.usr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="Kullanıcı görüntülemek için kullanılır.")
public class UsrUserDto {

    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private Long tckn;

    private String imageUrl;

    private String email;

    private String cellPhone;
}
