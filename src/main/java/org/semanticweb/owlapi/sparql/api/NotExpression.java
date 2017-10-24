package org.semanticweb.owlapi.sparql.api;

import org.semanticweb.owlapi.sparql.algebra.EvaluationContext;

import java.util.Set;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class NotExpression implements Expression {

    private Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public Set<Variable> getVariables() {
        return expression.getVariables();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Expression(NOT ");
        sb.append(expression);
        sb.append(")");
        return sb.toString();
    }


    public EvaluationResult evaluate(SolutionMapping sm, EvaluationContext evaluationContext) {
        return evaluateAsEffectiveBooleanValue(sm, evaluationContext);
    }

    public boolean canEvaluateAsStringLiteral(SolutionMapping sm) {
        return false;
    }

    public EvaluationResult evaluateAsStringLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public boolean canEvaluateAsSimpleLiteral(SolutionMapping sm) {
        return false;
    }

    public EvaluationResult evaluateAsSimpleLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public boolean canEvaluateAsBoolean(SolutionMapping sm) {
        return true;
    }

    public EvaluationResult evaluateAsEffectiveBooleanValue(SolutionMapping sm, EvaluationContext evaluationContext) {
        EvaluationResult evalResult = expression.evaluateAsEffectiveBooleanValue(sm, evaluationContext);
        if(evalResult.isError()) {
            return evalResult;
        }
        return EvaluationResult.getBoolean(!evalResult.isTrue());
    }

    public boolean canEvaluateAsNumeric(SolutionMapping sm) {
        return false;
    }

    public EvaluationResult evaluateAsNumeric(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public boolean canEvaluateAsDateTime(SolutionMapping sm) {
        return false;
    }

    public EvaluationResult evaluateAsDateTime(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public boolean canEvaluateAsIRI(SolutionMapping sm) {
        return false;
    }





    @Override
    public int hashCode() {
        return expression.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof NotExpression)) {
            return false;
        }
        NotExpression other = (NotExpression) obj;
        return this.expression.equals(other.expression);
    }

    @Override
    public EvaluationResult evaluateAsLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }


    @Override
    public EvaluationResult evaluateAsIRI(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    @Override
    public <R, E extends Throwable, C> R accept(ExpressionVisitor<R, E, C> visitor, C context) throws E {
        return visitor.visit(this, context);
    }
}
