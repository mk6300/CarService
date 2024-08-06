package project.carservice.srevice.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.carservice.model.dto.PartDTO;
import project.carservice.model.dto.UsedPartDTO;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.UsedPart;
import project.carservice.repository.UsedPartRepository;
import project.carservice.service.PartService;
import project.carservice.service.impl.UsedPartServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsedPartServiceImplTest {
    @Mock
    private PartService partService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UsedPartRepository usedPartRepository;

    @InjectMocks
    private UsedPartServiceImpl usedPartService;

    private PartDTO partDTO;
    private UsedPart usedPart;
    private Order order;
    private UsedPartDTO usedPartDTO;

    @BeforeEach
    void setUp() {
        partDTO = new PartDTO();
        partDTO.setName("Brake Pad");
        partDTO.setPrice(50.0);
        partDTO.setDescription("High quality brake pad");

        usedPart = new UsedPart();
        usedPart.setName(partDTO.getName());
        usedPart.setPrice(partDTO.getPrice());
        usedPart.setDescription(partDTO.getDescription());

        order = new Order();
        order.setId(UUID.randomUUID());

        usedPart.setOrder(order);

        usedPartDTO = new UsedPartDTO();
        usedPartDTO.setName(usedPart.getName());
        usedPartDTO.setPrice(usedPart.getPrice());
        usedPartDTO.setDescription(usedPart.getDescription());
    }

    @Test
    void testMapPartToUsedPart() {
       when(partService.getPartDetails(any(Long.class))).thenReturn(partDTO);

        UsedPart result = usedPartService.mapPartToUsedPart(1L, order);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(partDTO.getName(), result.getName());
        Assertions.assertEquals(partDTO.getPrice(), result.getPrice());
        Assertions.assertEquals(partDTO.getDescription(), result.getDescription());
        Assertions.assertEquals(order, result.getOrder());

        verify(partService, times(3)).getPartDetails(any(Long.class));
        verify(usedPartRepository, times(1)).save(any(UsedPart.class));
    }

    @Test
    void testGetUsedPartsForOrder() {
        when(usedPartRepository.findAllByOrderId(any(UUID.class))).thenReturn(List.of(usedPart));
        when(modelMapper.map(any(UsedPart.class), eq(UsedPartDTO.class))).thenReturn(usedPartDTO);

        List<UsedPartDTO> result = usedPartService.getUsedPartsForOrder(order.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(usedPartDTO.getName(), result.get(0).getName());
        Assertions.assertEquals(usedPartDTO.getPrice(), result.get(0).getPrice());
        Assertions.assertEquals(usedPartDTO.getDescription(), result.get(0).getDescription());

        verify(usedPartRepository, times(1)).findAllByOrderId(any(UUID.class));
        verify(modelMapper, times(1)).map(any(UsedPart.class), eq(UsedPartDTO.class));
    }
}
