package com.ttt.example.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Contact;
import com.ttt.example.api.model.Contact.ContactBuilder;
import com.ttt.example.api.repository.ContactRepository;

/**
 * Test utility class for creating test Contacts.
 */
public final class TestContactBuilder implements DataBuilder<Contact, Contact.ContactBuilder>
{
    @Autowired
    private TestClientBuilder clientBuilder;

    @Autowired
    private ContactRepository contactRepository;

    // Constants for testData column indexes
    public static final int CLIENT = 0;
    public static final int NAME   = 1;
    public static final int EMAIL  = 2;
    public static final int PHONE1 = 3;
    public static final int PHONE2 = 4;
    public static final int STATUS = 5;

    private static Object[][] testData = {
            {1, "Contact 1 for Client 1", "c1p1@junk.mail", "(123) 555-1212", "(456) 555-1212", "A"},
            {1, "Contact 2 for Client 1", "c2p1@junk.mail", "(123) 555-1212", "(456) 555-1212", "A"},
            {1, "Contact 3 for Client 1", "c3p1@junk.mail", "(123) 555-1212", "(456) 555-1212", "A"}
    };

    @Override
    public void resetDB() {
        contactRepository.deleteAll();
        clientBuilder.resetDB();
    }

    @Override
    public int dataRowCount() {
        return testData.length;
    }

    @Override
    public Object[] getTestData(int testDataIndex) {
        return testData[testDataIndex];
    }

    /**
     * Build a test client
     */
    @Override
    public ContactBuilder create(int testDataIndex) {
        int colIdx = 0;
        Object[] row = getTestData(testDataIndex);

        return Contact.builder()
                .client((Client) clientBuilder.create((int) row[CLIENT] - 1).build())
                .name((String) row[NAME])
                .email((String) row[EMAIL])
                .phoneNumber1((String) row[PHONE1])
                .phoneNumber2((String) row[PHONE2])
                .status((String) row[STATUS]);
    }

    @Override
    public Contact persist(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void addAll() {
        for (int i = 0; i < dataRowCount(); i++) {
            Contact contact = create(i).build();
            contact.setClient(clientBuilder.persist(contact.getClient()));
            persist(contact);
        }
    }
}
