package in.nit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.Employee;
import in.nit.model.Post;
import in.nit.repo.EmployeeRepo;
import in.nit.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepo  repo;

	@Override
	public Integer saveEmployee(Employee emp) {
		
		return repo.save(emp).getEid();
	}

	@Override
	public Employee login(String email,String pass) {
         
		 Employee employee = repo.findByEmailAndPassword(email,pass);
		 if(employee!=null)
			 return employee;
		 else
			 return null;
	}
	
	
	
}
