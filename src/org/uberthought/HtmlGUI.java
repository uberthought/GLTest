package org.uberthought;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.webkit.WebView;
import android.widget.Toast;

public class HtmlGUI extends WebView {
	
	private class JavaScriptInterface {
	    private Context _context;
	    private OpenGLRenderer _openGLRender; 

	    JavaScriptInterface(Context context, OpenGLRenderer openGLRender) {
	    	_context = context;
	    	_openGLRender = openGLRender;
	    }

	    public void showToast(String toast) {
	        Toast.makeText(_context, toast, Toast.LENGTH_SHORT).show();
	    }
	    
	    public void setOriginal() {
	    	_openGLRender.useShader("Original");
	        Toast.makeText(_context, "setOriginal", Toast.LENGTH_SHORT).show();
	    }
	    public void setSobelEdge() {
	    	_openGLRender.useShader("SobelEdge");
	        Toast.makeText(_context, "setSobelEdge", Toast.LENGTH_SHORT).show();
	    }
	}
	
	private Context _context;

	public HtmlGUI(Context context, OpenGLRenderer openGLRender) {
		super(context);
		_context = context;
		
		getSettings().setJavaScriptEnabled(true);
		
		String htmlText = resourceToString(R.raw.htmlgui);
        loadData(htmlText, "text/html", null);
        
        addJavascriptInterface(new JavaScriptInterface(_context, openGLRender), "Android");
	}

	private final String resourceToString(int resourceId) {
		String s = new String();
		try {
			Resources res = _context.getResources();
	        InputStream inputStream = res.openRawResource(resourceId);
	
	        byte[] data = new byte[inputStream.available()];
	        inputStream.read(data);
	        s = new String(data);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
		return s;
	}	
}
