package com.github.hippoom.ldd.wechat.miniapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wechat.miniapp")
public class WeChatMiniAppProperties {
    private String appId;
    private String secret;
    private String token;
    private String aesKey;
    private String msgDataFormat;
}
