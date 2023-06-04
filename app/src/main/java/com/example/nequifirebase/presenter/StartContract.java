package com.example.nequifirebase.presenter;


public interface StartContract {
    interface View {
        void showUserInfo(String name, String email);
    }

    interface Presenter {
        void getUserInfo(String telefono);

    }
}