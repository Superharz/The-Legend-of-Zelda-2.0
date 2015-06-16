package Editor;

import java.util.Stack;

/**
 * This is an Undo/Redo Class which let's you Undo and Redo the last 30 changes
 * that you made at the Map
 *
 * @author Florian Harz
 */
public class UnReDo {

    private final Stack<ObjectHelp> undo;
    private final Stack<ObjectHelp> redo;
    /**
     * Creates a new UnReDo()-Object
     */
    public UnReDo() {
        undo = new Stack<ObjectHelp>();
        redo = new Stack<ObjectHelp>();
    }
    /**
     * Gets the last Helper-Object from the Undo-Stack
     * @return The last Helper-Object from the Undo-Stack
     */
    public ObjectHelp undo() {
        if (undo.empty()) {
            return null;
        }
        return undo.pop();
    }
    /**
     * Gets the last Helper-Object from the Redo-Stack
     * @return The last Helper-Object from the Redo-Stack
     */
    public ObjectHelp redo() {
        if (redo.empty()) {
            return null;
        }
        return redo.pop();
    }
    /**
     * Adds an Helper-Object to the Undo-Stack
     * Removes the oldest object when Object 31 gets added to it
     * @param obj The Helper-Object to add to the Undo-Stack
     */
    public void addUndo(ObjectHelp obj) {
        this.undo.push(obj);
        if (undo.size() > 30) {
            undo.remove(0);
        }
    }
    /**
     * Adds an Helper-Object to the Redo-Stack
     * Removes the oldest object when Object 31 gets added to it
     * @param obj The Helper-Object to add to the Redo-Stack
     */
    public void addRedo(ObjectHelp obj) {
        this.redo.push(obj);
        if (redo.size() > 30) {
            redo.remove(0);
        }
    }
    /**
     * Clears/Empties the Redo-Stack
     */
    void clearRedo() {
        if (!redo.empty()) {
            redo.clear();
        }
    }
}
