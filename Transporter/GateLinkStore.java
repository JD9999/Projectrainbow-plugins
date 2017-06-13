package Transporter;

import java.util.List;

public class GateLinkStore {

	private List<String> linkNames;
	
	public GateLinkStore(List<String> linkNames){
		this.linkNames = linkNames;
	}
	
	public List<String> getLinkNames(){
		return linkNames;
	}
}
