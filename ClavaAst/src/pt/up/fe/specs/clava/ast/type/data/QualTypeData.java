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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pt.up.fe.specs.clava.ClavaCode;
import pt.up.fe.specs.clava.ast.type.enums.AddressSpaceQualifier;
import pt.up.fe.specs.clava.ast.type.enums.C99Qualifier;
import pt.up.fe.specs.clava.ast.type.enums.Qualifier;
import pt.up.fe.specs.clava.language.Standard;

public class QualTypeData {

    private final AddressSpaceQualifier addressSpaceQualifier;
    private List<Qualifier> qualifiers;
    private final Standard standard;

    public QualTypeData(AddressSpaceQualifier addressSpaceQualifier, List<Qualifier> qualifiers, Standard standard) {
        this.addressSpaceQualifier = addressSpaceQualifier;
        this.qualifiers = qualifiers;
        this.standard = standard;
    }

    public AddressSpaceQualifier getAddressSpaceQualifier() {
        return addressSpaceQualifier;
    }

    public List<Qualifier> getQualifiers() {
        return qualifiers;
    }

    // public Standard getStandard() {
    // return standard;
    // }

    public String getQualifiersCode() {
        return ClavaCode.getQualifiersCode(qualifiers, standard.isCxx());
    }

    public List<C99Qualifier> getC99Qualifiers() {
        return getQualifiers().stream()
                .filter(Qualifier::isC99Qualifier)
                .map(Qualifier::toC99Qualifier)
                .collect(Collectors.toList());
    }

    public void setQualifiers(List<Qualifier> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public QualTypeData copy() {
        return new QualTypeData(addressSpaceQualifier, new ArrayList<>(qualifiers), standard);
    }
}
