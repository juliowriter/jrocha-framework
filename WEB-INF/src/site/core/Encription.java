package site.core;

public class Encription {

	public String encode(String string){
		String mychar;
		int xx;
		char key = getKey(string);
		String ret = String.valueOf(key);
		for(xx = 1; xx <= string.length(); xx++){
			mychar = string.substring(xx-1, xx);
			ret += translate(mychar, key, "E");
		}
		return ret;
	}

	public String decode(String string){
		String mychar;
		int xx;
		char key = string.charAt(0);
		String ret = "";
		for(xx = 2; xx <= string.length(); xx++){
			mychar = string.substring(xx-1, xx);
			ret += translate(mychar, key, "D");
		}
		return ret;
	}

	public String encodeLetters(String string){
		String mychar;
		int xx;
		char key = getKeyLetter(string);
		String ret = String.valueOf(key);
		for(xx = 1; xx <= string.length(); xx++){
			mychar = string.substring(xx-1, xx);
			ret += translateLetter(mychar, key, "E");
		}
		return ret;
	}

	public String decodeLetters(String string){
		String mychar;
		int xx;
		char key = string.charAt(0);
		String ret = "";
		for(xx = 2; xx <= string.length(); xx++){
			mychar = string.substring(xx-1, xx);
			ret += translateLetter(mychar, key, "D");
		}
		return ret;
	}

