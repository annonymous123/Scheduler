/*
 * Call Manager AMI and place a cal
 */

package org.raxa.module.scheduler;
import org.raxa.module.MedicalInformation.MedicineInformer;
import java.util.List;

public class Caller implements Runnable {

  private MedicineInformer patient;
	
	public Caller(MedicineInformer patient){
		this.patient=patient;
	}
	
	public void run(){
		System.out.println(patient.getMsgId()+" "+patient.getPatientId()+" "+patient.getPhoneNumber());
		int count=0;
		List<String> a=patient.getMedicineInformation();
		while(true){
			if (a.isEmpty()) break;
			System.out.println((a.get(count++)));
			
			
			//Convert String to tts
			
			//Call Manager AMI which will call asterisk and then a call will be placed
		}
	}
}
