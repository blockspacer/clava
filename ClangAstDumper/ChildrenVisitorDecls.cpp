//
// Created by JoaoBispo on 20/03/2018.
//

#include "ClangAstDumper.h"
#include "ClangNodes.h"
#include "ClavaConstants.h"
#include "ClangEnums.h"

#include <string>


const std::map<const std::string, clava::DeclNode > ClangAstDumper::DECL_CHILDREN_MAP = {
        {"CXXConstructorDecl", clava::DeclNode::CXX_METHOD_DECL},
        {"CXXConversionDecl", clava::DeclNode::CXX_METHOD_DECL},
        {"CXXDestructorDecl", clava::DeclNode::CXX_METHOD_DECL},
        {"CXXMethodDecl", clava::DeclNode::CXX_METHOD_DECL},
        {"CXXRecordDecl", clava::DeclNode::CXX_RECORD_DECL},
        {"FunctionDecl", clava::DeclNode::FUNCTION_DECL},
        {"VarDecl", clava::DeclNode::VAR_DECL},
        {"ParmVarDecl", clava::DeclNode::VAR_DECL},
        {"TypeDecl", clava::DeclNode::TYPE_DECL},
        {"EnumDecl", clava::DeclNode::TYPE_DECL}


};


void ClangAstDumper::visitChildren(const Decl* D) {
    // Get classname
    const std::string classname = clava::getClassName(D);

    // Get corresponding DeclNode
    clava::DeclNode declNode = DECL_CHILDREN_MAP.count(classname) == 1 ? DECL_CHILDREN_MAP.find(classname)->second :
                               clava::DeclNode::DECL;

    visitChildren(declNode, D);
}


void ClangAstDumper::visitChildren(clava::DeclNode declNode, const Decl* D) {

    std::vector<std::string> visitedChildren;

    switch(declNode) {
        case clava::DeclNode::DECL:
            VisitDeclChildren(D, visitedChildren); break;
        case clava::DeclNode::NAMED_DECL:
            VisitNamedDeclChildren(static_cast<const NamedDecl *>(D), visitedChildren); break;
        case clava::DeclNode::TYPE_DECL:
            VisitTypeDeclChildren(static_cast<const TypeDecl *>(D), visitedChildren); break;
        case clava::DeclNode::VALUE_DECL:
            VisitValueDeclChildren(static_cast<const ValueDecl *>(D), visitedChildren); break;
        case clava::DeclNode::FUNCTION_DECL:
            VisitFunctionDeclChildren(static_cast<const FunctionDecl *>(D), visitedChildren); break;
        case clava::DeclNode::CXX_METHOD_DECL:
            VisitCXXMethodDeclChildren(static_cast<const CXXMethodDecl *>(D), visitedChildren); break;
        case clava::DeclNode::CXX_RECORD_DECL:
            VisitCXXRecordDeclChildren(static_cast<const CXXRecordDecl *>(D), visitedChildren); break;
        case clava::DeclNode::VAR_DECL:
            VisitVarDeclChildren(static_cast<const VarDecl *>(D), visitedChildren); break;
//        case clava::DeclNode::PARM_VAR_DECL:
//            visitedChildren = VisitParmVarDeclChildren(static_cast<const ParmVarDecl *>(D)); break;
        default: throw std::invalid_argument("ChildrenVisitorDecls::visitChildren: Case not implemented, '"+clava::getName(declNode)+"'");
    }

    dumpVisitedChildren(D, visitedChildren);
}


void ClangAstDumper::VisitDeclChildren(const Decl *D, std::vector<std::string> &children) {
    // Visit attributes
    for (Decl::attr_iterator I = D->attr_begin(), E = D->attr_end(); I != E;
         ++I) {
        Attr* attr = *I;
        VisitAttrTop(attr);
        dumpTopLevelAttr(attr);
    }
}

void ClangAstDumper::VisitNamedDeclChildren(const NamedDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitDeclChildren(D, children);

    // Just visit underlying decl
    //VisitDeclTop(D->getUnderlyingDecl());
    //llvm::errs() << "VISITING " << clava::getId(D->getUnderlyingDecl(), id) << " -> " << clava::getClassName(D->getUnderlyingDecl()) << "\n";
    //llvm::errs() << "ORIGINAL " << clava::getId(D, id) << "\n";
}

