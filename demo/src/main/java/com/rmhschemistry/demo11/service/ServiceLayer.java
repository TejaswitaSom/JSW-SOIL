package com.rmhschemistry.demo11.service;

import java.util.Date;
import java.util.List;

import com.rmhschemistry.demo11.entity.DataEntry;
import com.rmhschemistry.demo11.entity.MonthlyPlanner;
import com.rmhschemistry.demo11.entity.Planner;

public interface ServiceLayer {
	
	// Data entry page
	void save (DataEntry bnd);
	void save(MonthlyPlanner Planner);
	
	
	// Unit-day planner page
	List<Planner> getBrandsandAvgs(Date Date, String Location);
	List<DataEntry> getMode(Date Date, String Location);
    List<DataEntry> getBrand(Date Date, String Location, String Mode);
    DataEntry getBrandQualities(Date Date, String Location, String Mode, String Brand);
    void save (Planner brand);
    void deletePlannerRecord(Long Id);
    
    
	// Deviation report page
    MonthlyPlanner getMonthlyTotal(Integer month, Integer year, String Location);
    public Double dailySum(Date date, String Unit);
    public Double dailyAvgFe(Date date, String Unit);
    public Double dailyAvgSi(Date date, String Unit);
    public Double dailyAvgAl(Date date, String Unit);
	
    
	// Database display page
	List<DataEntry> displayDaily();
	List<Planner> displayPlans();
	List<MonthlyPlanner> displayMonthly();
	void deleteDailyEntry(Long Id);
	void deleteMonthlyPlannerRecord(Long Id);
	
	
	
	// Email notifications
	public void sendSimpleMessage(String to, String subject, String Text);
	
	// --- Extras ---
    /*List<Planner> getBrands(Date Date, String Location);
    List<DataEntry> findAllByEntryDate(Date From, Date To);
    public Double totalWeight(Date date);
    public Double totalWeightRoad(Date date);
    public Double totalWeightRake(Date date);
    public Double totalWeightHTP(Date date);
    public Double totalWeightBTP(Date date);
    public Double totalWeightNTP(Date date);*/
    
}
