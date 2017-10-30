package com.team.seoul.myapplication123.presenter;

/**
 * Created by USER on 2017-10-29.
 */

public interface IMainPresenter {
    //버튼이 클릭되었을때 Notification 발생시키기 - 확인용
    interface onButtonClickListener{
        void onSuccess();
    }

    //버튼이 클릭되면(- 데이터 들어오면) 창에 알림 띄우기
    void loadAlarm(onButtonClickListener listener);
}
