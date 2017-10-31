package org.semanticweb.owlapi.sparql.api;

import org.semanticweb.owlapi.sparql.algebra.AlgebraEvaluationContext;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public abstract class AbstractVariable extends Variable {


//    private final VariableNamePrefix prefix;
//
//    private final String variableName;

    public AbstractVariable(String variableName) {
        super(variableName);
//        checkNotNull(variableName);
//        if(variableName.startsWith(VariableNamePrefix.QUESTION_MARK.getPrefix())) {
//            prefix = VariableNamePrefix.QUESTION_MARK;
//            this.variableName = variableName.substring(1);
//        }
//        else if(variableName.startsWith(VariableNamePrefix.DOLLAR.getPrefix())) {
//            prefix = VariableNamePrefix.DOLLAR;
//            this.variableName = variableName.substring(1);
//        }
//        else {
//            prefix = VariableNamePrefix.getDefault();
//            this.variableName = variableName;
//        }
    }


    @Override
    public boolean isSameRDFTermAs(Term term) {
        if(term == this) {
            return true;
        }
        if(!(term instanceof AbstractVariable)) {
            return false;
        }
        AbstractVariable other = (AbstractVariable) term;
        return this.getName().equals(other.getName());
    }
//    public Set<Variable> getVariables() {
//        return Collections.<Variable>singleton(this);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Expression(");
//        sb.append(getClass().getSimpleName());
//        sb.append(" ?");
//        sb.append(variableName);
//        sb.append(")");
//        return sb.toString();
//    }
//
//    public String getIdentifier() {
//        return variableName;
//    }
//
//    public String getName() {
//        return variableName;
//    }
//
//    public VariableNamePrefix getVariableNamePrefix() {
//        return prefix;
//    }

    public AnnotationSubject toAnnotationSubject() {
        return new IRIVariable(getName());
    }

    public EvaluationResult evaluate(SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        Optional<RDFTerm> term = sm.getTermForVariable(this);
        if(!term.isPresent()) {
            return EvaluationResult.getError();
        }
        return term.get().evaluate(sm, evaluationContext);
    }

//    public boolean canEvaluateAsBoolean(SolutionMapping sm) {
//        return sm.isMapped(this) && sm.getTermForVariable(this).canEvaluateAsBoolean(sm);
//    }

    public EvaluationResult evaluateAsEffectiveBooleanValue(SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        Optional<RDFTerm> term = sm.getTermForVariable(this);
        if(!term.isPresent()) {
            return EvaluationResult.getError();
        }
        return term.get().evaluateAsEffectiveBooleanValue(sm, evaluationContext);
    }

    public EvaluationResult evaluateAsSimpleLiteral(SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        Optional<RDFTerm> term = sm.getTermForVariable(this);
        if(!term.isPresent()) {
            return EvaluationResult.getError();
        }
        return term.get().evaluateAsSimpleLiteral(sm, evaluationContext);
    }

    @Override
    public EvaluationResult evaluateAsLiteral(SolutionMapping sm, AlgebraEvaluationContext evaluationContext) {
        Optional<RDFTerm> term = sm.getTermForVariable(this);
        if(!term.isPresent()) {
            return EvaluationResult.getError();
        }
        return term.get().evaluateAsLiteral(sm, evaluationContext);
    }

    @Nonnull
    @Override
    public final java.util.Optional<Variable> asVariable() {
        return java.util.Optional.of(this);
    }
}
