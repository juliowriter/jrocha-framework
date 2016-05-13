package site.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class Security {

	private static Random r = new Random();

	public String getToken() {
		String letters = "ABCDEFGHJKLMNPQRTUVWXYZ";
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("SSwwssWWHHDDmmdd");
		letters += dateformated.format(now);

		//Construct token with 20 chars
		String token = "";
		for(int xx=0;xx < 20;xx++)
		{
			int rnd = r.nextInt(39);
			token += letters.substring(rnd, rnd + 1);
		}
		return token;
	}

	public String getToken(int size) {
		String letters = "ABCDEFGHJKLMNPQRTUVWXYZ";
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("SSwwssWWHHDDmmdd");
		letters += dateformated.format(now);

		//Construct token with N chars
		String token = "";
		for(int xx=0;xx < size;xx++)
		{
			int rnd = r.nextInt(39);
			token += letters.substring(rnd, rnd + 1);
		}
		return token.toLowerCase();
	}

	public String getLoginToken(String login) {
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMdd");
		String valid = dateformated.format(now);
		String token = new Encription().encode(valid + login);
		return token;
	}
}
