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

package pt.up.fe.specs.clava.weaver.joinpoints;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ClavaNodes;
import pt.up.fe.specs.clava.ast.expr.UnaryOperator;
import pt.up.fe.specs.clava.ast.expr.enums.UnaryOperatorKind;
import pt.up.fe.specs.clava.weaver.CxxJoinpoints;
import pt.up.fe.specs.clava.weaver.abstracts.ACxxWeaverJoinPoint;
import pt.up.fe.specs.clava.weaver.abstracts.joinpoints.AExpression;
import pt.up.fe.specs.clava.weaver.abstracts.joinpoints.AJoinPoint;
import pt.up.fe.specs.clava.weaver.abstracts.joinpoints.AUnaryOp;

public class CxxUnaryOp extends AUnaryOp {

    // Hardcoding kind names, in order to avoid breaking LARA code that depends on these names when changing Clang
    // version
    private static final Map<UnaryOperatorKind, String> KIND_NAMES;
    static {
        KIND_NAMES = new HashMap<>();
        KIND_NAMES.put(UnaryOperatorKind.PostInc, "post_inc");
        KIND_NAMES.put(UnaryOperatorKind.PostDec, "post_dec");
        KIND_NAMES.put(UnaryOperatorKind.PreInc, "pre_inc");
        KIND_NAMES.put(UnaryOperatorKind.PreDec, "pre_dec");
        KIND_NAMES.put(UnaryOperatorKind.AddrOf, "addr_of");
        KIND_NAMES.put(UnaryOperatorKind.Deref, "deref");
        KIND_NAMES.put(UnaryOperatorKind.Plus, "plus");
        KIND_NAMES.put(UnaryOperatorKind.Minus, "minus");
        KIND_NAMES.put(UnaryOperatorKind.Not, "not");
        KIND_NAMES.put(UnaryOperatorKind.LNot, "l_not");
        KIND_NAMES.put(UnaryOperatorKind.Real, "real");
        KIND_NAMES.put(UnaryOperatorKind.Imag, "imag");
        KIND_NAMES.put(UnaryOperatorKind.Extension, "extension");
        KIND_NAMES.put(UnaryOperatorKind.Coawait, "cowait");
    }

    private final UnaryOperator unaryOp;
    private final ACxxWeaverJoinPoint parent;

    public CxxUnaryOp(UnaryOperator unaryOp, ACxxWeaverJoinPoint parent) {
        super(new CxxExpression(unaryOp, parent));
        this.unaryOp = unaryOp;
        this.parent = parent;
    }

    @Override
    public ACxxWeaverJoinPoint getParentImpl() {
        return parent;
    }

    @Override
    public ClavaNode getNode() {
        return unaryOp;
    }

    @Override
    public String getKindImpl() {
        String kindName = KIND_NAMES.get(unaryOp.getOp());
        if (kindName == null) {
            throw new RuntimeException("Not implemented for " + unaryOp.getOp());
        }

        return kindName;
    }

    @Override
    public AJoinPoint getOperandImpl() {
        return CxxJoinpoints.create(ClavaNodes.normalize(unaryOp.getSubExpr()), this);
    }

    @Override
    public Boolean getIsPointerDerefImpl() {
        return unaryOp.getOp() == UnaryOperatorKind.Deref;
    }

    @Override
    public List<? extends AExpression> selectOperand() {
        return Arrays.asList((AExpression) getOperandImpl());
    }

}
