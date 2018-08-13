package com.example.hegua.androidwork.presenter.registerpresenter;

import com.example.hegua.androidwork.View.RegisterView;
import com.example.hegua.androidwork.object.hint_object.RegisterHint;
import com.example.hegua.androidwork.object.user_object.User;
import com.example.hegua.androidwork.presenter.asynctask.RegisterAsyncTask;
import com.example.hegua.androidwork.utils.Constant;

/**
 * Created by hegua on 2018/7/18.
 */

public class RegisterPresenterDaoImpl implements RegisterPresenterDao {

    private RegisterView registerView;
    private String url;
    private User user;

    public RegisterPresenterDaoImpl(RegisterView registerView){
        this.registerView = registerView;
    }

    @Override
    public void register() {
        RegisterHint registerHint = new RegisterHint();

        boolean flag = false;
        if (registerView.getUsername() == null || registerView.getUsername().equals("")) {
            flag = true;
            registerHint.setUsernameHint("用户名不能少于6位,只能为英文和数字");
        }
        if (registerView.getPassword() == null || registerView.getPassword().equals("")) {
            flag = true;
            registerHint.setPasswordHint("用户名不能少于6位,只能为英文和数字");
        }
        if (registerView.getRepassword() == null || registerView.getRepassword().equals("")) {
            flag = true;
            registerHint.setRepasswordHint("重复密码不能为空");
        }
        if (registerView.getPassword().equals(registerView.getRepassword()) == false) {
            flag = true;
            registerHint.setMismatchHint("两密码不一致");
        }

        if (registerView.getUsername() != null && registerView.getUsername().equals("") == false) {
            if (registerView.getUsername().length() <= 5) {
                flag = true;
                registerHint.setUsernameHint("用户名不能少于6位,只能为英文和数字");
            }

            char[] username = registerView.getUsername().toCharArray();
            for (int i = 0; i < registerView.getUsername().length(); i++) {
                boolean f = false;
                if (username[i] > '9' && username[i] < '0') {
                    System.out.println("username-------->" + username[i]);
                    int g = (int) '9';
                    System.out.println("g------------------------->" + g);
                    flag = true;
                    f = true;
                } else if (username[i] > 'z' && username[i] < 'a') {
                    flag = true;
                    f = true;
                } else if (username[i] > 'Z' && username[i] < 'A') {
                    flag = true;
                    f = true;
                }
                if (f = true) {
                    registerHint.setUsernameHint("密码不能少于6位,只能为英文和数字");
                    break;
                }
            }

        }

        if (registerView.getPassword() != null && registerView.getPassword().equals("") == false) {
            if (registerView.getPassword().length() <= 4) {
                flag = true;
                registerHint.setPasswordHint("密码不能少于6位,只能为英文和数字");
            }

            char[] password = registerView.getPassword().toCharArray();
            for (int i = 0; i < registerView.getPassword().length(); i++) {
                boolean f = false;
                if (password[i] > '9' && password[i] < '0') {
                    System.out.println("username-------->" + password[i]);
                    int g = (int) '9';
                    System.out.println("g------------------------->" + g);
                    flag = true;
                    f = true;
                } else if (password[i] > 'z' && password[i] < 'a') {
                    flag = true;
                    f = true;
                } else if (password[i] > 'Z' && password[i] < 'A') {
                    flag = true;
                    f = true;
                }
                if (f = true) {
                    registerHint.setUsernameHint("密码不能少于6位,只能为英文和数字");
                    break;
                }
            }
        }

        if (registerView.getRepassword() != null && registerView.getRepassword().equals("") == false) {
            if (registerView.getRepassword().length() <= 4) {
                flag = true;
                registerHint.setRepasswordHint(" ");
            }

            char[] repassword = registerView.getRepassword().toCharArray();
            for (int i = 0; i < registerView.getRepassword().length(); i++) {
                boolean f = false;
                if (repassword[i] > '9' && repassword[i] < '0') {
                    System.out.println("username-------->" + repassword[i]);
                    int g = (int) '9';
                    System.out.println("g------------------------->" + g);
                    flag = true;
                    f = true;
                } else if (repassword[i] > 'z' && repassword[i] < 'a') {
                    flag = true;
                    f = true;
                } else if (repassword[i] > 'Z' && repassword[i] < 'A') {
                    flag = true;
                    f = true;
                }
                if (f = true) {
                    registerHint.setUsernameHint("密码不能少于6位,只能为英文和数字");
                    break;
                }
            }

        }

        if (flag) {
            registerView.showRegisterFall(registerHint);
            return;
        }

        user = new User(registerView.getUsername(), registerView.getPassword(), registerView.getRepassword());

        String data = "?username=" + user.getUsername() + "&password=" + user.getPassword();
        System.out.println("username password----------->" + user.getUsername() + " " + user.getPassword());

        url = Constant.URL + Constant.REGISTER + data;
        System.out.println(url);

        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask(registerView);
        registerAsyncTask.execute(url);
    }

}