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
public class SAMPLE_Evaluator implements BuiltInCallEvaluator, BuiltInAggregateCallEvaluator {

    @Override
    public EvaluationResult evaluateAsAggregate(List<Expression> args, SolutionSequence solutionSequence, AlgebraEvaluationContext evaluationContext) {
        if(args.size() != 1) {
            return EvaluationResult.getError();
        }
        if(solutionSequence.size() == 0) {
            return EvaluationResult.getError();
        }
        Expression arg = args.get(0);
        return arg.evaluate(solutionSequence.get(0), evaluationContext);
    }

    @Nonnull
    @Override
    public EvaluationResult evaluate(@Nonnull List<Expression> args, @Nonnull SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }
}
