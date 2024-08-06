package project.carservice.service;

import project.carservice.model.dto.UsedPartDTO;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.UsedPart;

import java.util.List;
import java.util.UUID;

public interface UsedPartService {

    UsedPart mapPartToUsedPart(Long partId, Order order);

    List<UsedPartDTO> getUsedPartsForOrder(UUID orderId);
}
