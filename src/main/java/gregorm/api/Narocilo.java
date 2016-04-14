package gregorm.api;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "narocila")
@NamedQueries({
	@NamedQuery(name = "najdiVsa", 
				query = "select n from Narocilo n"),
	@NamedQuery(name = "najdiVsaPoTipu", 
				query = "select n from Narocilo n where tip like :tip"),
	@NamedQuery(name = "najdiVsaPoDatumu", 
				query = "select n from Narocilo n where cast(n.casNarocila as date) = :datum")
})
public class Narocilo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_narocila")
	private Integer stNarocila;
	
	private String tip;
	
	private Integer kolicina;
	
	@Column(name = "cas_narocila", insertable=false, updatable=false)
	private DateTime casNarocila;
	
	@Transient
	private final DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm:ss dd.MM.yyyy");

	
	public Narocilo() {}
	
	public Narocilo(Integer stNarocila, Integer kolicina, String tip, DateTime casNarocila) {
		this.stNarocila = stNarocila;
		this.kolicina = kolicina;
		this.tip = tip;
		this.casNarocila = casNarocila;
	}
	
	@JsonProperty
	public Integer getStNarocila() {
		return this.stNarocila;
	}
	
	@JsonProperty
	public Integer getKolicina() {
		return this.kolicina;
	}
	
	@JsonProperty
	public String getTip() {
		return this.tip;
	}

	@JsonProperty
	public void setStNarocila(Integer stNarocila) {
		this.stNarocila = stNarocila;
	}

	@JsonProperty
	public void setTip(String tip) {
		this.tip = tip;
	}

	@JsonProperty
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	@JsonProperty
	public String getCasNarocila() {
		return dtf.print(casNarocila);
	}

	@JsonProperty
	public void setCasNarocila(String casNarocila) {
		this.casNarocila = dtf.parseDateTime(casNarocila);
	}
}
