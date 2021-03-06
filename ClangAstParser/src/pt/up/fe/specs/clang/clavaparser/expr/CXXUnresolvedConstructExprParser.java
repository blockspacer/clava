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

package pt.up.fe.specs.clang.clavaparser.expr;

import java.util.List;

import com.google.common.base.Preconditions;

import pt.up.fe.specs.clang.ast.ClangNode;
import pt.up.fe.specs.clang.clavaparser.AClangNodeParser;
import pt.up.fe.specs.clang.clavaparser.ClangConverterTable;
import pt.up.fe.specs.clang.clavaparser.utils.ClangDataParsers;
import pt.up.fe.specs.clang.streamparser.StreamKeys;
import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ast.expr.CXXUnresolvedConstructExpr;
import pt.up.fe.specs.clava.ast.expr.Expr;
import pt.up.fe.specs.clava.ast.expr.data.ExprData;
import pt.up.fe.specs.clava.ast.type.Type;
import pt.up.fe.specs.util.stringparser.StringParser;

public class CXXUnresolvedConstructExprParser extends AClangNodeParser<CXXUnresolvedConstructExpr> {

    public CXXUnresolvedConstructExprParser(ClangConverterTable converter) {
        super(converter);
    }

    @Override
    protected CXXUnresolvedConstructExpr parse(ClangNode node, StringParser parser) {
        // Examples:
        //
        // 'std::logic_error':'class std::logic_error'

        ExprData exprData = parser.apply(ClangDataParsers::parseExpr, node, getTypesMap());

        String typeAsWrittenAddr = getStdErr().get(StreamKeys.TYPE_AS_WRITTEN).get(node.getExtendedId());
        Type typeAsWritten = getOriginalTypes().get(typeAsWrittenAddr);
        Preconditions.checkNotNull(typeAsWritten);

        List<ClavaNode> children = parseChildren(node);
        List<Expr> arguments = toExpr(children);
        throw new RuntimeException("deprecated");
        // return ClavaNodeFactory.cxxUnresolvedConstructExpr(typeAsWritten, exprData, node.getInfo(), arguments);
    }

}
