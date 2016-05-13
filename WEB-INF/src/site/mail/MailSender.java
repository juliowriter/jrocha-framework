package site.mail;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;



import site.core.Config;
import site.core.ConnectionPool;
import site.core.Util;
import site.mail.dao.MailtogoDAO;

public class MailSender extends ConnectionPool {

	//private static Session mySession = null;

	public static void start() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dtnow = new Date();
			//Verify email queue
			Iterator<Mailtogo> it = new MailtogoDAO().getQueue().iterator();
			while(it.hasNext())
			{
				Mailtogo mtg = (Mailtogo)it.next();
				mtg.setMtg_status("P");
				mtg.save();
				dtnow = new Date();
				System.out.println(format.format(dtnow) + " -> Sending message " + mtg.getMtg_id());
				send(mtg);
			}
		} catch (Exception e)	{
			e.printStackTrace();
		}
	}

	private static boolean send(Mailtogo envelope)
	{
		boolean ret = false;
		String finalContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";
		finalContent += "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n";
		finalContent += "<head>\n";
		finalContent += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n";
		finalContent += "<meta name=\"viewport\" content=\"initial-scale=1.0\">\n";
		finalContent += "<title>" + envelope.getMtg_subject() + "</title>\n";
		finalContent += "</head>\n";
		finalContent += "<body leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight=\"0\" offset=\"0\" style=\"margin: 0;padding: 0;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%;background-color: #ffffff; font-family: Arial, Verdana, Geneva, sans-serif;\">\n";
		finalContent += new Util().replaceSpecial(envelope.getMtg_text());
    	try {
			if(envelope.getMtg_address()!=null)
			{

				finalContent += "</body>\n</html>\n";
				Content contentBody = new Content(finalContent);
	    		contentBody.setCharset("UTF-8");
	    		Body body = new Body();
	    		body.setHtml(contentBody);				
	    		
	    		Message message = new Message(new Content(envelope.getMtg_subject()), body);

	    		Destination destination = new Destination();

	    		destination.withToAddresses(envelope.getMtg_address());
	    		SendEmailRequest request = new SendEmailRequest(envelope.getMtg_from(), destination, message);
	    		request.setReplyToAddresses(Collections.singleton( envelope.getMtg_replyto() ));
	    		final AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient( new BasicAWSCredentials(
	    				new Config().getConfig("SES_ACCESS_KEY").getCfg_value(), 
	    				new Config().getConfig("SES_SECRET_KEY").getCfg_value()));
	    		client.setRegion(Region.getRegion(Regions.US_EAST_1));
	            client.sendEmail(request);    		
			}
			ret = true;
			envelope.setMtg_status("S");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		envelope.save();
		return ret;
	}
}
