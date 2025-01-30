package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, String> {
    @Query(nativeQuery = true, value = "select * from operation_api_db.operations op where op.operation_id=:operationId")
    OperationEntity getOperationById(@Param("operationId") String operationId);

    @Query(nativeQuery = true, value = "select * from operation_api_db.operations op order by op.created_at desc")
    Collection<OperationEntity> findAllSavedOperations();
}
