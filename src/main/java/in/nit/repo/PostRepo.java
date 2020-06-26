package in.nit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.model.Image;
import in.nit.model.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

	
	Image findByImageName(String filename);
	//Post findById(int id);
}
