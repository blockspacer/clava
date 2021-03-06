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

package pt.up.fe.specs.clang.clavaparser.stmt;

import java.util.List;

import com.google.common.base.Preconditions;

import pt.up.fe.specs.clang.ast.ClangNode;
import pt.up.fe.specs.clang.clavaparser.AClangNodeParser;
import pt.up.fe.specs.clang.clavaparser.ClangConverterTable;
import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ast.stmt.DefaultStmt;
import pt.up.fe.specs.clava.ast.stmt.Stmt;
import pt.up.fe.specs.util.stringparser.StringParser;

public class DefaultStmtParser extends AClangNodeParser<DefaultStmt> {

    public DefaultStmtParser(ClangConverterTable converter) {
        super(converter, false);
    }

    @Override
    public DefaultStmt parse(ClangNode node, StringParser parser) {

        List<ClavaNode> children = parseChildren(node);

        Preconditions.checkArgument(children.size() == 1, "Expected a single child:" + children);

        Stmt subStmt = toStmt(children.get(0));

        throw new RuntimeException("deprecated");
        // return ClavaNodeFactory.defaultStmt(info(node), subStmt);
    }

}
