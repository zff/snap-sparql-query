package org.semanticweb.owlapi.sparql.api;

import java.util.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.sparql.algebra.EvaluationContext;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class AtomicIRI implements Atomic, HasIRI, AnnotationSubject, AnnotationValue, RDFTerm, HasAsRDFTerm {

    private final IRI iri;

    public AtomicIRI(IRI iri) {
        this.iri = checkNotNull(iri);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public Set<Variable> getVariables() {
        return Collections.emptySet();
    }

    public IRI getIRI() {
        return iri;
    }

    public String getIdentifier() {
        return iri.toString();
    }

    public EvaluationResult evaluate(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getResult(this);
    }

    public EvaluationResult evaluateAsStringLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getResult(Literal.createString(iri.toString()));
    }

    public EvaluationResult evaluateAsEffectiveBooleanValue(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public EvaluationResult evaluateAsNumeric(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public EvaluationResult evaluateAsDateTime(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getError();
    }

    public EvaluationResult evaluateAsSimpleLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getSimpleLiteral(iri.toString());
    }

    @Override
    public int hashCode() {
        return iri.hashCode();
    }

    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof AtomicIRI)) {
            return false;
        }
        AtomicIRI other = (AtomicIRI) obj;
        return this.iri.equals(other.iri);
    }

    @Override
    public boolean isSameRDFTermAs(Term term) {
        if(term == this) {
            return true;
        }
        if(!(term instanceof HasIRI)) {
            return false;
        }
        HasIRI other = (HasIRI) term;
        return ((HasIRI) term).getIRI().equals(iri);
    }

    public boolean isLiteral() {
        return false;
    }

    public boolean isEntityIRI() {
        return false;
    }

    public boolean isUntypedIRI() {
        return true;
    }

    @Override
    public RDFTerm asRDFTerm() {
        return this;
    }

    @Override
    public EvaluationResult evaluateAsLiteral(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getSimpleLiteral(iri.toString());
    }

    @Override
    public EvaluationResult evaluateAsIRI(SolutionMapping sm, EvaluationContext evaluationContext) {
        return EvaluationResult.getResult(this);
    }

    @Override
    public void collectVariables(Collection<Variable> variables) {

    }


    @Override
    public String toString() {
        return toStringHelper("AtomicIRI")
                .addValue(iri)
                .toString();
    }

    @Override
    public <R, E extends Throwable, C> R accept(ExpressionVisitor<R, E, C> visitor, C context) throws E {
        return visitor.visit(this, context);
    }

    @Override
    public Optional<AtomicIRI> bind(SolutionMapping sm) {
        return Optional.of(this);
    }

    @Override
    public IRI toOWLObject(OWLDataFactory df) {
        return getIRI();
    }

    @Nonnull
    @Override
    public java.util.Optional<Variable> asVariable() {
        return java.util.Optional.empty();
    }
}
