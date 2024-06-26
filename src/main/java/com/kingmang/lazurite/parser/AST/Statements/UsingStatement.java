package com.kingmang.lazurite.parser.AST.Statements;

import com.kingmang.lazurite.exceptions.LzrException;

import com.kingmang.lazurite.parser.AST.Expressions.Expression;
import com.kingmang.lazurite.parser.AST.InterruptableNode;
import com.kingmang.lazurite.patterns.visitor.ResultVisitor;
import com.kingmang.lazurite.patterns.visitor.Visitor;
import com.kingmang.lazurite.core.Types;
import com.kingmang.lazurite.runtime.values.LzrValue;
import com.kingmang.lazurite.libraries.Library;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public final class UsingStatement extends InterruptableNode implements Statement {

    private static final String PACKAGE = "com.kingmang.lazurite.libraries.%s.%s";

    
    public final Expression expression;

    @Override
    public void execute() {
        super.interruptionCheck();
        final LzrValue value = expression.eval();
        try {
            loadModule(value.asString());
        }catch (Exception e){
            throw new LzrException("Type","Array or string required in 'using' statement, " +
                    "got " + Types.typeToString(value.type()) + " " + value);
        }
    }

    private void loadModule(String name) {
        try {
            final Library module = (Library) Class.forName(String.format(PACKAGE, name, name)).newInstance();
            module.init();
        } catch (Exception ex) {
            throw new LzrException("RuntimeException", "Unable to load module " + name + "\n" + ex);
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
        return "using " + expression;
    }
}
