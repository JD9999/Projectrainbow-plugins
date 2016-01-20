package RainbowBans;

import PluginReference.MC_EventInfo;
import PluginReference.MC_Player;
import PluginReference.MC_Server;
import PluginReference.PluginBase;
import PluginReference.PluginInfo;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import joebkt.BannedPlayers;
import joebkt.CmdBan;
import joebkt.CmdKick;
import joebkt.PlayerList;
import joebkt._PermMgr;
import net.minecraft.server.MinecraftServer;

public class MyPlugin
  extends PluginBase
{
  public static MC_Server server = null;
  String pluginDir = "plugins_mod" + File.separatorChar;
  String folderDir = this.pluginDir + "RainbowBans" + File.separatorChar;
  String rainbowDir = MyPlugin.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
  
  public void onStartup(MC_Server svr)
  {
    System.out.println("plugins_mod folder located at:" + new File(this.pluginDir).getAbsolutePath());
    System.out.println("Plugin starting! Lets hope this works! :)");
    System.out.println("RainbowBansTransAgent is located at: " + new File(new StringBuilder(String.valueOf(this.pluginDir)).append("RainbowBansTransAgent.jar").toString()).getAbsolutePath());
    server = svr;
    System.out.println("Creating files now!");
    File file = new File(this.folderDir);
    if (file.isDirectory())
    {
      System.out.println("Directory already exists!");
    }
    else
    {
      System.out.println("Creating plugin directory");
      file.mkdir();
    }
    File errorfile = new File(this.folderDir + File.separatorChar + "Rainbowbanserrors.txt");
    if (errorfile.isFile()) {
      System.out.println("File errorfile already exists");
    } else {
      try
      {
        System.out.println("Creating stack trace file!");
        errorfile.createNewFile();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    File banfile = new File(this.folderDir + File.separatorChar + "banmessage.txt");
    if (banfile.isFile()) {
      System.out.println("Banfile already exists!");
    } else {
      try
      {
        banfile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(banfile));
        writer.write("You are banned.");
        writer.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    File logfile = new File(this.folderDir + File.separatorChar + "Rainbowbanstransagentlog.txt");
    if (logfile.isFile()) {
      System.out.println("Logfile already exists!");
    } else {
      try
      {
        logfile.createNewFile();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    JFrame frame = startDialog();
    JMenuBar menu = createMenu();
    frame.setJMenuBar(menu);
    File transagent = new File(this.pluginDir + File.separatorChar + "RainbowBansTransAgent.jar");
    ProcessBuilder pb = new ProcessBuilder(new String[] { "java", "-javaagent:" + transagent.getAbsolutePath() });
    pb.directory(new File(this.rainbowDir));
    pb.redirectError(ProcessBuilder.Redirect.appendTo(errorfile));
    pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logfile));
    try
    {
      pb.start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void onShutdown()
  {
    System.out.println("RainbowBans shutting down!");
  }
  
  public PluginInfo getPluginInfo()
  {
    PluginInfo info = new PluginInfo();
    info.description = "Ban players with a customisable message: version 0.1 started";
    return info;
  }
  
  public void onPlayerInput(MC_Player plr, String mes, MC_EventInfo ei)
  {
    ei.isCancelled = true;
    if (mes.startsWith("/unban")) {
      if (plr.hasPermission("RainbowBans.unban"))
      {
        if (!plr.hasPermission(CmdBan.permKey)) {
          _PermMgr.givePermission(plr.getName(), CmdBan.permKey);
        }
        String command = mes.replaceFirst("unban", "pardon");
        plr.executeCommand(command);
      }
      else
      {
        plr.sendMessage("You do not have the RainbowBans.unban permission!");
        if (plr.isOp()) {
          plr.sendMessage("RainbowBans has the potential to override op permissions, please ensure you have the permission");
        }
      }
    }
    if (mes.startsWith("/ban")) {
      if (plr.hasPermission("RainbowBans.ban"))
      {
        ei.isCancelled = false;
        if (!plr.hasPermission(CmdBan.permKey)) {
          _PermMgr.givePermission(plr.getName(), CmdBan.permKey);
        }
      }
      else
      {
        plr.sendMessage("You do not have permission RainbowBans.ban");
        if (plr.isOp()) {
          plr.sendMessage("RainbowBans has the potential to override op permissions, please ensure you have the permission");
        }
        ei.isCancelled = true;
      }
    }
    if (mes.startsWith("/kick")) {
      if (plr.hasPermission("RainbowBans.kick"))
      {
        ei.isCancelled = false;
        if (!plr.hasPermission(CmdKick.permKey)) {
          _PermMgr.givePermission(plr.getName(), CmdKick.permKey);
        }
      }
      else
      {
        plr.sendMessage("You do not have permission RainbowBans.kick");
        if (plr.isOp()) {
          plr.sendMessage("RainbowBans has the potential to override op permissions, please ensure you have the permission");
        }
        ei.isCancelled = true;
      }
    }
    if (mes.startsWith("/lookup"))
    {
      ei.isCancelled = true;
      if (plr.hasPermission("RainbowBans.lookup"))
      {
        String plrmessage = "";
        String[] commandarray = toCommandArray(mes);
        GameProfile profile = null;
        if (!mes.equals("/lookup"))
        {
          System.out.println(commandarray[1]);
          GameProfile[] profilearray = MinecraftServer.getServer().getPlayerGameProfiles();
          for (int i = 0; i < profilearray.length; i++)
          {
            System.out.println(profilearray[i].getName());
            if (commandarray[1].equals(profilearray[i].getName())) {
              profile = profilearray[i];
            }
          }
          if (profile == null)
          {
            plrmessage = "Unable to obtain player profile!";
          }
          else
          {
            BannedPlayers bp = new BannedPlayers(PlayerList.fileBannedPlayers);
            if (bp.a(profile))
            {
              plrmessage = "Player " + profile + " is banned!";
              if (plr.hasPermission(CmdBan.permKey)) {
                plrmessage = plrmessage + " However, you can unban them!";
              } else if (bp.isBanCommandAllowed()) {
                plrmessage = plrmessage + " And you can't unban them, but someone else can!";
              } else {
                plrmessage = plrmessage + " And only the server can unban them!";
              }
              Socket socket = new Socket(MinecraftServer.getServer().getProxyObj());
              SocketAddress socketaddress = socket.getLocalSocketAddress();
              try
              {
                socket.close();
              }
              catch (IOException e)
              {
                e.printStackTrace();
              }
              if (MinecraftServer.getServer().getThePlayerList().checkIfShouldDisconnect(socketaddress, profile) != null) {
                plrmessage = plrmessage + "The player would be banned with the following message: " + MinecraftServer.getServer().getThePlayerList().checkIfShouldDisconnect(socketaddress, profile);
              }
            }
            else
            {
              plrmessage = "Player " + profile.getName() + " is NOT banned!";
            }
          }
        }
        plr.sendMessage(plrmessage);
      }
    }
  }
  
  private String[] toCommandArray(String mes)
  {
    String[] array = mes.split(" ");
    return array;
  }
  
  private JMenuBar createMenu()
  {
    JMenuBar menu = new JMenuBar();
    JMenuItem i = new JMenuItem("See message");
    i.addActionListener(getActionListenerForMessage(i));
    menu.add(i);
    JMenuItem w = new JMenuItem("Go to project page");
    w.addActionListener(getActionListenerForWebsites("http://www.project-rainbow.org/site/index.php?board=9.0"));
    menu.add(w);
    JMenuItem k = new JMenuItem("Go to download page");
    k.addActionListener(getActionListenerForWebsites("http://www.project-rainbow.org/site/index.php?action=downloads;cat=3"));
    menu.add(k);
    return menu;
  }
  
  private void openWebsite(String url)
  {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if ((desktop != null) && (desktop.isSupported(Desktop.Action.BROWSE))) {
      try
      {
        desktop.browse(new URL(url).toURI());
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private ActionListener getActionListenerForWebsites(final String url)
  {
    ActionListener al = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        MyPlugin.this.openWebsite(url);
      }
    };
    return al;
  }
  
  private ActionListener getActionListenerForMessage(final JMenuItem i)
  {
    ActionListener al = new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          BufferedReader reader = new BufferedReader(new FileReader(new File(MyPlugin.this.folderDir + File.separatorChar + "banmessage.txt")));
          String message = reader.readLine();
          i.setText(message);
          Timer timer = new Timer();
          timer.schedule(MyPlugin.this.getTimerTask(i), 5000L);
          reader.close();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
    };
    return al;
  }
  
  private TimerTask getTimerTask(final JMenuItem i)
  {
    TimerTask task = new TimerTask()
    {
      public void run()
      {
        i.setText("See message");
      }
    };
    return task;
  }
  
  private JFrame startDialog()
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(0);
    frame.setVisible(true);
    frame.setSize(800, 600);
    frame.setAlwaysOnTop(false);
    frame.setBackground(Color.yellow);
    return frame;
  }
}