	private String translate(String stringChar, char key, String mode){
		String stringMatch;
		int xx;
		String ret = "";

		String stringDefault = "1234567890-=qwertyuiop[]asdfghjkl;zxcvbnm,./!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?";

		switch (key) {
			case 'K' :
				stringMatch = "56789-=qtyuiop[]a01234sdfghjkl;zxcvbnm,./!@#wer$%^&*()_+QWE}ASDFGHJKL:ZXCVBNM<>?RTYUIOP{";
				break;
			case 'B' :
			    stringMatch = "ertyuiop1!@#$%^&*()_+QWERTYUIOP{23l;zxcvbnm,./dfghjk}ASZXCVBNM<>?DFGHJKL:4567890-=qw[]as";
				break;
			case 'X' :
			    stringMatch = "sdfghjkl;zx&*()_+QWERTYUIO12cvbnm,./!@#$%^3DFGHJ?KL:ZXCVyuiop[]BNM<4567890-=qwertaP{}AS>";
				break;
			case 'O' :
				stringMatch = "90-=qwerCVBWE%^&NM<>?tyu456iop[]asdfghjkl;zxcv3bn78m,./!@#$*()_+QRT12YUIOP{}ASDFGHJKL:ZX";
				break;
			case 'J' :
			    stringMatch = "vbnm15670-=qwe89rty234uiop[]asdfghjzxc,./!@#kl;$%FGHJKL^&*()_UIO+QWERTYP{}ASDVBNM<>?:ZXC";
				break;
			case 'I' :
			    stringMatch = "2^&*(35q67er89-=wtyuiop[]aMsdfl;zxcvN<>?bnm,./!@#Q$%)_+WE0jRTYUIO1P{}ASDFghkGH4JKL:ZXCVB";
				break;
			case '%' :
			    stringMatch = "4qertw56789-=yu01iop[]asdfghjkl;zxc23vbnm,./!@#$%^&*()_+QTYUWERIOP{FGSDHJKL}A:ZM<>VBN?XC";
				break;
			case 'P' :
			    stringMatch = "wer490-=qt]asd5678fghjkl;zx&*()yuiop[cvN>?bnm,./!@#123$%^_+QUWERTYIOP{}ASCVBDFGHJKL:ZXM<";
				break;
			case 'E' :
			    stringMatch = "-=qw8er5tyuiop[]asdfghjkl;zxc6vbnm,./!@#$%^&*()_+Q0WER7T1YUIOP{}AS9DFGH2JKL:Z4XCVB3NM<>?";
				break;
			case 'D' :
			    stringMatch = "^&*(14567890-=qwert>yuiop[]asdfghjkl23;zxERTcvbnm,./!@#$%)_+QWYUIOP{}ASDZBNM<FGXC?VHJKL:";
				break;
			case 'Z' :
			    stringMatch = "ASDFGHJKL1234567890-=qwertyuiop[QWERTYUIOPasdfghjkl;zxc]vbnm,./{}:ZXCVBNM<>?!@#$%^&*()_+";
				break;
			case 'V' :
			    stringMatch = "zxcvbnm,./!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?1234567890-=qwertyuiop[]asdfghjkl;";
				break;
			case 'L' :
			    stringMatch = "JKL:ZXCVBNM<>UIOP{}A1234567890-=qwertSDFGH?yuiop[]asdfghjkl;zxcvbnm,./!@#$%^&*()_+QWERTY";
				break;
			case 'H' :
			    stringMatch = "P{}ASDFGHJKL:ZXCVBNM<>?op[]asdfghjkl;zxcvbnm,./!@#$%^&*()_+QW1234567890-=qwertyuiERTYUIO";
				break;
			case 'Q' :
			    stringMatch = "bnGHJKL2345@#$%^&*()_+QWER6789-=qwe01rtyuiop[]asdfghjkl;zxcv:ZXCVBNM<>?m,./!TYUIOP{}ASDF";
				break;
			case 'F' :
			    stringMatch = "1345KL:ZXC2VBNM<>?sdfghjkl;67890-=qwertyuiop[]aP{}ASDFGHJzxcvbnm,./!@#$IO%^&*()_+QWERTYU";
				break;
			case 'W' :
			    stringMatch = "56tyasdfghuibnm,./!@#$%^&*()_+QWE7123{}ASDFGHJKL:ZXCVBN4890-=qop[]jkl;zRTYUIOPM<>?xcvwer";
				break;
			case 'Y' :
			    stringMatch = "KL:ZXC1=qwer!@#$%^&*()_+QWERTYU234567890-IOP{}ASDFGHJVBNM<>?tyuiop[]asdfghjkl;zxcvbnm,./";
				break;
			case 'U' :
			    stringMatch = "12^&*()_34567890-=qwertyp[]asdfgh+QWERTYUIOP{}ASDFGHuioJKL:ZXCVBNM<jkl;zxcvbnm,./!@#$%>?";
				break;
			case '*' :
			    stringMatch = "67jkl;zxcvbnm,./!@890-=qweQWERT12345YUIOP{}ASDFGHJKL:ZXCBNM<>?Vrtyuiop[]asdfgh#$%^&*()_+";
				break;
			case '@' :
			    stringMatch = "KL:Z67jkl;zxcWERT12345YUIOP{}Avbnm,./!@890-rSDFGHJtyuiop[]asdfgh#$%^&*()_+=qweQXCBNM<>?V";
				break;
			case '&' :
			    stringMatch = "wer12^&*()_345typ[]as67890-=qdfgh+QWERT{}ASDFYUIOPGHuioJKL:ZXCVl;zxcvBNM<jkbnm,./!@#$%>?";
				break;
			case '~' :
			    stringMatch = "df56t489-=yasghuibnm,./!@#$QWE70123{}ASDFJK%^&*()_+L:ZXCVBNqop]jkwelGH;zRTYUIOPM<>?xcvr[";
				break;
			case '#' :
			    stringMatch = "WER345KL:ZXC2qVBNM<>?sdfghjkl;67tyu89-=wer01iop[]aP{}ASDUFGHJzxcvbnm,./!@#$IO%^&*()_+QTY";
				break;
			case '^' :
			    stringMatch = "SDFG90-=qwerCE%^&NM<>?tyu456iop[]asdfghjkl;zxcv3bn78m,./!@#$VBW*()_+QRT12YUIOP{}AHJKL:ZX";
				break;
			case '!' :
			    stringMatch = "$%sdfghjkl;zx&*()_+QERTIO12cvbnmYU,./!@#^3DFGHJ?KL:ZXCVyWuiop[]BNM<4567890-S>=qwertaP{}A";
				break;
			default :
			    stringMatch = "1234567890-=qwertyuiop[]asdfghjkl;zxcvbnm,./!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?";
				break;
		}
		if(mode.equals("E")) {
		    for(xx = 1; xx <= 88; xx++)
		    {
				if( stringChar.equals(stringDefault.substring(xx-1, xx))) {
			        stringChar = stringMatch.substring(xx-1, xx);
			        break;
				}
		    }
	   	    ret = stringChar;
		} else
		{
			for( xx = 1; xx <= 88; xx++ ) {
				if( stringChar.equals(stringMatch.substring(xx-1, xx)) )
				{
					stringChar = stringDefault.substring(xx-1, xx);
				    break;
				}
			}
			ret = stringChar;
		}
		return ret;
	}

