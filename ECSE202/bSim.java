
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.gui.TableLayout;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
/**
 * The main class in Assignment 4
 * bSim has been modified by inclusion of an interactive component that allows
 * the user to
 * 1. Override the default parameters for the simulation
 * 2. Ending or restricting the simulation by pressing a button.
 * 
 *
 * @author Saikou Ceesay
 *
 */
public class bSim extends GraphicsProgram implements ChangeListener, ItemListener{
	/**
	 * Instance variables - simulation parameters
	 */
	private static final int WIDTH = 1400; // n.b. screen coordinates
	private static final int HEIGHT = 600;
	private static final int OFFSET = 200;
	private static final double SCALE = HEIGHT/100; // pixels per meter


	/**
	 * Instance variables - slider boxes
	 * 
	 */	
	SliderBox num_balls_box;
	SliderBox min_size_box;
	SliderBox max_size_box;
	SliderBox loss_min_box;
	SliderBox loss_max_box;
	SliderBox min_vel_box;
	SliderBox max_vel_box;
	SliderBox theta_min_box;
	SliderBox theta_max_box;

	/**
	 * Instance variables - JCombo boxes
	 *
	 */
	JComboBox<String> bFileCB;
	JComboBox<String> bEditCB;
	JComboBox<String> bHelpCB;
	JComboBox<String> bSimCB;

	//instance variable 
	BinaryTree tree;

	/**
	 * 	method to create an instance of a ground plane
	 */
	public void groundLevel() {
		GRect ground = new GRect(0, HEIGHT, WIDTH, 6); 
		ground.setColor(Color.BLACK);				   
		ground.setFilled(true);						   
		add(ground);
	}


	// to use run function
	public void init()
	{

		this.resize(WIDTH, HEIGHT+OFFSET);

		groundLevel(); 		//sets up the ground 


		//creates an object (tree) of the bTree class to store the data
		tree = new BinaryTree();
		
		//user interface
		JLabel gsp = new JLabel("General Simulation Parameters");
		num_balls_box = new SliderBox("NUMBALLS:", 1, 255, 100);		//number of balls
		min_size_box = new SliderBox("MIN SIZE:", 1, 25, 4);			//minimum ball size
		max_size_box = new SliderBox("MAX SIZE:", 1, 25, 10);			//maximum ball size
		loss_min_box = new SliderBox("LOSS MIN:", 0.0, 1.0, 0.4);		//minimum energy loss
		loss_max_box = new SliderBox("LOSS MAX:", 0.0, 1.0, 0.4);		//maximum energy loss
		min_vel_box = new SliderBox("MIN VEL:", 1,200,25);				//minimum velocity
		max_vel_box = new SliderBox("MAX VEL:", 1, 200, 45);			//maximum velocity
		theta_min_box  = new SliderBox("TH MIN:", 1, 100, 80);			//minimum angle
		theta_max_box = new SliderBox("TH MAX:", 1, 100, 100);			//maximum angle

;

		JPanel right = new JPanel(new TableLayout(10, 1));
		right.add(gsp);
		right.add(num_balls_box);
		right.add(min_size_box);
		right.add(max_size_box);
		right.add(loss_min_box);
		right.add(loss_max_box);
		right.add(min_vel_box);
		right.add(max_vel_box);
		right.add(theta_min_box);
		right.add(theta_max_box);
		add(right,EAST);
		
		//to set up chooser-like pull down menus across the top
		//of the display.
		bSimCB = new JComboBox<String>();
		bSimCB.addItem("bSim");
		bSimCB.addItem("Run");
		bSimCB.addItem("Hist");
		bSimCB.addItem("Clear");
		bSimCB.addItem("Stop");
		bSimCB.addItem("Quit");

		bFileCB = new JComboBox<String>();
		bFileCB.addItem("File");

		bEditCB = new JComboBox<String>();
		bEditCB.addItem("Edit");

		bHelpCB = new JComboBox<String>();
		bHelpCB.addItem("Help");

		JPanel top = new JPanel(new TableLayout(1, 4));
		top.add(bSimCB);
		top.add(bFileCB);
		top.add(bEditCB);
		top.add(bHelpCB);
		add(top,NORTH);
		
		//listeners
		num_balls_box.slider.addChangeListener(this);
		min_size_box.slider.addChangeListener(this);
		max_size_box.slider.addChangeListener(this);
		loss_min_box.slider.addChangeListener(this);
		loss_max_box.slider.addChangeListener(this);
		min_vel_box.slider.addChangeListener(this);
		max_vel_box.slider.addChangeListener(this);
		theta_min_box.slider.addChangeListener(this);
		theta_max_box.slider.addChangeListener(this);

		bSimCB.addItemListener(this);


	}
	public void run()
	{

	}
	public void runSimulation(int myNUMBALLS, int myMinSize, int myMaxSize, double EMIN, double EMAX, int ThetaMIN, int ThetaMAX, int VoMIN, int VoMAX){
		
		RandomGenerator rgen = new RandomGenerator();	//Set up random number generator

		for(int i = 0 ; i < myNUMBALLS ; i++)
		{
			//let's create a ball
			double bLoss = rgen.nextDouble(EMIN, EMAX);						// Current loss coefficient
			double bSize = rgen.nextInt(myMinSize, myMaxSize);				//Current size
			double theta = rgen.nextDouble(ThetaMIN, ThetaMAX);				//Current Launch angle
			double Vo = rgen.nextDouble(VoMIN, VoMAX);						//current X velocity
			Color bColor = rgen.nextColor();								//current color
			double Yi = bSize;												//current Y coordinate
			double Xi = WIDTH/(3*SCALE);									//current x coordinate
			bBall allBalls = new bBall(Xi-bSize, Yi, Vo, theta, bSize, bColor, bLoss);
			add(allBalls.myOval);
			allBalls.start();
			tree.insertbBall(allBalls);

		}

	}
	/**
	 * Method to handle dispach for sliders
	 */
	
