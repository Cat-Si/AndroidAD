package com.example.androidad.data

class InMemoryRepository (private val contactsRepository:
                                 MutableList<Contact>) {

    fun getAllContacts(): MutableList<Contact>{
        return contactsRepository
    }

    fun addContact(newContact: Contact){
        contactsRepository.add(newContact)
    }

    fun deleteContact(contactToDelete: Contact){
        contactsRepository.remove(contactToDelete)
    }

    fun edit(selectedContactToEdit: Contact){
        //Find the contact that we want to edit and then amend its details
        for (contact in contactsRepository.iterator()){
            if (contact.id==selectedContactToEdit.id){
                contact.firstName = selectedContactToEdit.firstName
                contact.surname = selectedContactToEdit.surname
                contact.telNo = selectedContactToEdit.telNo
            }
        }
    }
}
