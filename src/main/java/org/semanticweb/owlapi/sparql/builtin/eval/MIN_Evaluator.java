package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.algebra.SolutionSequence;
import org.semanticweb.owlapi.sparql.api.EvaluationResult;
import org.semanticweb.owlapi.sparql.api.Expression;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.algebra.AlgebraEvaluationContext;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 23/06/15
 */
public class MIN_Evaluator implements BuiltInCallEvaluator, BuiltInAggregateCallEvaluator {

    @Override
    public EvaluationResult evaluateAsAggregate(List<Expression> args, SolutionSequence solutionSequence, AlgebraEvaluationContext evaluationContext) {
        if(args.size() != 1) {
            return EvaluationResult.getError();
        }
        if(solutionSequence.size() == 0) {
            return EvaluationResult.getError();
        }
        Expression arg = args.get(0);
        double min = Double.MAX_VALUE;
        int count = 0;
        for(SolutionMapping sm : solutionSequence.getSolutionMappings()) {
            EvaluationResult eval = arg.evaluate(sm, evaluationContext).asNumericOrElseError();
            if(!eval.isError()) {
                double numeric = eval.asNumeric();
                if(numeric < min) {
                    min = numeric;
                }
                count++;
            }
        }
        if(count == 0) {
            return EvaluationResult.getError();
        }
        else {
            return EvaluationResult.getDecimal(min);
        }

    }

    @Nonnull
    @Override
    public EvaluationResult evaluate(@Nonnull List<Expression> args, @Nonnull SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }
}
