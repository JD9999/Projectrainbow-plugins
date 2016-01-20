package RainbowBansTransAgent;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class TransAgent{

	public static TransAgent ta;
	public static File dir = new File("RainbowBans");
	public static ArrayList<Long> ln = new ArrayList<Long>();
	public static Exception randomexception;
	
	public static void main(String[] args){
		System.out.println("JarFile looking at: " + getRainbowPath() + "Rainbow.jar");
		System.out.println("Major version is " + IntegerKeys.major + " and minor version is " + IntegerKeys.minor);
		System.out.println("Which was changed to: " + IntegerKeys.newmajor + " and " + IntegerKeys.newminor);
		if(BooleanKeys.premain) System.out.println("Premain agent loaded"); 
		else{
			System.out.println("Premain agent not loaded!");
			ln.add(16185131914L);
			BooleanKeys.error = true;
			}
		if(BooleanKeys.transformer_loaded) System.out.println("Transformation agent loaded!");
		else{
			System.out.println("Transformation agent not loaded!");
			ln.add(16185131914L);
			BooleanKeys.error = true;
		}
		if(BooleanKeys.returns_line && BooleanKeys.returned_bytes) System.out.println("Transformation worked successfully");
		else if (BooleanKeys.returned_bytes && (IntegerKeys.line_Inserted_At != 409)) System.out.println("Transformation worked successfully, but code inserted at the wrong line");
		else if (BooleanKeys.error){
			System.out.println("There was an error in the transformation! Please report the following:");
			System.out.println("Error codes: ");
			for (int i = 0; i < ln.size(); i++){
				System.out.println(ln.get(i));
			}
			if(ln.contains(14152061521144L)){
				System.out.println("This indicates that the joebkt.PlayerList class could not be located!");		
			}else if (ln.contains(69125L)){
				if(BooleanKeys.IOFromEntry){
				System.out.println("This indicates the Rainbow.jar could not be found! Stack trace:");
				StackTraceElement[] e = randomexception.getStackTrace();
				for(int i = 0; i < e.length; i++){
					StackTraceElement ste = e[i];
					String s = ste.toString();
					System.out.println(s);
				}
				}else{
				System.out.println("This indicates that there was an error returning the inserted bytecode to the joebkt.PlayerList class!");
				}
			}else if (ln.contains(16185131914L)){
				System.out.println("This indicates that the transformation stopped either at the premain or the transformation agent!");
			}else if (ln.contains(31513169125L)){
				System.out.println("This indicates an error in adding the code into the joebkt.PLayerList class! Stack trace:");
				StackTraceElement[] e = randomexception.getStackTrace();
				System.out.println(randomexception.getMessage());
				for(int i = 0; i < e.length; i++){
					StackTraceElement ste = e[i];
					String s = ste.toString();
					System.out.println(s);
				}
			}else if (ln.contains(31211919161208L)){
				System.out.println("This indicates an error with javassist getting to Rainbow.jar!");
			}else if (ln.contains(131549625L)){
				System.out.println("This indicates the joebkt.PlayerList class could not be retransformed!");
			}else if(ln.contains(514201825L)){
				System.out.println("This indicates that the JarEntry for the joebkt.PlayerList class could not be made!");
			}
			else{
				System.out.println("Error code misspelt! Please report");
			}
		}else if (BooleanKeys.frozen){
				System.out.println("The class cannot be edited!");	
		}else if (BooleanKeys.found_class){
			System.out.println("At least the class was found. But there were errors in the process.");
		}else{
			System.out.println("NO IDEA WHAT HAPPENED!!!");
		}
		BooleanKeys.premain = false;
	}
	public TransAgent(){
		ta = this;
	}
	
	public static void agentmain(String args, Instrumentation inst){
		premain(args, inst);
	}
	
	public static void premain(String agentArgs, Instrumentation inst) {
		try {
			inst.appendToSystemClassLoaderSearch(new JarFile(getRainbowPath() + "Rainbow.jar", true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BooleanKeys.premain = true;	
		try {
			inst.addTransformer(new Transformationer(), true);
			Class<?> classs = Class.forName("joebkt.PlayerList", false, joebkt.PlayerList.class.getClassLoader());
			Class<?>[] classes = {classs};
			inst.retransformClasses(classes);
			BooleanKeys.premain_done = true;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static String getRainbowPath() {
		return new File(".").getAbsoluteFile().getParent() + File.separator;
	}
}
class BooleanKeys{
	public static boolean premain = false;
	public static boolean transformer_loaded = false;
	public static boolean entry = false;
	public static boolean premain_done = false;
	public static boolean returns_line = false;
	public static boolean error = false;
	public static boolean frozen = false;
	public static boolean found_class = false;
	public static boolean returned_bytes = false;
	public static boolean IOFromEntry = false;
}

class IntegerKeys{
	public static int line_Inserted_At = 0;
	public static int major = 0;
	public static int minor = 0;
	public static int newmajor = 0;
	public static int newminor = 0;
}
