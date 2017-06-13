package RainbowScripts;

public class CommandInstruction implements Instruction {

	public String sender;
    public String message;
    public boolean perm;
	
	public CommandInstruction(String type, String mes, boolean cando) {
		sender = type;
		message = mes;
		perm = cando;
	}
	
	@Override
	public String getReturnMessage() {
		return message;
	}

	@Override
	public String getSender() {
		return sender;
	}

	public boolean hasPerm(){
		return perm;
	}
	
}
