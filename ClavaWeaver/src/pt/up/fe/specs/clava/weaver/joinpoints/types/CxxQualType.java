/**
 * Copyright 2018 SPeCS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.clava.weaver.joinpoints.types;

import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ast.type.QualType;
import pt.up.fe.specs.clava.weaver.CxxJoinpoints;
import pt.up.fe.specs.clava.weaver.abstracts.ACxxWeaverJoinPoint;
import pt.up.fe.specs.clava.weaver.abstracts.joinpoints.AQualType;
import pt.up.fe.specs.clava.weaver.abstracts.joinpoints.AType;

public class CxxQualType extends AQualType {

    private final QualType qualType;
    private final ACxxWeaverJoinPoint parent;

    public CxxQualType(QualType qualType, ACxxWeaverJoinPoint parent) {
        super(new CxxType(qualType, parent));

        this.qualType = qualType;
        this.parent = parent;
    }

    @Override
    public ACxxWeaverJoinPoint getParentImpl() {
        return parent;
    }

    @Override
    public ClavaNode getNode() {
        return qualType;
    }

    @Override
    public String[] getQualifiersArrayImpl() {
        return qualType.getQualifierStrings().toArray(new String[0]);
    }

    @Override
    public AType getUnqualifiedTypeImpl() {
        return CxxJoinpoints.create(qualType.getUnqualifiedType(), this, AType.class);
    }
}
