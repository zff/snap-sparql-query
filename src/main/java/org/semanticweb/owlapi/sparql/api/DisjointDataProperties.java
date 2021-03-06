package org.semanticweb.owlapi.sparql.api;

import java.util.Optional;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObject;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class DisjointDataProperties extends NaryDataPropertyAxiom implements Axiom {

    public DisjointDataProperties(DataPropertyExpression left, DataPropertyExpression right) {
        super(left, right);
    }

    public DisjointDataProperties(Set<DataPropertyExpression> propertyExpressions) {
        super(propertyExpressions);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public <R, E extends Throwable> R accept(AxiomVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public int hashCode() {
        return getDataProperties().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof DisjointDataProperties)) {
            return false;
        }
        DisjointDataProperties other = (DisjointDataProperties) obj;
        return this.getDataProperties().equals(other.getDataProperties());
    }

    @Override
    public Optional<DisjointDataProperties> bind(SolutionMapping sm) {
        Optional<Set<DataPropertyExpression>> boundDataProperties = getBoundDataProperties(sm);
        if(!boundDataProperties.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new DisjointDataProperties(boundDataProperties.get()));
    }

    @Override
    public OWLDisjointDataPropertiesAxiom toOWLObject(OWLDataFactory df) {
        return df.getOWLDisjointDataPropertiesAxiom(
                getDataProperties().stream()
                        .map(p -> p.toOWLObject(df))
                        .collect(Collectors.toSet())
        );
    }
}
