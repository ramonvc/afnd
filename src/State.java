public class State {
    private String name;
    private boolean isFinal;

    /**
     * Constructs a state object.
     * 
     * @param name    The name of the state.
     * @param isFinal Whether the state is a final state or not.
     */
    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    /**
     * Returns the name of the state.
     * 
     * @return The name of the state.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the state.
     * 
     * @param name The new name of the state.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the state is a final state.
     * 
     * @return true if the state is a final state, false otherwise.
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets the state's final status.
     * 
     * @param isFinal Whether the state is a final state or not.
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
}
