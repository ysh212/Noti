package com.team.seoul.myapplication123.presenter;

import com.team.seoul.myapplication123.view.IMainView;

/**
 * Created by USER on 2017-10-29.
 */

public class MainPresenter implements IMainPresenter {
    IMainView mainView;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }


    @Override
    public void loadAlarm(onButtonClickListener listener) {
        mainView.showAlarm();
        //지워도 됨
        listener.onSuccess();
    }
}
