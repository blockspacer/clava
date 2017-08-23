//
// Created by JoaoBispo on 20/01/2017.
//

#include "ClangAstDumper.h"

#include "clang/AST/AST.h"

#include <iostream>
#include <sstream>

using namespace clang;

/*
 * STMTS
 */

bool ClangAstDumper::dumpStmt(const Stmt* stmtAddr) {
    if(seenStmts.count(stmtAddr) != 0) {
        return true;
    }

    // A StmtDumper is created for each context,
    // no need to use id to disambiguate
    seenStmts.insert(stmtAddr);

    std::ostringstream extendedId;
    extendedId << stmtAddr << "_" << id;

    // Dump location
    dumpSourceRange(extendedId.str(), stmtAddr->getLocStart(), stmtAddr->getLocEnd());

    return false;
}

void ClangAstDumper::VisitStmt(const Stmt *Node) {
    dumpStmt(Node);
}


void ClangAstDumper::VisitDeclStmt(const DeclStmt *Node) {
    if(dumpStmt(Node)) {
        return;
    }


    log("DeclStmt", Node);
    for (DeclStmt::const_decl_iterator I = Node->decl_begin(), E = Node->decl_end(); I != E; ++I) {
        VisitDeclTop(*I);
    }

}
void ClangAstDumper::VisitCXXForRangeStmt(const CXXForRangeStmt *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("CXXForRangeStmt", Node);
    VisitStmtTop(Node->getRangeStmt());
    VisitStmtTop(Node->getBeginEndStmt());
    VisitStmtTop(Node->getCond());
    VisitStmtTop(Node->getInc());
    VisitStmtTop(Node->getBody());
}

void ClangAstDumper::VisitCompoundStmt(const CompoundStmt *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("CompoundStmt", Node);
    for (auto &Arg : Node->body()) {
        VisitStmtTop(Arg);
    }
}

void ClangAstDumper::VisitForStmt(const ForStmt *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("ForStmt", Node);
    if(Node->getInit() != nullptr) {
        VisitStmtTop(Node->getInit());
    }

    if(Node->getCond() != nullptr) {
        VisitStmtTop(Node->getCond());
    }

    if(Node->getInc() != nullptr) {
        VisitStmtTop(Node->getInc());
    }

    if(Node->getConditionVariable()!= nullptr) {
        VisitDeclTop(Node->getConditionVariable());
    }

    if(Node->getBody() != nullptr) {
        VisitStmtTop(Node->getBody());
    }




}

// EXPR

void ClangAstDumper::VisitCXXConstructExpr(const CXXConstructExpr *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("CXXConstructExpr", Node);

        const Type* constructorType = Node->getConstructor()->getType().getTypePtr();
        llvm::errs() << "CONSTRUCTOR_TYPE\n";
        llvm::errs() << Node << "_" << id << "->" << constructorType << "_" << id << "\n";
        VisitTypeTop(Node->getConstructor()->getType().getTypePtr());

}

void ClangAstDumper::VisitUnaryExprOrTypeTraitExpr(const UnaryExprOrTypeTraitExpr *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("UnaryExprOrTypeTraitExpr", Node);

    // If argument type, dump mapping between node and type
    if (Node->isArgumentType()) {
        // Dump type
        VisitTypeTop(Node->getArgumentType().getTypePtr());

        llvm::errs() << "<UnaryExprOrTypeTraitExpr ArgType>\n";
        // First address is id of UnaryExprOrTypeTraitExpr, second is id of type of argument
        llvm::errs() << Node << "_" << id << "->" << Node->getArgumentType().getTypePtr() << "_" << id << "\n";
    }

}


void ClangAstDumper::VisitDeclRefExpr(const DeclRefExpr *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("DeclRefExpr", Node);

    if(Node->hasExplicitTemplateArgs()) {
        llvm::errs() << DUMP_TEMPLATE_ARGS << "\n";

        // Node id
        llvm::errs() << getId(Node) << "\n";

        // Number of template args
        llvm::errs() << Node->getNumTemplateArgs() << "\n";

        auto templateArgs = Node->getTemplateArgs();
        for (unsigned i = 0; i < Node->getNumTemplateArgs(); ++i) {
            auto templateArg = templateArgs + i;

            llvm::errs() << loc2str(templateArg->getSourceRange().getBegin(), templateArg->getSourceRange().getEnd()) << "\n";
        }

    }
}

void ClangAstDumper::VisitOffsetOfExpr(const OffsetOfExpr *Node) {
    if(dumpStmt(Node)) {
        return;
    }

    log("OffsetOfExpr", Node);

    // Dump offset set
    const Type* offsetType = Node->getTypeSourceInfo()->getType().getTypePtr();
    dumpType(offsetType);

    llvm::errs() << DUMP_OFFSET_OF_INFO<< "\n";

    // Node id
    llvm::errs() << getId(Node) << "\n";



    // Offset type
    llvm::errs() << getId(offsetType) << "\n";

    // Number of expressions
    llvm::errs() << Node->getNumExpressions() << "\n";

    // Number of components
    int numComp = Node->getNumComponents();
    llvm::errs() << numComp << "\n";

    for(int i=0; i<numComp; i++) {

        llvm::errs() << Node->getComponent(i).getKind() << "\n";

        switch(Node->getComponent(i).getKind()) {
            case OffsetOfNode::Array:
                llvm::errs() << Node->getComponent(i).getArrayExprIndex() << "\n";
                break;
            case OffsetOfNode::Field:
                llvm::errs() << Node->getComponent(i).getFieldName()->getName() << "\n";
                break;
            case OffsetOfNode::Identifier:
                //llvm::errs() << "OFFSET FIELD NAME:" << Node->getComponent(i).getFieldName()->getName() << "\n";
                //break;
            case OffsetOfNode::Base:
                //llvm::errs() << "OFFSET BASE TYPE START\n";
                //Node->getComponent(i).getBase()->getType().dump();
                //llvm::errs() << "OFFSET BASE TYPE END\n";
                //break;
            default:
                // Do nothing, cases not yet implemented
                break;
        }




    }

}

