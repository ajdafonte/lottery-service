package pt.caires.lottery.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryEntityToLotteryMapper;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemoryLotteryFinderTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final boolean FINISHED = false;
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final Lottery LOTTERY = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);
    private static final LotteryEntity LOTTERY_ENTITY = new LotteryEntity(ID, NAME, DATE, FINISHED, TICKETS);

    @Mock
    private WrapperStorageLottery wrapperStorageLottery;
    @Mock
    private LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;

    private MemoryLotteryFinder memoryLotteryFinder;

    @BeforeEach
    void setUp() {
        this.memoryLotteryFinder = new MemoryLotteryFinder(wrapperStorageLottery, lotteryEntityToLotteryMapper);
    }

    @Test
    void should_find_all_lotteries() {
        given(wrapperStorageLottery.selectAll()).willReturn(List.of(LOTTERY_ENTITY));
        given(lotteryEntityToLotteryMapper.map(LOTTERY_ENTITY)).willReturn(LOTTERY);

        Collection<Lottery> result = memoryLotteryFinder.findAll();

        assertThat(result)
                .isNotEmpty()
                .containsExactly(anExpectedLottery());
    }

    @Test
    void should_find_all_lotteries_by_date() {
        LocalDate date = LocalDate.of(2021, 4, 25);
        given(wrapperStorageLottery.selectAllBy(date)).willReturn(List.of(LOTTERY_ENTITY));
        given(lotteryEntityToLotteryMapper.map(LOTTERY_ENTITY)).willReturn(LOTTERY);

        Collection<Lottery> result = memoryLotteryFinder.findAllBy(date);

        assertThat(result)
                .isNotEmpty()
                .containsExactly(anExpectedLottery());
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