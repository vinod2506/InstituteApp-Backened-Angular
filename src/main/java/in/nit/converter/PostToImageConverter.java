package in.nit.converter;

import org.springframework.core.convert.converter.Converter;

import in.nit.model.Image;
import in.nit.model.Post;

public class PostToImageConverter implements Converter<Post, Image> {

	
	@Override
	public Image convert(Post source) {
		Image img=new Image(source.getImage().getName(),source.getImage().getImgType(),source.getImage().getPic());
		
		return img;
	}
}
