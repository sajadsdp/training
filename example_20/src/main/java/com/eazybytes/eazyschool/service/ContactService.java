package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.EazyschoolApplication;
import com.eazybytes.eazyschool.constant.EazySchoolConstant;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        contact.setCreateBy(EazySchoolConstant.ANONYMOUS);
        contact.setCreateAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result>0){
            isSave = true;
        }
        return isSave;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(EazySchoolConstant.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactID,String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactID,EazySchoolConstant.CLOSE,updatedBy);
        if (result>0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
