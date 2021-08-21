package com.rmhschemistry.demo11.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rmhschemistry.demo11.entity.DataEntry;
import com.rmhschemistry.demo11.entity.MonthlyPlanner;
import com.rmhschemistry.demo11.entity.Planner;
import com.rmhschemistry.demo11.repository.DataRepository;
import com.rmhschemistry.demo11.repository.MonthlyRepository;
import com.rmhschemistry.demo11.repository.PlannerRepository;

@Service("serviceLayer")
@Transactional
public class ServiceImplementation implements ServiceLayer {

    @Autowired
    private PlannerRepository plannerRepository;

    @Autowired
    private DataRepository dataRepository;
    
    @Autowired 
    private MonthlyRepository monthlyRepository;

    @Autowired
	private JavaMailSender emailSender;

    
    
    // Data entry page
    @Override
	public void save(DataEntry bnd) {
		dataRepository.save(bnd);
	}
    
    @Override
	public void save(MonthlyPlanner Planner) {
		// TODO Auto-generated method stub
		monthlyRepository.save(Planner);
	}
    
    
    
    // Unit-day planner page
    @Override
	public List<Planner> getBrandsandAvgs(Date Date, String Location) {
		// TODO Auto-generated method stub
		Planner abc = new Planner();
		abc.setQuantity(plannerRepository.dailySum(Date, Location));
		abc.setIron(plannerRepository.dailyAvgFe(Date, Location));
		abc.setAlumina(plannerRepository.dailyAvgAl(Date, Location));
		abc.setSilica(plannerRepository.dailyAvgSi(Date, Location));
		List<Planner> all = plannerRepository.findAllByPlannerDate(Date, Location);
		all.add(abc);
		return all;
	}
    
    @Override
	public List<DataEntry> getMode(Date Date, String Location) {
		return dataRepository.getMode(Date,Location);
	}

	@Override
	public List<DataEntry> getBrand(Date Date, String Location, String Mode) {
		return dataRepository.getBrand(Date, Location, Mode);
	}

	@Override
	public DataEntry getBrandQualities(Date Date, String Location, String Mode, String Brand) {
		return dataRepository.getBrandQualities(Date, Location, Mode, Brand);
	}
	
	@Override
    public void save(Planner brand) {
        plannerRepository.save(brand);
    }
	
	@Override
	public void deletePlannerRecord(Long Id) {
		plannerRepository.deleteById(Id);
	}
	
	
	
	// Deviation report page
	@Override
	public MonthlyPlanner getMonthlyTotal(Integer month, Integer year, String Location) {
		// TODO Auto-generated method stub
		return monthlyRepository.getMonthlyTotal(month,year,Location);
	}

	@Override
	public Double dailySum(Date date, String Unit) {
		// TODO Auto-generated method stub
		return plannerRepository.dailySum(date, Unit);
	}
	
	@Override
	public Double dailyAvgFe(Date date, String Unit) {
		// TODO Auto-generated method stub
		return plannerRepository.dailyAvgFe(date, Unit);
	}

	@Override
	public Double dailyAvgSi(Date date, String Unit) {
		// TODO Auto-generated method stub
		return plannerRepository.dailyAvgSi(date, Unit);
	}

	@Override
	public Double dailyAvgAl(Date date, String Unit) {
		// TODO Auto-generated method stub
		return plannerRepository.dailyAvgAl(date,Unit);
	}
	
	
	
	// Database display page
	@Override
	public List<DataEntry> displayDaily() {
		return (List<DataEntry>) dataRepository.findAll();
	}

	@Override
	public List<Planner> displayPlans() {
		// TODO Auto-generated method stub
		return (List<Planner>) plannerRepository.findAll();
	}
	
	@Override
	public List<MonthlyPlanner> displayMonthly() {
		// TODO Auto-generated method stub
		return (List<MonthlyPlanner>) monthlyRepository.findAll();
	}

	@Override
	public void deleteDailyEntry(Long Id) {
		// TODO Auto-generated method stub
		dataRepository.deleteById(Id);
	}

	@Override
	public void deleteMonthlyPlannerRecord(Long Id) {
		// TODO Auto-generated method stub
		monthlyRepository.deleteById(Id);
	}

	
	
	// Email service
	@Override
	public void sendSimpleMessage(String to, String subject, String Text) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@jsw.in");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(Text);
		emailSender.send(message);
	}
	
	
	
	// ---Extra---
	/*@Override
	public List<DataEntry> findAllByEntryDate(Date From, Date To) {
		return (List<DataEntry>) dataRepository.findAllByEntryDate(From, To);
	}

	@Override
	public List<Planner> getBrands(Date Date, String Location) {
		//return (List<Planner>) plannerRepository.findAll();
		return (List<Planner>) plannerRepository.findAllByPlannerDate(Date, Location);
	}

	@Override
	public Double totalWeightRoad(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeightRoad(date);
	}

	@Override
	public Double totalWeightRake(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeightRake(date);
	}

	@Override
	public Double totalWeightHTP(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeightHTP(date);
	}

	@Override
	public Double totalWeightBTP(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeightBTP(date);
	}

	@Override
	public Double totalWeightNTP(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeightNTP(date);
	}

	@Override
	public Double totalWeight(Date date) {
		// TODO Auto-generated method stub
		return plannerRepository.totalWeight(date);
	}*/
	
}