	private String translateLetter(String stringChar, char key, String mode){
		String stringMatch;
		int xx;
		String ret = "";

		String stringDefault = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM@. ";

		switch (key) {
			case 'K' :
				stringMatch = "56789qtyuiopa01234sdfghjklzxcvbnmwerQWEASDFGHJKLZXCVBNMRTYUIOP_-#";
				break;
			case 'B' :
			    stringMatch = "ertyuiop1QWERTYUIOP23lzxcvbnmdfghjkASZXCVBNMDFGHJKL4567890qwas_-#";
				break;
			case 'X' :
			    stringMatch = "sdfghjklzxQWERTYUIO12cvbnm3DFGHJKLZXCVyuiopBNM4567890qwertaPAS_-#";
				break;
			case 'O' :
				stringMatch = "90qwerCVBWENMtyu456iopasdfghjklzxcv3bn78mQRT12YUIOPASDFGHJKLZX_-#";
				break;
			case 'J' :
			    stringMatch = "vbnm15670qwe89rty234uiopasdfghjzxcklFGHJKLUIOQWERTYPASDVBNMZXC_-#";
				break;
			case 'I' :
			    stringMatch = "235q67er89wtyuiopaMsdflzxcvNbnmQWE0jRTYUIO1PASDFghkGH4JKLZXCVB_-#";
				break;
			case 'A' :
			    stringMatch = "4qertw56789yu01iopasdfghjklzxc23vbnmQTYUWERIOPFGSDHJKLAZMVBNXC_-#";
				break;
			case 'P' :
			    stringMatch = "wer490qtasd5678fghjklzxyuiopcvNbnm123QUWERTYIOPASCVBDFGHJKLZXM_-#";
				break;
			case 'E' :
			    stringMatch = "qw8er5tyuiopasdfghjklzxc6vbnmQ0WER7T1YUIOPAS9DFGH2JKLZ4XCVB3NM_-#";
				break;
			case 'D' :
			    stringMatch = "14567890qwertyuiopasdfghjkl23zxERTcvbnmQWYUIOPASDZBNMFGXCVHJKL_-#";
				break;
			case 'Z' :
			    stringMatch = "ASDFGHJKL1234567890qwertyuiopQWERTYUIOPasdfghjklzxcvbnmZXCVBNM_-#";
				break;
			case 'V' :
			    stringMatch = "zxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890qwertyuiopasdfghjkl_-#";
				break;
			case 'L' :
			    stringMatch = "JKLZXCVBNMUIOPA1234567890qwertSDFGHyuiopasdfghjklzxcvbnmQWERTY_-#";
				break;
			case 'H' :
			    stringMatch = "PASDFGHJKLZXCVBNMopasdfghjklzxcvbnmQW1234567890qwertyuiERTYUIO_-#";
				break;
			case 'Q' :
			    stringMatch = "bnGHJKL2345QWER6789qwe01rtyuiopasdfghjklzxcvZXCVBNMmTYUIOPASDF_-#";
				break;
			case 'F' :
			    stringMatch = "1345KLZXC2VBNMsdfghjkl67890qwertyuiopaPASDFGHJzxcvbnmIOQWERTYU_-#";
				break;
			case 'W' :
			    stringMatch = "56tyasdfghuibnmQWE7123ASDFGHJKLZXCVBN4890qopjklzRTYUIOPMxcvwer_-#";
				break;
			case 'Y' :
			    stringMatch = "KLZXC1qwerQWERTYU234567890IOPASDFGHJVBNMtyuiopasdfghjklzxcvbnm_-#";
				break;
			case 'U' :
			    stringMatch = "1234567890qwertypasdfghQWERTYUIOPASDFGHuioJKLZXCVBNMjklzxcvbnm_-#";
				break;
			case 'C' :
			    stringMatch = "67jklzxcvbnm890qweQWERT12345YUIOPASDFGHJKLZXCBNMVrtyuiopasdfgh_-#";
				break;
			case 'G' :
			    stringMatch = "KLZ67jklzxcWERT12345YUIOPAvbnm890rSDFGHJtyuiopasdfghqweQXCBNMV_-#";
				break;
			case 'S' :
			    stringMatch = "wer12345typas67890qdfghQWERTASDFYUIOPGHuioJKLZXCVlzxcvBNMjkbnm_-#";
				break;
			case 'M' :
			    stringMatch = "df56t489yasghuibnmQWE70123ASDFJKLZXCVBNqopjkwelGHzRTYUIOPMxcvr_-#";
				break;
			case 'N' :
			    stringMatch = "WER345KLZXC2qVBNMsdfghjkl67tyu89wer01iopaPASDUFGHJzxcvbnmIOQTY_-#";
				break;
			case 'R' :
			    stringMatch = "SDFG90qwerCENMtyu456iopasdfghjklzxcv3bn78mVBWQRT12YUIOPAHJKLZX_-#";
				break;
			case 'T' :
			    stringMatch = "sdfghjklzxQERTIO12cvbnmYU3DFGHJKLZXCVyWuiopBNM4567890SqwertaPA_-#";
				break;
			default :
			    stringMatch = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_-#";
				break;
		}
		if(mode.equals("E")) {
		    for(xx = 1; xx <= 65; xx++)
		    {
				if( stringChar.equals(stringDefault.substring(xx-1, xx))) {
			        stringChar = stringMatch.substring(xx-1, xx);
			        break;
				}
		    }
	   	    ret = stringChar;
		} else
		{
			for( xx = 1; xx <= 65; xx++ ) {
				if( stringChar.equals(stringMatch.substring(xx-1, xx)) )
				{
					stringChar = stringDefault.substring(xx-1, xx);
				    break;
				}
			}
			ret = stringChar;
		}
		return ret;
	}

