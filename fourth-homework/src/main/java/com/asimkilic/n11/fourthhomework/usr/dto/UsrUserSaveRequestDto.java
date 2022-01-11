package com.asimkilic.n11.fourthhomework.usr.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(name="Yeni Kullanıcı oluşturmak için kullanılır.",description = "TCKN için mernis doğrulaması vardır.")
public class UsrUserSaveRequestDto {
    @NotNull
    @Size(min=6,max = 20)
    private String username;

    @NotNull
    @Size(min=6, max = 16)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Min(10000000000L)
    @Max(99999999999L)
    @Schema(description = "11 haneli Türkiye Cumhuriyeti Kimlik Numarası")
    private Long tckn;

    private String imageUrl;

    @Email
    private String email;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    @Schema(description="Başında sıfır olmadan 10 haneli giriniz")
    private String cellPhone;
}
