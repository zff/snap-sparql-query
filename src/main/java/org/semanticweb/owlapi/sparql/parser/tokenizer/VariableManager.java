/*
 * This file is part of the OWL API.
 *  
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *  
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *  
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, The University of Manchester
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semanticweb.owlapi.sparql.parser.tokenizer;

import java.util.Optional;
import org.semanticweb.owlapi.sparql.api.PrimitiveType;
import org.semanticweb.owlapi.sparql.api.UntypedVariable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 16/03/2012
 */
public class VariableManager {

    private final Set<UntypedVariable> variableNames = new LinkedHashSet<>();

    private final Deque<VariableTypeManager> variableTypeManagerStack = new ArrayDeque<>();

    public VariableManager() {
        variableTypeManagerStack.push(new VariableTypeManager());
    }

    public void pushVariableTypeScope() {
        variableTypeManagerStack.push(new VariableTypeManager());
    }

    public void popVariableTypeScope() {
        variableTypeManagerStack.pop();
    }


    public void registerVariable(UntypedVariable variable) {
        variableNames.add(variable);
    }

    public void registerVariable(UntypedVariable variable, PrimitiveType type) {
        variableNames.add(variable);
        variableTypeManagerStack.peek().setVariableType(variable, type);
    }

    public Set<UntypedVariable> getVariables() {
        return new LinkedHashSet<>(variableNames);
    }

    public Optional<PrimitiveType> getVariableType(UntypedVariable variable) {
        VariableTypeManager typeManager = variableTypeManagerStack.peek();
        return typeManager.getVariableType(variable);
    }

    public Optional<PrimitiveType> getVariableTypeIndirect(UntypedVariable variable) {
        for(VariableTypeManager variableTypeManager : variableTypeManagerStack) {
            Optional<PrimitiveType> variableType = variableTypeManager.getVariableType(variable);
            if(variableType.isPresent()) {
                return variableType;
            }
        }
        return Optional.empty();
    }
}
