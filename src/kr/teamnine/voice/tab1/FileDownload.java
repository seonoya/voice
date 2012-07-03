package kr.teamnine.voice.tab1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.util.Log;

public class FileDownload {

	//http://translate.google.co.kr/translate_tts?ie=UTF-8&q=문장&tl=ko&prew=input
	String apiUrl = "http://translate.google.co.kr/translate_tts?ie=UTF-8&";
	String tempFileName = "120530144241.mp3";
	String filePath = "/data/data/kr.teamnine.voice/files";
	String fileName = "120530144241.mp3";

	public final String TAG = "Ryu";
	public FileDownload(){
		
		
		
		
	}

	// 
	public String startDownload(String txt){
		System.out.println("test===>"+ txt);
		File folder = new File(filePath);
        folder.mkdir();
        folder = null;
		
		
		InputStream DownInputStream = null;
		int	m_fileSize	= 0;	// 3MB
		int	nStatusCode	= 0;
		
		System.out.println(txt);
		
		try {
			
			HttpClient client = new DefaultHttpClient();

			// 파일 요청
	        String strSafeURL = safeUrlEncoder(apiUrl + "&q=" + txt + "&tl=ko&prew=input");
	        strSafeURL = apiUrl + "&q=" + txt + "&tl=ko&prew=input";
	        HttpGet request	= new HttpGet();
	        request.setURI(new URI(strSafeURL));
			request.addHeader("Connection", "close");

			Log.e(TAG, "HTTP execute request: " + strSafeURL);
			HttpResponse response = client.execute(request);
			DownInputStream = response.getEntity().getContent();

			nStatusCode	= response.getStatusLine().getStatusCode();
    		Log.e(TAG, "HTTP Response Status Code: " + nStatusCode);
			if( nStatusCode != 200 && nStatusCode != 206 )
			{
				Log.e(TAG, "파일이 없거나 접근할 수 없음.");
				return "null";
			}

			// 응답 메시지에서 파일 전체 길이 획득
			Header[] resHeader = response.getHeaders("Content-Length");
            if( resHeader.length > 0 )
            {
            	String strValue	= resHeader[0].getValue();
            	if( strValue != null );
            	{
            		// 파일 길이(용량)가 존재하면 화면에 갱신
            		m_fileSize = Integer.parseInt(strValue);
//            		Log.e(TAG, "filesize: " + m_fileSize);
            	}
            }
            
            // 파일에 쓰기
            if(isFile(tempFileName) == true){			// 이미 파일이 존재하는지 체크
//            	if(isFileSize(s_curFileName) != m_fileSize)		// 파일의 용량이 다르면 삭제처리
	            	deleteFile(tempFileName);
            }

            String	strNewFile		= filePath + "/" + tempFileName;
            File	fileDown		= new File(strNewFile);
            FileOutputStream fos	= new FileOutputStream(fileDown);
            byte[]	bszDownBuf		= new byte[20480];
            int		nRead			= 0;
            int		nTotalRead		= 0;
            
            do{
            	nRead = DownInputStream.read(bszDownBuf);
            	
            	if(nRead < 1)
            		break;
            	
        		fos.write(bszDownBuf, 0, nRead);

        		nTotalRead += nRead;
        		

            }while(!Thread.interrupted());
            
//            Log.e(TAG, "nTotalRead: " + nTotalRead);
            
            // temp파일로 받은후에 다운로드가 완료되면 진짜 파일이름으로 변경한다.
            fileDown.renameTo(new File(filePath + "/" + fileName));
            System.out.println(filePath + "/" + fileName);
            System.out.println(fileDown.getName());
            fos.close();
            
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            if( DownInputStream != null ) {
                try {
                	DownInputStream.close();
                	DownInputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}		
		
		
		return fileName;
	}
	
	
	public String safeUrlEncoder(String strInput)
	{
		return	safeUrlEncoder(strInput, "UTF-8");
	}
	
	public String safeUrlEncoder(String strInput, String strEnc)
	{
		StringBuilder strResult	= new StringBuilder();
		String strPorcess;
		if( strInput.startsWith("http://") )
		{
			strPorcess = strInput.substring(7);
		}
		else
		{
			strPorcess = strInput;
		}
		
		String	strURLUnit[] = strPorcess.split("\\/");
		int	i = 0;
		int	nUnitCount = strURLUnit.length;
		
		strResult.append("http://");
		strResult.append(strURLUnit[0]);
		
		try{
			for(i = 1; i < nUnitCount; i++)
			{
				strResult.append("/");
				strResult.append(URLEncoder.encode(strURLUnit[i], strEnc));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Log.e(TAG, "safeUrlEncoder: " + strResult.toString());
		return	strResult.toString();
	}
	
	public boolean isFile(String fileName)
	{
		File file = new File(filePath + "/" + fileName);
		return file.isFile();
	}
	
	public long isFileSize(String fileName)
	{
		File file = new File(filePath + "/" + fileName);
		return file.length();
	}
	
	public boolean deleteFile(String fileName)
	{
		File file = new File(filePath + "/" + fileName);
		return file.delete();
	}
	
	
}
