package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constant.EazySchoolConstant;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
public class  ContactService {

    @Autowired
    private ContactRepository contactRepository;
    
//    we use  @Slf4j for log
//    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        boolean isSave = false;
        contact.setStatus(EazySchoolConstant.OPEN);
        contact.setCreatedBy(EazySchoolConstant.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact  = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0){
            isSave = true;
        }
        return isSave;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstant.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactID,String updatedBy){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactID);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstant.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(now());
        });
        Contact updateContact = contactRepository.save(contact.get());
        if (null != updateContact && updateContact.getContactId()>0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
