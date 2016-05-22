package BetterBackups;

import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class FTPManager {

	String website;
	String username;
	String password;
	
	public FTPManager(String website, String username, String password){
		this.website = website;
		this.username = username;
		this.password = password;
	}
	
	public void uploadViaFTP(){
		FTPClient clinet = new FTPClient();
		try {
			clinet.connect(website);
			clinet.login(username, password);
			clinet.upload(BackupManager.getZIPFile());
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException | FTPDataTransferException | FTPAbortedException e) {
			e.printStackTrace();
		}
	}
	
}
