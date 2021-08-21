package com.rmhschemistry.demo11.entity;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  
@Entity
@Table 
public class MonthlyPlanner {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column
    private Integer month;
    @Column
    private Integer year;
    @Column
    private Double monthlyQuantity;
    @Column
    private Double tpd;
	@Column
    private String destination;
    @Column
    private Double iron;
    @Column
    private Double silica;
    @Column
    private Double alumina;
    
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getMonthlyQuantity() {
		return monthlyQuantity;
	}

	public void setMonthlyQuantity(Double monthlyQuantity) {
		this.monthlyQuantity = monthlyQuantity;
	}

	public Double getTpd() {
		return tpd;
	}

	public void setTpd(Double tpd) {
		this.tpd = tpd;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getIron() {
		return iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	public Double getSilica() {
		return silica;
	}

	public void setSilica(Double silica) {
		this.silica = silica;
	}

	public Double getAlumina() {
		return alumina;
	}

	public void setAlumina(Double alumina) {
		this.alumina = alumina;
	}
	
}