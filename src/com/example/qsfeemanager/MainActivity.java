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
		System.out.println("��ȥ�ˣ�");
		 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
	        fee =sp.getInt("fee",-1);
	        payfor=sp.getString("payfor",null);
	        text=(TextView)findViewById(R.id.textView4);
	        if(fee!=-1)
	        	text.setText(""+fee);
	        else
	        	text.setText("���ֵ");
	        text2=(TextView)findViewById(R.id.textView2);
	        if(payfor!=null)
	        {
	        	text2.setText(payfor);
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {                                        //���ò˵�
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override                                                                              //���ò˵����Ч��
	public boolean onOptionsItemSelected(MenuItem item) {
		//
		final EditText Number=new EditText(this);
		OnClickListener click=new OnClickListener(){
			@Override
			   public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String num=Number.getText().toString();
				System.out.println("numΪ"+num);
				if(num.equals("000000")){
						 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
						 Editor editor=sp.edit();
						 fee=0;
						 payfor="";
						 editor.putInt("fee",fee);
						 editor.putString("payfor",payfor);
						 editor.commit();
						 refnum();
						 Toast.makeText(MainActivity.this,"��ʼ���ɹ�", Toast.LENGTH_LONG).show();	
			}
				else
					 Toast.makeText(MainActivity.this,"�������", Toast.LENGTH_LONG).show();	
				}
		};
		//
		
	    switch(item.getItemId()) {
	    case R.id.item1:	
	    	Toast.makeText(MainActivity.this,"�Բ���������ܻ�û��~", Toast.LENGTH_LONG).show();	
	break;
	    case R.id.item2:
	    	
	    	new AlertDialog.Builder(this)  
			.setTitle("�������ʼ������")  
			.setIcon(android.R.drawable.ic_dialog_info)  
			.setView(Number)  
			.setPositiveButton("ȷ��", click)  
			.setNegativeButton("ȡ��", null)  
			.show();
	   
	break;
	    }
	    return true;
	}
	public void click1(View view){                  //֧��
		final EditText Number=new EditText(this);
		 box1=(CheckBox)findViewById(R.id.checkBox1);
		OnClickListener click=new OnClickListener(){
			@Override
			   public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String num=Number.getText().toString();
				System.out.println("numΪ"+num);
				if(!num.equals("")){
				int a= Integer.parseInt(num);
				fee-=a;
				SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
				String    date    =    sDateFormat.format(new    java.util.Date()); 
		        e=(EditText)findViewById(R.id.editText1);
				String event=e.getText().toString();
				if(event.equals(""))
					event="���ڳ���Ȼ�����������ǲ��ò����ѵ�";
				payfor=date+"\n"+event+"\n"+"֧��"+a+"Ԫ\n"+payfor;
			    
				 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
				 Editor editor=sp.edit();
				 editor.putInt("fee",fee);
				 editor.putString("payfor",payfor);
				 editor.commit();
				 refnum();
				 e.setText("");
				System.out.println("��ӳɹ�");
				 e.setText("");
				Toast.makeText(MainActivity.this,"��ӳɹ�", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(MainActivity.this,"���ʧ�ܣ���������", Toast.LENGTH_LONG).show();	
			}
		};
		
		if(!box1.isChecked()){
		new AlertDialog.Builder(this)  
		.setTitle("������֧����Ŀ")  
		.setIcon(android.R.drawable.ic_dialog_info)  
		.setView(Number)  
		.setPositiveButton("ȷ��", click)  
		.setNegativeButton("ȡ��", null)  
		.show();}
		else
		{
			SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
			String    date    =    sDateFormat.format(new    java.util.Date()); 
			fee-=10;
			payfor=date+"\n"+"֧��ˮ��10Ԫ\n"+payfor;
			 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
			 Editor editor=sp.edit();
			 editor.putInt("fee",fee);
			 editor.putString("payfor",payfor);
			 editor.commit();
			 refnum();
			System.out.println("��ӳɹ�");
			Toast.makeText(MainActivity.this,"��ӳɹ�", Toast.LENGTH_LONG).show();
		}
	}
	
	public void click2(View view){                  //��ֵ
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
					event="���Ǯ���Ĳ��岻��~~�������� ĬĬ��";
				payfor=date+"\n"+event+"\n"+"����"+a+"Ԫ\n"+payfor;
				 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
				 Editor editor=sp.edit();
				 editor.putInt("fee",fee);
				 editor.commit();
				 refnum();
				System.out.println("��ֵ�ɹ�");
				//Toast.makeText(MainActivity.this,"��ֵ�ɹ�", Toast.LENGTH_LONG).show();
				toastshow("��ֵ�ɹ�");
				}
				else
					//Toast.makeText(MainActivity.this,"��ֵʧ�ܣ���������", Toast.LENGTH_LONG).show();	
				toastshow("��ֵʧ�ܣ���������");
			}
		};
		if(!box2.isChecked()){
		new AlertDialog.Builder(this)  
		.setTitle("�������ֵ��Ŀ")  
		.setIcon(android.R.drawable.ic_dialog_info)  
		.setView(Number)  
		.setPositiveButton("ȷ��", click)  
		.setNegativeButton("ȡ��", null)  
		.show();}
		else
		{
			SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd hh:mm:ss");       
			String    date    =    sDateFormat.format(new    java.util.Date()); 
			fee+=300;
			payfor=date+"\n"+"�����������ҷ�300Ԫ\n"+payfor;
			 SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
			 Editor editor=sp.edit();
			 editor.putInt("fee",fee);
			 editor.putString("payfor",payfor);
			 editor.commit();
			 refnum();
			System.out.println("��ӳɹ�");
			//Toast.makeText(MainActivity.this,"��ӳɹ�", Toast.LENGTH_LONG).show();
			toastshow("��ӳɹ�");
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
