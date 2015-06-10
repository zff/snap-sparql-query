package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.EvaluationResult;
import org.semanticweb.owlapi.sparql.api.Expression;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.api.Variable;

import java.util.List;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 10/06/15
 */
public class BOUND_Evaluator extends AbstractUnaryBuiltInCallEvaluator {

    @Override
    protected EvaluationResult evaluate(Expression arg, SolutionMapping sm) {
        if(!(arg instanceof Variable)) {
            return EvaluationResult.getError();
        }
        return EvaluationResult.getBoolean(sm.isMapped((Variable) arg));
    }
}