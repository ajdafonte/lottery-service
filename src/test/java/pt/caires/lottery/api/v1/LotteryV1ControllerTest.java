package pt.caires.lottery.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.api.v1.dto.LotteriesV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.api.v1.dto.PurchaseLotteryV1DTO;
import pt.caires.lottery.api.v1.mapper.CreateLotteryV1DTOToLotteryMapper;
import pt.caires.lottery.api.v1.mapper.LotteriesToLotteriesV1DTOMapper;
import pt.caires.lottery.api.v1.mapper.LotteryToLotteryV1DTOMapper;
import pt.caires.lottery.api.v1.mapper.PurchaseLotteryV1DTOToLotteryPurchaseEventMapper;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.usecase.CreateLottery;
import pt.caires.lottery.usecase.GetLotteries;
import pt.caires.lottery.usecase.PurchaseLotteryTickets;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LotteryV1ControllerTest {

    private static final String ID = "id";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final CreateLotteryV1DTO CREATE_LOTTERY_DTO = new CreateLotteryV1DTO(ID, DATE, 2);
    private static final String NAME = "name";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final boolean FINISHED = false;
    private static final Lottery LOTTERY = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);
    private static final LotteryV1DTO LOTTERY_DTO = new LotteryV1DTO(ID, NAME, DATE, FINISHED, TICKETS);
    private static final String USER_ID = "userId";
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    @Mock
    private CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper;
    @Mock
    private CreateLottery createLottery;
    @Mock
    private LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;
    @Mock
    private GetLotteries getLotteries;
    @Mock
    private LotteriesToLotteriesV1DTOMapper lotteriesToLotteriesV1DTOMapper;
    @Mock
    private PurchaseLotteryTickets purchaseLotteryTickets;
    @Mock
    private PurchaseLotteryV1DTOToLotteryPurchaseEventMapper purchaseLotteryV1DTOToLotteryPurchaseEventMapper;

    private LotteryV1Controller lotteryV1Controller;

    @BeforeEach
    void setUp() {
        this.lotteryV1Controller = new LotteryV1Controller(
                createLotteryV1DTOToLotteryMapper,
                createLottery,
                lotteryToLotteryV1DTOMapper,
                getLotteries,
                lotteriesToLotteriesV1DTOMapper,
                purchaseLotteryTickets,
                purchaseLotteryV1DTOToLotteryPurchaseEventMapper);
    }

    @Test
    void should_create_a_lottery() {
        given(createLotteryV1DTOToLotteryMapper.map(CREATE_LOTTERY_DTO)).willReturn(LOTTERY);
        given(createLottery.execute(LOTTERY)).willReturn(LOTTERY);
        given(lotteryToLotteryV1DTOMapper.map(LOTTERY)).willReturn(LOTTERY_DTO);

        LotteryV1DTO result = lotteryV1Controller.createLottery(CREATE_LOTTERY_DTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryV1DTO());
    }

    @Test
    void should_retrieve_lotteries() {
        List<Lottery> lotteries = List.of(LOTTERY);
        given(getLotteries.execute(DATE)).willReturn(lotteries);
        given(lotteriesToLotteriesV1DTOMapper.map(lotteries)).willReturn(new LotteriesV1DTO(List.of(LOTTERY_DTO)));

        LotteriesV1DTO result = lotteryV1Controller.getLotteriesBy(DATE);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteriesV1DTO());
    }

    @Test
    void should_purchase_lottery_tickets() {
        PurchaseLotteryV1DTO purchaseLotteryV1DTO = new PurchaseLotteryV1DTO(USER_ID, TICKETS);
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT);
        given(purchaseLotteryV1DTOToLotteryPurchaseEventMapper.map(ID, purchaseLotteryV1DTO)).willReturn(lotteryPurchaseEvent);

        lotteryV1Controller.purchaseLotteryTickets(ID, purchaseLotteryV1DTO);

        verify(purchaseLotteryTickets).execute(lotteryPurchaseEvent);
    }

    private LotteriesV1DTO anExpectedLotteriesV1DTO() {
        return new LotteriesV1DTO(List.of(anExpectedLotteryV1DTO()));
    }

    private LotteryV1DTO anExpectedLotteryV1DTO() {
        return new LotteryV1DTO(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
    }

}