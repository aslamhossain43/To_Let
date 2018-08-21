package com.renu.to_let.addservice_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renu.to_let.models.AddService;

public interface AddServiceRepository extends JpaRepository<AddService, Long>,JpaSpecificationExecutor<AddService>{
	
	//get all services
	@Query("FROM AddService")
	public List<AddService>getServicesTable();
	
	//find by category
	public static final String category="FROM AddService where rentType=:category";
        	@Query(category)
        List<AddService> findByCategory(@Param("category")String category);
        	//find by category
        	public static final String country="FROM AddService where country=:country";
                	@Query(country)
			public List<AddService> findByCountryry(@Param("country")String country); 
        
        
        
}
