package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.*;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 06/06/15
 */
public class ABS_Evaluator extends AbstractUnaryBuiltInCallEvaluator {

    @Override
    protected EvaluationResult evaluate(Expression arg, SolutionMapping sm) {
        EvaluationResult eval = arg.evaluateAsNumeric(sm);
        if(eval.isError()) {
            return eval;
        }
        Literal literal = eval.asLiteral();
        if(literal.getDatatype().equals(Datatype.getXSDInteger())) {
            return EvaluationResult.getInteger(Math.abs(Integer.parseInt(literal.getLexicalForm())));
        }
        else if(literal.getDatatype().equals(Datatype.getXSDFloat())) {
            return EvaluationResult.getFloat(Math.abs(Float.parseFloat(literal.getLexicalForm())));
        }
        else if(literal.getDatatype().equals(Datatype.getXSDDouble())) {
            return EvaluationResult.getDouble(Math.abs(Double.parseDouble(literal.getLexicalForm())));
        }
        else if(literal.getDatatype().equals(Datatype.getXSDDecimal())) {
            return EvaluationResult.getDecimal(Math.abs(Double.parseDouble(literal.getLexicalForm())));
        }
        else {
            double value = eval.asNumeric();
            return EvaluationResult.getLong(Math.round(value));
        }
    }
}