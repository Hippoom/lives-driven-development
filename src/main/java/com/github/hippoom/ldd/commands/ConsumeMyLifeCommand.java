package com.github.hippoom.ldd.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.hippoom.ldd.web.security.support.CurrentLoggedInUserAware;
import lombok.Data;

@Data
public class ConsumeMyLifeCommand implements CurrentLoggedInUserAware {
    @JsonIgnore
    private String openId;
    private String reason;

    @Override
    public void setCurrentLoggedInUser(String openId) {
        setOpenId(openId);
    }
}
