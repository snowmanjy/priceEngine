package com.test.app.ruleengine.rule;

import com.test.app.action.Action;
import com.test.app.ruleengine.condition.Condition;

public class GenericRule extends AbstractRule {

    public GenericRule(Condition condition, Action action) {
        super(condition, action);
    }

    public void run() {
        if(condition.check()) {
            // TODO add action to the action queue
        }
    }
}
