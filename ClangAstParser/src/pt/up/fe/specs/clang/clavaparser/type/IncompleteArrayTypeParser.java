/**
 * Copyright 2016 SPeCS.
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

package pt.up.fe.specs.clang.clavaparser.type;

import java.util.Arrays;
import java.util.List;

import org.suikasoft.jOptions.Interfaces.DataStore;

import pt.up.fe.specs.clang.ast.ClangNode;
import pt.up.fe.specs.clang.clavaparser.AClangNodeParser;
import pt.up.fe.specs.clang.clavaparser.ClangConverterTable;
import pt.up.fe.specs.clang.clavaparser.utils.ClangDataParsers;
import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ast.LegacyToDataStore;
import pt.up.fe.specs.clava.ast.type.IncompleteArrayType;
import pt.up.fe.specs.clava.ast.type.Type;
import pt.up.fe.specs.clava.ast.type.data.ArrayTypeData;
import pt.up.fe.specs.clava.ast.type.data.TypeData;
import pt.up.fe.specs.util.stringparser.StringParser;

public class IncompleteArrayTypeParser extends AClangNodeParser<IncompleteArrayType> {

    public IncompleteArrayTypeParser(ClangConverterTable converter) {
        super(converter);
    }

    @Override
    protected IncompleteArrayType parse(ClangNode node, StringParser parser) {
        // Examples:
        //
        // 'char *[]'

        TypeData typeData = parser.apply(ClangDataParsers::parseType);
        ArrayTypeData arrayTypeData = parser.apply(ClangDataParsers::parseArrayType, getStandard());

        List<ClavaNode> children = parseChildren(node);
        checkNumChildren(children, 1);

        Type elementType = toType(children.get(0));

        // return ClavaNodeFactory.incompleteArrayType(arrayTypeData, typeData, node.getInfo(), elementType);
        DataStore data = new LegacyToDataStore()
                .setArrayType(arrayTypeData)
                .setType(typeData)
                .setNodeInfo(node.getInfo())
                .getData();

        return new IncompleteArrayType(data, Arrays.asList(elementType));
    }

}
