package bedClaim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

	public class Readlastline {
		public static final int zero = 0;
		public static final int one = 1;
		public static final int two = 2;
		public static final int three = 3;
		public static final int four = 4;
		public static final int five = 5;
		public static final int six = 6;
		public static final int seven = 7;
		public static final int eight = 8;
		public static final int nine = 9;
		public static final int ten = 10;
		public static final int eleven = 11;
		public static final int twelve = 12;
		public static final int thirteen = 13;
		public static final int fourteen = 14;
		public static final int fifteen = 15;
		public static final int sixteen = 16;
		public static final int seventeen = 17;
		public static final int eighteen = 18;
		public static final int nineteen = 19;
		public static final int twenty = 20;
		public static final int twentyone = 21;
		public static final int twentytwo = 22;
		public static final int twentythree = 23;
		public static final int twentyfour = 24;
		public static final int twentyfive = 25;
		public static final int twentysix = 26;
		public static final int twentyseven = 27;
		public static final int twentyeight = 28;
		public static final int twentynine = 29;
		public static final int thirty = 30;
		public static final int thirtyone = 31;
		public static final int thirtytwo = 32;
		public static final int thirtythree = 33;
		public static final int thirtyfour = 34;
		public static final int thirtyfive = 35;
		public static final int thirtysix = 36;
		public static final int thirtyseven = 37;
		public static final int thirtyeight = 38;
		public static final int thirtynine = 39;
		public static final int fourty = 40;
		public static final int fourtyone = 41;
		public static final int fourtytwo = 42;
		public static final int fourtythree = 43;
		public static final int fourtyfour = 44;
		public static final int fourtyfive = 45;
		public static final int fourtysix = 46;
		public static final int fourtyseven = 47;
		public static final int fourtyeight = 48;
		public static final int fourtynine = 49;
		public static final int fifty = 50;
		public static final int fiftyone = 51;
		public static final int fiftytwo = 52;
		public static final int fiftythree = 53;
		public static final int fiftyfour = 54;
		public static final int fiftyfive = 55;
		public static final int fiftysix = 56;
		public static final int fiftyseven = 57;
		public static final int fiftyeight = 58;
		public static final int fiftynine = 59;
		public static final int sixty = 60;
		public static final int sixtyone = 61;
		public static final int sixtytwo = 62;
		public static final int sixtythree = 63;
		public static final int sixtyfour = 64;
		public static final int sixtyfive = 65;
		public static final int sixtysix = 66;
		public static final int sixtyseven = 67;
		public static final int sixtyeight = 68;
		public static final int sixtynine = 69;
		public static final int seventy = 70;
		public static final int seventyone = 71;
		public static final int seventytwo = 72;
		public static final int seventythree = 73;
		public static final int seventyfour = 74;
		public static final int seventyfive = 75;
		public static final int seventysix = 76;
		public static final int seventyseven = 77;
		public static final int seventyeight = 78;
		public static final int seventynine = 79;
		public static final int eighty = 80;
		public static final int eightyone = 81;
		public static final int eightytwo = 82;
		public static final int eightythree = 83;
		public static final int eightyfour = 84;
		public static final int eightyfive = 85;
		public static final int eightysix = 86;
		public static final int eightyseven = 87;
		public static final int eightyeight = 88;
		public static final int eightynine = 89;
		public static final int ninety = 90;
		public static final int ninetyone = 91;
		public static final int ninetytwo = 92;
		public static final int ninetythree = 93;
		public static final int ninetyfour = 94;
		public static final int ninetyfive = 95;
		public static final int ninetysix = 96;
		public static final int ninetyseven = 97;
		public static final int ninetyeight = 98;
		public static final int ninetynine = 99;

	public static String tail(File file, String BedMessage1) {
	    RandomAccessFile fileHandler = null;
	    try {
	        fileHandler = new RandomAccessFile( file, "r" );
	        long fileLength = fileHandler.length() - 1;
	        StringBuilder sb = new StringBuilder();

	        for(long filePointer = fileLength; filePointer != -1; filePointer--){
	            fileHandler.seek( filePointer );
	            int readByte = fileHandler.readByte();

	            if( readByte == 0xA ) {
	                if( filePointer == fileLength ) {
	                    continue;
	                }
	                break;

	            } else if( readByte == 0xD ) {
	                if( filePointer == fileLength - 1 ) {
	                    continue;
	                }
	                break;
	            }

	            sb.append( ( char ) readByte );
	        }

	        String lastLine = sb.reverse().toString();
	       int no = Integer.parseInt(lastLine);
	       try{
	   		BufferedWriter writer = null;
	   		writer = new BufferedWriter(new FileWriter("BedClaim.txt", true));
	   		writer.newLine();
	   		writer.write(BedMessage1);
	   		writer.newLine();
	   		switch(no){
	   		case zero:
	   			writer.write("1");
	   			break;
	   		case one:
	   			writer.write("2");
	   			break;
	   		case two:
	   			writer.write("3");
	   			break;
	   		case three:
	   			writer.write("4");
	   			break;
	   		case four:
	   			writer.write("5");
	   			break;
	   		case five:
	   			writer.write("6");
	   			break;
	   		case six:
	   			writer.write("7");
	   			break;
	   		case seven:
	   			writer.write("8");
	   			break;
	   		case eight:
	   			writer.write("9");
	   			break;
	   		case nine:
	   			writer.write("10");
	   			break;
	   		case ten:
	   			writer.write("11");
	   			break;
	   		case eleven:
	   			writer.write("12");
	   			break;
	   		case twelve:
	   			writer.write("13");
	   			break;
	   		case thirteen:
	   			writer.write("14");
	   			break;
	   		case fourteen:
	   			writer.write("15");
	   			break;
	   		case fifteen:
	   			writer.write("16");
	   			break;
	   		case sixteen:
	   			writer.write("17");
	   			break;
	   		case seventeen:
	   			writer.write("18");
	   			break;
	   		case eighteen:
	   			writer.write("19");
	   			break;
	   		case nineteen:
	   			writer.write("20");
	   			break;
	   		case twenty:
	   			writer.write("21");
	   			break;
	   		case twentyone:
	   			writer.write("22");
	   			break;
	   		case twentytwo:
	   			writer.write("23");
	   			break;
	   		case twentythree:
	   			writer.write("24");
	   			break;
	   		case twentyfour:
	   			writer.write("25");
	   			break;
	   		case twentyfive:
	   			writer.write("26");
	   			break;
	   		case twentysix:
	   			writer.write("27");
	   			break;
	   		case twentyseven:
	   			writer.write("28");
	   			break;
	   		case twentyeight:
	   			writer.write("29");
	   			break;
	   		case twentynine:
	   			writer.write("30");
	   			break;
	   		case thirty:
	   			writer.write("31");
	   			break;
	   		case thirtyone:
	   			writer.write("32");
	   			break;
	   		case thirtytwo:
	   			writer.write("33");
	   			break;
	   		case thirtythree:
	   			writer.write("34");
	   			break;
	   		case thirtyfour:
	   			writer.write("35");
	   			break;
	   		case thirtyfive:
	   			writer.write("36");
	   			break;
	   		case thirtysix:
	   			writer.write("37");
	   			break;
	   		case thirtyseven:
	   			writer.write("38");
	   			break;
	   		case thirtyeight:
	   			writer.write("39");
	   			break;
	   		case thirtynine:
	   			writer.write("40");
	   			break;
	   		case fourty:
	   			writer.write("41");
	   			break;
	   		case fourtyone:
	   			writer.write("42");
	   			break;
	   		case fourtytwo:
	   			writer.write("43");
	   			break;
	   		case fourtythree:
	   			writer.write("44");
	   			break;
	   		case fourtyfour:
	   			writer.write("45");
	   			break;
	   		case fourtyfive:
	   			writer.write("46");
	   			break;
	   		case fourtysix:
	   			writer.write("47");
	   			break;
	   		case fourtyseven:
	   			writer.write("48");
	   			break;
	   		case fourtyeight:
	   			writer.write("49");
	   			break;
	   		case fourtynine:
	   			writer.write("50");
	   			break;
	   		case fifty:
	   			writer.write("51");
	   			break;
	   		case fiftyone:
	   			writer.write("52");
	   			break;
	   		case fiftytwo:
	   			writer.write("53");
	   			break;
	   		case fiftythree:
	   			writer.write("54");
	   			break;
	   		case fiftyfour:
	   			writer.write("55");
	   			break;
	   		case fiftyfive:
	   			writer.write("56");
	   			break;
	   		case fiftysix:
	   			writer.write("57");
	   			break;
	   		case fiftyseven:
	   			writer.write("58");
	   			break;
	   		case fiftyeight:
	   			writer.write("59");
	   			break;
	   		case fiftynine:
	   			writer.write("60");
	   			break;
	   		case sixty:
	   			writer.write("61");
	   			break;
	   		case sixtyone:
	   			writer.write("62");
	   			break;
	   		case sixtytwo:
	   			writer.write("63");
	   			break;
	   		case sixtythree:
	   			writer.write("64");
	   			break;
	   		case sixtyfour:
	   			writer.write("65");
	   			break;
	   		case sixtyfive:
	   			writer.write("66");
	   			break;
	   		case sixtysix:
	   			writer.write("67");
	   			break;
	   		case sixtyseven:
	   			writer.write("68");
	   			break;
	   		case sixtyeight:
	   			writer.write("69");
	   			break;
	   		case sixtynine:
	   			writer.write("70");
	   			break;
	   		case seventy:
	   			writer.write("71");
	   			break;
	   		case seventyone:
	   			writer.write("72");
	   			break;
	   		case seventytwo:
	   			writer.write("73");
	   			break;
	   		case seventythree:
	   			writer.write("74");
	   			break;
	   		case seventyfour:
	   			writer.write("75");
	   			break;
	   		case seventyfive:
	   			writer.write("76");
	   			break;
	   		case seventysix:
	   			writer.write("77");
	   			break;
	   		case seventyseven:
	   			writer.write("78");
	   			break;
	   		case seventyeight:
	   			writer.write("79");
	   			break;
	   		case seventynine:
	   			writer.write("80");
	   			break;
	   		case eighty:
	   			writer.write("81");
	   			break;
	   		case eightyone:
	   			writer.write("82");
	   			break;
	   		case eightytwo:
	   			writer.write("83");
	   			break;
	   		case eightythree:
	   			writer.write("84");
	   			break;
	   		case eightyfour:
	   			writer.write("85");
	   			break;
	   		case eightyfive:
	   			writer.write("86");
	   			break;
	   		case eightysix:
	   			writer.write("87");
	   			break;
	   		case eightyseven:
	   			writer.write("88");
	   			break;
	   		case eightyeight:
	   			writer.write("89");
	   			break;
	   		case eightynine:
	   			writer.write("90");
	   			break;
	   		case ninety:
	   			writer.write("91");
	   			break;
	   		case ninetyone:
	   			writer.write("92");
	   			break;
	   		case ninetytwo:
	   			writer.write("93");
	   			break;
	   		case ninetythree:
	   			writer.write("94");
	   			break;
	   		case ninetyfour:
	   			writer.write("95");
	   			break;
	   		case ninetyfive:
	   			writer.write("96");
	   			break;
	   		case ninetysix:
	   			writer.write("97");
	   			break;
	   		case ninetyseven:
	   			writer.write("98");
	   			break;
	   		case ninetyeight:
	   			writer.write("99");
	   			break;
	   		case ninetynine:
	   			writer.write("0");
	   			break;
	   		}
	   		writer.close();
	   	}catch (IOException e){
	   		e.printStackTrace();
	   	}

	        return lastLine;
	    }catch(IOException e ) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (fileHandler != null )
	            try {
	                fileHandler.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	}
	}
