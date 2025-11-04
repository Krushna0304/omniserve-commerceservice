package com.omniserve.commondblib.event;

import com.omniserve.commondblib.entity.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ShipmentRequestEvent {
        String shipmentId;
        String orderId;
        Double pickupLongitude;
        Double pickupLatitude;
        Double dropLongitude;
        Double dropLatitude;
        OrderRequest orderRequest;
        String userId;
        LocalDateTime timestamp;

}
