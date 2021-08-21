package com.rmhschemistry.demo11.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date PlannerDate;
	@Column
    private String Location;
    @Column
    private String BrandName;
    @Column
    private String Mode;
    @Column
    private Double Iron;
    @Column
    private Double Silica;
    @Column
    private Double Alumina;
    @Column
    private Double Quantity;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBrandName() {
        return BrandName;
    }


	public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public Double getIron() {
        return Iron;
    }

    public void setIron(Double iron) {
        Iron = iron;
    }

    public Double getSilica() {
        return Silica;
    }

    public void setSilica(Double silica) {
        Silica = silica;
    }

    public Double getAlumina() {
        return Alumina;
    }

    public void setAlumina(Double alumina) {
        Alumina = alumina;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

	public String getLocation() {
		return Location;
	}


	public void setLocation(String location) {
		Location = location;
	}

	public Date getPlannerDate() {
		return PlannerDate;
	}


	public void setPlannerDate(Date plannerDate) {
		this.PlannerDate = plannerDate;
	}

    
}
