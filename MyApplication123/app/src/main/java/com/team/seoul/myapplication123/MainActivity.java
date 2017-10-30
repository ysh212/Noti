package com.team.seoul.myapplication123;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.team.seoul.myapplication123.presenter.IMainPresenter;
import com.team.seoul.myapplication123.presenter.MainPresenter;
import com.team.seoul.myapplication123.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView, IMainPresenter.onButtonClickListener{
    private MainPresenter mainPresenter;
    private Button exampleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        exampleBtn = (Button) findViewById(R.id.exampleBtn);
        //버튼이 눌렸을 때의 성공 이벤트 - Notification 띄우기
        exampleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mainPresenter.loadAlarm(MainActivity.this);
            }
        });

    }
    @Override
    public void showAlarm() {
        Log.d("click2", "click");
        showBasicNotification();
    }

    //확인용 - 지워도 됨
    @Override
    public void onSuccess() {
        Log.d("click1", "click");
        Toast.makeText(MainActivity.this, "클릭 성공", Toast.LENGTH_SHORT).show();
    }

    /**
     * 가장 기본적인 노티피케이션
     */
    private void showBasicNotification() {
        //알림에 대한 UI 정보와 작업을 지정하기 위한 개체 - NotificationCompat.Builder
        NotificationCompat.Builder mBuilder = createNotification();
        mBuilder.setContentIntent(createPendingIntent());

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //알림 발행 - notify(), 알림 자체를 생성 - build(), 첫번째 인자는 id 번호 값
        mNotificationManager.notify(1, mBuilder.build());
    }

    /**
     * 노티피케이션 빌드
     * @return
     */
    private NotificationCompat.Builder createNotification(){
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) //필수
                .setLargeIcon(icon)
                .setContentTitle("Tourist Title")   //필수
                .setContentText("Tourist subTitle") //필수
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }
        return builder;
    }

    /**
     * 노티피케이션을 누르면 실행되는 기능을 가져오는 노티피케이션
     *
     * 실제 기능을 추가하는 것
     * @return
     */
    private PendingIntent createPendingIntent(){
        Intent resultIntent = new Intent(this, SubActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SubActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        return stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
