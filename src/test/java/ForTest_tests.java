import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForTest_tests {

    private final ForTest automaton = new ForTest();

    @ParameterizedTest(name = "[{index}] input=\"{0}\" -> expected={1}")
    @CsvSource({
            "'', S",
            "'abc', S",
            "'T', S1",
            "'abcT', S1",
            "'TE', S2",
            "'abcTE', S2",
            "'abcTES', S3",
            "'TEST', F",
            "'abcTESTabc', F",
            "'TESTTEST', F",
            "'xxTESTyyTESTzz', F"
    })
    void shouldReturnExpectedStateForStandardCases(String input, ForTest.State expected) {
        assertEquals(expected, automaton.process(input));
    }

    @ParameterizedTest(name = "[{index}] edge/star case input=\"{0}\" -> expected={1}")
    @CsvSource({
            "'TTEST', F",
            "'TETEST', F",
            "'TESTEST', F",
            "'TTT TEST', F",
            "'Т', S"
    })
    void shouldHandleStarCases(String input, ForTest.State expected) {
        assertEquals(expected, automaton.process(input));
    }
}