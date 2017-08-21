package org.esupportail.smsuapiadmin.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.esupportail.smsuapiadmin.dao.DaoService;
import org.esupportail.smsuapi.dao.beans.Institution;
import org.esupportail.smsuapiadmin.dto.DTOConverterService;
import javax.inject.Inject;

/**
 * AccountManager is the business layer between the web and the database for
 * 'account' objects.
 * 
 */
public class InstitutionManager {

	private final Logger logger = Logger.getLogger(getClass());

	@Inject private DaoService daoService;
	@Inject private DTOConverterService dtoConverterService;

	/**
	 * Retrieves all the institutions defined in database.
	 * 
	 * @return
	 */
	public List<Institution> getAllInstitutions() {
			logger.debug("Retrieve the institutions from the database");
		
		List<Institution> allInstitutions = daoService.getInstitutions();
		return allInstitutions;
	}

	/**
	 * Returns all institutions as UIObject.
	 * 
	 * @return
	 */
	public List<String> getAllUIInstitutions() {
			logger.debug("Retrieves the institutions from the database");
		
		List<String> allUiInstitutions = new ArrayList<>();
		List<Institution> allInstitutions = daoService.getInstitutions();

		for (Institution inst : allInstitutions) {
			String ui = dtoConverterService.convertToUI(inst);
			allUiInstitutions.add(ui);
		}
		return allUiInstitutions;
	}



	/**
	 * Returns the institution with the specified id.
	 * 
	 * @param institutionId
	 * @return
	 */
	public Institution getInstitutionById(final String institutionId) {
		Institution result = null;

		try {
			int id = Integer.parseInt(institutionId);
			result = daoService.getInstitutionById(id);
		} catch (NumberFormatException e) {
			logger.warn("L'identifiant d'une institution doit etre un entier",
					e);
		}

		return result;
	}

}
