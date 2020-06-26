package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.Image;
import in.nit.model.Post;

public interface IPostService {

	int savePost(Post post);
	List<Post> getAllPost();
	
	Image findByImageName(String filename);
	Integer deletePost(int id);
	Optional<Post> getOnePost(int id);
	
}
