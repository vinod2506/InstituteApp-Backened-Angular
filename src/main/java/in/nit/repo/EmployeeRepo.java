package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

  Employee	findByEmailAndPassword(String mail,String password);
}
