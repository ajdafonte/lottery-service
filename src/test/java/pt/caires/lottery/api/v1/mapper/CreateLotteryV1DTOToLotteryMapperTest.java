package pt.caires.lottery.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.shared.IdentifierGenerator;
import pt.caires.lottery.domain.shared.Randomizer;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CreateLotteryV1DTOToLotteryMapperTest {

    @Mock
    private IdentifierGenerator identifierGenerator;
    @Mock
    private Randomizer randomizer;

    private CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper;

    @BeforeEach
    void setUp() {
        this.createLotteryV1DTOToLotteryMapper = new CreateLotteryV1DTOToLotteryMapper(identifierGenerator, randomizer);
    }

    @Test
    void should_map() {
        CreateLotteryV1DTO createLotteryV1DTO = new CreateLotteryV1DTO(
                "name",
                LocalDate.of(2021, 4, 25),
                2);
        given(identifierGenerator.generate()).willReturn("id");
        given(randomizer.getPositiveValues(2)).willReturn(List.of(123, 456));

        Lottery result = createLotteryV1DTOToLotteryMapper.map(createLotteryV1DTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLottery());

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