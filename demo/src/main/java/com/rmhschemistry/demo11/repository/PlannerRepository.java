package com.rmhschemistry.demo11.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmhschemistry.demo11.entity.Planner;

@Repository("brandRepository")
public interface PlannerRepository extends CrudRepository<Planner, Long> {

	@Query("Select ROUND(SUM(m.Quantity),2) From Planner m where m.PlannerDate=:date and m.Location=:unit")
    public Double dailySum(@Param("date") Date date, @Param("unit") String Unit);
    
    @Query("Select ROUND((SUM(m.Quantity * m.Iron)/SUM(m.Quantity)),2) From Planner m where m.PlannerDate=:date and m.Location=:unit")
    public Double dailyAvgFe(@Param("date") Date date, @Param("unit") String Unit);

    @Query("Select ROUND((SUM(m.Quantity * m.Silica)/SUM(m.Quantity)),2) From Planner m where m.PlannerDate=:date and m.Location=:unit")
    public Double dailyAvgSi(@Param("date") Date date, @Param("unit") String Unit);
    
    @Query("Select ROUND((SUM(m.Quantity * m.Alumina)/SUM(m.Quantity)),2) From Planner m where m.PlannerDate=:date and m.Location=:unit")
    public Double dailyAvgAl(@Param("date") Date date, @Param("unit") String Unit);
    
	@Query("Select m From Planner m where m.PlannerDate=:date and m.Location=:unit")
    public List<Planner> findAllByPlannerDate(@Param("date") Date date, @Param("unit") String Location);
	
    /*@Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date")
    public Double totalWeight(@Param("date") Date date);
    
    @Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date and m.Mode='Road'")
    public Double totalWeightRoad(@Param("date") Date date);
    
    @Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date and m.Mode='Rake'")
    public Double totalWeightRake(@Param("date") Date date);
    
    @Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date and m.Mode='HTP'")
    public Double totalWeightHTP(@Param("date") Date date);
    
    @Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date and m.Mode='BTP'")
    public Double totalWeightBTP(@Param("date") Date date);
    
    @Query("SELECT SUM(m.Quantity) FROM Planner m where m.PlannerDate=:date and m.Mode='NTP'")
    public Double totalWeightNTP(@Param("date") Date date);

    @Query("Select SUM(m.Quantity * m.Iron)/SUM(m.Quantity) From Planner m where m.PlannerDate=:date")
    public Double weightedFe(@Param("date") Date date);

    @Query("Select SUM(m.Quantity * m.Alumina)/SUM(m.Quantity) From Planner m where m.PlannerDate=:date")
    public Double weightedAl(@Param("date") Date date);

    @Query("Select SUM(m.Quantity * m.Silica)/SUM(m.Quantity) From Planner m where m.PlannerDate=:date")
    public Double weightedSi(@Param("date") Date date);*/
    
}
