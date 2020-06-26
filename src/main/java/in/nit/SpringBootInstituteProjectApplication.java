package in.nit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import in.nit.converter.PostToImageConverter;
import in.nit.service.impl.StorageService;

@SpringBootApplication
public class SpringBootInstituteProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootInstituteProjectApplication.class, args);
	}
	// this is local storage servie
	
	@Resource
	  StorageService storageService;
	 
	  
	 
	  @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
	  
	  @PostConstruct
	    void initConverters() {
	     //   log.info("DefaultConversionService add Int2LongConverter")
	        GenericConversionService conversionService = (GenericConversionService) DefaultConversionService.getSharedInstance();
	        conversionService.addConverter(new PostToImageConverter());
	    }

}
