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

package pt.up.fe.specs.clang.clavaparser.decl;

import pt.up.fe.specs.clang.ast.ClangNode;
import pt.up.fe.specs.clang.clavaparser.AClangNodeParser;
import pt.up.fe.specs.clang.clavaparser.ClangConverterTable;
import pt.up.fe.specs.clava.ast.decl.FunctionTemplateDecl;
import pt.up.fe.specs.clava.ast.decl.RedeclarableTemplateDecl;
import pt.up.fe.specs.util.stringparser.StringParser;

public class FunctionTemplateDeclParser extends AClangNodeParser<FunctionTemplateDecl> {

    public FunctionTemplateDeclParser(ClangConverterTable converter) {
        super(converter);
    }

    @Override
    protected FunctionTemplateDecl parse(ClangNode node, StringParser parser) {
        // Examples:
        //
        // line:100:10 Distance2

        RedeclarableTemplateDecl redeclarableTemplateDecl = new RedeclarableTemplateDeclParser(getConverter())
                .parse(node, parser);
        throw new RuntimeException("deprecated");
        // return ClavaNodeFactory.functionTemplateDecl(redeclarableTemplateDecl);
    }

}
