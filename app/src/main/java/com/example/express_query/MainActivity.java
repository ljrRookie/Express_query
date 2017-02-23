package com.example.express_query;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private static final int LOADDATA = 0;
    private static final int SHOW_EXPRESSINFO = 1;
    private ArrayList<CompanyInfo> mCompanyInfoList;
    private EditText mCompany;
    private EditText mOrder;
    private Button mQuery;
    private String mCompanyNo;
    private String key = "8badc93851871a0d25fca3d60fb94cf1";
    private static final String TAG = "MainActivity";
    private String mOrderStr;
    private ExpressInfo mExpressInfo;
   private Handler mHandler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             switch(msg.what){
                 case LOADDATA:
                     Toast.makeText(MainActivity.this, "完成读取公司数据", Toast.LENGTH_SHORT).show();
                     break;
                 case SHOW_EXPRESSINFO:
                     List<ExpressInfo.ResultBean.ListBean> list = mExpressInfo.getResult().getList();
                     for(int i = 0; i < list.size(); i++){
                         String datetime = list.get(i).getDatetime();
                         String remark = list.get(i).getRemark();
                         Log.d(TAG, "handleMessage: "+datetime);
                         Log.d(TAG, "handleMessage: "+remark);
                         TextView tv_data = new TextView(MainActivity.this);
                         tv_data.setTextColor(Color.BLACK);
                         tv_data.setText(datetime+"==="+remark);
                         mActivityMain.addView(tv_data,-1);
                     }
                     break;
             }

         }
     } ;
    private LinearLayout mActivityMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCompanyData();
        initView();


    }


    private void initView() {
        mCompany = (EditText) findViewById(R.id.company);
        mOrder = (EditText) findViewById(R.id.order);
        mQuery = (Button) findViewById(R.id.query);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mCompanyNameStr = mCompany.getText().toString().trim();
                mOrderStr = mOrder.getText().toString().trim();
                Log.d(TAG, "onClick: " + mCompanyNameStr + "===" + mOrderStr);
                if (!TextUtils.isEmpty(mCompanyNameStr) && !TextUtils.isEmpty(mOrderStr)) {

                    Log.d(TAG, "onClick: " + mCompanyNameStr + "===" + mOrderStr);
                    mCompanyNo = null;
                    for (CompanyInfo mCompanyInfo : mCompanyInfoList) {
                        Log.d(TAG, "mCompanyInfo: " + mCompanyInfo.com);
                        if (mCompanyInfo.com.equals(mCompanyNameStr)) {
                            mCompanyNo = mCompanyInfo.no;
                            Log.d(TAG, "CompanyInfo: " + mCompanyInfo.com + "==" + mCompanyNo);
                            Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();

                        }
                    }
                    if (mCompanyNo == null) {
                        Toast.makeText(MainActivity.this, "快递公司输入有误", Toast.LENGTH_SHORT).show();
                    } else {
                          getExpreeData();
                    }


                } else if (TextUtils.isEmpty(mCompanyNameStr)) {
                    Toast.makeText(MainActivity.this, "请输入公司名称", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mOrderStr)) {
                    Toast.makeText(MainActivity.this, "请输入订单号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getExpreeData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String mDataUrl = "http://v.juhe.cn/exp/index?key=" + key + "&com=" + mCompanyNo + "&no=" + mOrderStr;
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(mDataUrl)
                            .build();
                    Response response = client.newCall(request).execute();
                    String mExpressData = response.body().string();
                    Log.d(TAG, "run: " + mExpressData);
                    parseJsonWithGson(mExpressData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void parseJsonWithGson(String responseData) {
        Gson gson = new Gson();
        mExpressInfo = gson.fromJson(responseData, ExpressInfo.class);
        mHandler.sendEmptyMessage(SHOW_EXPRESSINFO);

    }

    private List<CompanyInfo> initCompanyData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://v.juhe.cn/exp/com?key=8badc93851871a0d25fca3d60fb94cf1")
                            .build();
                    Response response = client.newCall(request).execute();
                    String companyData = response.body().string();
                    JSONArray array = new JSONObject(companyData).getJSONArray("result");
                    mCompanyInfoList = new ArrayList<CompanyInfo>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = (JSONObject) array.get(i);
                        String com = jsonObject.getString("com");
                        String no = jsonObject.getString("no");
                        Log.d(TAG, "parseJsonWithJSONObject: com: " + com);
                        Log.d(TAG, "parseJsonWithJSONObject: no: " + no);
                        mCompanyInfoList.add(new CompanyInfo(com, no));
                    }
                        mHandler.sendEmptyMessage(LOADDATA);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return null;
    }

    class CompanyInfo {
        private String com;
        private String no;


        public CompanyInfo(String com, String no) {
            this.com = com;
            this.no = no;
        }
    }

}

