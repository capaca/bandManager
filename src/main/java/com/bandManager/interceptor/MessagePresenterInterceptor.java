package com.bandManager.interceptor;

import java.lang.reflect.Method;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class MessagePresenterInterceptor implements Interceptor{

	public void destroy() {
		
	}

	public void init() {
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		
		//Recupera a action sendo invocada
		Object action = invocation.getAction();
		
		//Pega o método de apresentaçao de erros e mensagens da action
		Method metodoApresentarErrosEMensagens = action.getClass().getMethod("apresentarErrosEMensagens", null);
		
		//Roda o método método de apresentaçao de erros e mensagens da action
		metodoApresentarErrosEMensagens.invoke(action, null);
		
		return invocation.invoke();
	}

}
