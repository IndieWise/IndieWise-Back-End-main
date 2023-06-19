package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;

public class ImageDto {
    @NotBlank
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
