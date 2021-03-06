package org.semanticweb.owlapi.sparql.api;


import java.util.Optional;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class AsymmetricObjectProperty extends ObjectPropertyCharacteristic {

    public AsymmetricObjectProperty(ObjectPropertyExpression property) {
        super(property);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    public <R, E extends Throwable> R accept(AxiomVisitor<R, E> visitor) throws E {
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
        if(!(obj instanceof AsymmetricObjectProperty)) {
            return false;
        }
        AsymmetricObjectProperty other = (AsymmetricObjectProperty) obj;
        return this.getProperty().equals(other.getProperty());
    }

    @Override
    public Optional<AsymmetricObjectProperty> bind(SolutionMapping sm) {
        Optional<? extends ObjectPropertyExpression> boundProperty = getProperty().bind(sm);
        if(!boundProperty.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new AsymmetricObjectProperty(boundProperty.get()));
    }

    @Override
    public OWLAxiom toOWLObject(OWLDataFactory df) {
        return df.getOWLAsymmetricObjectPropertyAxiom(
                getProperty().toOWLObject(df)
        );
    }
}
