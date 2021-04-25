package pt.caires.lottery.api.v1.mapper;

import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.shared.IdentifierGenerator;
import pt.caires.lottery.domain.shared.Randomizer;

import javax.inject.Named;

@Named
public class CreateLotteryV1DTOToLotteryMapper {

    private final IdentifierGenerator identifierGenerator;
    private final Randomizer randomizer;

    public CreateLotteryV1DTOToLotteryMapper(IdentifierGenerator identifierGenerator, Randomizer randomizer) {
        this.identifierGenerator = identifierGenerator;
        this.randomizer = randomizer;
    }

    public Lottery map(CreateLotteryV1DTO createLotteryV1DTO) {
        return new Lottery(
                identifierGenerator.generate(),
                createLotteryV1DTO.getName(),
                createLotteryV1DTO.getDate(),
                false,
                randomizer.getPositiveValues(createLotteryV1DTO.getNumberTickets()));
    }

}
