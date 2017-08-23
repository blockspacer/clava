//
// Created by JoaoBispo on 20/01/2017.
//

#include "ClangAstDumper.h"

#include "clang/AST/AST.h"

#include <iostream>
#include <sstream>

using namespace clang;


/*
 * DECLS PARTS
 */
void ClangAstDumper::dumpNumberTemplateParameters(const Decl *D, const TemplateParameterList *TPL) {
    int numberOfTemplateParameters = 0;
    if(TPL) {
        for (auto I = TPL->begin(), E = TPL->end(); I != E; ++I) {
            numberOfTemplateParameters++;
        }
    }

    llvm::errs() << DUMP_NUMBER_TEMPLATE_PARAMETERS << "\n";
    // Dump id
    llvm::errs() << D << "_" << id << "\n";
    // Dump number
    llvm::errs() << numberOfTemplateParameters << "\n";
}


/*
 * DECLS
 */

bool ClangAstDumper::dumpDecl(const Decl* declAddr) {
    if(seenDecls.count(declAddr) != 0) {
        return true;
    }

    // A StmtDumper is created for each context,
    // no need to use id to disambiguate
    seenDecls.insert(declAddr);

    std::ostringstream extendedId;
    extendedId << declAddr << "_" << id;

    // Dump location
    dumpSourceRange(extendedId.str(), declAddr->getLocStart(), declAddr->getLocEnd());

    return false;
}

void ClangAstDumper::VisitDecl(const Decl *D) {
    dumpDecl(D);
}

void ClangAstDumper::VisitVarDecl(const VarDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("VarDecl", D);
    if (D->hasInit()) {
        VisitStmtTop(D->getInit());
    }
}


void ClangAstDumper::VisitCXXRecordDecl(const CXXRecordDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("CXXRecordDecl", D);


    // Visit definition
    if(D->hasDefinition()) {
        VisitDeclTop(D->getDefinition());

        // If has bases, dump types of bases
        if(D->getNumBases() != 0) {
            llvm::errs() << "<CXXRecordDecl Bases Start>\n";
            // First address is id of CXXRecordDecl
            llvm::errs() << D << "_" << id << "\n";
            for (const auto &I : D->bases()) {
                llvm::errs() << I.getType().getTypePtr() << "_" << id << "\n";
            }
            llvm::errs() << "<CXXRecordDecl Bases End>\n";

        }

        // Visit constructors
        for (auto ctor : D->ctors()) {
            VisitDeclTop(ctor);
        }


        // Visit methods
        // This makes the program explode

        for (auto method : D->methods()) {
            VisitDeclTop(method);
        }

        // Visit fields
        for (auto field : D->fields()) {
            VisitDeclTop(field);
        }

    }


}

void VisitCXXMethodDeclBody(ClangAstDumper* dumper, const CXXMethodDecl *D);
void ClangAstDumper::VisitCXXMethodDecl(const CXXMethodDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("CXXMethodDecl", D);

    VisitCXXMethodDeclBody(this, D);

}

void VisitCXXMethodDeclBody(ClangAstDumper* dumper, const CXXMethodDecl *D) {
    if(D->hasBody()) {
        dumper->VisitStmtTop(D->getBody());
    }

    // Dump the corresponding CXXRecordDecl
    llvm::errs() << DUMP_CXX_METHOD_DECL_PARENT << "\n";
    llvm::errs() << dumper->getId(D) << "\n";
    llvm::errs() << dumper->getId(D->getParent()) << "\n";
}

void ClangAstDumper::VisitCXXConstructorDecl(const CXXConstructorDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("CXXConstructorDecl", D);

    VisitCXXMethodDeclBody(this, D);


    // Check if there are CXXCtorInitializers
    if(D->init_begin() != D->init_end()) {
        llvm::errs() << CXX_CTOR_INITIALIZER_BEGIN << "\n";

        // Dump address of decl
        llvm::errs() << getId(D) << "\n";

        // Dump initializers info
        for (CXXConstructorDecl::init_const_iterator I = D->init_begin(), E = D->init_end(); I != E; ++I) {
            dumpCXXCtorInitializer(*I);
        }

        llvm::errs() << CXX_CTOR_INITIALIZER_END << "\n";
    }




    // Store id if it has no name
    if(D->getDeclName().getAsString().length() == 0) {
        llvm::errs() << DUMP_NAMED_DECL_WITHOUT_NAME << "\n";
        llvm::errs() << getId(D) << "\n";
    }

}


void ClangAstDumper::VisitObjCImplementationDecl(const ObjCImplementationDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("ObjCImplementationDecl", D);

    // Check if there are CXXCtorInitializers
    if(D->init_begin() != D->init_end()) {
        llvm::errs() << CXX_CTOR_INITIALIZER_BEGIN << "\n";

        // Dump address of decl
        llvm::errs() << D << "_" << id << "\n";

        // Dump initializers info
        for (ObjCImplementationDecl::init_const_iterator I = D->init_begin(), E = D->init_end(); I != E; ++I) {
            dumpCXXCtorInitializer(*I);
        }

        llvm::errs() << CXX_CTOR_INITIALIZER_END << "\n";
    }


}

void ClangAstDumper::VisitTemplateDecl(const TemplateDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("TemplateDecl", D);

    dumpNumberTemplateParameters(D, D->getTemplateParameters());
}

void ClangAstDumper::VisitTemplateTypeParmDecl(const TemplateTypeParmDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("TemplateTypeParmDecl", D);
}

void ClangAstDumper::VisitNamespaceAliasDecl(const NamespaceAliasDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("NamespaceAliasDecl", D);

    // Dump nested namespace prefix
    llvm::errs() << DUMP_NAMESPACE_ALIAS_PREFIX << "\n";
    llvm::errs() << getId(D) << "\n";
    llvm::errs() << loc2str(D->getQualifierLoc().getBeginLoc(), D->getQualifierLoc().getEndLoc()) << "\n";

}

void ClangAstDumper::VisitFieldDecl(const FieldDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("FieldDecl", D);

    // Dump nested namespace prefix
    llvm::errs() << DUMP_FIELD_DECL_INFO << "\n";
    llvm::errs() << getId(D) << "\n";
    llvm::errs() << toBoolString(D->isBitField()) << "\n";
    llvm::errs() << toBoolString(D->getInClassInitializer() != nullptr) << "\n";

}

void ClangAstDumper::VisitParmVarDecl(const ParmVarDecl *D) {
    if(dumpDecl(D)) {
        return;
    }

    log("ParmVarDecl", D);

    if(D->hasInheritedDefaultArg()) {
        llvm::errs() << DUMP_PARM_VAR_DECL_HAS_INHERITED_DEFAULT_ARG << "\n";
        llvm::errs() << getId(D) << "\n";
    }

}


