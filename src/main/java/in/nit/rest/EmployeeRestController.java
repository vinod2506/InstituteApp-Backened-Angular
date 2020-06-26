package in.nit.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import in.nit.model.Employee;
import in.nit.model.EmployeeLogin;
import in.nit.model.Image;
import in.nit.model.Post;
import in.nit.repo.PostRepo;
import in.nit.service.IEmployeeService;
import in.nit.service.IPostService;
import in.nit.util.CompressedFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

	@Autowired
	private PostRepo repo;
	@Autowired
	private IEmployeeService service;
	@Autowired
	private IPostService serv;
	
	@PostMapping("/save/user")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee emp){
		Integer id = service.saveEmployee(emp);
		return ResponseEntity.ok("User saved with "+id);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody EmployeeLogin login){
		Employee employee = service.login(login.getUsername(),login.getPassword());
		  if(employee!=null) {
			if(employee.getEmail().equals(login.getUsername())) {
				return ResponseEntity.ok(employee);
			}
		  }
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Wrong");
		}
		return null;
	}
	
	@PostMapping(value="/save/post") 
	public ResponseEntity<?> createPost(@ModelAttribute Post post,@RequestPart(value = "images") MultipartFile file) {
		int id=0;
		try {
			String randomAlphabetic = RandomStringUtils.randomAlphabetic(12);
			Image img=new Image(randomAlphabetic+file.getOriginalFilename(),file.getContentType(),CompressedFile.compress(file.getBytes()));
		    post.setImage(img);
		    post.setIsActive("1");
		     id = serv.savePost(post);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        return ResponseEntity.ok("Post Saved"+id);
	
	}
	
	@PutMapping(value="/update/post/{id}") 
	public ResponseEntity<?> updatepost(@ModelAttribute Post post,@PathVariable int id,@RequestPart(value = "images",required = false) MultipartFile file) {
		System.out.println("in update method");
		int ids=0;
		try {
			if(file==null) {
				Optional<Post> pid = repo.findById(id);
				  if(pid.isPresent()) {
					  Post post2 = pid.get();
					    Image image = post2.getImage();
					  post.setImage(image);
					  System.out.println("Image id "+post2.getImage().getId());
				  }
			
		}
			Image img=null;
		//	post.getImage().setId(post.getImage().getId());
			post.setId(id);
			String randomAlphabetic = RandomStringUtils.randomAlphabetic(12);
			if(file!=null) {
			 img=new Image(randomAlphabetic+file.getOriginalFilename(),file.getContentType(),CompressedFile.compress(file.getBytes()));
		    post.setImage(img);
			}
		    post.setIsActive("1");
		     id = serv.savePost(post);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        return ResponseEntity.ok("Post Updated"+ids);
	
	}
	@GetMapping("/allImage")
	public ResponseEntity<List<String>> getImages(){
		System.out.println("all image");
		List<Post> allPost = serv.getAllPost();
		List<Image> listImg=new ArrayList<>();
		
		
		    for(Post post:allPost) { 
		    	Image img=new Image(post.getImage().getName(),post.getImage().getImgType(),CompressedFile.deCompress(post.getImage().getPic ())); 
		    	listImg.add(img);
		 
		 }
		  List<String> files = listImg.stream().map(filename->MvcUriComponentsBuilder.fromMethodName(EmployeeRestController.class, "getFile", filename.getName())
				  
			 .build().toString()).collect(Collectors.toList());
		  System.out.println(files);
		    return ResponseEntity.ok(files);
	
	}
	 @GetMapping("/fileall/{filename}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		//  System.out.println("getFile method   ---"+filename);
	    Image file = serv.findByImageName(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
	        .body(new ByteArrayResource(CompressedFile.deCompress(file.getPic())));
	 }
	 
	@GetMapping("/course/post")
	public ResponseEntity<?> getAllPost(){
		List<Post> allPost = serv.getAllPost();
	
	     if(!allPost.isEmpty() && allPost!=null)
	     	return ResponseEntity.ok(allPost);
	
	     return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable int id) {
           Integer pid = serv.deletePost(id);
           return ResponseEntity.ok("Post deleted"+pid);
	}
	@GetMapping("/one/{id}")
	public  ResponseEntity<Post> getOnePost(@PathVariable int id) {
		System.out.println("inside getOnePost");
		  Optional<Post> post = serv.getOnePost(id);
            if(post.isPresent())
            return	ResponseEntity.ok(post.get());
          return null;

	}
	
	
}
