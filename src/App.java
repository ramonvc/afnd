import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número de estados:");
        int numStates = scanner.nextInt();
        List<State> states = new ArrayList<>();

        for (int i = 0; i < numStates; i++) {
            System.out.println("Digite o nome do estado " + (i + 1) + " (" + (i + 1) + "/" + numStates + "):");
            String name = scanner.next();
            states.add(new State(name, false));
        }

        System.out.println("Digite o número de estados finais:");
        int numFinalStates = scanner.nextInt();
        List<State> finalStates = new ArrayList<>();

        for (int i = 0; i < numFinalStates; i++) {
            String availableStates = availableStatesAsString(states, finalStates);
            System.out.println("Digite o nome do estado final " + (i + 1) + " (" + (i + 1) + "/" + numFinalStates
                    + "). Estados disponíveis: " + availableStates);
            String finalStateName = scanner.next();

            while (!availableStates.contains(finalStateName)) {
                System.out.println("Estado inválido. Digite novamente:");
                finalStateName = scanner.next();
            }

            for (State state : states) {
                if (state.getName().equals(finalStateName)) {
                    state.setFinal(true);
                    finalStates.add(state);
                    break;
                }
            }
        }

        System.out.println("Digite o nome do estado inicial:");
        String initialName = scanner.next();
        State initialState = null;
        for (State state : states) {
            if (state.getName().equals(initialName)) {
                initialState = state;
                break;
            }
        }

        System.out.println("Digite o número de transições:");
        int numTransitions = scanner.nextInt();
        List<Transition> transitions = new ArrayList<>();
        for (int i = 0; i < numTransitions; i++) {
            System.out.println(
                    "Digite a transição. Formato: <estado origem> <estado destino> <simbolo> (use '-' para epsilon)");
            String source = scanner.next();
            String destination = scanner.next();
            String symbol = scanner.next();

            if (symbol.equals("-")) {
                symbol = ""; // representa uma transição ε
            }

            State sourceState = null;
            State destinationState = null;
            for (State state : states) {
                if (state.getName().equals(source)) {
                    sourceState = state;
                }
                if (state.getName().equals(destination)) {
                    destinationState = state;
                }
                if (sourceState != null && destinationState != null) {
                    break;
                }
            }
            transitions.add(new Transition(sourceState, destinationState, symbol));
        }

        Automaton automaton = new Automaton(initialState, states, transitions);

        System.out.println("Digite o número de cadeias de entrada:");
        int numInputs = scanner.nextInt();

        for (int i = 0; i < numInputs; i++) {
            System.out.println("Digite a cadeia de entrada:");
            String inputString = scanner.next();

            if (automaton.processInputString(inputString)) {
                System.out.print("\033[32mACEITA\033[0m");
            } else {
                System.out.print("\033[31mRECUSA\033[0m");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static String availableStatesAsString(List<State> states, List<State> finalStates) {
        List<String> availableStateNames = new ArrayList<>();
        for (State state : states) {
            if (!finalStates.contains(state)) {
                availableStateNames.add(state.getName());
            }
        }
        return availableStateNames.toString();
    }

}
