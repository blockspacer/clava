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

package pt.up.fe.specs.clava.ast.type.enums;

import pt.up.fe.specs.util.enums.EnumHelperWithValue;
import pt.up.fe.specs.util.lazy.Lazy;
import pt.up.fe.specs.util.providers.StringProvider;

/**
 * @deprecated
 * @author JoaoBispo
 *
 */
public enum UnaryTransformTypeKindOld implements StringProvider {

    ENUM_UNDERLYING_TYPE("underlying_type"),
    NONE;

    private final String string;

    private UnaryTransformTypeKindOld() {
        this.string = name().toLowerCase();
    }

    private UnaryTransformTypeKindOld(String string) {
        this.string = string;
    }

    private static final Lazy<EnumHelperWithValue<UnaryTransformTypeKindOld>> ENUM_HELPER = EnumHelperWithValue
            .newLazyHelperWithValue(
                    UnaryTransformTypeKindOld.class,
                    NONE);

    public static EnumHelperWithValue<UnaryTransformTypeKindOld> getHelper() {
        return ENUM_HELPER.get();
    }

    @Override
    public String getString() {
        return string;
    }
}
