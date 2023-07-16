package com.syr.whispy.spamWord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SpamWordCreateDto {

    @NotBlank
    @Size(min = 1, max = 20)
    private String word;

}
