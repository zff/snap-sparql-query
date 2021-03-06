package org.semanticweb.owlapi.sparql.algebra;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.sparql.api.SolutionMapping;
import org.semanticweb.owlapi.sparql.api.Variable;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 09/06/15
 */
public class SolutionSequence {

    private static final SolutionSequence EMPTY_SOLUTION_SEQUENCE = new SolutionSequence(Collections.<Variable>emptyList(), ImmutableList.<SolutionMapping>of());

    private List<Variable> variableList;

    private ImmutableList<SolutionMapping> solutionMappings;

    public SolutionSequence(List<Variable> variableList, ImmutableList<SolutionMapping> solutionMappings) {
        this.variableList = variableList;
        this.solutionMappings = solutionMappings;
    }

    public static SolutionSequence getEmptySolutionSequence() {
        return EMPTY_SOLUTION_SEQUENCE;
    }

    public List<Variable> getVariableList() {
        return variableList;
    }

    public ImmutableList<SolutionMapping> getSolutionMappings() {
        return solutionMappings;
    }

    public int size() {
        return solutionMappings.size();
    }

    public SolutionMapping get(int index) {
        return solutionMappings.get(index);
    }


    @Override
    public String toString() {
        return toStringHelper("SolutionSequence")
                .addValue(variableList)
                .addValue(solutionMappings)
                .toString();
    }

}
