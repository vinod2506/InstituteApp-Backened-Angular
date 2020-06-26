package in.nit.service;

import in.nit.model.Employee;
import in.nit.model.Post;


public interface IEmployeeService {

	Integer saveEmployee(Employee emp);
	Employee login(String email,String pass);
	
}
