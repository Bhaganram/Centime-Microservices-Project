
package com.centime.l1.dbconnectionservice.repository;

import com.centime.l1.dbconnectionservice.entity.NameToColorMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameToColorMappingRepository extends JpaRepository<NameToColorMappingEntity,Long> {

}
