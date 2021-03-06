package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.EvaluationResult;
import org.semanticweb.owlapi.sparql.api.Expression;
import org.semanticweb.owlapi.sparql.api.Literal;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.algebra.AlgebraEvaluationContext;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 16 Oct 2017
 */
public class FLOOR_Evaluator extends AbstractUnaryBuiltInCallEvaluator {

    @Override
    protected EvaluationResult evaluate(Expression arg, SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        EvaluationResult eval = arg.evaluate(sm, evaluationContext).asNumericOrElseError();
        if (eval.isError()) {
            return EvaluationResult.getError();
        }
        double numericValue = eval.asNumeric();
        double ceiling = Math.floor(numericValue);
        return new EvaluationResult(Literal.createDecimal(ceiling));
    }
}
