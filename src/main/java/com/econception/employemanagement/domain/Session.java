package com.econception.employemanagement.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Session {
    private User currentUser;
    private boolean loginFailed;

    public void setUser(User currentUser) {
        this.currentUser = currentUser;

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public Long getCurrentUserId() {
        return this.currentUser.getId();
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }
}
