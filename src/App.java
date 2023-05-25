import java.util.ArrayList;  
import java.util.List;  
import java.util.Scanner;  
import java.util.stream.Collectors;  
  
public class App {  
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
  
        List<State> states = getStates(scanner);  
        State initialState = getInitialState(scanner, states);  
        setFinalStates(scanner, states);  
        List<Transition> transitions = getTransitions(scanner, states);  
  
        Automaton automaton = new Automaton(initialState, states, transitions);  
        automaton.printConfiguration();  
  
        processInputStrings(scanner, automaton);  
  
        scanner.close();  
    }  
  
    /**  
     * Gets the list of states from the user.  
     *  
     * @param scanner The scanner to read user input.  
     * @return The list of states.  
     */  
    private static List<State> getStates(Scanner scanner) {  
        System.out.println("Digite a quantidade de estados:");  
        int numStates = scanner.nextInt();  
        List<State> states = new ArrayList<>();  
  
        for (int i = 0; i < numStates; i++) {  
            System.out.println("Digite o nome do estado " + (i + 1) + " (" + (i + 1) + "/" + numStates + "):");  
            String name = scanner.next();  
            states.add(new State(name, false));  
        }  
  
        return states;  
    }  
  
    /**  
     * Sets the final states from the user input.  
     *  
     * @param scanner The scanner to read user input.  
     * @param states  The list of states.  
     */  
    private static void setFinalStates(Scanner scanner, List<State> states) {  
        System.out.println("Digite a quantidade de estados finais:");  
        int numFinalStates = scanner.nextInt();  
  
        for (int i = 0; i < numFinalStates; i++) {  
            String availableStates = availableStatesAsString(states);  
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
                    break;  
                }  
            }  
        }  
    }  
  
    /**  
     * Gets the initial state from the user.  
     *  
     * @param scanner The scanner to read user input.  
     * @param states  The list of states.  
     * @return The initial state.  
     */  
    private static State getInitialState(Scanner scanner, List<State> states) {  
        System.out.println("Digite o nome do estado inicial:");  
        String initialName = scanner.next();  
        State initialState = null;  
        for (State state : states) {  
            if (state.getName().equals(initialName)) {  
                initialState = state;  
                break;  
            }  
        }  
  
        return initialState;  
    }  
  
    /**  
     * Gets the list of transitions from the user.  
     *  
     * @param scanner The scanner to read user input.  
     * @param states  The list of states.  
     * @return The list of transitions.  
     */  
    private static List<Transition> getTransitions(Scanner scanner, List<State> states) {  
        System.out.println("Digite a quantidade de transições:");  
        int numTransitions = scanner.nextInt();  
        List<Transition> transitions = new ArrayList<>();  
        for (int i = 0; i < numTransitions; i++) {  
            System.out.println(  
                    "Digite a transição. Formato: <estado origem> <estado destino> <simbolo> (use '&' para Épsilon)");  
            String source = scanner.next();  
            String destination = scanner.next();  
            String symbol = scanner.next();  
  
            if (symbol.equals("&")) {  
                symbol = ""; // represents an ε transition  
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
  
        return transitions;  
    }  
  
    /**  
     * Processes the input strings and displays the results.  
     *  
     * @param scanner   The scanner to read user input.  
     * @param automaton The automaton to process the input strings.  
     */  
    private static void processInputStrings(Scanner scanner, Automaton automaton) {  
        System.out.println("Digite as cadeias de entrada, uma por vez. Para encerrar o programa, digite 'SAIR':");  
        String inputString = "";  
  
        while (!inputString.equalsIgnoreCase("SAIR")) {  
            inputString = scanner.next();  
  
            if (automaton.processInputString(inputString)) {  
                System.out.print("\033[32mACEITA\033[0m");  
            } else {  
                System.out.print("\033[31mRECUSA\033[0m");  
            }  
            System.out.println();  
        }  
    }  
  
    /**  
     * Converts the available states to a string representation.  
     *  
     * @param states The list of states.  
     * @return The string representation of the available states.  
     */  
    private static String availableStatesAsString(List<State> states) {  
        return states.stream()  
                .filter(state -> !state.isFinal())  
                .map(State::getName)  
                .collect(Collectors.toList())  
                .toString();  
    }  
}  
