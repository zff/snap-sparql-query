package org.semanticweb.owlapi.sparql.api;

import java.util.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;

import java.util.Collection;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class IndividualVariable extends AbstractVariable implements AtomicIndividual {

    public IndividualVariable(String variableName) {
        super(variableName);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public RDFTerm getBound(IRI iri) {
        return new AtomicIRI(iri);
    }

    @Override
    public void collectVariables(Collection<Variable> variables) {
        variables.add(this);
    }

    @Override
    public <R, E extends Throwable, C> R accept(ExpressionVisitor<R, E, C> visitor, C context) throws E {
        return visitor.visit(this, context);
    }

    @Override
    public Optional<? extends AtomicIndividual> bind(SolutionMapping sm) {
        java.util.Optional<AtomicIRI> term = sm.getIRIForVariable(this);
        if(term.isPresent()) {
            return Optional.of(new NamedIndividual(term.get().getIRI()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public OWLIndividual toOWLObject(OWLDataFactory df) {
        throw new UnboundVariableTranslationException();
    }

    @Override
    public boolean isEntityVariable() {
        return true;
    }
}
