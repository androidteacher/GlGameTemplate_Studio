package com.beck.GLGameTemplate;







import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;



public class Start extends Activity {

	/** The OpenGL View */	 
    Context context1;
	static AlertDialog alertDialog ;
	static AlertDialog.Builder alertDialogBuilder;
	
	private GLSurfaceView glSurface;
	
	ProgressDialog progress;
	Handler mHandler;
	TextView tv;
	TextView tv1;
	TextView top;
	public int version = 1;
	
		
	GLSurfaceView cubesView;
	public static Context context;  
	public float velocity;
	public float velocityud;
	private static final int SWIPE_MIN_DISTANCE = 10;
	private static final int SWIPE_MAX_OFF_PATH = 10;
	private static final int SWIPE_THRESHOLD_VELOCITY = 10;
	private GestureDetector flingDetector;
	View.OnTouchListener gestureListener;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		 context = getApplicationContext();
		 context1 = this;
		 requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 setContentView(R.layout.main); 
		 
		 progress = new ProgressDialog(context);
		
		
	     //Here I have a method that shows an alert. It can be triggered at any time with alertDialog.show(); , but currently is not called upon.
		 createdialog();
       
			
		 progress = ProgressDialog.show(Start.this, "Simple Progress Dialogue.","Beginning of Game!");
	     
	     cubesView = (GLSurfaceView) findViewById(R.id.cubes); 
	        
	     cubesView.setRenderer(new MainDraw(this)); 
	    
	     final ImageButton button = (ImageButton) findViewById(R.id.button1);
	  
	     button.setOnTouchListener(new View.OnTouchListener() {
				    	 
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN ) { 
					
					  return true;
					  }
				if (event.getAction() == MotionEvent.ACTION_UP ) { 
						
							  return true;
							  }
					return false;
				}
			});
	        
         
	     final ImageButton button2 = (ImageButton) findViewById(R.id.button2);
	     button2.setOnTouchListener(new View.OnTouchListener() {
			
	    	 public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN ) 
				{
					
					
						// TODO Auto-generated method stub
						  return true;
				}
				if (event.getAction() == MotionEvent.ACTION_UP )
				{ 
							
								// TODO Auto-generated method stub
								  return true;
				}
						return false;
					}
				});
	        
	      
	        //You can place a ttf file in the fonts directory and reference here for your TextViews
	         Typeface tf = Typeface.createFromAsset(getAssets(),
	        	        "fonts/bullpen.ttf");
	        //TextView that Appears at the Top
	         top = (TextView) findViewById(R.id.score);
	         
	         top.setTextColor(Color.CYAN);
	         top.setTypeface(tf);
	         top.setTextSize(18);
	      
	         
           //TextView that Appears At the Bottom above the number 1
	         tv1 = (TextView) findViewById(R.id.TextView02);
         
	         tv1.setTextColor(Color.CYAN);
	         tv1.setTypeface(tf);
	         tv1.setTextSize(16);
	         tv1.setText("Score:");
	         
	         
	        //TextView that appears at the very Bottom
	         tv = (TextView) findViewById(R.id.TextView01);
	         
	         tv.setTextColor(Color.CYAN);
	         tv.setTypeface(tf);
	         tv.setTextSize(24);
	       
	         
	         mHandler = new Handler();
	  	     mHandler.post(mUpdate);
	        
	   
	    flingDetector = new GestureDetector(new MyFlingListener());
	    gestureListener = new View.OnTouchListener() {
	             
	    	public boolean onTouch(View v, MotionEvent event) {
	                  if (flingDetector.onTouchEvent(event)) {
	                      return true;
	                  }
	                  return false;
	              }
	          };
	          
	}
	
	
	public void createdialog()
	{
		alertDialogBuilder = new AlertDialog.Builder(context1);
		alertDialogBuilder.setTitle("--Game Over--");
	
		alertDialogBuilder.setCancelable(false);
		
		alertDialogBuilder.setPositiveButton("Play Again",new DialogInterface.OnClickListener() {
	    
			public void onClick(DialogInterface dialog,int id) {
				
				 dialog.cancel();
				
				 createdialog();
			}
		});
		
		alertDialogBuilder.setMessage("Great Game!");
		alertDialog = alertDialogBuilder.create();
   
	}
	
	 @Override
	    public boolean onTouchEvent(MotionEvent e)
	    {
	    	
	      
	        flingDetector.onTouchEvent(e);
			return true;
	    }
	
	private Runnable mUpdate = new Runnable() {
		 
		public void run() {
                  //while(true) 
			top.setText("GLGame Template:\n Linuxclassroom.com");
			tv.setText("" + GetterSetter.scoreint);
			
			/* By setting a GetterSetter Value, we can show the Game Over Alert created in createdialog();
			if (GetterSetter.alert == 1)
        {
				
        	alertDialog.show();
        }
			*/
        	  
        	
        	
        
		 //GetterSetter.dismiss is set to 2 after all models are loaded.      
		 if (GetterSetter.dismiss == 2)
		       {
		    	  	if (progress.isShowing())
		    	  		{
		    	  				progress.dismiss();
		    	  		}
		    	  	GetterSetter.dismiss = 0; 
		       }
		       
		   
		       mHandler.postDelayed(this, 1);
                  

		    }
		};
		
	
	
	
	       
			
	// set view 
	    
	

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
		cubesView.onResume();
		context1 = this;
		createdialog();
	
	
		
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		
		super.onPause();
		cubesView.onPause();	
	
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    Log.d("I am destroyed", ".");
	 
	    cubesView.onPause();
	    
	}
	
	//Every time you flick the screen, these values get set in the GetterSetter Class
	class MyFlingListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
               
            	  if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                  	
                  	velocity = velocityX;
                  
                   	GetterSetter.setVelocityX(velocity);
                  	GetterSetter.setJustflicked(1);
                  	GetterSetter.setDirection(0);
                  	
                  }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	velocity = velocityX;
                
                	GetterSetter.setVelocityX(velocity);
                	GetterSetter.setJustflicked(1);
                	GetterSetter.setDirection(1);
                 
                  }
            	  if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    	
                    	velocityud = velocityY;
                    	
                    	GetterSetter.setVelocityY(velocityud);
                    	GetterSetter.setJustflicked(1);
                    	GetterSetter.setDirectionud(0);
                    	
                    }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                  	velocityud = velocityY;
                	
                  	GetterSetter.setVelocityY(velocityud);
                  	GetterSetter.setJustflicked(1);
                  	GetterSetter.setDirectionud(1);
                    }
                   
            	  
            	
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
	}
	
}