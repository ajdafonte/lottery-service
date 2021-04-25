package pt.caires.lottery.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryEntityToLotteryMapper;
import pt.caires.lottery.infrastructure.mapper.LotteryToLotteryEntityMapper;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemoryLotteryRepositoryTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final boolean FINISHED = false;
    private static final List<Integer> TICKETS = List.of(123, 456);

    @Mock
    private WrapperStorageLottery wrapperStorageLottery;
    @Mock
    private LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;
    @Mock
    private LotteryToLotteryEntityMapper lotteryToLotteryEntityMapper;

    private MemoryLotteryRepository memoryLotteryRepository;

    @BeforeEach
    void setUp() {
        this.memoryLotteryRepository = new MemoryLotteryRepository(
                wrapperStorageLottery,
                lotteryToLotteryEntityMapper,
                lotteryEntityToLotteryMapper);
    }

    @Test
    void should_save_a_lottery() {
        Lottery lottery = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);
        LotteryEntity lotteryEntity = new LotteryEntity(ID, NAME, DATE, FINISHED, TICKETS);
        given(lotteryToLotteryEntityMapper.map(lottery)).willReturn(lotteryEntity);
        given(wrapperStorageLottery.selectBy(ID)).willReturn(lotteryEntity);
        given(lotteryEntityToLotteryMapper.map(lotteryEntity)).willReturn(lottery);

        Lottery result = memoryLotteryRepository.save(lottery);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLottery());
        verify(wrapperStorageLottery).insert(lotteryEntity);
    }

    private Lottery anExpectedLottery() {
        return new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
    }

}