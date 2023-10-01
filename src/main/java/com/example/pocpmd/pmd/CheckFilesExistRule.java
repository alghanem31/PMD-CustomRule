package com.example.pocpmd.pmd;

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.io.File;


public class CheckFilesExistRule extends AbstractJavaRule {

    private static final String HELP_PATH = "/Users/alghanem/Documents/Yousef Repository/POC-Pmd/HELP.md";
    private static final String GITIGNORE_PATH = "/Users/alghanem/Documents/Yousef Repository/POC-Pmd/.gitignore";

    @Override
    public Object visit(final ASTMethodDeclaration node, final Object data) {
        File helpFile = new File(HELP_PATH);
        if (!helpFile.exists()) {
            addViolationWithMessage(data, node, "ReadMe.md File Does not exist");
        }

        File gitignoreFile = new File(GITIGNORE_PATH);
        if (!gitignoreFile.exists()) {
            addViolationWithMessage(data, node, "Gitignore File Does not exist");
        }
        return super.visit(node, data);
    }
}
