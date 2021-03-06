package org.semanticweb.owlapi.sparql.api;

import com.google.common.base.Objects;
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
public class DataPropertyRange implements Axiom, HasProperty<DataPropertyExpression>, HasRange<DataRange> {

    private DataPropertyExpression property;

    private DataRange dataRange;

    public DataPropertyRange(DataPropertyExpression property, DataRange dataRange) {
        this.property = property;
        this.dataRange = dataRange;
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public <R, E extends Throwable> R accept(AxiomVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public DataPropertyExpression getProperty() {
        return property;
    }

    public DataRange getRange() {
        return dataRange;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(property, dataRange);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof DataPropertyRange)) {
            return false;
        }
        DataPropertyRange other = (DataPropertyRange) obj;
        return this.property.equals(other.property) && this.dataRange.equals(other.dataRange);
    }

    @Override
    public void collectVariables(Collection<Variable> variables) {
        property.collectVariables(variables);
        dataRange.collectVariables(variables);
    }

    @Override
    public Optional<DataPropertyRange> bind(SolutionMapping sm) {
        Optional<? extends DataPropertyExpression> property = this.property.bind(sm);
        if(!property.isPresent()) {
            return Optional.empty();
        }
        Optional<? extends DataRange> dataRange = this.dataRange.bind(sm);
        if(!dataRange.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(
                new DataPropertyRange(property.get(), dataRange.get())
        );
    }

    @Override
    public OWLAxiom toOWLObject(OWLDataFactory df) {
        return df.getOWLDataPropertyRangeAxiom(
                getProperty().toOWLObject(df),
                getRange().toOWLObject(df)
        );
    }
}
