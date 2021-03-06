package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.EvaluationResult;
import org.semanticweb.owlapi.sparql.api.Expression;
import org.semanticweb.owlapi.sparql.api.Literal;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.algebra.AlgebraEvaluationContext;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 10/03/15
 */
public class LANG_Evaluator extends AbstractUnaryBuiltInCallEvaluator {

    @Override
    protected EvaluationResult evaluate(Expression arg, SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        EvaluationResult result = arg.evaluate(sm, evaluationContext).asLiteralOrElseError();
        if(result.isError()) {
            return result;
        }
        Literal literal = result.asLiteral();
        if(literal.isRDFPlainLiteral()) {
            String lang = literal.getLang();
            return EvaluationResult.getResult(Literal.createRDFPlainLiteralNoLang(lang));
        }
        else {
            return EvaluationResult.getError();
        }
    }
}
