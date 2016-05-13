package site.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

	public String format(float num)
	{
		String ret = String.valueOf(num);
		int pos = ret.lastIndexOf(".");
		int len = ret.length();
		if(pos > 0)
		{
			if(len > (pos + 2)){ ret = ret.substring(0, pos + 3); }
			else if(len == (pos + 2)){ ret = ret + "0"; }
		} else if(len == 0) {
			ret = "0.00";
		} else if(pos == 0) {
			ret = "0" + ret;
		} else {
			ret = ret + ".00";
		}
		return ret;
	}

	public String removeSigns(String txt)
	{
		String[] signs = {"\"","'","%","^","\\","<",">","~","`"};
		for(int xx=0; xx < signs.length; xx++)
		{
			txt = txt.replace(signs[xx], "");
		}
		return txt;
	}

	public String removeSpecials(String txt)
	{
		String lettersNumbersOnly = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
		String ret = txt;
		for(int xx=0; xx < txt.length(); xx++)
		{
			if(lettersNumbersOnly.indexOf(txt.substring(xx,xx+1)) < 0)
			{	
				ret = ret.replace(txt.substring(xx,xx+1),"-");
			}
		}
		return ret;
	}

	public String formatMoney(int num)
	{
		String ret = String.valueOf(num);
		if(ret.length() == 4)
			ret = ret.substring(0, 1) + "," + ret.substring(1);
		else if(ret.length() == 5)
			ret = ret.substring(0, 2) + "," + ret.substring(2);
		else if(ret.length() == 6)
			ret = ret.substring(0, 3) + "," + ret.substring(3);
		else if(ret.length() == 7)
			ret = ret.substring(0, 1) + "," + ret.substring(1, 4) + "," + ret.substring(4);
		else if(ret.length() == 8)
			ret = ret.substring(0, 2) + "," + ret.substring(2, 5) + "," + ret.substring(5);
		else if(ret.length() == 9)
			ret = ret.substring(0, 3) + "," + ret.substring(3, 6) + "," + ret.substring(6);
		return ret;
	}

	public String formatPrice(int num)
	{
		String ret = String.valueOf(num);
		ret = ret.substring(0, ret.length()-2) + "." + ret.substring(ret.length()-2);
		return ret;
	}

	public String formatNumber(int num)
	{
		String ret = String.valueOf(num);
		if(ret.length() == 4)
			ret = ret.substring(0, 1) + "," + ret.substring(1);
		else if(ret.length() == 5)
			ret = ret.substring(0, 2) + "," + ret.substring(2);
		else if(ret.length() == 6)
			ret = ret.substring(0, 3) + "," + ret.substring(3);
		else if(ret.length() == 7)
			ret = ret.substring(0, 1) + "," + ret.substring(1, 3) + "," + ret.substring(3);
		else if(ret.length() == 8)
			ret = ret.substring(0, 2) + "," + ret.substring(2, 4) + "," + ret.substring(4);
		else if(ret.length() == 9)
			ret = ret.substring(0, 3) + "," + ret.substring(3, 5) + "," + ret.substring(5);
		return ret;
	}

	public String formatPercent(double num)
	{
		String ret = String.valueOf(num) + "00";
		int pos = ret.indexOf(".");
		if(pos > 0)
			ret = ret.substring(0, pos + 3);
		return ret;
	}

	public String formatZero(long num, int pad)
	{
		String ret = String.valueOf(num);
		int len = ret.length();
		String zeros = "";
		if(len < pad)
		{
			for(int xx = 0;xx < pad-len;xx++)
			{
				zeros += "0";
			}
		}
		ret = (zeros + ret);
		return ret;
	}

	public String formatZero(int num, int pad)
	{
		String ret = String.valueOf(num);
		int len = ret.length();
		String zeros = "";
		if(len < pad)
		{
			for(int xx = 0;xx < pad-len;xx++)
			{
				zeros += "0";
			}
		}
		ret = (zeros + ret);
		return ret;
	}

	public String formatZipCode(long zipcode)
	{
		String ret = formatZero(zipcode, 8);
		return ret.substring(0,5) + "-" + ret.substring(5,8);
	}

	public String formatZipCode(String zipcode)
	{
		return formatZipCode(Long.parseLong(zipcode));
	}

	public String arrayToString(String[] array)
	{
		String ret = "";
		for(int xx=0;xx<array.length;xx++)
		{
			ret += array[xx] + ",";
		}
		if(ret.length()>1){ ret = ret.substring(0,ret.length() -1); }
		return ret;
	}

	public String arrayToString(String[] array, String concat)
	{
		String ret = "";
		concat = (concat==null?"":concat);
		for(int xx=0;xx<array.length;xx++)
		{
			ret += array[xx] + concat;
		}
		if(ret.length()>concat.length()){ ret = ret.substring(0,ret.length() -concat.length()); }
		return ret;
	}

	public boolean validSqlDate(String data)
	{
		  boolean ret = false;
		  String[] parts = data.split("-");
		  if(parts[0].length() == 4 && parts[1].length() == 2 && parts[2].length() == 2)
		  {
			  try{
				  int ano = Integer.parseInt(parts[0]);
				  int mes = Integer.parseInt(parts[1]);
				  int dia = Integer.parseInt(parts[2]);
				  if(ano >= 1890 && ano <= 2100 && mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31)
					  ret = true;
			  } catch(Exception e) { ret = false; }
		  }
		  return ret;
	}

	public String sysDateSQL()
	  {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyy-MM-dd");
		return dateformated.format(now);
	  }

	  public long sysDateTimeMinuteLong()
	  {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMddHHmm");
		return Long.parseLong(dateformated.format(now));
	  }

	  public long sqlToLongMinute(String dta)
	  {
        Calendar sdta = Calendar.getInstance();
	    sdta.set(Integer.parseInt(dta.substring(0,4)), Integer.parseInt(dta.substring(5,7)), Integer.parseInt(dta.substring(8, 10)),Integer.parseInt(dta.substring(11, 13)),Integer.parseInt(dta.substring(14, 16)));		
	    SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMddHHmm");
		return Long.parseLong(dateformated.format(sdta));
	  }

	  public String sysDateTimeSQL()
	  {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformated.format(now);
	  }

	  public String sysDateTime()
	  {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateformated.format(now);
	  }

	  public long sysDateTimeLong()
	  {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMdd");
		return Long.parseLong(dateformated.format(now));
	  }

	  public long sysDateTimeLongMinus(String fromwhen)
	  {
			long ret = 0;
			Calendar dataInicio = Calendar.getInstance();
			if(fromwhen == "yesterday")
				dataInicio.add(Calendar.DAY_OF_YEAR, -1);
		    else if(fromwhen == "week")
				dataInicio.add(Calendar.DAY_OF_YEAR, -7);
		    else if(fromwhen == "month")
				dataInicio.add(Calendar.DAY_OF_YEAR, -30);
		    else if(fromwhen == "year")
				dataInicio.add(Calendar.DAY_OF_YEAR, -365);
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd").format(dataInicio.getTime());
			ret = Long.parseLong(timeStamp);
	        return ret;
	  }

	  public String sqlToString(Date arg0)
	  {
		SimpleDateFormat dateformated = new SimpleDateFormat("dd/MM/yyyy");
		return dateformated.format(arg0);
	  }

	  public String stringToSql(String arg0)
	  {
		return arg0.substring(6,10) + "-" + arg0.substring(3,5) + "-" + arg0.substring(0,2);
	  }

	  public String stringToSqlAll(String arg0, String language)
	  {
		String ret = null;
		if(arg0!=null)
		{
			ret = arg0.substring(6,10) + "-" + arg0.substring(3,5) + "-" + arg0.substring(0,2) + arg0.substring(10, 19);
			if(language.equals("en"))
				ret = arg0.substring(6,10) + "-" + arg0.substring(0,2) + "-" + arg0.substring(3,5) + arg0.substring(10, 19);
		}
		return ret;
	  }

	  public String stringToSqlInicio(String arg0)
	  {
		  String ret = "";
		  if(arg0.length() >= 10)
			  ret = arg0.substring(6,10) + "-" + arg0.substring(3,5) + "-" + arg0.substring(0,2) + " 00:00:00";
		  else
			  ret = "1900-12-31 00:00:00";
		  return ret;
	  }

	  public String stringToSqlFim(String arg0)
	  {
		  String ret = "";
		  if(arg0.length() >= 10)
			  return arg0.substring(6,10) + "-" + arg0.substring(3,5) + "-" + arg0.substring(0,2) + " 23:59:59";
		  else
			  ret = "2100-12-31 23:59:59";
		  return ret;
	  }

	  public String stringToHTML(String arg0)
	  {
		return arg0.substring(0,2) + arg0.substring(3,5) + arg0.substring(6,10);
	  }

	  public String sqlToString(String arg0)
	  {
		return arg0.substring(8,10) + "/" + arg0.substring(5,7) + "/" + arg0.substring(0,4);
	  }

	  public String sqlToString(String arg0, String language)
	  {
		String ret = arg0.substring(8,10) + "/" + arg0.substring(5,7) + "/" + arg0.substring(0,4);
		if(language.equalsIgnoreCase("EN"))
			ret = arg0.substring(5,7) + "/" + arg0.substring(8,10) + "/" + arg0.substring(0,4);
		return ret;
	  }

	  public String sqlToStringHour(String arg0)
	  {
		  return arg0.substring(8,10) + "/" + arg0.substring(5,7) + "/" + arg0.substring(0,4) + " " + arg0.substring(11,16);
	  }

	  public String sqlToFlat(String arg0)
	  {
		return arg0.substring(0,4) + arg0.substring(5,7) + arg0.substring(8,10);
	  }

	  public String sqlToDay(String arg0)
	  {
		return arg0.substring(8,10);
	  }

	  public String sqlToMonth(String arg0)
	  {
		return arg0.substring(5,7);
	  }

	  public String sqlToYear(String arg0)
	  {
		return arg0.substring(0,4);
	  }

	  public String sqlToStringComplete(String arg0)
	  {
		  arg0 = (arg0==null?"01/01/2000 00:00:00":arg0);
		  return arg0.substring(8,10) + "/" + arg0.substring(5,7) + "/" + arg0.substring(0,4) + arg0.substring(10,16);
	  }

	  public String sqlToStringAll(String arg0, String language)
	  {
		  String ret = null;
		  if(arg0!=null)
		  {
			  ret = arg0.substring(8,10) + "/" + arg0.substring(5,7) + "/" + arg0.substring(0,4) + arg0.substring(10, 19);
			if(language==null || language.equals("en"))
				ret =  arg0.substring(5,7) + "/" + arg0.substring(8,10) + "/" + arg0.substring(0,4) + arg0.substring(10, 19);
		  }
		return ret;
	  }

	  public String sqlToStringTime(String arg0)
	  {
		return arg0.substring(10,16);
	  }

	  public String sqlToHour(String arg0)
	  {
		return arg0.substring(10,12);
	  }

	  public String sqlToMinute(String arg0)
	  {
		return arg0.substring(14,16);
	  }

	  public String diaMes(String dia, String mes)
	  {
		  if(dia.length() == 1) dia = "0" + dia;
		  if(mes.length() == 1) mes = "0" + mes;
		  return dia + mes;
	  }

	  public String graficoBarra(String titulo, long valor, long total)
	  {
			String tabela = titulo + "</td><td align='center' style='width:50px;'>";
			tabela += valor + "</td><td valign='middle'>";
			tabela += "<table border=0 cellpadding=0 cellspacing=0><tr>";
			tabela += "<td border='0' style='width:" + (calculaPercent(total, valor)>0?(calculaPercent(total, valor)*3):1);
			tabela += "px; height:15px; background-color: " + (calculaPercent(total, valor)>0?"#FF2211":"#FFFFFF");
			tabela += ";'></td></tr></table>" + calculaPercent(total, valor) + "%";
			return tabela;
	  }

	  private long calculaPercent(long total, long parcial)
	  {
			return (total>0?(parcial * 100)/total:0);
	  }

	public StringBuffer transformToStringBuffer(InputStream in, String charSet) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(in, charSet));
		StringBuffer newData = new StringBuffer();
		String s = "";
		while (null != ((s = br.readLine()))) {
			newData.append(s + "\n");
		}
		br.close();
		return newData;
	}

	public long dateDiff(String data1, String data2)
	{
		long ret = 0;
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Integer.parseInt(data1.substring(0,4)), Integer.parseInt(data1.substring(5,7)), Integer.parseInt(data1.substring(8, 10)));
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.set(Integer.parseInt(data2.substring(0,4)), Integer.parseInt(data2.substring(5,7)), Integer.parseInt(data2.substring(8, 10)));
        long diferenca = dataFinal.getTimeInMillis() -
                         dataInicio.getTimeInMillis();
        int tempoDia = 1000 * 60 * 60 * 24;
        ret = diferenca / tempoDia;
        return ret;
	}

	public int dateComp(String data1, String data2)
	{
		int ret = 0;
        Calendar dataInicio = Calendar.getInstance();
        dataInicio.set(Integer.parseInt(data1.substring(0,4)), Integer.parseInt(data1.substring(5,7)), Integer.parseInt(data1.substring(8, 10)));
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.set(Integer.parseInt(data2.substring(0,4)), Integer.parseInt(data2.substring(5,7)), Integer.parseInt(data2.substring(8, 10)));
        long dif = dataFinal.getTimeInMillis() - dataInicio.getTimeInMillis();
        ret = (dif < 0?-1:(dif > 0?1:0));
        return ret;
	}

