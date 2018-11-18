package com.ttt.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Client.ClientBuilder;
import com.ttt.example.api.repository.ClientRepository;

/**
 * Test utility class for creating test Clients.
 */
@Component
public final class TestClientBuilder implements DataBuilder<Client, Client.ClientBuilder>
{
    @Autowired
    private ClientRepository clientRepository;

    private static Object[][] testData = {
            {"Client 1", "Client 1 notes"},
            {"Client 2", "Client 2 notes"},
            {"Client 3", "Client 3 notes"}
    };

    @Override
    public void resetDB() {
        clientRepository.deleteAll();
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
    public ClientBuilder create(int testDataIndex) {
        int colIdx = 0;
        Object[] row = getTestData(testDataIndex);

        return Client.builder()
                .name((String) row[colIdx++])
                .notes((String) row[colIdx++]);
    }

    @Override
    public Client persist(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    public Client addTestClient(ClientBuilder builder) {
        Client client = builder.build();
        // client.setPartyType(partyTypeRepository.saveAndFlush(client.getPartyType()));
        return persist(client);
    }

    public Client addTestClient(int testDataIndex) {
        Client client = create(testDataIndex).build();
        // client.setPartyType(partyTypeRepository.saveAndFlush(client.getPartyType()));
        return persist(client);
    }

    @Override
    public void addAll() {
        for (int i = 0; i < dataRowCount(); i++) {
            Client client = create(i).build();
            // client.setPartyType(partyTypeRepository.saveAndFlush(client.getPartyType()));
            persist(client);
        }
    }
}
