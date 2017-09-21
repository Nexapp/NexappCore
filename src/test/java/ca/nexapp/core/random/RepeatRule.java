package ca.nexapp.core.random;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RepeatRule implements TestRule {

    private static class RepeatStatement extends Statement {

        private final Statement statement;
        private final int repeat;

        public RepeatStatement(Statement statement, int repeat) {
            this.statement = statement;
            this.repeat = repeat;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < repeat; i++) {
                statement.evaluate();
            }
        }

    }

    @Override
    public Statement apply(Statement statement, Description description) {
        Repeat repeat = description.getAnnotation(Repeat.class);
        if (repeat == null) {
            return statement;
        }

        int times = repeat.value();
        return new RepeatStatement(statement, times);
    }

}