	public void stateChanged(ChangeEvent ce) {
		if(ce.getSource() == num_balls_box.slider)
		{
			num_balls_box.def_val_lab.setText(num_balls_box.slider.getValue()+"");
		}
		if(ce.getSource() == min_size_box.slider)
		{
			min_size_box.def_val_lab.setText(min_size_box.slider.getValue()+"");
		}
		if(ce.getSource() == max_size_box.slider)
		{
			max_size_box.def_val_lab.setText(max_size_box.slider.getValue()+"");
		}
		if(ce.getSource() == loss_min_box.slider)
		{
			loss_min_box.def_val_lab.setText(loss_min_box.slider.getValue()/10.0+"");
		}
		if(ce.getSource() == loss_max_box.slider)
		{
			loss_max_box.def_val_lab.setText(loss_max_box.slider.getValue()/10.0+"");
		}
		if(ce.getSource() == theta_min_box.slider)
		{
			theta_min_box.def_val_lab.setText(theta_min_box.slider.getValue()+"");
		}
		if(ce.getSource() == theta_max_box.slider)
		{
			theta_max_box.def_val_lab.setText(theta_max_box.slider.getValue()+"");
		}
		if(ce.getSource() == min_vel_box.slider)
		{
			min_vel_box.def_val_lab.setText(min_vel_box.slider.getValue()+"");
		}
		if(ce.getSource() == max_vel_box.slider)
		{
			max_vel_box.def_val_lab.setText(max_vel_box.slider.getValue()+"");
		}



	}
	/**
	 * Method to handle dispatch for JComboBox
	 */
	
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getSource() == bSimCB)
		{
			if(bSimCB.getSelectedIndex()== 1 && ie.getStateChange()==1){
				System.out.println("running simulation");  
				bBall.running = true;
				runSimulation(num_balls_box.slider.getValue(),min_size_box.slider.getValue(),max_size_box.slider.getValue(),loss_min_box.slider.getValue()/10.0,loss_max_box.slider.getValue()/10.0,theta_min_box.slider.getValue(),theta_max_box.slider.getValue(),min_vel_box.slider.getValue(),max_vel_box.slider.getValue());
			}
			if(bSimCB.getSelectedIndex() == 2 && ie.getStateChange() == 1){
				System.out.println("Sorting balls");
				while(tree.isRunning())
				{
					//do nothing
				}
				// interrupt my balls
				tree.stopAll();
				// reset tree so that the balls appear from the left most area of the screen
				tree.reset();
				// sort all balls from my binary tree
				tree.traverseLMR();	
			}
			if(bSimCB.getSelectedIndex() == 3 && ie.getStateChange() == 1){
				System.out.println("clearing simulation");
				this.removeAll();		// clears the screen
				groundLevel();			//displays the line

			}
			if(bSimCB.getSelectedIndex() == 4 && ie.getStateChange() == 1){
				System.out.println("Stopping simulation");
				//tree.stopAll();
				bBall.running = false;

			}
			if(bSimCB.getSelectedIndex() == 5 && ie.getStateChange() == 1){
				System.out.println("Shutting down");
				System.exit(0);

			}
		}

	}

}
