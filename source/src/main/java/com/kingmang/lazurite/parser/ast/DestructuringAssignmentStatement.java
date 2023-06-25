package com.kingmang.lazurite.parser.ast;

import com.kingmang.lazurite.runtime.ArrayValue;
import com.kingmang.lazurite.runtime.MapValue;
import com.kingmang.lazurite.lib.Types;
import com.kingmang.lazurite.runtime.Value;
import com.kingmang.lazurite.runtime.Variables;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public final class DestructuringAssignmentStatement extends InterruptableNode implements Statement {
    
    public final List<String> variables;
    public final Expression containerExpression;

    public DestructuringAssignmentStatement(List<String> arguments, Expression container) {
        this.variables = arguments;
        this.containerExpression = container;
    }
    
    @Override
    public void execute() {
        super.interruptionCheck();
        final Value container = containerExpression.eval();
        switch (container.type()) {
            case Types.ARRAY:
                execute((ArrayValue) container);
                break;
            case Types.MAP:
                execute((MapValue) container);
                break;
        }
    }
    
    private void execute(ArrayValue array) {
        final int size = variables.size();
        for (int i = 0; i < size; i++) {
            final String variable = variables.get(i);
            if (variable != null) {
                Variables.define(variable, array.get(i));
            }
        }
    }
    private void execute(MapValue map) {
        int i = 0;
        for (Map.Entry<Value, Value> entry : map) {
            final String variable = variables.get(i);
            if (variable != null) {
                Variables.define(variable, new ArrayValue(
                        new Value[] { entry.getKey(), entry.getValue() }
                ));
            }
            i++;
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <R, T> R accept(ResultVisitor<R, T> visitor, T t) {
        return visitor.visit(this, t);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("extract(");
        final Iterator<String> it = variables.iterator();
        if (it.hasNext()) {
            String variable = it.next();
            sb.append(variable == null ? "" : variable);
            while (it.hasNext()) {
                variable = it.next();
                sb.append(", ").append(variable == null ? "" : variable);
            }
        }
        sb.append(") = ").append(containerExpression);
        return sb.toString();
    }
}