void ClangAstDumper::VisitTypeDeclChildren(const TypeDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitNamedDeclChildren(D, children);

    // Visit type
    VisitTypeTop(D->getTypeForDecl());
    dumpType(D->getTypeForDecl());


}

void ClangAstDumper::VisitValueDeclChildren(const ValueDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitNamedDeclChildren(D, children);

    // Visit type
    VisitTypeTop(D->getType());
    dumpTopLevelType(D->getType());


}

void ClangAstDumper::VisitFunctionDeclChildren(const FunctionDecl *D, std::vector<std::string> &children) {

    // Hierarchy
    VisitValueDeclChildren(D, children);

    // Visit canonical and previous decls
    VisitDeclTop(D->getPreviousDecl());
    VisitDeclTop(D->getCanonicalDecl());

    // Visit template arguments
    auto templateSpecializationArgs = D->getTemplateSpecializationArgs();
    if(templateSpecializationArgs != nullptr) {
        for(auto templateArg : templateSpecializationArgs->asArray()) {
            switch(templateArg.getKind()) {
                case TemplateArgument::ArgKind::Type:
                    VisitTypeTop(templateArg.getAsType());
                    break;
                case TemplateArgument::ArgKind::Expression:
                    VisitStmtTop(templateArg.getAsExpr());
                    break;
                default: throw std::invalid_argument("ClangNodes::dump(TemplateArgument&): Case not implemented, '"+clava::TEMPLATE_ARG_KIND[templateArg.getKind()]+"'");
            }
        }
    }



    // Visit parameters
    for(auto param : D->parameters()) {
        VisitDeclTop(param);
        children.push_back(clava::getId(param, id));
    }
    /*
    for (auto I = D->param_begin(), E = D->param_end(); I != E; ++I) {
        llvm::errs() << "PARAM: " <<  getId(I) << "\n";
        llvm::errs() << "PARAM CLASS: " <<  clava::getClassName(I) << "\n";

        VisitDeclTop(*I);
        children.push_back(getId(I));
    }
     */

    // Visit decls in prototype scope
    for (ArrayRef<NamedDecl *>::iterator I = D->getDeclsInPrototypeScope().begin(),
                 E = D->getDeclsInPrototypeScope().end(); I != E; ++I) {
        VisitDeclTop(*I);
        children.push_back(clava::getId(*I, id));
    }

    // Visit body
    //if(D->hasBody()) {
    if (D->doesThisDeclarationHaveABody()) {
        //llvm::errs() << "BODY: " <<  getId(D->getBody()) << "\n";
        VisitStmtTop(D->getBody());
        children.push_back(clava::getId(D->getBody(), id));
    }

}


void ClangAstDumper::VisitCXXMethodDeclChildren(const CXXMethodDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitFunctionDeclChildren(D, children);


    // Visit record decl
    VisitDeclTop(D->getParent());

}



void ClangAstDumper::VisitCXXRecordDeclChildren(const CXXRecordDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitTypeDeclChildren(D, children);

    // Visit types in bases
     for (const auto &I : D->bases()) {
        VisitTypeTop(I.getType());
    }
}


/*
void ClangAstDumper::VisitCXXConstructorDeclChildren(const CXXConstructorDecl *D) {
    // Hierarchy
    VisitFunctionDeclChildren(D);


}
 */


void ClangAstDumper::VisitVarDeclChildren(const VarDecl *D, std::vector<std::string> &children) {
    // Hierarchy
    VisitValueDeclChildren(D, children);

    if (D->hasInit()) {
        VisitStmtTop(D->getInit());
        children.push_back(clava::getId(D->getInit(), id));
    }

}

/*
std::vector<std::string> ClangAstDumper::VisitParmVarDeclChildren(const ParmVarDecl *D) {

    // Hierarchy
    std::vector<std::string> children = VisitVarDeclChildren(D);
    //children.push_back(getId(D));

    return children;
}
 */