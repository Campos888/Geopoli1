package personalProject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
public class Country {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
	@SequenceGenerator(name = "country_seq", sequenceName = "country_seq", allocationSize = 1)
	private Long id;

	/*
	@Enumerated(EnumType.STRING)
	@Column(name = "danger_level")
	private DangerLevel dangerLevel;
	*/
	
	@NotBlank
	private String Name;
	
	@Column(name = "url_image")
	private String urlImage;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	
	private String code;
	
	private Integer danger;
	private Integer healthcare;
	private Integer pollusion;
	private Integer wealth;
	private Integer temperature;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
	
	/*
	public DangerLevel getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(DangerLevel dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
	*/
	
	
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Name);
	}
	
	
	
	


	public Integer getDanger() {
		return danger;
	}

	public void setDanger(Integer danger) {
		this.danger = danger;
	}

	public Integer getHealthcare() {
		return healthcare;
	}

	public void setHealthcare(Integer healthcare) {
		this.healthcare = healthcare;
	}

	public Integer getPollusion() {
		return pollusion;
	}

	public void setPollusion(Integer pollusion) {
		this.pollusion = pollusion;
	}

	public Integer getWealth() {
		return wealth;
	}

	public void setWealth(Integer wealth) {
		this.wealth = wealth;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(Name, other.Name);
	}
	
	
	
}
