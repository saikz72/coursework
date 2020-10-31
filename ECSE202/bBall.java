import java.awt.Color;
import acm.graphics.GOval;
/**
 * This class provides a single instance of a ball launch at an angle from the ground
 * and undergoes projectile motion. Because it is an extension of the Thread class, each
 * instance will run concurrently, with animations on the screen as side effects.
 * @author Saikou Ceesay
 *
 */
public class bBall extends Thread {
	/**
	 * Instance variables and class parameters
	 */
	boolean isRunning;
	private static final double Pi = 3.141592654; // To convert degrees to radians
	private static final double g = 9.8; // MKS gravitational constant 9.8 m/s^2
	private static final double TICK = 0.1; // Clock tick duration (sec)
	private static final int HEIGHT = 600;
	private static final double SCALE = HEIGHT/100; // pixels per meter
	private static final double ETHR = 0.01; // Whey Voy < ETHR * Vo, STOP
	public static boolean running = true;
	GOval myOval;
	double Xi;
	double Yi;
	double Vo;
	double theta;
	double bSize;
	Color bColor;
	double bLoss;
	/**
	 * The constructor specifies the parameters for simulation. They are
	 *
	 * @param Xi double The initial X position of the center of the ball
	 * @param Yi double The initial Y position of the center of the ball
	 * @param Vo double The initial velocity of the ball at launch
	 * @param theta double Launch angle (with the horizontal plane)
	 * @param bSize double The radius of the ball in simulation units
	 * @param bColor Color The initial color of the ball
	 * @param bLoss double Fraction [0,1] of the energy lost on each bounce
	 */

	public bBall (double inputXi, double inputYi, double inputVo, double inputTheta, double inputBSize, Color inputBColor, double inputBLoss) {
		//assign the random variables to the object
		Xi = inputXi;
		Yi = inputYi;
		Vo = inputVo;
		theta = inputTheta;
		bSize = inputBSize;
		bColor = inputBColor;
		bLoss = inputBLoss;

		//create instance of a ball using specified parameters
		myOval = new GOval(gUtil.WindowX(Xi-bSize), gUtil.WindowY(Yi +bSize), gUtil.WindowL(2*bSize),gUtil.WindowL(2*bSize));
		myOval.setFilled(true);
		myOval.setFillColor(bColor);	

	}

	// move function
	public void moveTo(double x, double y) {
		myOval.setLocation(x, y);
	}

	/**
	 * The run method implements the simulation loop from Assignment 1.
	 * Once the start method is called on the bBall instance, the
	 * code in the run method is executed concurrently with the main
	 * program.
	 * @param void
	 * @return void
	 */
	public void run() {
		isRunning = true;
		double X;
		double Y;
		double time_g = 0;
		double time_l = 0;

		double V_ox = Vo*Math.cos(theta*Pi/180);
		double V_oy = Vo*Math.sin(theta*Pi/180);

		while(running) {
			
			X = Xi+V_ox*time_g;
			Y = Yi+V_oy*time_l- 0.5*g*time_l*time_l;		
			double V_y = V_oy-g*time_l;

			time_g = time_g+TICK;			
			time_l = time_l+TICK;

			// check to see if we've hit the ground
			// if yes, inject energy loss, and force current value of Y to radius of ball(bSize)

			if(V_y < 0 && Y <= bSize){
				V_oy = V_oy*Math.sqrt(1-bLoss);
				time_l = 0;						// reset current interval time
				Y = bSize;
			}
			myOval.setLocation(gUtil.WindowX(X-bSize), gUtil.WindowY(Y + bSize));			//add(new GOval(ScrX, HEIGHT-ScrY-bSize*SCALE, PD, PD));

			if (V_oy <= Vo*ETHR)
				break;

			try { 
				// pause for 50 milliseconds
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		isRunning = false;

	}
}