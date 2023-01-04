package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	private GameWorld gw;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt ) {
		//If the check box is selected the sound is "ON"
		if(((CheckBox)evt.getComponent()).isSelected()) {
			gw.setSound(true);
			gw.getBgSound().play();
		//Else it is set to false which is "OFF"
		}else {
			gw.setSound(false);
			gw.getBgSound().pause();
		}
		
	}
}