	private char getKey(String string) {
	   int mynumber = 0;
	   int xx;
	   char ret;
	   if(string.length() < 5)
	   {
		  string = string + "xalxa";
		  string = string.substring(0, 5);
	   }
	   for( xx = 1; xx <= 5; xx++ )
	   {
		   mynumber += string.substring(xx-1, xx).hashCode();
	   }
	   mynumber = (mynumber / 5);
	   switch (mynumber) {
		case 97 :
		    ret = 'B';
			break;
		case 98 :
			ret = 'X';
			break;
		case 99 :
			ret = 'O';
			break;
		case 100 :
			ret = 'J';
			break;
		case 101 :
			ret = 'I';
			break;
		case 102 :
			ret = 'P';
			break;
		case 103 :
			ret = 'E';
			break;
		case 104 :
			ret = 'D';
			break;
		case 105 :
			ret = 'Z';
			break;
		case 106 :
			ret = 'V';
			break;
		case 107 :
			ret = 'L';
			break;
		case 108 :
			ret = 'H';
			break;
		case 109 :
			ret = 'Q';
			break;
		case 110 :
			ret = 'F';
			break;
		case 111 :
			ret = 'W';
			break;
		case 112 :
			ret = 'Y';
			break;
		case 113 :
			ret = 'U';
			break;
		case 114 :
			ret = '*';
			break;
		case 115 :
			ret = '@';
			break;
		case 116 :
			ret = '&';
			break;
		case 117 :
			ret = '~';
			break;
		case 118 :
			ret = '#';
			break;
		case 119 :
			ret = '%';
			break;
		case 120 :
			ret = '$';
			break;
		case 121 :
			ret = '^';
			break;
		default :
		    if (mynumber < 96 ){
		    	ret = 'K';
		    } else {
		    	ret = '!';
		    }
			break;
		}
		return ret;
	 }

	private char getKeyLetter(String string) {
		   int mynumber = 0;
		   int xx;
		   char ret;
		   if(string.length() < 5)
		   {
			  string = string + "xalxa";
			  string = string.substring(0, 5);
		   }
		   for( xx = 1; xx <= 5; xx++ )
		   {
			   mynumber += string.substring(xx-1, xx).hashCode();
		   }
		   mynumber = (mynumber / 5);
		   switch (mynumber) {
			case 97 :
			    ret = 'B';
				break;
			case 98 :
				ret = 'X';
				break;
			case 99 :
				ret = 'O';
				break;
			case 100 :
				ret = 'J';
				break;
			case 101 :
				ret = 'I';
				break;
			case 102 :
				ret = 'P';
				break;
			case 103 :
				ret = 'E';
				break;
			case 104 :
				ret = 'D';
				break;
			case 105 :
				ret = 'Z';
				break;
			case 106 :
				ret = 'V';
				break;
			case 107 :
				ret = 'L';
				break;
			case 108 :
				ret = 'H';
				break;
			case 109 :
				ret = 'Q';
				break;
			case 110 :
				ret = 'F';
				break;
			case 111 :
				ret = 'W';
				break;
			case 112 :
				ret = 'Y';
				break;
			case 113 :
				ret = 'U';
				break;
			case 114 :
				ret = 'A';
				break;
			case 115 :
				ret = 'C';
				break;
			case 116 :
				ret = 'G';
				break;
			case 117 :
				ret = 'S';
				break;
			case 118 :
				ret = 'M';
				break;
			case 119 :
				ret = 'N';
				break;
			case 120 :
				ret = 'R';
				break;
			case 121 :
				ret = 'T';
				break;
			default :
			    if (mynumber < 96 ){
			    	ret = 'K';
			    } else {
			    	ret = '1';
			    }
				break;
			}
			return ret;
		 }
}
