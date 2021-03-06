package org.semanticweb.owlapi.sparql.api;

import java.util.Optional;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;

import java.util.Collection;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class FunctionalDataProperty implements Axiom, HasProperty<DataPropertyExpression> {

    private DataPropertyExpression dataPropertyExpression;

    public FunctionalDataProperty(DataPropertyExpression dataPropertyExpression) {
        this.dataPropertyExpression = dataPropertyExpression;
    }

    public DataPropertyExpression getProperty() {
        return dataPropertyExpression;
    }

    public <R, E extends Throwable> R accept(AxiomVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public int hashCode() {
        return getProperty().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof FunctionalDataProperty)) {
            return false;
        }
        FunctionalDataProperty other = (FunctionalDataProperty) obj;
        return this.getProperty().equals(other.getProperty());
    }

    @Override
    public void collectVariables(Collection<Variable> variables) {
        dataPropertyExpression.collectVariables(variables);
    }

    @Override
    public Optional<FunctionalDataProperty> bind(SolutionMapping sm) {
        Optional<? extends DataPropertyExpression> property = dataPropertyExpression.bind(sm);
        if(!property.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new FunctionalDataProperty(property.get()));
    }

    @Override
    public OWLAxiom toOWLObject(OWLDataFactory df) {
        return df.getOWLFunctionalDataPropertyAxiom(getProperty().toOWLObject(df));
    }
}
