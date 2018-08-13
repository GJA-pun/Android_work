package com.example.hegua.androidwork.object.hint_object;

/**
 * Created by hegua on 2018/7/19.
 */

public class RegisterHint {
    private String usernameHint = "";
    private String passwordHint = "";
    private String repasswordHint = "";
    private String mismatchHint = "";

    public String getUsernameHint() {
        return usernameHint;
    }

    public void setUsernameHint(String usernameHint) {
        this.usernameHint = usernameHint;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public String getRepasswordHint() {
        return repasswordHint;
    }

    public void setRepasswordHint(String repasswordHint) {
        this.repasswordHint = repasswordHint;
    }

    public String getMismatchHint() {
        return mismatchHint;
    }

    public void setMismatchHint(String mismatchHint) {
        this.mismatchHint = mismatchHint;
    }
}
