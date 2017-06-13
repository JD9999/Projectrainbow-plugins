package Transporter;

import java.util.List;
import java.util.Random;

public class GateRequest {

	private List<Gate> allGates;
	private Gate gate;
	
	public GateRequest(List<Gate> list){
		allGates = list;
	}
	
	public GateRequest(Gate g){
		gate = g;
	}
	
	public Gate returnGate(){
		return gate !=null ? gate : randomGate(allGates);
	}
	
	private Gate randomGate(List<Gate> gates){
		int size = gates.size();
		Random r = new Random();
		int iLoc = r.nextInt(size);
		return gates.get(iLoc);
	}
	
}
