package apim.github.tutorial;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCode {

	public static void main(String args[]) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		CustomerDAO dao = (CustomerDAO) ctx.getBean("customerDAO");
		dao.addCustomer(new Customer(10, "Noah", 10000));
		dao.addCustomer(new Customer(20, "Alley", 8500));
		List<Customer> list = dao.findCustomerByBalance(9000);
		for (Customer c : list) {
			System.out.println(c.getId() + ": " + c.getName());
		}
		ctx.close();
	}

}