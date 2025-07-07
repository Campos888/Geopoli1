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
	
	
	//Key features
	@Column(name = "total_pupulation")
	private Integer population;
	
	private String capital;
	
	private Integer area;
	
	private String currency;
	
	private String government;
	
	
	private Integer current_temperautre;
	
	private String conflict_status;  // might b changed to enum
	
	
	
	
	// Metrics
	private Integer political_stability;
	
	private Integer healthcare;
	
	private Integer education;
	
	private Integer envaironment;
	
	private Integer military_strength;
	
	private Integer wealth;
	
	private Integer travel_safty;
	

	
	
	

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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Integer getPopulation() {
		return population;
	}


	public void setPopulation(Integer population) {
		this.population = population;
	}


	public String getCapital() {
		return capital;
	}


	public void setCapital(String capital) {
		this.capital = capital;
	}


	public Integer getArea() {
		return area;
	}


	public void setArea(Integer area) {
		this.area = area;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getGovernment() {
		return government;
	}


	public void setGovernment(String government) {
		this.government = government;
	}


	public Integer getCurrent_temperautre() {
		return current_temperautre;
	}


	public void setCurrent_temperautre(Integer current_temperautre) {
		this.current_temperautre = current_temperautre;
	}


	public String getConflict_status() {
		return conflict_status;
	}


	public void setConflict_status(String conflict_status) {
		this.conflict_status = conflict_status;
	}


	public Integer getPolitical_stability() {
		return political_stability;
	}


	public void setPolitical_stability(Integer political_stability) {
		this.political_stability = political_stability;
	}


	public Integer getHealthcare() {
		return healthcare;
	}


	public void setHealthcare(Integer healthcare) {
		this.healthcare = healthcare;
	}

	
	

	public Integer getEducation() {
		return education;
	}


	public void setEducation(Integer education) {
		this.education = education;
	}


	public Integer getEnvaironment() {
		return envaironment;
	}


	public void setEnvaironment(Integer envaironment) {
		this.envaironment = envaironment;
	}


	public Integer getMilitary_strength() {
		return military_strength;
	}


	public void setMilitary_strength(Integer military_strength) {
		this.military_strength = military_strength;
	}


	public Integer getWealth() {
		return wealth;
	}


	public void setWealth(Integer wealth) {
		this.wealth = wealth;
	}

	
	

	public Integer getTravel_safty() {
		return travel_safty;
	}


	public void setTravel_safty(Integer travel_safty) {
		this.travel_safty = travel_safty;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Name);
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
