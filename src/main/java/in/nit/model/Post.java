package in.nit.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity

public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String sort;
//CreationTimestamp
	@UpdateTimestamp
	private Timestamp enteredDate;
	private String full;
	private String author;

	//private byte[] image;
	@OneToOne(cascade = {CascadeType.ALL})
	private Image image;
	private String isActive;
	
}
