package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.EvaluationResult;
import org.semanticweb.owlapi.sparql.api.Expression;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.algebra.AlgebraEvaluationContext;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 10/03/15
 */
public class LANGMATCHES_Evaluator implements BuiltInCallEvaluator {

    @Nonnull
    @Override
    public EvaluationResult evaluate(@Nonnull List<Expression> args, @Nonnull SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        if(args.size() != 2) {
            return EvaluationResult.getError();
        }
        EvaluationResult result0 = args.get(0).evaluate(sm, evaluationContext).asLiteralOrElseError();
        if(result0.isError()) {
            return result0;
        }
        EvaluationResult result1 = args.get(1).evaluate(sm, evaluationContext).asStringLiteralOrElseError();
        if(result1.isError()) {
            return result1;
        }
        return EvaluationResult.getBoolean(result0.asLiteral().getLang().equalsIgnoreCase(result1.asSimpleLiteral()));
    }
}
