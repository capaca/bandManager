package com.bandManager;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.providers.XWorkConfigurationProvider;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;

public abstract class Spring {

	private static ApplicationContext context;
	private static Ant ant;
	private static String BUILD_XML = "build.xml";
	private static ActionContext actionContext;
	private static ValueStack valueStack;
	
	public static ApplicationContext getContext(){
		if(context == null)
			context = new ClassPathXmlApplicationContext(new String[] { "application-context-test.xml" });
		
		return context;
	}
	
	public static Ant getAnt(){
		if (ant == null){
			System.out.println("ant null, inicializando");
			//Instancia o ant para rodar o build default
			ant = new Ant();
			ant.setAntfile(BUILD_XML);
			Project project = new Project();
			project.init();
			ant.setProject(project);
		}
		
		return ant;
	}
	
	public static ActionContext getActionContext(){
		if (actionContext == null){
			System.out.println("actionContext null, inicializando");
			//Inicializa o contexto do struts 2.1.6
			ConfigurationManager configurationManager = new ConfigurationManager();
			configurationManager.addContainerProvider(new XWorkConfigurationProvider());
			Configuration config = configurationManager.getConfiguration();
			Container container = config.getContainer();

			valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
			valueStack.getContext().put(ActionContext.CONTAINER, container);
			actionContext = new ActionContext(valueStack.getContext());
		}
		
		return actionContext;
	}
}
