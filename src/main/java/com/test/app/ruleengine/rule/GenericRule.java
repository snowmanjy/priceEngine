package com.test.app.ruleengine.rule;

import com.test.app.action.ActionHandler;
import com.test.app.ruleengine.condition.Condition;

public class GenericRule extends AbstractRule {

    public GenericRule(Condition condition, ActionHandler actionHandler) {
        super(condition, actionHandler);
    }

    public void run() {
        if(condition.match()) {
            // TODO add actionHandler to the actionHandler queue
        }
    }
}
