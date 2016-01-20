package RainbowBansTransAgent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

public class Transformationer implements ClassFileTransformer {
	
	public byte[] transform(String arg1, byte[] arg2){
		ClassReader cr = new ClassReader(arg2);
	    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
	    cr.accept(cw, 0);
	    return cw.toByteArray();
	}

	@Override
	public byte[] transform(ClassLoader arg0, String className, Class<?> arg2,
			ProtectionDomain arg3, byte[] arg4)
			throws IllegalClassFormatException {
		BooleanKeys.transformer_loaded = true;
		byte[] b = null;
		String realName = className.replaceAll("/", ".");
		if(realName.equals("joebkt.PlayerList")){
			if(BooleanKeys.returned_bytes){
				return null;
			}else{
			BooleanKeys.found_class = true;
			b =  transform(realName, arg4);
			if(b !=null){
				BooleanKeys.returned_bytes = true;
			}
			}
		}
		else System.out.println("Class name " + realName + " is not what we're looking for!");
		return b;
	}

}
