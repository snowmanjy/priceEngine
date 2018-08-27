package com.test.app.ruleengine.rule;

import com.test.app.action.Action;
import com.test.app.ruleengine.condition.Condition;

public abstract class AbstractRule implements Rule{

    final Condition condition;

    final Action action;

    public AbstractRule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }
}
