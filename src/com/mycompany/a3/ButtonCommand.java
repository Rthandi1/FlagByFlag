package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ButtonCommand extends Button {
	
	ButtonCommand(Command c){
	
	this.getUnselectedStyle().setBgTransparency(255);
	//sets color of text to white
	this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
	//sets color of button to blue
	this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
	this.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
	//Set padding for all styles of buttons
	this.getAllStyles().setPadding(Component.TOP, 10);
	this.getAllStyles().setPadding(Component.BOTTOM, 10);
	this.setFocusable(false);
	this.setCommand(c);
	}
	
}
