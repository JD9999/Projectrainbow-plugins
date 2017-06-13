package RainbowScripts;

public class MessageInstruction implements Instruction{

	String sender;
	String mes;
	
	public MessageInstruction(String type, String message){
		sender = type;
		mes = message;
	}

	@Override
	public String getReturnMessage() {
		return mes;
	}

	@Override
	public String getSender() {
		return sender;
		
	}
	
}
