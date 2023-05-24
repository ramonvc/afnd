public class Transition {  
    private State sourceState;  
    private State destinationState;  
    private String symbol;  
  
    /**  
     * Constructs a transition object.  
     *  
     * @param sourceState      The source state of the transition.  
     * @param destinationState The destination state of the transition.  
     * @param symbol           The symbol associated with the transition.  
     */  
    public Transition(State sourceState, State destinationState, String symbol) {  
        this.sourceState = sourceState;  
        this.destinationState = destinationState;  
        this.symbol = symbol;  
    }  
  
    /**  
     * Returns the source state of the transition.  
     *  
     * @return The source state of the transition.  
     */  
    public State getSourceState() {  
        return sourceState;  
    }  
  
    /**  
     * Sets the source state of the transition.  
     *  
     * @param sourceState The new source state of the transition.  
     */  
    public void setSourceState(State sourceState) {  
        this.sourceState = sourceState;  
    }  
  
    /**  
     * Returns the destination state of the transition.  
     *  
     * @return The destination state of the transition.  
     */  
    public State getDestinationState() {  
        return destinationState;  
    }  
  
    /**  
     * Sets the destination state of the transition.  
     *  
     * @param destinationState The new destination state of the transition.  
     */  
    public void setDestinationState(State destinationState) {  
        this.destinationState = destinationState;  
    }  
  
    /**  
     * Returns the symbol associated with the transition.  
     *  
     * @return The symbol associated with the transition.  
     */  
    public String getSymbol() {  
        return symbol;  
    }  
  
    /**  
     * Sets the symbol associated with the transition.  
     *  
     * @param symbol The new symbol associated with the transition.  
     */  
    public void setSymbol(String symbol) {  
        this.symbol = symbol;  
    }  
}  
