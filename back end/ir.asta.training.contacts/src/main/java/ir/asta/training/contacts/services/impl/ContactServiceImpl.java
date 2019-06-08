package ir.asta.training.contacts.services.impl;

import javax.inject.Inject;
import javax.inject.Named;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.manager.ContactManager;
import ir.asta.training.contacts.services.ContactService;
import ir.asta.wise.core.datamanagement.ActionResult;

@Named("contactService")
public class ContactServiceImpl implements ContactService {

	@Inject
	ContactManager manager;
	
	@Override
	public ContactEntity load(Long id) {
		// TODO implement this method
		ContactEntity entity = new ContactEntity(); 
		entity.setId(100L);
		entity.setName("Dummy Contact");
		return entity;
	}

	@Override
	public ActionResult<Long> save(ContactEntity entity) {
		manager.save(entity);
		return new ActionResult<Long>(true, "New contact saved successfully.", entity.getId());
	}
}
