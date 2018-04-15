package com.github.hippoom.ldd.commands;

import com.github.hippoom.ldd.web.security.support.CurrentLoggedInUserAware;
import lombok.Data;

@Data
public class RestoreMyLivesCommand implements CurrentLoggedInUserAware {
    private String openId;

    @Override
    public void setCurrentLoggedInUser(String openId) {
        setOpenId(openId);
    }
}
