package site.braintree;

import site.account.Account;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Subscription;
import com.braintreegateway.SubscriptionRequest;

public class Braintree {

	private static String merchantID = "";
	private static String publicKey = "";
	private static String privateKey = "";
	private static String environment = "SANDBOX";
	
	private static BraintreeGateway gateway = new BraintreeGateway(
	        (environment.equals("PRODUCTION")?Environment.PRODUCTION:Environment.SANDBOX),
	        merchantID,
	        publicKey,
	        privateKey
	    );
	
	public static boolean createSubscription(String cliID, String planID) 
	{
		boolean ret = false;
		Customer customer = gateway.customer().find(cliID);
		SubscriptionRequest request = new SubscriptionRequest()
	    	.paymentMethodToken(customer.getDefaultPaymentMethod().getToken())
	    	.planId(planID);
		Result<Subscription> result = gateway.subscription().create(request);
		if(result.isSuccess())
			ret = true;
		else
			System.out.println("Braintree CreateSubscription Error: " + result.getMessage() + " - cliID: " + cliID + " - PlanID: " + planID);
		return ret;
	}

	public static String getToken(String bID) {
		  String token = null;
		  try {
			  ClientTokenRequest clientTokenRequest = new ClientTokenRequest().customerId(bID);
			  token = gateway.clientToken().generate(clientTokenRequest);
		  } catch (com.braintreegateway.exceptions.NotFoundException e) {
			  e.printStackTrace();
		  } catch (com.braintreegateway.exceptions.AuthorizationException e) {
			  e.printStackTrace();
		  } catch (com.braintreegateway.exceptions.ServerException e) {
			  e.printStackTrace();
		  }
		return token;	  
    }
	
	public static String createCustomer(Account acc)
	{
		String ret = null;
		CustomerRequest request = new CustomerRequest()
		    .firstName(acc.getPrf_firstname())
		    .lastName(acc.getPrf_lastname())
		    .company(acc.getPrf_company())
		    .email(acc.getPrf_email())
		    .phone(acc.getPrf_phone());
		Result<Customer> result = gateway.customer().create(request);
	
		if(result.isSuccess())
			ret = result.getTarget().getId();
		else
			System.out.println("Braintree CreateCustomer Error: " + result.getMessage() + " - TPF Account: " + acc.getPrf_id() + " - Name: " + acc.getPrf_firstname());
		
		return ret;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		Braintree.merchantID = merchantID;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		Braintree.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		Braintree.privateKey = privateKey;
	}
	
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		Braintree.environment = environment;
	}
}
