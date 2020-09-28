package top.erzhiqian.auth.authenticate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WechatCode2SessionDto {
    private String code;
}
