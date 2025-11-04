package com.omniserve.commondblib.repository;

import com.omniserve.commondblib.entity.ShipmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRequestRepository extends JpaRepository<ShipmentRequest,String> {

    @Query(
            value = """
                    SELECT * FROM shipment_request
                    WHERE
                        order_state = :state and (EXTRACT(EPOCH(LocalDate.now() - timestamp)/60) >= :minuteAgo;
                    """,
            nativeQuery = true
    )
    List<ShipmentRequest> getShipmentsByStateAndInterval(String state,int minuteAgo);


    @Query(
            value = """
                    UPDATE shipment_request s
                    SET order_status = :nextState
                    where
                        shipment_id = :shipmentID and order_state = :currState
                    RETURNING s
                    """,
            nativeQuery = true
    )
    Optional<ShipmentRequest> getAndSetState(String shipmentID,String currState,String nextState);



}
