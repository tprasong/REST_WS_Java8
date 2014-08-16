package au.com.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rest.model.Customer;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		Customer customer = getCustomer(1);
		model.addAttribute("serverTime", formattedDate );
		if(customer != null)
		{
			model.addAttribute("customer", customer);
		}
		return "home";
	}
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("home"); 
		Customer customer1 = getCustomer(1);
		Customer customer2 = getCustomer(2);
        mav.addObject(customer1);
        mav.addObject(customer2);
        mav.addObject("customer1", customer1);
        mav.addObject("customer2", customer2);
        

        return mav;
	}
	public Customer getCustomer(int id)
	{
		String uri =
			    "http://localhost:8080/rest/customers/jaxb/1";
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(connection!=null)
				{
					connection.disconnect();
				}
			}
			return customer;
			

			
	}
}
