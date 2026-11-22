public class ForTest {

    public enum State {
        S, S1, S2, S3, F
    }

    public State process(String input) {
        if (input == null || input.isEmpty()) {
            return State.S;
        }

        State state = State.S;

        for (char ch : input.toCharArray()) {
            state = nextState(state, ch);
        }

        return state;
    }

    private State nextState(State current, char ch) {
        return switch (current) {
            case F -> State.F;
            case S -> (ch == 'T') ? State.S1 : State.S;
            case S1 -> {
                if (ch == 'E') yield State.S2;
                if (ch == 'T') yield State.S1;
                yield State.S;
            }
            case S2 -> {
                if (ch == 'S') yield State.S3;
                if (ch == 'T') yield State.S1;
                yield State.S;
            }
            case S3 -> (ch == 'T') ? State.F : State.S;
        };
    }

    public static void main(String[] args) {
        ForTest automaton = new ForTest();
        System.out.println("abcTESTabc -> " + automaton.process("abcTESTabc"));
        System.out.println("abcTES     -> " + automaton.process("abcTES"));
    }
}