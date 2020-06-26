package in.nit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.Image;
import in.nit.model.Post;
import in.nit.repo.PostRepo;
import in.nit.service.IPostService;
@Service
public class PostServiceImpl implements IPostService{

	@Autowired
	private PostRepo repo;
	
	@Override
	public int savePost(Post post) {
		return repo.save(post).getId();
	}

	@Override
	public List<Post> getAllPost() {	
		return repo.findAll();
		
	}

	@Override
	public Image findByImageName(String filename) {
		
		return repo.findByImageName(filename);
	}
	
	@Override
	public Integer deletePost(int id) {
		 repo.deleteById(id);
		 return id;
	}
	
	@Override
	public Optional<Post> getOnePost(int id) {
		return repo.findById(id);
	}

	
}
