package com.example.covid_tracing_app;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SignupActivity_C extends AppCompatActivity {
    private EditText editID;
    private EditText editPassword;
    private EditText editConfirm;
    private EditText editName;
    private EditText editBirth;
    private EditText editPhone;
    private Button btnNext;

    private String ID = "";

    private boolean isPasswordEnable = false;
    private boolean isConfirmEnable = false;
    private boolean isNameEnable = false;
    private boolean isBirthEnable = false;
    private boolean isPhoneEnable = false;

    private String pass = "";
    private String confirm = "";

    private String url = "http://203.250.32.29:8083";
    //private String url = "http://1.251.103.64:8888";
    //private String url = "http://180.189.121.112:63000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_c);

        editID = (EditText)findViewById(R.id.editTextID);
        editPassword = (EditText)findViewById(R.id.editTextPassword);
        editConfirm = (EditText)findViewById(R.id.editTextConfirm);
        editName = (EditText)findViewById(R.id.editTextName);
        editBirth = (EditText)findViewById(R.id.editTextBirth);
        editPhone = (EditText)findViewById(R.id.editTextPhone);
        btnNext = (Button)findViewById(R.id.buttonNext);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        ID = intent.getExtras().getString("email");

        if(ID!=""){
            editID.setText(ID);
            editID.setTextColor(ContextCompat.getColor(SignupActivity_C.this,R.color.colorTextHidden));
            editID.setEnabled(false);
            editID.setClickable(false);
        }else{
            Toast.makeText(getApplicationContext(),"이메일 값을 불러오지 못하였습니다.", Toast.LENGTH_SHORT).show();
        }

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pass = String.valueOf(editPassword.getText());

                if(pass.equals(confirm)){
                    editConfirm.setBackground(ContextCompat.getDrawable(SignupActivity_C.this,R.drawable.editpassowrd));
                    isPasswordEnable = true;
                }
                else{
                    editConfirm.setBackground(ContextCompat.getDrawable(SignupActivity_C.this,R.drawable.editerror));
                    isPasswordEnable = false;
                }

                if(isPasswordEnable&&isConfirmEnable&&isNameEnable&&isBirthEnable&&isPhoneEnable){
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnsignup));
                    btnNext.setEnabled(true);
                }
                else{
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnnext));
                    btnNext.setEnabled(false);
                }
            }
        });

        editConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length()>=8){
                    isConfirmEnable = true;
                }
                else{
                    isConfirmEnable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                confirm = String.valueOf(editConfirm.getText());

                if(pass.equals(confirm)){
                    editConfirm.setBackground(ContextCompat.getDrawable(SignupActivity_C.this,R.drawable.editpassowrd));
                    isPasswordEnable = true;
                }
                else{
                    editConfirm.setBackground(ContextCompat.getDrawable(SignupActivity_C.this,R.drawable.editerror));
                    isPasswordEnable = false;
                }

                if(isPasswordEnable&&isConfirmEnable&&isNameEnable&&isBirthEnable&&isPhoneEnable){
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnsignup));
                    btnNext.setEnabled(true);
                }
                else{
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnnext));
                    btnNext.setEnabled(false);
                }
            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=2){
                    isNameEnable = true;
                }
                else{
                    isNameEnable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isPasswordEnable&&isConfirmEnable&&isNameEnable&&isBirthEnable&&isPhoneEnable){
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnsignup));
                    btnNext.setEnabled(true);
                }
                else{
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnnext));
                    btnNext.setEnabled(false);
                }
            }
        });

        editBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=8){
                    isBirthEnable = true;
                }
                else{
                    isBirthEnable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isPasswordEnable&&isConfirmEnable&&isNameEnable&&isBirthEnable&&isPhoneEnable){
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnsignup));
                    btnNext.setEnabled(true);
                }
                else{
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnnext));
                    btnNext.setEnabled(false);
                }
            }
        });

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=10){
                    isPhoneEnable = true;
                }
                else{
                    isPhoneEnable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isPasswordEnable&&isConfirmEnable&&isNameEnable&&isBirthEnable&&isPhoneEnable){
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnsignup));
                    btnNext.setEnabled(true);
                }
                else{
                    btnNext.setBackground(ContextCompat.getDrawable(SignupActivity_C.this, R.drawable.btnnext));
                    btnNext.setEnabled(false);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editPassword.getText().toString().trim();
                String name = editName.getText().toString().trim();
                String birth = editBirth.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                try {
                    /* DB 대조 */
                    JSONObject values = new JSONObject();
                    values.put("email", ID);
                    values.put("password", password);
                    values.put("name", name);
                    values.put("birthdate", birth);
                    values.put("phone", phone);

                    NetworkTask networkTask = new NetworkTask(url+"/user/sign-up", values, "POST");
                    networkTask.execute();//서버로 인증코드 요청 후 반환
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally {

                }


            }
        });
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        String url;
        JSONObject values;
        String method;

        NetworkTask(String url, JSONObject values, String method){
            this.url = url;
            this.values = values;
            this.method = method;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progress bar를 보여주는 등등의 행위
        }

        @Override
        protected String doInBackground(Void... params) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values, method);
            return result; // 결과가 여기에 담깁니다. 아래 onPostExecute()의 파라미터로 전달됩니다.
        }

        @Override
        protected void onPostExecute(String result) {
            // 통신이 완료되면 호출됩니다.
            // 결과에 따른 UI 수정 등은 여기서 합니다.
            if(result.contains("201")){
                Toast.makeText(getApplicationContext(), result.substring(result.indexOf("_")+1,result.indexOf(":")), Toast.LENGTH_LONG).show();

                String data = result.substring(result.indexOf(":")+1);
                String uid = "";
                String token = "";
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    uid = jsonObject.getString("userId");
                    token = jsonObject.getString("token");

                    SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("uid",uid);
                    editor.commit();
                    editor.putString("token",token);
                    editor.commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SignupActivity_C.this, SignupActivity_D.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            } else{
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            }

        }
    }

}