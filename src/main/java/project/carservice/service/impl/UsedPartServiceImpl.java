package project.carservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.UsedPartDTO;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.UsedPart;
import project.carservice.repository.UsedPartRepository;
import project.carservice.service.PartService;
import project.carservice.service.UsedPartService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UsedPartServiceImpl implements UsedPartService {

    private final PartService partService;

    private final ModelMapper modelMapper;

    private final UsedPartRepository usedPartRepository;

    public UsedPartServiceImpl(PartService partService, ModelMapper modelMapper, UsedPartRepository usedPartRepository) {
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.usedPartRepository = usedPartRepository;
    }


    @Override
    public UsedPart mapPartToUsedPart(Long partId, Order order) {
        UsedPart usedPart = new UsedPart();
        usedPart.setName(partService.getPartDetails(partId).getName());
        usedPart.setPrice(partService.getPartDetails(partId).getPrice());
        usedPart.setDescription(partService.getPartDetails(partId).getDescription());
        usedPart.setOrder(order);
        usedPartRepository.save(usedPart);
        return usedPart;
    }

    @Override
    public List<UsedPartDTO> getUsedPartsForOrder(UUID orderId) {
      return usedPartRepository.findAllByOrderId(orderId).stream()
               .map(this::map)
               .collect(Collectors.toList());
    }

    private UsedPartDTO map(UsedPart usedPart) {
        return modelMapper.map(usedPart, UsedPartDTO.class);
    }
}
