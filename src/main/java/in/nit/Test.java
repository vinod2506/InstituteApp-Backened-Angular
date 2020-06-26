package in.nit;

import java.util.Base64;

import org.hibernate.annotations.Check;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nit.model.EmployeeLogin;

public class Test {

	public static void main(String[] args) throws JsonProcessingException {
	//	System.out.println(check());
		System.out.println(json());
	}
	
	private static ResponseEntity<?> json() throws JsonProcessingException {
	     String json = new ObjectMapper().writeValueAsString(new EmployeeLogin("vinod","123"));
	     System.out.println(json);
		return new ResponseEntity(new String("{message"+":"+"User saved with id}"),HttpStatus.ACCEPTED);
	}
	
	private static  String check() {
		 byte[] encode = Base64.getEncoder().encode("vinod".getBytes());
		 String s=null;
		if(s!=null) {
		if(s.equals("vinod")) {
				
				return "if";
			}
		}
		else {
			return "else";
		}
		return "out";

	}
}
