package com.asimkilic.n11.fourthhomework.usr.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(name="Yeni Kullanıcı oluşturmak için kullanılır.",description = "TCKN için mernis doğrulaması vardır.")
public class UsrUserSaveRequestDto {
    @NotNull
    @Size(min=6,max = 20,message = "Kullanıcı adı en az 6 en fazla 20 karakter olmalıdır")
    @Schema(example = "asimkilic")
    private String username;

    @NotNull
    @Size(min=6, max = 16,message = "Parola en az 6 en fazla 16 karakter uzunluğunda olmalıdır")
    private String password;

    @NotNull(message = "İsim boş olamaz")
    private String firstName;

    @NotNull(message = "Soyisim boş olamaz")
    private String lastName;

    @NotNull
    @Min(value = 10000000000L,message = "11 haneli Türkiye Cumhuriyeti Kimlik Numaranızı giriniz.")
    @Max(value= 99999999999L,message = "11 haneli Türkiye Cumhuriyeti Kimlik Numaranızı giriniz.")
    @Schema(description = "11 haneli Türkiye Cumhuriyeti Kimlik Numarası")
    private Long tckn;

    private String imageUrl;

    @Email(message = "Geçerli bir email adresi giriniz")
    private String email;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})",message = "10 haneli telefon numaranızı giriniz")
    @Schema(description="Başında sıfır olmadan 10 haneli giriniz")
    private String cellPhone;
}
