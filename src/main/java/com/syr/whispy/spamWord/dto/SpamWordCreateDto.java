package com.syr.whispy.spamWord.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SpamWordCreateDto {

    @Size(min = 1, max = 20)
    private String word;
}