/*
	public boolean dateOk(String data, int days)
	{
		boolean ret = true;
        Calendar data1 = Calendar.getInstance();
        data1.set(Integer.parseInt(data.substring(0,4)), Integer.parseInt(data.substring(5,7)), Integer.parseInt(data.substring(8, 10)));
        data1.add(Calendar.DAY_OF_MONTH, days);

        return ret;
	}
*/

	public String getWeekDay(String date)
	{
		String[] weekDays = "Domingo,Segunda-feira,Terça-feira,Quarta-feira,Quinta-feira,Sexta-feira,Sábado".split(",");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(date.substring(6, 10)),Integer.parseInt(date.substring(3, 5))-1,Integer.parseInt(date.substring(0, 2)));
		return weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}

	public String toUTF8(String txt)
	{
	    Charset charsetUtf8 = Charset.forName("ISO-8859-1");
	    Charset charsetIso88591 = Charset.forName("UTF-8");
	    CharsetEncoder encoder = charsetIso88591.newEncoder();
	    CharsetDecoder decoder = charsetUtf8.newDecoder();
	    String s = "";
	    try {
	       ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(txt));
	       CharBuffer cbuf = decoder.decode(bbuf);
	       s = cbuf.toString();
	    } catch (CharacterCodingException e) {
	        e.printStackTrace();
	    }
	    return s;
	}

	public String toISO88591(String txt)
	{
	    Charset charsetUtf8 = Charset.forName("ISO-8859-1");
	    Charset charsetIso88591 = Charset.forName("UTF-8");
	    CharsetEncoder encoder = charsetUtf8.newEncoder();
	    CharsetDecoder decoder = charsetIso88591.newDecoder();
	    String s = "";
	    System.out.println(txt);
	    try {
	       ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(txt));
	       CharBuffer cbuf = decoder.decode(bbuf);
	       s = cbuf.toString();
	    } catch (CharacterCodingException e) {
	        e.printStackTrace();
	    }
	    return s;
	}

	public String[][] splitParameters(String pars)
	{
		pars = pars.replace("amp;", "");
		String[] ret = pars.split("&");
		String[][] farr = new String[ret.length][2];
		for(int xx = 0; xx < ret.length; xx++)
		{
			String[] par = ret[xx].split("=");
			farr[xx][0] = par[0];
			farr[xx][1] = par[1];
		}
		return farr;
	}

	public String getValueByKey(String[][] pars, String key)
	{
		String ret = "";
		for(int xx = 0; xx < pars.length; xx++)
		{
			if(pars[xx][0].equals(key))
			{
				ret = pars[xx][1];
				break;
			}
		}
		return ret;
	}

    public String replaceSpecial(String text)
    {
    	  return text.replace("Á","&Aacute;")
    	  .replace("á","&aacute;")
    	  .replace("Â","&Acirc;")
    	  .replace("â","&acirc;")
    	  .replace("À","&Agrave;")
    	  .replace("à","&agrave;")
    	  .replace("Ã","&Atilde;")
    	  .replace("ã","&atilde;")
    	  .replace("Ä","&Auml;")
    	  .replace("ä","&auml;")
    	  .replace("ï","&iuml;")
    	  .replace("É","&Eacute;")
    	  .replace("é","&eacute;")
    	  .replace("Ê","&Ecirc;")
    	  .replace("ê","&ecirc;")
    	  .replace("È","&Egrave;")
    	  .replace("è","&egrave;")
    	  .replace("Ë","&Euml;")
    	  .replace("ë","&euml;")
    	  .replace("ñ","&ntilde;")
    	  .replace("Ñ","&Ntilde;")
    	  .replace("Õ","&Otilde;")
    	  .replace("õ","&otilde;")
    	  .replace("ô","&ocirc;")
    	  .replace("Ò","&Ograve;")
    	  .replace("ò","&ograve;")
    	  .replace("Ö","&Ouml;")
    	  .replace("ö","&ouml;")
    	  .replace("Ó","&Oacute;")
    	  .replace("ó","&oacute;")
    	  .replace("Ô","&Ocirc;")
    	  .replace("Í","&Iacute;")
    	  .replace("í","&iacute;")
    	  .replace("Î","&Icirc;")
    	  .replace("î","&icirc;")
    	  .replace("Ì","&Igrave;")
    	  .replace("ì","&igrave;")
    	  .replace("Ï","&Iuml;")
    	  .replace("Ú","&Uacute;")
    	  .replace("ú","&uacute;")
    	  .replace("Û","&Ucirc;")
    	  .replace("û","&ucirc;")
    	  .replace("Ù","&Ugrave;")
    	  .replace("ù","&ugrave;")
    	  .replace("Ü","&Uuml;")
    	  .replace("ü","&uuml;")
    	  .replace("Ç","&Ccedil;")
    	  .replace("ç","&ccedil;");
  	}

}
