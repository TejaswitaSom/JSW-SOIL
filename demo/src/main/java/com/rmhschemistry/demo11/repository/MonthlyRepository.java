package com.rmhschemistry.demo11.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rmhschemistry.demo11.entity.MonthlyPlanner;

@Repository
@Transactional
public interface MonthlyRepository extends CrudRepository<MonthlyPlanner, Long>{

	@Query("Select ROUND(m,2) FROM MonthlyPlanner m where m.month=:Month AND m.year=:Year AND m.destination=:loc")
	MonthlyPlanner getMonthlyTotal(@Param("Month") Integer month, @Param("Year") Integer year, @Param("loc") String Location);
	
}
