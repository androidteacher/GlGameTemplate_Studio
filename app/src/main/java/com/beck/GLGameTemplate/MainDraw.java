package com.beck.GLGameTemplate;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Handler;


public class MainDraw implements Renderer {
	

	ProgressDialog progress;
	public Handler mHandler;
	private static Context mContext;
	
    Random random;
	GLShip chip;
	GLShip bonus[] = new GLShip[4];
	public MediaPlayer mediaPlayer;
	private Context context;
	float speed = 0;
    float speedud = 0;
    int flicked = 0;
    int flickdirection = 0;
    int flickdirectionud = 0;
    int zdirection=0;
    int xdirection=0;
    int ydirection=0;
    float testvar = 180f;
	public MainDraw(Context context) {
		mHandler = new Handler();
        random = new Random();
		this.context = Start.context;
		chip = new GLShip(this.context);
		GetterSetter.dismiss = 2;	
		
			
	}

	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		
		chip.Init(gl);
	
		gl.glColor4f(1.0f, 0.0f, 0.0f, 0.8f);				//Full Brightness. 50% Alpha ( NEW )
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);	
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
		
		
		
	}

	
	
	public void onDrawFrame(GL10 gl) {
		//Listen for Flick Velocity and Direction Values.
		flicked = GetterSetter.getJustflicked();

		if (flicked == 1)
		{
			speed = GetterSetter.getVelocityX();
			speedud = GetterSetter.getVelocityY();
			flickdirection = GetterSetter.getDirection();
			flickdirectionud = GetterSetter.getDirectionud();
			
			GetterSetter.setJustflicked(0);


		if (flickdirection == 0)
			{
				xdirection = 0;
			}
		if (flickdirection == 1)
			{
				xdirection = 1;
			}
		if (flickdirectionud == 0)
			{
				ydirection = 1;
			}
		if (flickdirectionud == 1)
			{
				ydirection = 0;
			}
		}
		
		
		 if(speed > 0 && flickdirection == 1)
		 {
			 speed = speed  -5;
		 }
		 if(speed < 0 && flickdirection == 1)
		 {
			 speed = 0; 
		 }

		 if(speed < 0 && flickdirection == 0)
		 {
			 speed = speed  + 5;
		 }
		 if(speed > 0 && flickdirection == 0)
		 {
			 speed = 0;
		 }
		 if(speed > 0 && flickdirection == 1)
		 {
			 speed = speed  -5;
		 }
		 
		 
		 if(speedud < 0 && flickdirectionud == 1)
		 {
			 speedud = 0; 
		 }

		 if(speedud < 0 && flickdirectionud == 0)
		 {
			 speedud = speedud  + 20;
		 }
		 if(speedud > 0 && flickdirectionud == 0)
		 {
			 speedud = 0;
		 }
		//End Flick Direction/Velocity Check
		
		mHandler.post(mUpdate);
		if (gl == null)
			return;
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	
		 gl.glPushMatrix(); 
		 
	 		gl.glScalef(1.5f, 1.5f, 1.5f); 			//Scale the Cube to 80 percent, otherwise it would be too large for the screen
		 	gl.glTranslatef(0.0f,-7.0f,-45f);
			
			gl.glRotatef(160, 1.0f, 0.0f, 0.0f);	//X
			gl.glRotatef(0, 0.0f, 0.0f, 1.0f);	
			gl.glRotatef(testvar, 0.0f, 0.0f, 1.0f);	
			
							
			chip.draw(gl, false);
			
			
			gl.glPopMatrix();
			 testvar += speed/120;

				} 
	
	
	

	public Runnable mUpdate = new Runnable() {
		   public void run() {
			 //check GetterSetters for sounds.
			 //reset mediaplayer, create, start code
		 
		    }
		};
		
	public void onSurfaceChanged(GL10 gl, int width, int height) {
	
		chip.Init(gl);
   
		gl.glViewport(0, 0, width, height); 	
		gl.glMatrixMode(GL10.GL_PROJECTION); 	
		gl.glLoadIdentity(); 					

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	
		gl.glLoadIdentity(); 					
	}
}
