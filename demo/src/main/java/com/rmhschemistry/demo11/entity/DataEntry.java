package com.rmhschemistry.demo11.entity;

import java.util.Date;

/*import javax.validation.constraints.NotBlank;*/

import javax.persistence.*;

@Entity
public class DataEntry{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date EntryDate;
    @Column
    private Integer Quantity;
	@Column
    private String Destination;
    @Column
    private String Mode;
	@Column
    private String BrandName;
    @Column
    private Double Iron;
    @Column
    private Double Silica;
    @Column
    private Double Alumina;

    public Date getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(Date entryDate) {
		EntryDate = entryDate;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	
    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
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
        Iron= iron;
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
    
    public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

}
