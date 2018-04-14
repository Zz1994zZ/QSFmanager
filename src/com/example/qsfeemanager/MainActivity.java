package com.example.qsfeemanager;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Toast toast=null;
    TextView  text;
    TextView  text2;
    CheckBox  box1,box2;
    EditText  e;
    int fee=0;
    int head;
    String payfor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("进去了！");
		 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
	        fee =sp.getInt("fee",-1);
	        payfor=sp.getString("payfor",null);
	        text=(TextView)findViewById(R.id.textView4);
	        if(fee!=-1)
	        	text.setText(""+fee);
	        else
	        	text.setText("请充值");
	        text2=(TextView)findViewById(R.id.textView2);
	        if(payfor!=null)
	        {
	        	text2.setText(payfor);
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {                                        //设置菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override                                                                              //设置菜单点击效果
	public boolean onOptionsItemSelected(MenuItem item) {
		//
		final EditText Number=new EditText(this);
		OnClickListener click=new OnClickListener(){
			@Override
			   public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String num=Number.getText().toString();
				System.out.println("num为"+num);
				if(num.equals("000000")){
						 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
						 Editor editor=sp.edit();
						 fee=0;
						 payfor="";
						 editor.putInt("fee",fee);
						 editor.putString("payfor",payfor);
						 editor.commit();
						 refnum();
						 Toast.makeText(MainActivity.this,"初始化成功", Toast.LENGTH_LONG).show();	
			}
				else
					 Toast.makeText(MainActivity.this,"密码错误！", Toast.LENGTH_LONG).show();	
				}
		};
		//
		
	    switch(item.getItemId()) {
	    case R.id.item1:	
	    	Toast.makeText(MainActivity.this,"对不起，这个功能还没做~", Toast.LENGTH_LONG).show();	
	break;
	    case R.id.item2:
	    	
	    	new AlertDialog.Builder(this)  
			.setTitle("请输入初始化密码")  
			.setIcon(android.R.drawable.ic_dialog_info)  
			.setView(Number)  
			.setPositiveButton("确定", click)  
			.setNegativeButton("取消", null)  
			.show();
	   
	break;
	    }
	    return true;
	}
	public void click1(View view){                  //支出
		final EditText Number=new EditText(this);
		 box1=(CheckBox)findViewById(R.id.checkBox1);
		OnClickListener click=new OnClickListener(){
			@Override
			   public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String num=Number.getText().toString();
				System.out.println("num为"+num);
				if(!num.equals("")){
				int a= Integer.parseInt(num);
				fee-=a;
				SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
				String    date    =    sDateFormat.format(new    java.util.Date()); 
		        e=(EditText)findViewById(R.id.editText1);
				String event=e.getText().toString();
				if(event.equals(""))
					event="由于超自然的力量！我们不得不艰难的";
				payfor=date+"\n"+event+"\n"+"支出"+a+"元\n"+payfor;
			    
				 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
				 Editor editor=sp.edit();
				 editor.putInt("fee",fee);
				 editor.putString("payfor",payfor);
				 editor.commit();
				 refnum();
				 e.setText("");
				System.out.println("添加成功");
				 e.setText("");
				Toast.makeText(MainActivity.this,"添加成功", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(MainActivity.this,"添加失败，请检查输入", Toast.LENGTH_LONG).show();	
			}
		};
		
		if(!box1.isChecked()){
		new AlertDialog.Builder(this)  
		.setTitle("请输入支出数目")  
		.setIcon(android.R.drawable.ic_dialog_info)  
		.setView(Number)  
		.setPositiveButton("确定", click)  
		.setNegativeButton("取消", null)  
		.show();}
		else
		{
			SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
			String    date    =    sDateFormat.format(new    java.util.Date()); 
			fee-=10;
			payfor=date+"\n"+"支出水费10元\n"+payfor;
			 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
			 Editor editor=sp.edit();
			 editor.putInt("fee",fee);
			 editor.putString("payfor",payfor);
			 editor.commit();
			 refnum();
			System.out.println("添加成功");
			Toast.makeText(MainActivity.this,"添加成功", Toast.LENGTH_LONG).show();
		}
	}
	
	public void click2(View view){                  //充值
		final EditText Number=new EditText(this);
		 box2=(CheckBox)findViewById(R.id.checkBox2);
		OnClickListener click=new OnClickListener(){
			@Override
			   public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String num=Number.getText().toString();
				if(!num.equals("")){
					int a= Integer.parseInt(num);
				fee+=a;
				SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
				String    date    =    sDateFormat.format(new    java.util.Date()); 
			    e=(EditText)findViewById(R.id.editText1);
				String event=e.getText().toString();
				if(event.equals(""))
					event="这笔钱来的不清不白~~难以明述 默默的";
				payfor=date+"\n"+event+"\n"+"充入"+a+"元\n"+payfor;
				 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
				 Editor editor=sp.edit();
				 editor.putInt("fee",fee);
				 editor.commit();
				 refnum();
				System.out.println("充值成功");
				//Toast.makeText(MainActivity.this,"充值成功", Toast.LENGTH_LONG).show();
				toastshow("充值成功");
				}
				else
					//Toast.makeText(MainActivity.this,"充值失败，请检查输入", Toast.LENGTH_LONG).show();	
				toastshow("充值失败，请检查输入");
			}
		};
		if(!box2.isChecked()){
		new AlertDialog.Builder(this)  
		.setTitle("请输入充值数目")  
		.setIcon(android.R.drawable.ic_dialog_info)  
		.setView(Number)  
		.setPositiveButton("确定", click)  
		.setNegativeButton("取消", null)  
		.show();}
		else
		{
			SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
			String    date    =    sDateFormat.format(new    java.util.Date()); 
			fee+=300;
			payfor=date+"\n"+"例行收入寝室费300元\n"+payfor;
			 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
			 Editor editor=sp.edit();
			 editor.putInt("fee",fee);
			 editor.putString("payfor",payfor);
			 editor.commit();
			 refnum();
			System.out.println("添加成功");
			//Toast.makeText(MainActivity.this,"添加成功", Toast.LENGTH_LONG).show();
			toastshow("添加成功");
		}
	}
	public void refnum(){
        text=(TextView)findViewById(R.id.textView4);
        SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
        fee =sp.getInt("fee",-1);
        if(fee!=-1)
		text.setText(""+fee);
        text2=(TextView)findViewById(R.id.textView2);
        if(payfor!=null)
        	text2.setText(payfor);
	}
	public void toastshow(String msg){
	        if (toast == null) {
	            toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
	        } else {
	            toast.setText(msg);
	        }
	        toast.show();
	}
}
