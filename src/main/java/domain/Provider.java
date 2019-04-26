package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Provider extends Actor{
	
	private String make;

	@NotBlank
	@SafeHtml
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
	

}
