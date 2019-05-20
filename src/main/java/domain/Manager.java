
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private Team	team;


	@OneToOne(optional = true)
	public Team getTeam() {
		return this.team;
	}

	public void setTeam(final Team team) {
		this.team = team;
	}

}
