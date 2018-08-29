package com.test.app.ruleengine.rule;

import com.test.app.action.ActionHandler;
import com.test.app.ruleengine.condition.Condition;

public abstract class AbstractRule implements Rule{

    final Condition condition;

    final ActionHandler actionHandler;

    public AbstractRule(Condition condition, ActionHandler actionHandler) {
        this.condition = condition;
        this.actionHandler = actionHandler;
    }
}
