// Noncompliant  
if(condition)
	doSomeThing();
	
var func = () => doSmoeThing();

() => doSomeThing();


// Ok
if(condition){
	doSomeThing();
}

var func = () => {doSmoeThing();}

() => {doSomeThing();}


