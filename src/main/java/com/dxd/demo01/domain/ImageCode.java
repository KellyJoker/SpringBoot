package com.dxd.demo01.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCode extends ValidateCode{
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireId) {
        super(code, LocalDateTime.now().plusSeconds(expireId));
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime localDateTime) {
        super(code, localDateTime);
        this.image = image;
    }
}
