package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {
    
//    we use  @Slf4j for log
//    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        boolean isSave = true;
        //TODO - need to persist the data into the DB table
        log.info(contact.toString());
        return isSave;
    }
}
