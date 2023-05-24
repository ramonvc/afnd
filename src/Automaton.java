import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Automaton {
    private State initialState;
    private List<Transition> transitions;
    private List<State> states;

    /**
     * Constructs an automaton object.
     * 
     * @param initialState The initial state of the automaton.
     * @param states       The list of states in the automaton.
     * @param transitions  The list of transitions between states.
     */
    public Automaton(State initialState, List<State> states, List<Transition> transitions) {
        this.initialState = initialState;
        this.states = states;
        this.transitions = transitions;
    }

    /**
     * Processes the input string and returns whether the automaton accepts it.
     * 
     * @param inputString The input string to be processed.
     * @return true if the input string is accepted, false otherwise.
     */
    public boolean processInputString(String inputString) {
        return processInputStringRecursively(initialState, inputString);
    }

    /**
     * Processes the input string recursively starting from the current state.
     * 
     * @param currentState The current state of the automaton.
     * @param inputString  The remaining input string to be processed.
     * @return true if the input string is accepted, false otherwise.
     */
    private boolean processInputStringRecursively(State currentState, String inputString) {
        if (inputString.isEmpty()) {
            return currentState.isFinal();
        }

        String currentSymbol = inputString.substring(0, 1);
        List<Transition> possibleTransitions = findTransitions(currentState, currentSymbol);
        List<Transition> epsilonTransitions = findTransitions(currentState, "");

        // Adds epsilon (E) transitions to the list of possible transitions
        possibleTransitions.addAll(epsilonTransitions);

        for (Transition transition : possibleTransitions) {
            // If the transition is epsilon, do not consume the input symbol
            int symbolsToConsume = transition.getSymbol().isEmpty() ? 0 : 1;
            if (processInputStringRecursively(transition.getDestinationState(),
                    inputString.substring(symbolsToConsume))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds the possible transitions from the given state with the given symbol.
     * 
     * @param sourceState The source state of the transition.
     * @param symbol      The symbol associated with the transition.
     * @return A list of possible transitions.
     */
    private List<Transition> findTransitions(State sourceState, String symbol) {
        List<Transition> possibleTransitions = new ArrayList<>();

        for (Transition transition : transitions) {
            if (transition.getSourceState().equals(sourceState) && transition.getSymbol().equals(symbol)) {
                possibleTransitions.add(transition);
            }
        }

        return possibleTransitions;
    }

    /**
     * Prints the configuration of the automaton, including states, initial state,
     * final states, and transitions.
     */
    public void printConfiguration() {
        System.out.println("\n\n");
        System.out.println("Estados: {" + states.stream().map(State::getName).collect(Collectors.joining(", ")) + "}");
        System.out.println("Estado Inicial: {" + initialState.getName() + "}");
        System.out.println("Estados Finais: {"
                + states.stream().filter(State::isFinal).map(State::getName).collect(Collectors.joining(", ")) + "}");
        System.out.println("Transições:");
        for (Transition transition : transitions) {
            String symbol = transition.getSymbol().isEmpty() ? "&" : transition.getSymbol();
            System.out.println("(" + transition.getSourceState().getName() + ") -> ("
                    + transition.getDestinationState().getName() + ") (" + symbol + ")");
        }
        System.out.println("\n");
    }
}
