/*
 * Its run method set all threads to run Caller object and provide patient information to the caller object
 * 
 * It will take into account both alertTypes i.e IVR AND SMS
 * 
 */

package org.raxa.module.scheduler;
import org.raxa.module.variables.VariableSetter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.sql.Time;
import java.util.Date;
import org.raxa.module.MedicalInformation.MedicineInformer;

public class CallSetter implements Runnable,VariableSetter{
  
	public void run(){
		Time lowertime=new Time((new Date()).getTime());
		System.out.println(lowertime);
		
		List<MedicineInformer> listOfIVRCaller=(new MedicineInformer()).getPatientInfoOnTime(lowertime,IVR_TYPE);
		List<MedicineInformer> listOfSMSCaller=(new MedicineInformer()).getPatientInfoOnTime(lowertime,SMS_TYPE);
		
		if(listOfIVRCaller!=null)
			setIVRThread(listOfIVRCaller);
		else System.out.println("In CallSetter:run-No IVRTuple found for the next hour");
		
		if(listOfSMSCaller!=null)
			setSMSThread(listOfSMSCaller);
		else System.out.println("In CallSetter:run-No SMSTuple found for the next hour");
		
		System.out.println("Exiting CallSetter:run()");
			
	}
	
	public void setIVRThread(List<MedicineInformer> list){
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(THREAD_POOL_CALL_SETTER);
		int count=0;
		
		while(count<list.size()){
			MedicineInformer a;
			a=list.get(count);
			Caller caller=new Caller(a);
			try{
				Time time=a.getTime();
				Time currentTime=new Time((new Date()).getTime());
				long diff=time.getTime()-currentTime.getTime();
				executor.schedule(caller,diff,TimeUnit.MILLISECONDS);
			}
			catch(Exception ex){
				System.out.println("In function setIVRThread:Error Occured");
			}
			finally{
				count++;
			}
		}
	}
		
	public void setSMSThread(List<MedicineInformer> list){
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(THREAD_POOL_CALL_SETTER);
		int count=0;
		
		while(count<list.size()){
			MedicineInformer a;
			a=list.get(count);
			Messager messager=new Messager(a);
			try{
				Time time=a.getTime();
				Time currentTime=new Time((new Date()).getTime());
				long diff=time.getTime()-currentTime.getTime();
				executor.schedule(messager,diff,TimeUnit.MILLISECONDS);
			}
			catch(Exception ex){
				System.out.println("In function setSMSThread:Error Occured");
			}
		}
	}

}
		
		
		
		
		
		
		
		
		
	
