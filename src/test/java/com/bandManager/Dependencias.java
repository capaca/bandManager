package com.bandManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.bandManager.action.banda.BandaCRUDAction;
import com.bandManager.action.banda.BandaUploadArquivosAction;
import com.bandManager.action.lancamento.LancamentoCRUDAction;
import com.bandManager.action.musica.MusicaCRUDAction;
import com.bandManager.dao.IBandaDAO;
import com.bandManager.dao.ILancamentoDAO;
import com.bandManager.dao.IMusicaDAO;
import com.bandManager.dao.IPaisDAO;
import com.bandManager.facade.IBandaFacade;
import com.bandManager.facade.ILancamentoFacade;
import com.bandManager.facade.IMusicaFacade;
import com.bandManager.facade.IPaisFacade;
import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class Dependencias extends Spring {

//==/* DAOs */=============================================================================================================================
	private static IMusicaDAO musicaDAO;
	private static ILancamentoDAO lancamentoDAO;
	private static IBandaDAO bandaDAO;
	private static IPaisDAO paisDAO;
	
//==/* Facades */==========================================================================================================================
	private static IBandaFacade bandaFacade;
	private static IPaisFacade paisFacade;
	private static ILancamentoFacade lancamentoFacade;
	private static IMusicaFacade musicaFacade;
	
//==/* Actions */==========================================================================================================================
	private static BandaCRUDAction bandaCRUDAction;
	private static LancamentoCRUDAction lancamentoCRUDAction;
	private static MusicaCRUDAction musicaCRUDAction;
	private static BandaUploadArquivosAction bandaUploadArquivosAction;
	
//==/* Entity Manager */======================================================================================================
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

//==/* Getters Entity Manager */======================================================================================================
	public static EntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory==null)
			entityManagerFactory = (EntityManagerFactory) Spring.getContext().getBean("entityManagerFactory");
		return entityManagerFactory;
	}
	
	public static EntityManager getEntityManager(){
		if(entityManager==null)
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}
	
//==/* Getters DAO */======================================================================================================================
	public static IMusicaDAO getMusicaDAO(){
		if(musicaDAO == null)
			musicaDAO = (IMusicaDAO) Spring.getContext().getBean("musicaDAO");
		
		return musicaDAO;
	}
	
	public static IBandaDAO getBandaDAO(){
		if(bandaDAO == null)
			bandaDAO = (IBandaDAO) Spring.getContext().getBean("bandaDAO");
		
		return bandaDAO;
	}
	
	public static ILancamentoDAO getLancamentoDAO(){
		if(lancamentoDAO == null)
			lancamentoDAO = (ILancamentoDAO) Spring.getContext().getBean("lancamentoDAO");
		
		return lancamentoDAO;
	}
	
	public static IPaisDAO getPaisDAO(){
		if(paisDAO == null)
			paisDAO = (IPaisDAO) Spring.getContext().getBean("paisDAO");
		
		return paisDAO;
	}
	
//==/* Getters Facade */===================================================================================================================
	public static IMusicaFacade getMusicaFacade(){
		if(musicaFacade==null)
			musicaFacade = (IMusicaFacade) Spring.getContext().getBean("musicaFacade");
		
		return musicaFacade;
	}
	
	public static ILancamentoFacade getLancamentoFacade(){
		if(lancamentoFacade==null)
			lancamentoFacade = (ILancamentoFacade) Spring.getContext().getBean("lancamentoFacade");
		
		return lancamentoFacade;
	}
	
	public static IBandaFacade getBandaFacade(){
		if(bandaFacade==null)
			bandaFacade = (IBandaFacade) Spring.getContext().getBean("bandaFacade");
		
		return bandaFacade;
	}
	
	public static IPaisFacade getPaisFacade(){
		if(paisFacade==null)
			paisFacade = (IPaisFacade) Spring.getContext().getBean("paisFacade");
		
		return paisFacade;
	}
	
	//==/* Getters Action */===================================================================================================================
	public static BandaCRUDAction getBandaCRUDAction(){
		if(bandaCRUDAction==null)
			bandaCRUDAction = (BandaCRUDAction) Spring.getContext().getBean("bandaCRUDAction");
		
		return bandaCRUDAction;
	}
	
	public static LancamentoCRUDAction getLancamentoCRUDAction(){
		if(lancamentoCRUDAction==null)
			lancamentoCRUDAction = (LancamentoCRUDAction) Spring.getContext().getBean("lancamentoCRUDAction");
		
		return lancamentoCRUDAction;
	}
	
	public static MusicaCRUDAction getMusicaCRUDAction(){
		if(musicaCRUDAction==null)
			musicaCRUDAction = (MusicaCRUDAction) Spring.getContext().getBean("musicaCRUDAction");
		
		return musicaCRUDAction;
	}
	
	public static BandaUploadArquivosAction getBandaUploadArquivosAction(){
		if(bandaUploadArquivosAction==null)
			bandaUploadArquivosAction = (BandaUploadArquivosAction) Spring.getContext().getBean("bandaUploadArquivosAction");
		
		return bandaUploadArquivosAction;
	}

}
