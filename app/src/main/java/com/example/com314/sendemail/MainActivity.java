package com.example.com314.sendemail;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class MainActivity extends AppCompatActivity {
    private final static String mGoogleID="chkm0703@gmail.com";
    private final static String mGooglePW="tkddnjf1!";
    private TextView textView = null;
    private EditText message = null;
    private Button button = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());


        textView = (TextView) findViewById(R.id.u_mail); //받는 사람의 이메일
        message = (EditText) findViewById(R.id.message); //본문 내용

        button = (Button) findViewById(R.id.sendBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GMailSender gMailSender = new GMailSender(mGoogleID, mGooglePW);
                    //GMailSender.sendMail(제목, 본문내용, 받는사람);
                    //userID+님께서 로그인을 시도
                    gMailSender.sendMail("중고장터 로그인 인증메일입니다.", "중고장터 회원이 되신 것을 진심으로 환영합니다.\n이메일 인증을 하지 않을 경우 사이트 이용이 제한이 있으니" +
                            "\n번거로우시더라도 아래 이메일 코드를 어플에 입력하여" +
                            "\n인증절차를 완료해 주시기 바랍니다. \n인증코드 : abc"+gMailSender.getEmailCode(), textView.getText().toString());
                    Log.d("test",gMailSender.getEmailCode());

                    Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
                } catch (SendFailedException e) {
                    Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


