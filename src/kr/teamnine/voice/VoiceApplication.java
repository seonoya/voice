package kr.teamnine.voice;

import android.app.Application;

public class VoiceApplication extends Application{

	private int voiceCode;
	private String voiceTxt;
	
	
//	@Override
//	public void onCreate(){
//		
//		voiceCode = 0;
//		voiceTxt = "";
//		
//	}
//	
//	@Override
//	public void onTerminate(){
//		super.onTerminate();
//	}
	

	public void setVoiceCode(int voiceCode){
		this.voiceCode = voiceCode;
	}

	public int getVoiceCode(){
		return this.voiceCode;
	}
	
	public void setVoiceTxt(String voiceTxt){
		this.voiceTxt = voiceTxt;
	}

	public String getVoicevoiceTxt(){
		return this.voiceTxt;
	}
	
	
	
}
