package BetterBackups;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackupManager {

	private static PrintStream stream;
	private static boolean received = false;
	private static List<File> directories = new ArrayList<File>();
	private static String directory = "";
	public static boolean silent = false;
	private static int count = 0;
	private static List<File> files = new ArrayList<File>();
	private static FTPManager manager = null;
	public static List<String> determineLineData(File file) {
		if(file.length() <= 0){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(MyPlugin.backupfile));
				writer.write("base_directory:plugins_mod/BetterBackups/Backups");
				writer.newLine();
				writer.write("automatic:false");
				writer.newLine();
				writer.write("time_unit:3h");
				writer.newLine();
				writer.write("ftp:false");
				writer.newLine();
				writer.write("ftp-website:");
				writer.newLine();
				writer.write("ftp-username:");
				writer.newLine();
				writer.write("ftp-password:");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String un = null;
		List<String> data = new ArrayList<String>();
		boolean ftp = false;
		String username = "?";
		String website = "?";
		String password = "?";
		try {
			List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
			for(int i = 0; i < lines.size(); i++){
				String line = lines.get(i);
				if(line.startsWith("time_unit:")){
					String unit = line.substring(10);
					un = unit;
					data.add(unit + "dir");
				}else if (line.startsWith("base_directory:")){
					String directory = line.substring(15);
					data.add(directory);
					BackupManager.directory = directory;
				}else if (line.startsWith("automatic:")){
					String b = line.substring(10);
					boolean bo = Boolean.parseBoolean(b); //EqualsIgnoreCase("true");
					if(bo){
						BackupManager.addTimerFor(timefromunit(un), BackupManager.directory );
					}
				}else if(line.startsWith("ftp:")){
					if(line.equals("ftp:true")) ftp = true;
					else ftp = false;
				}else if(line.startsWith("ftp-website:") && ftp){
					website = line.substring(12);
				}else if(line.startsWith("ftp-username:") && ftp){
					username = line.substring(13);
				}else if(line.startsWith("ftp-password:") && ftp){
					password = line.substring(13);
				}else if(!ftp){
					website = "?";
					username = "?";
					password = "?";
				}else throw new IOException("unable to read line: " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(ftp){
			if(username == "?" || password == "?" || website == "?"){
				ftp = false;
				System.out.println("FTP is off!");
			}else{
				manager = new FTPManager(website, username, password);
				System.out.println("FTP is on!");
			}
		}
		return data;
	}

	private static Integer timefromunit(String unit) {
		if(unit == null && received == true){
			throw new NullPointerException("Time unit is null!");
		}else if (unit == null) return 0;
		else{
			int i = Integer.parseInt(unit.substring(0, unit.length() - 3));
			if(unit.endsWith("m")){
				return i * 60;
			}else if (unit.endsWith("h")){
				return i * 3600;
			}else if (unit.endsWith("d")){
				return i * 3600 * 24; // Not bothered to do the maths, java can do that.
			}else if (unit.endsWith("w")){
				return i * 3600 * 188;
			}else{
				System.err.println("Incorrect syntax! timeunit not recognised");
			}
		}
		return 0;
	}

	private static void addTimerFor(Integer s, String dir) {
		if(s == 0){
			return;
		}else{
			Timer t = new Timer();
			TimerTask tt = new TimerTask(){
				
				@Override
				public void run(){
					backupData(obtainBackupPath());
				}
			};
			t.scheduleAtFixedRate(tt, s * 1000, s * 1000);
		}
	}
	
	protected static String obtainBackupPath() {
		if(received){
		List<String> lines = MyPlugin.info;
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			if(BackupManager.directory.equals(line)){
				return line;	
		}
		}
		return MyPlugin.backupfile.getAbsolutePath() + File.separator + dateString() + File.separator;
		}else{
		return MyPlugin.backupfile.getParentFile().getAbsolutePath() + File.separator + dateString() + File.separator;
		}
	}
		

	public static void backupData(String dir) {
		System.out.println("Backing up to directory: " + dir);	
		PrintStream stream = System.err;
		BackupManager.stream = stream;
		System.setErr(getFilePrintStream());
		File dirfile = new File(dir);
		dirfile = dirfile.getAbsoluteFile();
		File[] files = new File(MyPlugin.rainbowLoc).listFiles();
		for(int i = 0; i < files.length; i++){
			File f = files[i];
			if(f.getAbsolutePath().contains("Rainbow.jar")) continue; //Copying the JAR will break the server.
			else if(f.getAbsolutePath().contains("BetterBackups")) continue; //This is so it doesn't backup other backups. That's one feature about FabioZumi12's backup plugin that got really annoying
			else try {
				Path currentPath = f.toPath();
				Path targetPath = getTargetPath(f);
				CopyOption[] options = getCopyOptions();
				count++;
				BackupManager.files.add(f);
				Files.copy(currentPath, targetPath, options);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(f.isDirectory()){
				if(!silent)System.out.println("Located subfolder: " + f.getAbsolutePath());
				scheduleDirectory(f);
			}
		}
		System.setErr(BackupManager.stream);
		backupDirectories();
		if(manager !=null){
			manager.uploadViaFTP();
		}
	}

	private static PrintStream getFilePrintStream() {
		File f = new File(MyPlugin.backupfile.getParent() + File.separator + "errors.txt");
		if(!f.isFile())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			OutputStream stream = new FileOutputStream(f);
			return new PrintStream(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static CopyOption[] getCopyOptions() {
		CopyOption[] co = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES};
		return co;
	}

	private static Path getTargetPath(File f) {
		if(!silent)System.out.println("Parsing file" + f.getAbsolutePath());
		String absolute = f.getAbsolutePath();
		String rbowParent = new File(MyPlugin.rainbowLoc).getAbsoluteFile().getParent();
		String filepath = absolute.substring(0, absolute.indexOf(rbowParent));
		String rbowParentSingle = rbowParent.substring(rbowParent.lastIndexOf(File.separator), rbowParent.length());
		String filename = absolute.substring(absolute.indexOf(rbowParentSingle), absolute.length());
		String newpath = null;
		try {
			newpath = advanceFilePath(filepath, filename);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String dirs = newpath.substring(0, newpath.lastIndexOf(File.separator));
		File directories = new File(dirs);
		if(!directories.exists()) directories.mkdirs();
		File file = new File(newpath);
		if(file.exists()) file.delete();
		return file.toPath();
	}

	private static String advanceFilePath(String filepath, String filename) throws URISyntaxException {
		Path p = Paths.get(new File(filepath).toURI());
		Path p1 = Paths.get(new File(".").toURI());
		Path relative = p.relativize(p1);
		String parentpath = MyPlugin.backupfile.getParent();
		String backuppath = parentpath + File.separator + dateString() + File.separator;
		String newpath = backuppath.concat(relative.toString());
		String path = newpath.concat(filename);
		return path;
	}

	static String dateString() {
		Calendar cal = Calendar.getInstance();
		String dateformat = cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH + 1) + "-" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.HOUR_OF_DAY);
		return dateformat;
	}

	private static void scheduleDirectory(File f) {
		directories.add(f);
	}

	private static void backupDirectories() {
		for(int i = 0; i < directories.size(); i++){
			File f = directories.get(i);
			directories.remove(f);
			File[] files = f.listFiles();
			for(int e = 0; e < files.length; e++){
				File file = files[e];
				try {
					count++;
					BackupManager.files.add(file);
					Files.copy(file.toPath(), getTargetPath(file), getCopyOptions());
				} catch (IOException exception) {
					if(!exception.getMessage().contains("DirectoryNotEmptyException"))
					exception.printStackTrace();
				}
				if(file.isDirectory()){
					scheduleDirectory(file);
					if(!silent)System.out.println("Located subfolder in subfolder: " + file.getAbsolutePath());
				}
			}
		}
	}

	public static File getZIPFile() {
		try {
			File file = zipBackup();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static File zipBackup() throws IOException {
		File file = new File("backup-" + dateString() + ".zip");
		if(!file.exists()) file.createNewFile();
			ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(file));
			List<ZipEntry> entry = getZipEntries();
			for(int i= 0; i < entry.size(); i++){
				ZipEntry ze = entry.get(i);
				stream.putNextEntry(ze);
			}
			stream.close();
			return file;
	}

	private static List<ZipEntry> getZipEntries() {
		List<ZipEntry> ls = new ArrayList<ZipEntry>();
		for(int i = 0; i < count; i++){
			File f = forNumber(count);
			ZipEntry ze = new ZipEntry(f.getPath());
			ls.add(ze);
		}
		return ls;
	}

	private static File forNumber(int count) {
		return BackupManager.files.get(count);
	}
}
