package au.com.pf.ctrl;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import au.com.pf.models.Customer;


/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Customer[] get(Locale locale, Model model) {
		logger.info(String.format("Welcome home! The client locale is %1$s, %2$s.", new Object[]{locale, "abc"}));
		List<Customer> customers = new ArrayList<Customer>();
		for(int idx = 1; idx<= 10; idx++)
		{
			try{
				customers.add(getCustomer(idx));
			}catch(IOException | JAXBException e)
			{
				break;
			}
		}
		
		return customers.toArray(new Customer[0]);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public List<Customer> post(@RequestBody List<Customer> customers) {
		logger.info(String.format("Welcome home! The number of customers is %1$s.", customers.size()));
		
		customers.forEach(c->{System.out.println(c.getFirstName());});
		
		return customers;
	}
	public Customer getCustomer(int id) throws IOException, JAXBException
	{
		String uri =
			    String.format("http://localhost:8080/rest/customers/jaxb/%1$s", id);
			URL url;
			HttpURLConnection connection = null;
			Customer customer = null;
			try {
				url = new URL(uri);
				connection =
					    (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");

					JAXBContext jc = JAXBContext.newInstance(Customer.class);
					InputStream xml = connection.getInputStream();
					customer =
					    (Customer) jc.createUnmarshaller().unmarshal(xml);
					
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}finally{
				if(connection!=null)
				{
					connection.disconnect();
				}
			}
			return customer;
			

			
	}
}
