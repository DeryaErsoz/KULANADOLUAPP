
package com.example.kulAnadolu;

import com.example.kulAnadolu.MainActivity;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
 
public class MainActivity extends Activity 
{
	
	public static Context icerik;
	 TextView myCalendarDate,location,title,organizator,eDate,sDate;
	ImageButton imgDate; 
	String[] dates;
	Button alert;
	 String date=null;
	
   	private Calendar myCalendar = Calendar.getInstance();
   	
   	SimpleDateFormat fmtDateAndTime =new SimpleDateFormat("dd.MM.yyyy");
   
   
   
	private void updateDate() {
		 
     	myCalendarDate.setText(fmtDateAndTime.format(myCalendar.getTime()));

	}
	
          
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);
              
           	myCalendarDate =(TextView)findViewById(R.id.txtDate);
               icerik = MainActivity.this;
               final ListView lv = (ListView) findViewById(R.id.listView1);
               WebService etkinlikleri_getir = new WebService(getApplicationContext(), lv);
           	   etkinlikleri_getir.execute();
       	      
       	       imgDate =(ImageButton)findViewById(R.id.imgDate);
       	       
       	       imgDate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showDialog(v);
					
				}
			});
       	       
       	   
       	    
       		String[] items = new String[] {"- Herhangi -",
       				"Açıkoturum",
       				"Çalıştay",
       				"Dia Gösterileri",
       				"Eğitim",
       				"Eğitim ve Tanıtım Fuarları",
       				"Festival vb. Etkinlikler",
       				"Film Gösterileri",
       				"Folklor Gösterileri",
       				"Geziler ve Turlar",
       				"Gösteriler",
       				"İmza Günleri",
       				"Kampanyalar",
       				"Karnaval",
       				"Kokteyller ve Yemekler",
       				"Konferanslar",
       				"Kongreler",
       				"Konser ve Dinletiler",
       				"Kurslar",
       				"Müzikal Gösteriler",
       				"Opera ve Bale Gösterileri",
       				"Oryantasyon",
       				"Panayır",
       				"Paneller",
       				"Seminerler",
       				"Sempozyumlar",
       				"Şenlik",
       				"Sergiler",
       				"Şölen",
       				"Söyleşiler",
       				"Spor Etkinlikleri",
       				"Tanıtım Aktiviteleri",
       				"Tanıtımlar ve Turlar",
       				"Tiyatro Gösterileri",
       				"Toplantılar",
       				"Törenler",
       				"Yarışmalar"};
       		
       		
       		final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
       	
       		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       		            android.R.layout.simple_spinner_item, items);
       		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       		spinner2.setAdapter(adapter);
      
       		Button btn_uygula =(Button) findViewById(R.id.btnUygula);
       		
       		btn_uygula.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				    
					String str_tur = spinner2.getSelectedItem().toString();
					
				String date2 = myCalendarDate.getText().toString();
					date=date2;
							
					List<Etkinlik> liste = new ArrayList<Etkinlik>();
					List<Etkinlik> myList = new ArrayList<Etkinlik>();
					
				
					if(date==""){
						
						if(str_tur.contains("- Herhangi -")){
							
							ListAdapter adapter= new EtkinlikAdapter(getApplicationContext(), WebService.liste_etkinlikler);
					    	lv.setAdapter(adapter);
					    	
						}
						if(str_tur!="- Herhangi -" ){
							
							for(int i=0; i<WebService.liste_etkinlikler.size(); i++)
							{
							if(WebService.liste_etkinlikler.get(i).getType().contentEquals(str_tur))
							{
							
								liste.add(WebService.liste_etkinlikler.get(i));
							}
							
								
							}
					
							ListAdapter adapter= new EtkinlikAdapter(getApplicationContext(), liste);
							lv.setAdapter(adapter);					    
						   if(liste.size()==0){
							Toast.makeText(getApplicationContext(), " Ture aıt etkınlık yok", Toast.LENGTH_LONG).show();
						}
						}
					}
					
					
					
					else{
						
						if(str_tur.contains("- Herhangi -")){
							
							for(int i=0; i<WebService.liste_etkinlikler.size(); i++)
							{
							if(date.contentEquals(WebService.liste_etkinlikler.get(i).getDate_start().split(" ")[0]))
							{
							
								liste.add(WebService.liste_etkinlikler.get(i));
							}
							
								
							}
					
							ListAdapter adapter= new EtkinlikAdapter(getApplicationContext(), liste);
							lv.setAdapter(adapter);
					    	 if(liste.size()==0){
									Toast.makeText(getApplicationContext(), "Bu tarihe ıt etkınlık yok", Toast.LENGTH_LONG).show();
								}
						}
						if(str_tur!="- Herhangi -" && date==date ){
							
							for(int i=0; i<WebService.liste_etkinlikler.size(); i++)
								
							{
								if(WebService.liste_etkinlikler.get(i).getType().contentEquals(str_tur))
								{
									liste.add(WebService.liste_etkinlikler.get(i));
									for(int j=0; j<liste.size(); j++)
										
									{
									if(liste.get(j).getDate_start().split(" ")[0].contentEquals(date))
									{
										myList.add(liste.get(j));
									}
									 
									}
								}
							
							ListAdapter adapter= new EtkinlikAdapter(getApplicationContext(), myList);
							
					    	lv.setAdapter(adapter);
					    	 if(myList.size()==0){
									Toast.makeText(getApplicationContext(), "Bu tarıh ve Ture ait etkınlık yoktur", Toast.LENGTH_LONG).show();
								}
					    	
							}
							
						}
					}
				}
					
									
       		});

}
        	public void showDialog(View v)
           	{
           		
           		AlertDialog.Builder builder=new AlertDialog.Builder(this);
           		builder.setIcon(R.drawable.ic_launcher_web);
           		builder.setMessage("Tarih Sec ");
           	
           		builder.setPositiveButton("Takvime Git", new DialogInterface.OnClickListener() {

           	        @Override
           	     public void onClick(DialogInterface dialog, int which) {
        				// TODO Auto-generated method stub
        			 	new DatePickerDialog(MainActivity.this, d, myCalendar
                             	.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                               myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            	
        				
        			}
        			});
           		
           		builder.setNeutralButton("Tarihi Resetle", new DialogInterface.OnClickListener(){
           			
           			
        			@Override
        			public void onClick(DialogInterface dialog, int which) {
        				// TODO Auto-generated method stub
        			myCalendarDate.setText("");
        				
        			}
           		});
           		builder.show();
           	}
           	
           	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
                 	public void onDateSet(DatePicker view, int year, int monthOfYear,
                               	int dayOfMonth) {
                        	myCalendar.set(Calendar.YEAR, year);
                        	myCalendar.set(Calendar.MONTH, monthOfYear);
                        	myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        	updateDate();
                 	}
           	};
           	
}