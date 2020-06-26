package in.nit.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import in.nit.service.impl.StorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4100")
@RequestMapping("/api")
public class UploadController {
 
  @Autowired
  StorageService storageService;
 
  List<String> files = new ArrayList<String>();
 
  @PostMapping("/post")
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
	  System.out.println(file.getName()+"   "+file.getOriginalFilename());
	  System.out.println(file.getResource()+"   "+file);
	  System.out.println(files);
	  System.out.println("come request");
    String message = "";
    try {
      storageService.store(file);
      files.add(file.getOriginalFilename());
 
      message = "You successfully uploaded " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "FAIL to upload " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
 
  @GetMapping("/getallfiles")
  public ResponseEntity<List<String>> getListFiles( ) {
	  System.out.println("get all files");
    List<String> fileNames = files
        .stream().map(fileName -> MvcUriComponentsBuilder
            .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
        .collect(Collectors.toList());
      System.out.println(fileNames+"  all"+files);
    return ResponseEntity.ok().body(fileNames);
  }
 
	/*
	 * @GetMapping("/filesall/{filename}")
	 * 
	 * @ResponseBody public ResponseEntity<Resource> getFile(@PathVariable String
	 * filename) { System.out.println("getFile method"); Resource file =
	 * storageService.loadFile(filename); return ResponseEntity.ok()
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
	 * file.getFilename()) .body(file); }
	 */
}