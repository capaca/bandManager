package com.bandManager.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Id;

public class ReflectionUtil <T>{

	public T getId(T objeto) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		Method[] metodos = objeto.getClass().getDeclaredMethods();
		Field[] atributos = objeto.getClass().getDeclaredFields();
		
		for (Method metodo : metodos) {
			if(metodo.isAnnotationPresent(Id.class)){
				Class tipoResultado = metodo.getReturnType();
				
				return (T) metodo.invoke(objeto, null);
			}
		}
		
		for (Field atributo : atributos) {
			if(atributo.isAnnotationPresent(Id.class)){
				String nomeAtribiuto = atributo.getName();
				
				nomeAtribiuto.substring(0, 0).toUpperCase();
				
				Method metodo = objeto.getClass().getMethod("get"+nomeAtribiuto.charAt(0), null);
				metodo.invoke(objeto, null);
			}
		}
		
		return objeto;
	}
}
