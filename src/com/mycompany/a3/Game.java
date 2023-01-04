package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
 
public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;  
	private static String TITLE = "FlagByFlag Game";
	private UITimer timer = new UITimer(this);
	private AccelerateCommand ac;
	private BrakeCommand bc;
	private TurnLeftCommand lc;
	private TurnRightCommand rc;
	private PositionCommand posC;
	private PauseCommand pauC;
	private HelpCommand helpC;
	private SoundCommand soundC;
	private AboutCommand aboutC;
	private ExitCommand exitC;
	private ButtonCommand b;
	private ButtonCommand btAccel;
	private ButtonCommand btBrake;
	private ButtonCommand btRight;
	private ButtonCommand btLeft;
	private ButtonCommand btPause;
	private ButtonCommand btPosition;

	
	public Game() {
		  
		 this.setLayout(new BorderLayout());
		  gw = new GameWorld();    // create Observable GameWorld 
		  mv = new MapView(gw,this); // create an Observer for the map 
		  sv = new ScoreView(gw);    // create an Observer for the game/ant state data 
		  gw.addObserver(mv);  // register the map observer 
		  gw.addObserver(sv);  // register the score observer 
		 
		 
		
		  //add border for the mapview and score view
		  this.add(BorderLayout.CENTER,mv);
		  this.add(BorderLayout.NORTH,sv); 
		// add commands to side menu and title bar area, bind commands to keys, create
		AllCommands();
	
		// ScoreView to the form 
		  this.show(); 
		
		 // code here to query MapViews width and height and set them as worlds  
		    // width and height  
		  gw.setHeight(mv.getHeight());
		  gw.setWidth(mv.getWidth());
		 
		  // initialize world 
		   gw.init();
		  //add the sounds to Game class
		  gw.createSounds();
	   	  revalidate();
	   	 
		  timer.schedule(100, true, this);
	
	} 
	/*
	 * Combines all the button commands, menu, and the keybinds for the program
	 */
	 private void AllCommands() {
		 	
		 	CommandRight();
		 	CommandLeft();
		 	CommandBottom();
		 	CommandMenu();
		 
	 }
	 /*
	  * Create a container for the right container with a set border and 
	  * initialize the commands and buttons and 
	  * add it to the container
	  */
	 private void CommandRight() {
		  Container rightC = new Container(new GridLayout(4,1));
		  rightC.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.BLACK));
		  bc = new BrakeCommand(gw);
		  btBrake = new ButtonCommand(bc);
		  rightC.add(btBrake);
		
		  rc = new TurnRightCommand(gw);
		  btRight = new ButtonCommand(rc);
		  rightC.add(btRight);
	
		  
		  this.add(BorderLayout.EAST,rightC);
 
		 
		 
	 }
	 /*
	  * Create a container for the left container with a set border and 
	  * initialize the commands and buttons and 
	  * add it to the container
	  */
	 private void CommandLeft() {
		  Container leftC = new Container ((new GridLayout(4,1)));
		  leftC.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.BLACK));
		  ac = new AccelerateCommand(gw);
		  btAccel = new ButtonCommand(ac);
		  leftC.add(btAccel);
		  
		  
		  lc = new TurnLeftCommand(gw);
		  btLeft = new ButtonCommand(lc);
		  leftC.add(btLeft);
		 
		 this.add(BorderLayout.WEST,leftC);
	 }
	 /*
	  * Create a container for the bottom set of button and initialize all commands and add it 
	  * to the container
	  */
	 private void CommandBottom() {
		
		 Container bottomC = new Container ((new FlowLayout(Component.CENTER)));
		 bottomC.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.BLACK));
		  pauC = new PauseCommand(gw,this,b);
		  btPause= new ButtonCommand(pauC);
		  bottomC.add(btPause);
		  
		  
		  posC = new PositionCommand(gw);
		  btPosition = new ButtonCommand(posC);
		  bottomC.add(btPosition);
		 
		  this.add(BorderLayout.SOUTH,bottomC);
	 }
	 /*
	  * CommandMenu method includes 
	  */
	 private void CommandMenu() {
		 	Toolbar menu = new Toolbar();
			this.setToolbar(menu);
			menu.setTitle(TITLE);
			CheckBox checkSideMenuComponent = new CheckBox();
			checkSideMenuComponent.getAllStyles().setBgTransparency(255);
			checkSideMenuComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			soundC = new SoundCommand(gw);
			checkSideMenuComponent.setCommand(soundC);
			menu.addComponentToSideMenu(checkSideMenuComponent);
			
			//Adds accelerate command to the side menu
			ac = new AccelerateCommand(gw);
			menu.addCommandToSideMenu(ac);
			
			//Adds help command on the right side of the title bar
			helpC = new HelpCommand(gw);
			menu.addCommandToRightBar(helpC);
			
			//Adds about command on the left side of the title bar
			aboutC = new AboutCommand(gw);
			menu.addCommandToLeftBar(aboutC);
			
			//Add exit command to the side menu
		    exitC = new ExitCommand(gw);
			menu.addCommandToSideMenu(exitC);
	 }
	

	@Override
	public void run() {
		Dimension dCmpSize = new Dimension(gw.getWidth(),gw.getHeight());
		
		gw.tick(100, dCmpSize);
		
	}
	public void pauseG(){
		//if the game is paused
		if(!gw.pauseMode()) {
			//disable all keybinds and buttons
			 removeKeyListener('b', bc);
			 removeKeyListener('r', rc);
			 removeKeyListener('a', ac);
			 removeKeyListener('l', lc);
			 removeKeyListener('x', exitC);
			 bc.setEnabled(false);
			 rc.setEnabled(false);
			 ac.setEnabled(false);
			 lc.setEnabled(false);
			 exitC.setEnabled(false);
			posC.setEnabled(true);
			 btAccel.setEnabled(false);
			btBrake.setEnabled(false);
			btRight.setEnabled(false);
			btLeft.setEnabled(false);
			//enables only position button
		    btPosition.setEnabled(true);
		    //pause sound
			 gw.getBgSound().pause();
			 gw.setPause(!gw.pauseMode());
			 timer.cancel();
			 
		}else {
			//enables all keybinds and buttons except position
			 addKeyListener('b', bc);
			 addKeyListener('r', rc);
			 addKeyListener('a', ac);
			 addKeyListener('l', lc);
			 addKeyListener('p', pauC);
			 addKeyListener('x', exitC);
			 bc.setEnabled(true);
			 rc.setEnabled(true);
			 ac.setEnabled(true);
			 lc.setEnabled(true);
			 exitC.setEnabled(true);
			 posC.setEnabled(false);
			 btAccel.setEnabled(true);
			 btBrake.setEnabled(true);
			 btRight.setEnabled(true);
		     btLeft.setEnabled(true);
			 btPosition.setEnabled(false);
			 //runs sound 
			 gw.getBgSound().run();
			 gw.setPause(!gw.pauseMode());
			 timer.schedule(100, true, this);
		}
		 
	}

		} 
		 
	