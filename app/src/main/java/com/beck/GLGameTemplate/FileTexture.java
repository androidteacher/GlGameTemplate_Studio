package com.beck.GLGameTemplate;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class FileTexture {
	public Context context;
	private GL10 gl;
	private int[] textures = new int[1];
	private static BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
	private Bitmap bitmap;
	private String fname;
	

	public FileTexture(GL10 gl,String fname) {
		if(gl==null)return;
		this.gl = gl;
		this.fname=fname;
		
		 sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
		
		
		 context = Start.context;
		
		}

		
	public void loadTexture() {
		
		InputStream is = null;
		 
	
		
		
			is = context.getResources().openRawResource(R.drawable.ship);
		
		
		
		
		bitmap = BitmapFactory.decodeStream(is);
   	   
 	  		
		
  	   createTexture();
	
		}
	

	private void createTexture() {
		
			gl.glGenTextures(1, textures, 0);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
			gl.glPixelStorei(GL10.GL_UNPACK_ALIGNMENT, 1);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
					GL10.GL_LINEAR);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
					GL10.GL_LINEAR);
	
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
					GL10.GL_REPEAT);
		    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
					GL10.GL_REPEAT);
	
			gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
					GL10.GL_MODULATE);
	
			
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	
			
	
			bitmap.recycle();
			
	
			int error = gl.glGetError();
			if (error != GL10.GL_NO_ERROR) {
				Log.w("", "" + error);
			}
		//}
	}

	public void setTexture() {
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
	}
}
