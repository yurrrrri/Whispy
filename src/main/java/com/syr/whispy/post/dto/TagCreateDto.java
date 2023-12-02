package com.syr.whispy.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TagCreateDto {

    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
}
