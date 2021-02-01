/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complements;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Elliot
 */
public class Hilo implements Runnable{

    String hora,minutos,segundos,ampm;
    Calendar calendario;
    Thread h1;
    
    public Hilo(){
        h1=new Thread(this,"hilo 1");
        h1.start();
    }
   
    @Override
    public void run() {
        Thread ct=Thread.currentThread();
       
       while(ct==h1){
           calcula();
          
           try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        } 
       }       
    }
        private void calcula(){
         Calendar calendario=new GregorianCalendar();
         Date fechaHoraActual=new Date();
         calendario.setTime(fechaHoraActual);
         ampm=calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
         if(ampm.equals("PM")){
         int h=calendario.get(Calendar.HOUR_OF_DAY)-12;
         hora=h>9?""+h:"0"+h;
         }else{
          hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
         }
       minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
       segundos=calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);

        }
  
    
}
