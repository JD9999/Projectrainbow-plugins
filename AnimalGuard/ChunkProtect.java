package AnimalGuard;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing chunk protection. These are all the basic methods.
 * The only variables you need to get to use this class is the chunk x and chunk z for the constructor.
 * The class adds the coordinates into the system and all commands are then done without variables.
 * 
 * You can use whatever code from here you want. I will be adding this into Shared Packages with a few more options under a new package called "MyFirstPlugin".
 * 
 * @version v0.1 (of the chunk protection)
 */
public class ChunkProtect{

static List<Integer> ls = new ArrayList<Integer>();
int cx;
int cz;

/**
 * Registers a chunk with the chunk protection
 * @param cx chunk x
 * @param cz chunk z
 */
public ChunkProtect(int cx, int cz){
	this.cx = cx;
	this.cz = cz;
}

/**
 * Adds the registered chunk to be protected
 */
public void addchunk(){
	ls.add((cx * 100) + cz);
	System.out.println(cx);
	System.out.println(cz);
	int i = ls.indexOf((cx * 100) + cz);
	System.out.println("At index " + i);
	
}

/**
 * Checks if a chunk is protected
 * @return true if the chunk is protected, false otherwise
 */
public boolean checkifchunkisprotected(){
	if(ls.isEmpty()){
		System.out.println("No chunks are protected!");
		return false;
	}else{
	if(ls.contains((cx * 100) + cz)){
			return true;
			}else{
			}
	return false;
}
}
/**
 * Removes the registered chunk from the protection list.
 */
public void removechunk(){
	ls.remove((cx * 100) + cz);
}

public static List<Integer> getProtectedChunks(){
	return ls;
	
}

}
