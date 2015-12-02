package Clock;

import java.io.Console;
import java.util.Date;

public class Application {

	public static void main(String[] args)
	{
        Console console = System.console();
        if (console == null) 
        {
            System.err.println("No console.");
            System.exit(1);
        }
         
        boolean keepRunning = true;
        while (keepRunning)
        {       
            String name =  console.readLine("Type your command, or 'exit' to quit: ");
            if ("exit".equals(name))
            {
                keepRunning = false;
            }
            else if ("/addtest".equals(name)){
            	int number = 0;
            	System.out.println(number);
            	int newnumber = number + 1;
            	System.out.println(newnumber);
            }
            else if ("/time".equals(name)){
            	Date date = new Date();
				System.out.println(date.toString());
            }
            else if ("/minutetimer".equals(name)){
            	System.out.println("Putting on a non-repeating minute timer");
            	Timerclass.minutetimer();
            }else if ("/minutetimerrepeating".equals(name)){
            	System.out.println("Putting on a repeating minute timer");
            	Timerclass.repeatingminutetimer();
            }else if ("/halfhourtimer".equals(name)){
            	Timerclass.halfhourtimer();
            }else if ("/halfhourtimerrepeating".equals(name)){
            	Timerclass.repeatinghalfhourtimer();
            }else if ("/hourtimer".equals(name)){
            	Timerclass.hourtimer();
            }else if ("/hourtimerrepeating".equals(name)){
            	Timerclass.repeatinghourtimer();
            }else if ("/stopwatchstart".equals(name)){
            	Stopwatch sw = new Stopwatch();
            	Stopwatch.millisecond = false;
            	sw.startstopwatch();
            }else if ("/stopwatchstop".equals(name)){
            	Stopwatch sw = new Stopwatch();
            	sw.stopstopwatch();
            }
            else
            {
                System.out.println("That is not a command. Commands are:");
                System.out.println("/time tells you the time");
                System.out.println("/minutetimer puts on a minute timer");
                System.out.println("/minutetimerrepeating puts on a repeating minute timer");
                System.out.println("/halfhourtimer puts on a half hour timer");
                System.out.println("/halfhourtimerrepeating puts on a repeating half hour timer");
                System.out.println("/hourtimer puts on a hour timer");
                System.out.println("/hourtimerrepeating puts on a repeating hour timer");
                System.out.println("/stopwatchstart starts a stopwatch");
                System.out.println("/stopwatchpause pauses the stopwatch");
                System.out.println("/stopwatchstop stops the stopwatch");
            }
        }
    }	
	
}
