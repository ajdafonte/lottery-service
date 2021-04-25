package pt.caires.lottery.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.api.v1.dto.LotteriesV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.domain.Lottery;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LotteriesToLotteriesV1DTOMapperTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final boolean FINISHED = false;
    private static final Lottery LOTTERY = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);
    private static final LotteryV1DTO LOTTERY_DTO = new LotteryV1DTO(ID, NAME, DATE, FINISHED, TICKETS);

    @Mock
    private LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;

    private LotteriesToLotteriesV1DTOMapper lotteriesToLotteriesV1DTOMapper;

    @BeforeEach
    void setUp() {
        this.lotteriesToLotteriesV1DTOMapper = new LotteriesToLotteriesV1DTOMapper(lotteryToLotteryV1DTOMapper);
    }

    @Test
    void should_map() {
        given(lotteryToLotteryV1DTOMapper.map(LOTTERY)).willReturn(LOTTERY_DTO);

        LotteriesV1DTO result = lotteriesToLotteriesV1DTOMapper.map(List.of(LOTTERY));

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteriesV1DTO());
    }

    private LotteriesV1DTO anExpectedLotteriesV1DTO() {
        return new LotteriesV1DTO(
                List.of(new LotteryV1DTO(
                        "id",
                        "name",
                        LocalDate.of(2021, 4, 25),
                        false,
                        List.of(123, 456))));
    }

}