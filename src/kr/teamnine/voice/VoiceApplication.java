package kr.teamnine.voice;

import android.app.Application;

public class VoiceApplication extends Application{

	private int voiceCode;
	private int cateCode;
	private String voiceTxt;
	private String voicePath;
	private String voiceFileName;
	private boolean onStart;

	private int noteCode;
	public int getNoteCode() {
		return noteCode;
	}

	public void setNoteCode(int noteCode) {
		this.noteCode = noteCode;
	}

	public String getNoteData() {
		return noteData;
	}

	public void setNoteData(String noteData) {
		this.noteData = noteData;
	}

	private String noteData;

	
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

	public void setCateCode(int cateCode){
		this.cateCode = cateCode;
	}

	public int getCateCode(){
		return this.cateCode;
	}

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
	
	public void setVoicePath(String voicePath){
		this.voicePath = voicePath;
	}

	public String getVoicePath(){
		return this.voicePath;
	}
	
	public void setVoiceFileName(String voiceFileName){
		this.voiceFileName = voiceFileName;
	}

	public String getVoiceFileName(){
		return this.voiceFileName;
	}	
	
	public void setOnStart(boolean onStart){
		this.onStart = onStart;
	}

	public boolean getOnStart(){
		return this.onStart;
	}	
	
	
	
}
