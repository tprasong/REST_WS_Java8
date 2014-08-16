package au.com.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Customer;

@RestController
@RequestMapping(value="/restTest/{id}")
public class RestTestController {
	
	public Customer getCustomer()
	{
		return getCustomer(1);
	}
	@RequestMapping
	public Customer getCustomer(@PathVariable int id)
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
