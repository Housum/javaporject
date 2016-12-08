package com.hotusm.designModel.command;

public class LightOffCommand implements Command{

	private Light light;
	
	public LightOffCommand(Light light) {
		
		this.light=light;
	}
	
	public void execute(){
		
		this.light.off();
	}

	@Override
	public void undo() {
		
		this.light.off();
	}
	
	
}
