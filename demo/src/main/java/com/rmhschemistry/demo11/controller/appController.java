package com.rmhschemistry.demo11.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rmhschemistry.demo11.entity.DataEntry;
import com.rmhschemistry.demo11.entity.MonthlyPlanner;
import com.rmhschemistry.demo11.entity.Planner;
import com.rmhschemistry.demo11.service.ServiceLayer;

@Controller()
public class appController {

	public LocalDateTime timeOBP2;
	public LocalDateTime timeSP1;
	public LocalDateTime timePP1;
	public LocalDateTime timeBasemix;
	String content = "";
	
    @Autowired
    private ServiceLayer service;
    
    
    
    @GetMapping()
    public String homePage() {
        return "index";
    }
    
    @GetMapping("index.html")
    public String navHome() {
        return "index";
    }
    
    @GetMapping("nav.html")
    public String Home() {
        return "nav";
    }
    
    @GetMapping("plannerOld.html")
    public String test() {
        return "plannerOld";
    }
    
    
    @GetMapping("sapentry.html")
    public String dataEntryPage() {
        return "sapentry";
    }
    
    @ResponseBody
    @RequestMapping(value="/sapentry.html/saveDaily",method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  	public String saveEntry(DataEntry bnd) {
    	service.save(bnd);
  	    //System.out.println("success save");
  	    return "{\"status\":\"success\"}";
  	}
    
    @ResponseBody
    @RequestMapping(value="/sapentry.html/saveMonthly",method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  	public String saveMonthlyEntry(MonthlyPlanner Plan) {
    	service.save(Plan);
  	    //System.out.println("success save");
  	    return "{\"status\":\"success\"}";
  	}
    
    
    
    @GetMapping("DatabaseDisplay.html")
    public String dataDisplay(){
        return "DatabaseDisplay";
    }
    
    @ResponseBody
    @RequestMapping(value = "/DatabaseDisplay.html/dailydata", method = RequestMethod.GET)
    public String dailyDataDisplay(){
    	Gson gson = new Gson();
        return gson.toJson(service.displayDaily());
    }
    
    @ResponseBody
    @RequestMapping(value = "/DatabaseDisplay.html/monthlyTotalPlans", method = RequestMethod.GET)
    public String monthlyPlansDisplay(){
    	Gson gson = new Gson();
        return gson.toJson(service.displayMonthly());
    }
    
    @ResponseBody
    @RequestMapping(value = "/DatabaseDisplay.html/dailyPlans", method = RequestMethod.GET)
    public String dailyPlansDisplay(){
    	Gson gson = new Gson();
        return gson.toJson(service.displayPlans());
    }
    
     @ResponseBody
	 @RequestMapping(value="/DatabaseDisplay.html/delete/dailyData/{id}",method=RequestMethod.POST)
	 public void deleteDailyRecord(@PathVariable("id") Long Id) {
	     service.deleteDailyEntry(Id); 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="/DatabaseDisplay.html/delete/monthlyPlan/{id}",method=RequestMethod.POST)
	 public void deletePlannedRecord(@PathVariable("id") Long Id) {
	 	service.deleteMonthlyPlannerRecord(Id); 
		 
	 }
    
    
    
    
    
    @GetMapping("planner.html") 
    public String Render(Model model){
    	List<String> locations = Arrays.asList("OBP2", "SP1", "PP1", "Basemix");
    	model.addAttribute("Location", locations); 
    	return "planner"; 
    }

    @ResponseBody
    @RequestMapping(value = "/planner.html/{loc}", method = RequestMethod.GET)
    public String displayDailyLocationPlannerEntries(@PathVariable("loc") String Location){    
    	LocalDate localDate = LocalDate.now(ZoneId.of("GMT+05:30"));
    	Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Gson gson = new Gson();
        return gson.toJson(service.getBrandsandAvgs(date, Location));
    }
    
	@ResponseBody
    @RequestMapping(value = "/planner.html/getmode/{Date}/{loc}", method = RequestMethod.GET)
    public String modeRender(@PathVariable("Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("loc") String Location){
		//System.out.println("working");       
		Gson gson = new Gson();
        return gson.toJson(service.getMode(date, Location));
    }
	  
    @ResponseBody
    @RequestMapping(value = "/planner.html/getbrand/{Date}/{loc}/{Mode}", method = RequestMethod.GET)
    public String brandRender(@PathVariable("Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("Mode") String Mode, @PathVariable("loc") String Location){
        Gson gson = new Gson();
        return gson.toJson(service.getBrand(date, Location, Mode));
    }
  
    @ResponseBody
    @RequestMapping(value = "/planner.html/brandQual/{Date}/{loc}/{Mode}/{Brand}", method = RequestMethod.GET)
	      public String qualityRender(@PathVariable("Date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("Mode") String Mode, @PathVariable("Brand") String Brand,@PathVariable("loc") String Location){
        Gson gson = new Gson();
        return gson.toJson(service.getBrandQualities(date, Location, Mode, Brand));
    }
 
	
	 @ResponseBody
	 @RequestMapping(value="/planner.html/{loc}/save",method=RequestMethod.POST,
					 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
					 produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	 public String saveBrand(@PathVariable("loc") String Location, Planner bnd) { 
		 service.save(bnd);
		 if(Location.equals("OBP2")) {
			 timeOBP2 = LocalDateTime.now();
		 }
		 if(Location.equals("SP1")) {
			 timeSP1 = LocalDateTime.now();
		 }
		 if(Location.equals("PP1")) {
			 timePP1 = LocalDateTime.now();
		 }
		 if(Location.equals("Basemix")) {
			 timeBasemix = LocalDateTime.now();
		 }
		 LocalDate localDate = LocalDate.now(ZoneId.of("GMT+05:30"));
	     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 Gson gson = new Gson(); 
		 return gson.toJson(service.getBrandsandAvgs(date, Location)); 
	 }
	
	 @ResponseBody
	 @RequestMapping(value="/planner.html/delete/{loc}/{id}",method=RequestMethod.POST)
	 public String deletePlannedRecord(@PathVariable("loc") String Location, @PathVariable("id") Long Id) {
		 service.deletePlannerRecord(Id); 
		 if(Location.equals("OBP2")) {
			 timeOBP2 = LocalDateTime.now();
		 }
		 if(Location.equals("SP1")) {
			 timeSP1 = LocalDateTime.now();
		 }
		 if(Location.equals("PP1")) {
			 timePP1 = LocalDateTime.now();
		 }
		 if(Location.equals("Basemix")) {
			 timeBasemix = LocalDateTime.now();
		 }
		 LocalDate localDate = LocalDate.now(ZoneId.of("GMT+05:30"));
	     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 Gson gson = new Gson(); 
		 return gson.toJson(service.getBrandsandAvgs(date, Location));
	 }
	 
	@ResponseBody
	@RequestMapping(value="/planner.html/sendmail/{loc}", method=RequestMethod.GET)
	public void sendmail(@PathVariable("loc") String Location) {
		content = "Deviation Report\n\n" + Location;
		
		LocalDate localDate = LocalDate.now(ZoneId.of("GMT+05:30"));
    	Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	MonthlyPlanner x = service.getMonthlyTotal(localDate.getMonthValue(), localDate.getYear(), Location);
    	
		content += "\nQuantity: " + String.valueOf(service.dailySum(date, Location)-x.getTpd());
		content += "\nIron: " + String.valueOf(service.dailyAvgFe(date, Location)-x.getIron());
		content += "\nAlumina: " + String.valueOf(service.dailyAvgAl(date, Location)-x.getAlumina());
		content += "\nSilica: " + String.valueOf(service.dailyAvgSi(date, Location)-x.getSilica());
		
		service.sendSimpleMessage("tejaswita.sip9@jsw.in", "Test mail for Deviation Report - JSW Soil mail notifications", content);
    	service.sendSimpleMessage("tanya.sip9@jsw.in", "Test mail for Deviation Report - JSW Soil mail notifications", content);
		// --Add mailing function or concerned here like previous
    	
    	content = "";
    	//System.out.println("Email sent..");
	}
	
	 
	 
    @GetMapping("/calc.html")
    public String DeviationReport(Model model) {
    	
    	LocalDate localDate = LocalDate.now(ZoneId.of("GMT+05:30"));
    	Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	
    	
    	try {
    		MonthlyPlanner OBP2 = service.getMonthlyTotal(localDate.getMonthValue(), localDate.getYear(), "OBP2");
    		try{
        		List<Double> bp2 = Arrays.asList(OBP2.getMonthlyQuantity(), service.dailySum(date, "OBP2")-OBP2.getTpd(), 
    					   service.dailyAvgFe(date, "OBP2")-OBP2.getIron(), service.dailyAvgAl(date, "OBP2")-OBP2.getAlumina(), 
    					   service.dailyAvgSi(date, "OBP2")-OBP2.getSilica());
        		model.addAttribute("devOBP2", bp2);
        		model.addAttribute("time1", timeOBP2);
        		
        		
        		Map<String, Double> graphData = new HashMap<>();
        		graphData.put("Quantity", (service.dailySum(date, "OBP2")-OBP2.getTpd())*100/(OBP2.getTpd()));
                graphData.put("Iron", (service.dailyAvgFe(date, "OBP2")-OBP2.getIron())*100/(OBP2.getIron()));
                graphData.put("Alumina", (service.dailyAvgAl(date, "OBP2")-OBP2.getAlumina())*100/(OBP2.getAlumina()));
                graphData.put("Silica", (service.dailyAvgSi(date, "OBP2")-OBP2.getSilica())*100/(OBP2.getSilica()));
                model.addAttribute("OBP2chart", graphData);
                
                
        	}catch(NullPointerException e) {
        		String s = "Plan not updated yet!";
        		model.addAttribute("errorMessage", s);
        	}
    	}catch(NullPointerException e) {
    		String s = "Monthly Plan not added yet!";
    		model.addAttribute("errorMessage", s);
    	}
    	
    	try {
    		MonthlyPlanner SP1 = service.getMonthlyTotal(localDate.getMonthValue(), localDate.getYear(), "SP1");
        	try {
        		List<Double> sp1 = Arrays.asList(SP1.getMonthlyQuantity(), service.dailySum(date, "SP1")-SP1.getTpd(),
         			   			   service.dailyAvgFe(date, "SP1")-SP1.getIron(), service.dailyAvgAl(date, "SP1")-SP1.getAlumina(), 
         			   			   service.dailyAvgSi(date, "SP1")-SP1.getSilica());
        		model.addAttribute("devSP1", sp1);
        		model.addAttribute("time2", timeSP1);
        		
        		Map<String, Double> graphData = new HashMap<>();
        		graphData.put("Quantity", (service.dailySum(date, "SP1")-SP1.getTpd())*100/(SP1.getTpd()));
                graphData.put("Iron", (service.dailyAvgFe(date, "SP1")-SP1.getIron())*100/(SP1.getIron()));
                graphData.put("Alumina", (service.dailyAvgAl(date, "SP1")-SP1.getAlumina())*100/(SP1.getAlumina()));
                graphData.put("Silica", (service.dailyAvgSi(date, "SP1")-SP1.getSilica())*100/(SP1.getSilica()));
                model.addAttribute("SP1chart", graphData);
                
        	}catch(NullPointerException e) {
        		String s = "Plan not updated yet!";
        		model.addAttribute("errorMessage", s);
        	}
    	}catch(NullPointerException e) {
    		String s = "Monthly Plan not added yet!";
    		model.addAttribute("errorMessage", s);
    	}
    	
    	
    	try {
    		MonthlyPlanner PP1 = service.getMonthlyTotal(localDate.getMonthValue(), localDate.getYear(), "PP1");
        	try{
        		List<Double> pp1 = Arrays.asList(PP1.getMonthlyQuantity(), service.dailySum(date, "PP1")-PP1.getTpd(),
        					   service.dailyAvgFe(date, "PP1")-PP1.getIron(), service.dailyAvgAl(date, "PP1")-PP1.getAlumina(), 
        					   service.dailyAvgSi(date, "PP1")-PP1.getSilica());
        		model.addAttribute("devPP1", pp1);
        		model.addAttribute("time3", timePP1);
        		
        		Map<String, Double> graphData = new HashMap<>();
        		graphData.put("Quantity", (service.dailySum(date, "PP1")-PP1.getTpd())*100/(PP1.getTpd()));
                graphData.put("Iron", (service.dailyAvgFe(date, "PP1")-PP1.getIron())*100/(PP1.getIron()));
                graphData.put("Alumina", (service.dailyAvgAl(date, "PP1")-PP1.getAlumina())*100/(PP1.getAlumina()));
                graphData.put("Silica", (service.dailyAvgSi(date, "PP1")-PP1.getSilica())*100/(PP1.getSilica()));
                model.addAttribute("PP1chart", graphData);
                
        	}catch(NullPointerException e) {
        		String s = "Plan not updated yet!";
        		model.addAttribute("errorMessage", s);
        	}
    	}catch(NullPointerException e) {
    		String s = "Monthly Plan not added yet!";
    		model.addAttribute("errorMessage", s);
    	}
    	
    	
    	try {
    		MonthlyPlanner Basemix = service.getMonthlyTotal(localDate.getMonthValue(), localDate.getYear(), "Basemix");
        	try {
    	    	List<Double> baseMix = Arrays.asList(Basemix.getMonthlyQuantity(), service.dailySum(date, "Basemix")-Basemix.getTpd(),
    	    						   service.dailyAvgFe(date, "Basemix")-Basemix.getIron(), service.dailyAvgAl(date, "Basemix")-Basemix.getAlumina(), 
    	    						   service.dailyAvgSi(date, "Basemix")-Basemix.getSilica());
    	    	model.addAttribute("devBasemix", baseMix);
    	    	model.addAttribute("time4", timeBasemix);
    	    	
    	    	Map<String, Double> graphData = new HashMap<>();
        		graphData.put("Quantity", (service.dailySum(date, "Basemix")-Basemix.getTpd())*100/(Basemix.getTpd()));
                graphData.put("Iron", (service.dailyAvgFe(date, "Basemix")-Basemix.getIron())*100/(Basemix.getIron()));
                graphData.put("Alumina", (service.dailyAvgAl(date, "Basemix")-Basemix.getAlumina())*100/(Basemix.getAlumina()));
                graphData.put("Silica", (service.dailyAvgSi(date, "Basemix")-Basemix.getSilica())*100/(Basemix.getSilica()));
                model.addAttribute("Basemixchart", graphData);
                
        	}catch(NullPointerException e) {
        		String s = "Plan not updated yet!";
        		model.addAttribute("errorMessage", s);
        	}
    	}catch(NullPointerException e) {
    		String s = "Monthly Plan not added yet!";
    		model.addAttribute("errorMessage", s);
    	}

        return "calc";
    }
	  
    
   
    // Email service
    /* @ResponseBody
    @RequestMapping(value="/sendmail", method=RequestMethod.GET)
    public String sendEmail() {
    	System.out.println(content);
    	if(content!=null) {
    		service.sendSimpleMessage("tejaswita.sip9@jsw.in", "Test mail for Deviation Report - JSW Soil mail notifications", content);
        	service.sendSimpleMessage("tanya.sip9@jsw.in", "Test mail for Deviation Report - JSW Soil mail notifications", content);
        	System.out.println("Email sent..");
        	return "Email sent..";
    	}
    	else 
    		return "Plans yet to be updated";
    }*/
    
}
