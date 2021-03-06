/**
 * Copyright 2017 SPeCS.
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

package pt.up.fe.specs.clava.ast.type.data;

import java.util.Collections;
import java.util.List;

import pt.up.fe.specs.clava.ClavaCode;
import pt.up.fe.specs.clava.ast.type.enums.ArraySizeType;
import pt.up.fe.specs.clava.ast.type.enums.Qualifier;
import pt.up.fe.specs.clava.language.Standard;

/**
 * @deprecated
 * @author JoaoBispo
 *
 */
@Deprecated
public class ArrayTypeData {

    private final ArraySizeType arraySizeType;
    private final List<Qualifier> qualifiers;
    private final Standard standard;

    public ArrayTypeData(ArraySizeType arraySizeType, List<Qualifier> qualifiers, Standard standard) {
        this.arraySizeType = arraySizeType;
        this.qualifiers = qualifiers;
        this.standard = standard;
    }

    public ArrayTypeData() {
        this(ArraySizeType.NORMAL, Collections.emptyList(), Standard.CXX11);
    }

    public ArraySizeType getArraySizeType() {
        return arraySizeType;
    }

    public List<Qualifier> getQualifiers() {
        return qualifiers;
    }

    public Standard getStandard() {
        return standard;
    }

    public String getQualifiersCode() {
        return ClavaCode.getQualifiersCode(qualifiers, standard.isCxx());
    }
}
