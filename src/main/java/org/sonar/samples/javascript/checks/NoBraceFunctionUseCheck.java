/*
 * SonarQube JavaScript Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.javascript.checks;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.javascript.tree.KindSet;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.declaration.FunctionDeclarationTree;
import org.sonar.plugins.javascript.api.tree.declaration.FunctionTree;
import org.sonar.plugins.javascript.api.tree.expression.ArgumentListTree;
import org.sonar.plugins.javascript.api.tree.expression.ArrowFunctionTree;
import org.sonar.plugins.javascript.api.tree.expression.CallExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.DotMemberExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.ExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.IdentifierTree;
import org.sonar.plugins.javascript.api.tree.statement.IfStatementTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.plugins.javascript.api.visitors.SubscriptionVisitorCheck;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * Example of a check extending {@link SubscriptionVisitorCheck}.
 * 
 * We define the kinds of the nodes that we subscribe to in
 * {@link #nodesToVisit()}. We can then override visitNode or leaveNode: these
 * methods will be called for all nodes of the kinds we subscribed to.
 */
@Rule(key = "if-Arrow-brace", priority = Priority.MINOR, name = "使用if或者箭头函数时，即使只有一行代码也要使用大括号 {},来定义代码块。", tags = {
		"convention" ,"html5"}, description = "使用if或者箭头函数时，即使只有一行代码也要使用大括号 {},来定义代码块。")
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.DATA_RELIABILITY)
@SqaleConstantRemediation("2min")
public class NoBraceFunctionUseCheck extends DoubleDispatchVisitorCheck  {

	@Override
	public void visitIfStatement(IfStatementTree tree) {
		// TODO Auto-generated method stub
		if(!tree.statement().is(Kind.BLOCK)){
  	      addIssue(tree, "使用if或者箭头函数时，即使只有一行代码也要使用大括号 {},来定义代码块。");
		}  
		super.visitIfStatement(tree);
	}

	@Override
	public void visitArrowFunction(ArrowFunctionTree tree) {
		if(!tree.body().is(Kind.BLOCK)){
	  	      addIssue(tree, "使用if或者箭头函数时，即使只有一行代码也要使用大括号 {},来定义代码块。");
			}  
		super.visitArrowFunction(tree);
	}
 

	 
}
