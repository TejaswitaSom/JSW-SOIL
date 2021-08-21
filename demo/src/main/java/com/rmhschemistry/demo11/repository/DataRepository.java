package com.rmhschemistry.demo11.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rmhschemistry.demo11.entity.DataEntry;

@Repository
@Transactional
public interface DataRepository extends CrudRepository<DataEntry, Long> {

	
	@Query("Select m FROM DataEntry m where m.EntryDate=:date AND m.Destination=:loc")
	List<DataEntry> getMode(@Param("date") Date Date, @Param("loc") String Location);
	
	@Query("Select m FROM DataEntry m where m.EntryDate=:date AND m.Destination=:loc AND m.Mode=:mode")
	List<DataEntry> getBrand(@Param("date") Date Date, @Param("loc") String Location, @Param("mode") String Mode);
	
	@Query("Select m FROM DataEntry m where m.EntryDate=:date AND m.Destination=:loc AND m.Mode=:mode AND m.BrandName=:brand")
	DataEntry getBrandQualities(@Param("date") Date Date, @Param("loc") String Location, @Param("mode") String Mode, @Param("brand") String Brand);
	
	@Query("Select m FROM DataEntry m")
	List<DataEntry> display();
	
	@Query("select m from DataEntry m where m.EntryDate <= :to and m.EntryDate >= :from")
	List<DataEntry> findAllByEntryDate(@Param("from") Date From, @Param("to") Date To);
	
	
}
