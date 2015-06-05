/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.util.Stack;

/**
 *
 * @author f.harz
 */
public class UnReDo {

    private Stack<ObjectHelp> undo;
    private Stack<ObjectHelp> redo;

    public UnReDo() {
        undo = new Stack<ObjectHelp>();
        redo = new Stack<ObjectHelp>();
    }

    public ObjectHelp undo() {
        if (undo.empty()) {
            return null;
        }
        return undo.pop();
    }

    public ObjectHelp redo() {
        if (redo.empty()) {
            return null;
        }
        return redo.pop();
    }

    public void addUndo(ObjectHelp obj) {
        this.undo.push(obj);
        if (undo.size() > 30) {
            undo.remove(0);
        }
    }

    public void addRedo(ObjectHelp obj) {
        this.redo.push(obj);
        if (redo.size() > 30) {
            redo.remove(0);
        }
    }

    @Override
    public String toString() {
        return null;
    }

    public boolean equals(UnReDo object) {
        return false;
    }

    public void destroy() {
    }

    @Override
    public UnReDo clone() {
        return null;
    }

    void clearRedo() {
        if (!redo.empty()) {
            redo.clear();
        }
    }
}
