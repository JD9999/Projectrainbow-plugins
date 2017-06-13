package Transporter;

import java.util.List;

import PluginReference.MC_Location;

public class GateBuildFailureException extends Exception {

	private String message;
	
	private static final long serialVersionUID = 192L;

	public GateBuildFailureException(String name, List<MC_Location> locations) {
		if(name != null){
			if((locations.size() < 3) && (locations.size() > 0)){ //There is either 1 or two locations
				message = "Less locations than required!";
			}else if(locations.isEmpty()){
				message = "No locations loaded!";
			}else if(locations.size() > 3){
				message = "Too many locations were logged!";
			}else{
				message = "Key switch failed!";
			}
		}else message = "The gate has no name!";
	}
	
	public String getMessage(){
		return message;
	}

}
